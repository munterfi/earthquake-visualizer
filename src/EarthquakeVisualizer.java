import java.util.*;
import java.text.SimpleDateFormat;
import processing.core.PApplet;
import processing.opengl.*;
import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.utils.*;
import de.fhpotsdam.unfolding.providers.Microsoft;
import de.fhpotsdam.unfolding.marker.*;

/**
 * Application that uses the Unfolding (unfoldingmaps.org) and Processing (processing.org) APIs
 * to display a map of recently occured earthquakes on a worldmap.
 * 
 * @author Merlin Unterfinger
 * @version 1.1
 */
public class EarthquakeVisualizer extends PApplet {
    // the map instance variable
    UnfoldingMap map;
    private EarthquakeGroup eqg;
    private String datestring;
    private String title;
    private long timer;

    public static void main(String args[]){
        PApplet.main(new String[] { EarthquakeVisualizer.class.getName() });
    }

    /**
     *  The setup method prepares things that will be displayed. It runs when the main method is called.
     */
    public void setup() {
        this.title = "Earthquakes of the past Week (Magnitude > 2.5)";
        size(1000, 800); // The size of the window where the map is displayed, in pixels
        map = new UnfoldingMap(this, new Microsoft.AerialProvider()); // uses Microsoft tiles, default zoom level
        MapUtils.createDefaultEventDispatcher(this, map); // Get ready to draw a map
        map.setTweening(true); // Slow animation of zooms and pans
        map.setZoomRange(2, 12); // Limit zoom range

        // Parse the Earthquakes from the URL, here some URLs:
        // https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_month.geojson
        // https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/4.5_month.geojson
        // https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.geojson
        // https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/4.5_week.geojson
        JSONReader jsonreader = new JSONReader(
                new URLReader("https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.geojson"));
        eqg = jsonreader.read(); // Start Reading

        // Filter the EarthquakeGroup instance, to ignore observations with small magnitudes.
        eqg = eqg.filterEqMag(">", 2.5);

        // Create a Location instance for each earthquake and pass it to the map
        for(int i = 0; i < eqg.getAl().size(); i++) {
            // Create a Location
            Location eqLocation = new Location(eqg.getEQ(i).getLat(), eqg.getEQ(i).getLon());
            // Round the magnitude to one decimal place, for better readability
            double label = Math.round(eqg.getEQ(i).getMag()*10)/10.0;
            // Use the EarthquakeMarker class to create customized markers.
            EarthquakeMarker eqMarker = new EarthquakeMarker(eqLocation,
                    eqg.getEQ(i).getMag(),
                    Double.toString(label),
                    eqg.getEQ(i).getTitle(),
                    eqg.getEQ(i).getDateTime());
            // Add marker to map
            map.addMarker(eqMarker);
        }

        // Extract the date of the map's construction
        this.datestring = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

        // Set timer to zero
        this.timer = System.currentTimeMillis(); 
    }

    /**
     * The draw method actually puts things on the screen.
     */
    public void draw() {        
        background(0);
        map.draw();
        // Show mouse position by clicking.
        if (mousePressed) {
            Location location = map.getLocation(mouseX, mouseY);
            fill(255, 255, 255);
            text(location.getLat() + ", " + location.getLon(), mouseX, mouseY);
        }

        // Text annotations
        // Data, date & author
        fill(255, 255, 255);
        text("Data: USGS, " +datestring+ "\nAuthor: Merlin Unterfinger", 4, map.getHeight()-22);
        textSize(20);
        // Title
        fill(0, 0, 0, 150);
        rect(0, 0, map.getWidth(), 30);
        fill(255, 255, 255);
        text(this.title, map.getWidth()/2-textWidth(this.title)/2, 22);
        // Reset parameters
        fill(0, 0, 0, 200);
        textSize(12);

        // Check the elapsed time, the map should be upfated every 5 minutes.
        long elapsedTime =  System.currentTimeMillis() - this.timer;
        if (elapsedTime > 1000*60*5) {
            // Setup the map with the new data
            setup();
        }
    }

    /**
     * The mouseMoved() method checks if the mouse position is over a marker.
     */ 
    public void mouseMoved() {
        // Deselect all marker
        for (Marker marker : map.getMarkers()) {
            marker.setSelected(false);
        }

        // Select hit marker
        Marker marker = map.getFirstHitMarker(mouseX, mouseY);
        if (marker != null) {
            marker.setSelected(true);
        }
    }

}