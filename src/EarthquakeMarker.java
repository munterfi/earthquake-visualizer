import processing.core.*;
import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.marker.SimplePointMarker;
import processing.core.PGraphics;

/**
 * EarthquakeMarker is a class for the customized representation of earthquakes in an unfolding map.
 * It inherits from the basic marker class SimplePointMarker of the unfolding package.
 * 
 * @author Merlin Unterfinger
 * @version 1.2
 */
public class EarthquakeMarker extends SimplePointMarker
{
    // instance variables
    private float markersize;
    private String markertext;
    private String markerlabel;
    private String markerdatetime;

    /**
     * Constructor for objects of class EarthquakeMarker
     */
    public EarthquakeMarker(Location location, double markersize,
    String markertext, String markerlabel, String markerdatetime) {
        // Initialise instance variables
        super(location);
        this.markertext = markertext;
        this.markersize = (float) markersize;
        this.markerlabel = markerlabel;
        this.markerdatetime = markerdatetime;
    }

    /**
     * Draw method to draw the marker on the map, which later gets 
     * called from the EarthquakeVisualizer class.
     * 
     * @param  pg  the PGGraphics from the processing API
     * @param  x   x image coordiante from the marker
     * @param  y   y image coordinate from the marker (y is inverted in images!)
     */
    public void draw(PGraphics pg, float x, float y) {
        pg.pushStyle();
        pg.noStroke();
        // Outer ellipse of the marker
        pg.fill(150+this.markersize*30, 255-this.markersize*30, 0, 70+this.markersize*20);
        pg.ellipse(x, y, this.markersize*8, this.markersize*8);
        // Inner ellipse of the marker
        pg.fill(255, 100);
        pg.ellipse(x, y, this.markersize*6, this.markersize*6);
        pg.popStyle();
        // Set the Markertext
        pg.text(markertext, x - pg.textWidth(markertext) / 2, y + 4);

        // Interactive label
        // Define some variables for the interative label
        int strokeWeight = 2;
        int fontSize = 12;
        int space = 4;
        float distance = (float) (this.markersize*3.14);
        // Create interactive label, when mouse is over marker
        if (selected && this.markerlabel != null) {
            pg.pushStyle();
            pg.pushMatrix();

            // Darken marker
            pg.fill(0, 0, 0, 150);
            pg.ellipse(x, y, this.markersize*8, this.markersize*8);

            // Label
            // Display textbox
            pg.fill(0, 0, 0, 150);
            pg.rect(distance + x + strokeWeight / 2,
                -distance + y - fontSize + strokeWeight / 2 - space,
                pg.textWidth(markerlabel) + space * 1.5f,
                fontSize + space);
            // Display text
            pg.fill(255, 255, 255);
            pg.text(markerlabel,
                Math.round(distance + x + space * 0.75f + strokeWeight / 2),
                Math.round(-distance + y + strokeWeight / 2 - space * 0.75f));

            // DateTime 
            // Display textbox
            pg.fill(0, 0, 0, 150);
            pg.rect(-distance + x + strokeWeight / 2 - pg.textWidth(markerdatetime),
                distance + 12 + y - fontSize + strokeWeight / 2 - space,
                pg.textWidth(markerdatetime) + space * 1.5f,
                fontSize + space);
            // Display text
            pg.fill(255, 255, 255);
            pg.text(markerdatetime,
                Math.round(-distance + x + space * 0.75f + strokeWeight / 2 - pg.textWidth(markerdatetime)),
                Math.round(distance + 12 + y + strokeWeight / 2 - space * 0.75f));

            pg.popMatrix();
            pg.popStyle();
        }
    }
}
