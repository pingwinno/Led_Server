import com.google.gson.Gson;

/**
 * Object for transfering RGB value and number of lamp
 */

public class JSONEntity {


    private int ledNumber;
    private int r;
    private int g;
    private int b;
    private boolean power;

    public  void setJSON ( int ledNumber, int r, int g, int b, boolean power)
    {
        this.ledNumber = ledNumber;
        this.r = r;
        this.g = g;
        this.b = b;
        this.power = power;
    }




    public int getLedNumber ()
    {
        return this.ledNumber;
    }

    public int getR ()
    {
        return this.r;
    }

    public int getG ()
    {
        return this.g;
    }

    public int getB ()
    {
        return this.b;
    }

    public boolean getPowerSwitch ()
    {
        return this.power;
    }





}
