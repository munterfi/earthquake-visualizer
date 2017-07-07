import java.util.ArrayList;

/**
 * The class EarthquakeGroup represents a group of earthquakes. An instance
 * of type EarthquakeGroup is empty when it is newly constructed, but it can 
 * be filled with objects of type Earthquake
 * 
 * @author Merlin Unterfinger
 * @version 1.1
 */
public class EarthquakeGroup
{
    // instance variables
    private ArrayList<Earthquake> al;

    /**
     * Constructor for objects of class EarthquakeGroup
     */
    public EarthquakeGroup()
    {
        // initialise instance variables
        al = new ArrayList<Earthquake>();
    }

    /**
     * This method is used to accces the ArrayList containing earthquakes.
     * 
     * @return     al ArrayList containing Earthquakes
     */
    public ArrayList getAl()
    {
        return(this.al);
    }

    /**
     * This methods adds an object of type Earthquake to the EarthquakeGroup.
     * 
     * @param  eq  an object of type Earthquake
     */
    public void add(Earthquake eq)
    {
        // Add an earthquake
        this.al.add(eq);
    }

    /**
     * This method returns an object of type Earthquake at a specific position "i" (index)
     * in the "al" ArrayList, which contains the object of type Earthquake.
     * 
     * @param  i  index of Earthquake
     * @return  eq  Earthquake
     */
    public Earthquake getEQ(int i)
    {
        // Add an earthquake
        return(this.al.get(i));
    }

    /**
     * This methods filters the earthquakes based on their magnitude. It takes a limit
     * and an arithmetic operator in string format as input.
     * 
     * @param  op       an arithmetic operator in string format; either "<", ">" or "lower", "greater"
     * @param  limit    (double), the split value
     * @return eqgf     An object of type EarthquakeGroup containing the filtered earthquakes.
     */
    public EarthquakeGroup filterEqMag(String op, double limit)
    {
        // Filter the Earthquakes
        EarthquakeGroup eqgf= new EarthquakeGroup();
        if (op == "<" || op =="lower") {
            for (Earthquake e : this.al) {
                if (e.getMag() < limit) {
                    eqgf.add(e);
                }
            }
        } else if (op == ">" || op =="greater") {
            for (Earthquake e : this.al) {
                if (e.getMag() > limit) {
                    eqgf.add(e);
                }
            }
        } else {
            System.out.println("Invalid arithmetic opertaor input.");
        }
        return(eqgf);
    }
}
