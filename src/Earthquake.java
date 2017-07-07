
/**
 * The class Earthquake defines instances, which are representing earthquakes. 
 * An instance of this class stores various properties of an earthquake.
 * 
 * @author Merlin Unterfinger
 * @version 1.3
 */
public class Earthquake
{
    // instance variables
    private String title;
    private String place;
    private String url;
    private double lon;
    private double lat;
    private double mag;
    private String datetime;

    /**
     * Constructor for objects of class Earthquake
     */
    public Earthquake(String title, String place, String url, String datetime, double lon, double lat, double mag)
    {
        // initialise instance variables
        this.title = title;
        this.place = place;
        this.url = url;
        this.lon = lon;
        this.lat = lat;
        this.mag = mag;
        this.datetime = datetime;
    }

    /**
     * An example of a method - replace this comment with your own
     * 
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y 
     */
    public void print()
    {
        // print some properties
        System.out.println(this.title);
        System.out.println("Coords - lon: " + this.lon + " lat:" + this.lat);
    }

    /**
     * Get longitude
     * 
     * @return     lon 
     */
    public double getLon()
    {
        return(this.lon);
    }

    /**
     * Get latitude
     * 
     * @return     lat 
     */
    public double getLat()
    {
        return(this.lat);
    }

    /**
     * Get magnitude
     * 
     * @return     mag 
     */
    public double getMag()
    {
        return(this.mag);
    }

    /**
     * Get title
     * 
     * @return     title 
     */
    public String getTitle()
    {
        return(this.title);
    }

    /**
     * Get datetime
     * 
     * @return     datetime 
     */
    public String getDateTime()
    {
        return(this.datetime);
    }

    /**
     * Get place
     * 
     * @return     place 
     */
    public String getPlace()
    {
        return(this.place);
    }

    /**
     * Get url
     * 
     * @return     url 
     */
    public String getURL()
    {
        return(this.url);
    }

}
