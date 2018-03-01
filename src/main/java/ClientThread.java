import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringReader;
import java.net.Socket;
import java.util.Arrays;
import java.lang.*;
import java.util.Scanner;
import java.util.concurrent.Exchanger;

public class ClientThread extends Thread {
    private Socket serverClient = null;
    ExchangeHelper exchangeHelper = ExchangeHelper.getInstance();
    private InputStream inputStream = null;
    private JSONEntity jsonEntity = exchangeHelper.getJsonThread();
    Exchanger <JSONEntity> exchanger = exchangeHelper.getExchanger();
    private Gson gson = new Gson();
    private JsonReader reader;




    ClientThread(Socket serverClient)
    {
        this.serverClient = serverClient;
    }

    public void run() {





            try {

                inputStream = serverClient.getInputStream();
                System.out.println(" thread opened");



                OutputStream out = serverClient.getOutputStream();

                String string = gson.toJson(jsonEntity);
                System.out.println(string);
                out.write(string.getBytes());
                out.flush();

                System.out.println("send json");


            }
            catch (Exception e)
            {
                System.out.println("Can't get input stream");
            }


            byte[] data = new byte[1024];
            int bytesRead = 0;







            try {


                while ((bytesRead = inputStream.read(data)) > 0) {


                    byte[] readData = Arrays.copyOf(data, bytesRead);





                        reader = new JsonReader(new StringReader(new java.lang.String(readData)));

                        jsonEntity = gson.fromJson(reader, JSONEntity.class);

                        System.out.println(jsonEntity.getR() + "  " + jsonEntity.getB() + "  " + jsonEntity.getG() + " " + jsonEntity.getPowerSwitch() );


                }
            } catch (IOException e) {

            }





            try {


                jsonEntity = exchanger.exchange(jsonEntity);

                inputStream.close();
                System.out.println("close connection");

            } catch (Exception e) {
            }

        }
    }
