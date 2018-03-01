


import java.io.IOException;
import java.io.InputStream;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Exchanger;


public class MainApp {
    private final static String lampCon = "LAMP_CON"; // lamp connection identification
    private final static String clientCon = "CLIENT_CON"; // client app connection identification




    public static void main (String[] agrs)
    {



        Socket serverClient;

        try
       {
           ServerSocket serverSocket = new ServerSocket(8000);

           BroadcastSearchHelper broadcastSearchHelper = new BroadcastSearchHelper();
           broadcastSearchHelper.start();

           while (true)
           {

               serverClient = serverSocket.accept();



                   System.out.println("connection accepted");

                   ClientThread clientThread = new ClientThread(serverClient);
                   clientThread.start();




           }

       }
       catch (IOException e)
       {
           System.out.println(e);
       }






       }


}
