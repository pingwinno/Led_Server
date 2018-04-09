


import com.google.gson.Gson;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.io.*;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Exchanger;


public class MainApp  {

    private String result;
    private Gson gson = new Gson();
    private JSONEntity storedJSON;
    private JSONEntity powerOff = new JSONEntity(0,0,0);
    MQTTService service;

    public static void main (String[] agrs) throws IOException {
        String brokerURL = "tcp://192.168.1.201:1883";
        String clientID = "BackendServer11";
        MqttMessage reciver = new MqttMessage();
        Settings settings;
        Gson gson = new Gson();

        try (BufferedReader br =
                     new BufferedReader(new FileReader("config.conf"))) {
            settings = gson.fromJson(br, Settings.class);
        }

            try {


                MQTTService service = new MQTTService(settings.getServerIP(), settings.getPrimaryID(), true, settings.getLogin(), settings.getPassword());
                service.subscribe(settings.getSecondaryTopic(), 0);


            } catch (MqttException e) {
                e.printStackTrace();
            }

        
        }
    }






