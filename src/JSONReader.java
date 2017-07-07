import java.util.*;
import java.io.*;
import org.json.simple.parser.*;
import org.json.simple.*;

/**
 * JSONReader is partly based on code from Kai-Floarian Richter. It takes an URLreader as input
 * for the constructor. By its construction, the read() method of the URLReader gets called. 
 * The resulting String from the method call gets parsed with JSONParser and the content is 
 * assigned to the jsonObjects variable. The read() method is used to convert the earthquakes
 * from JSONObjects into instances of type "Earthquake" and store them in a instance of the
 * EarthquakeGroup class.
 * 
 * @author Merlin Unterfinger
 * @version 1.2
 */
public class JSONReader
{
    //// initialise instance variables
    private JSONObject jsonObjects;
    private URLReader urlreader;

    /**
     * Constructor for objects of class JSONReader.
     * Reads in the the content (one long string) of the passed URLReader
     * and stores its content in jsonObjects.
     * 
     * @param urlreader  An object of type URLReader, which is bound to an URL.
     */
    public JSONReader(URLReader urlreader)
    {
        this.urlreader = urlreader;
        String jsonFileContent = this.urlreader.read();
        // now that we've stored the content of the JSON file in a single String, we parse it.
        // this turns the original JSON specification into an iteratable Java data structure
        // consisting of JSONObject (similar to ArrayList) and JSONArray elements
        JSONParser parser = new JSONParser();
        try {
            Object parsedFile = parser.parse(jsonFileContent);
            jsonObjects = (JSONObject) parsedFile;
        }
        catch(ParseException pe) {
            System.out.println("Error at position: " + pe.getPosition());
            System.out.println(pe);
        }
    }

    /**
     * The read() method is used to convert the earthquakes
     * from JSONObjects into instances of type "Earthquake" and store them in a instance of the
     * EarthquakeGroup class.
     * 
     * @return eqg  EarthquakeGroup storing objects of type "Earthquake"
     */
    public EarthquakeGroup read() {
        EarthquakeGroup eqg = new EarthquakeGroup();
        JSONArray features = (JSONArray) jsonObjects.get("features");
        for (int i = 0; i < features.size(); i++) {
            try {
                // get the current feature
                JSONObject object = (JSONObject)  features.get(i);
                // get a feature's properties
                JSONObject properties = (JSONObject) object.get("properties");
                // get a features's geometry
                JSONObject geometry = (JSONObject) object.get("geometry");
                // coordinates are an array with two entries: longitude first, then latitude
                JSONArray coordinates = (JSONArray) geometry.get("coordinates");
                // Extract relevant text properties
                String title = properties.get("title").toString();
                String place = properties.get("place").toString();
                String url = properties.get("url").toString();
                // Extract time (milliseconds from 1970) and convert into datetime
                long millis = Long.parseLong(properties.get("time").toString());
                Date date = new Date(millis);
                String datetime = date.toString();
                // Extract numeric values
                double mag = Double.parseDouble(properties.get("mag").toString());
                double lon = Double.parseDouble(coordinates.get(0).toString());
                double lat = Double.parseDouble(coordinates.get(1).toString());
                // Create object of type "Earthquake"
                Earthquake eq = new Earthquake(title, place, url, datetime, lon, lat, mag);
                eqg.add(eq);
                eq.print();
                System.out.println(date.toString());
            } catch (NullPointerException npe) {
                System.out.println("Incomplete data record, this particular earthquake is dismissed.");
                System.out.println(npe);
            }

        }

        // Print number of fetched earthquakes
        System.out.println("------------------------------------------\n"+
            eqg.getAl().size()+" earthquakes fetched from:\n" + this.urlreader.getUrl() +
            "\n------------------------------------------");

        // return the EarthquakeGroup
        return(eqg);
    }
}
