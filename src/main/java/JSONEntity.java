import com.google.gson.Gson;

/**
 * Object for transfering RGB value and number of lamp
 */

public class JSONEntity {



    private int r;
    private int g;
    private int b;

    JSONEntity ()
    {

    }
JSONEntity ( int r, int g, int b)
    {
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public  void setJSON (  int r, int g, int b)
    {

        this.r = r;
        this.g = g;
        this.b = b;

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







}
