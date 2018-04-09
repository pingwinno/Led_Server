import com.google.gson.Gson;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MqttDefaultFilePersistence;

import java.security.Timestamp;

public class MQTTService implements MqttCallback {


    String clientId;
    private MqttClient client2;
    private MqttClient client;
    private String 				brokerUrl;
    private MqttConnectOptions conOpt;
    private boolean 			clean;
    private String password;
    private String userName;
    private String receivedMessage = "null";
    private boolean status;
    private String tmpDir = System.getProperty("java.io.tmpdir");

   private String tmpDir2 = System.getProperty("java.io.tmpdir");
    private MqttDefaultFilePersistence dataStore2 = new MqttDefaultFilePersistence(tmpDir2);
    private MqttDefaultFilePersistence dataStore = new MqttDefaultFilePersistence(tmpDir);
    private String string;
    private Gson gson = new Gson();
    private JSONEntity storedJSON;
    private JSONEntity powerOff = new JSONEntity(0,0,0);
    String brokerURL = "tcp://192.168.1.201:1883";
    String clientID2 = "BackendServer111111111";
    private String powerState;



    /**
     * Constructs an instance of the sample client wrapper
     * @param brokerUrl the url of the server to connect to
     * @param clientId the client id to connect with
     * @param cleanSession clear state at end of connection or not (durable or non-durable subscriptions)
     * @param userName the username to connect with
     * @param password the password for the user
     * @throws MqttException
     */



    public  MQTTService(String brokerUrl, String clientId, boolean cleanSession, String userName, String password) throws MqttException {
        this.brokerUrl = brokerUrl;
        this.clean = cleanSession;
        this.password = password;
        this.userName = userName;

        this.clientId = clientId;


        //This sample stores in a temporary directory... where messages temporarily
        // stored until the message has been delivered to the server.
        //..a real application ought to store them somewhere
        // where they are not likely to get deleted or tampered with
        try
        {
            // Construct the connection options object that contains connection parameters
            // such as cleanSession and LWT
            conOpt = new MqttConnectOptions();
            conOpt.setCleanSession(clean);
            if (password != null) {
                conOpt.setPassword(this.password.toCharArray());
            }
            if (userName != null) {
                conOpt.setUserName(this.userName);
            }

            // Construct an MQTT blocking mode client
            client = new MqttClient(this.brokerUrl, clientId, dataStore);
            client2 = new MqttClient(this.brokerUrl, clientID2, dataStore2);

            // Set this wrapper as the callback handler
            client.setCallback(this);
            client2.setCallback(this);

        }
        catch (MqttException e) {
            e.printStackTrace();
            System.out.println("Unable to set up client: " + e.toString());
            System.exit(1);
        }







    }







    /**
     * Publish / send a message to an MQTT server
     * @param topicName the name of the topic to publish to
     * @param qos the quality of service to delivery the message at (0,1,2)
     * @param payload the set of bytes to send to the MQTT server
     * @throws MqttException
     */
    public void publish(String topicName, int qos, byte[] payload) throws MqttException {

        // Connect to the MQTT server
        System.out.println("Connecting to "+brokerUrl + " with client ID "+client.getClientId());
        //client.connect(conOpt);
        System.out.println("Connected");



        // Create and configure a message
        MqttMessage message = new MqttMessage(payload);
        message.setQos(qos);

        // Send the message to the server, control is not returned until
        // it has been delivered to the server meeting the specified
        // quality of service.
        client.publish(topicName, message);

        // Disconnect the client
       // client.disconnect();
        System.out.println("Disconnected");
    }





    /**
     * Subscribe to a topic on an MQTT server
     * Once subscribed this method waits for the messages to arrive from the server
     * that match the subscription. It continues listening for messages until the enter key is
     * pressed.
     * @param topicName to subscribe to (can be wild carded)
     * @param qos the maximum quality of service to receive messages at for this subscription
     * @throws MqttException
     */
    public void subscribe(String topicName, int qos) throws MqttException {

        // Connect to the MQTT server
        client.connect(conOpt);
        client2.connect(conOpt);
        System.out.println("Connected to "+brokerUrl+" with client ID "+client.getClientId());

        // Subscribe to the requested topic
        // The QoS specified is the maximum level that messages will be sent to the client at.
        // For instance if QoS 1 is specified, any messages originally published at QoS 2 will
        // be downgraded to 1 when delivering to the client but messages published at 1 and 0
        // will be received at the same level they were published at.
        System.out.println("Subscribing to topic \""+topicName+"\" qos "+qos);
        client.subscribe(topicName, qos);



        // Disconnect the client from the server
       // client.disconnect();

    }










    @Override
    public void connectionLost(Throwable throwable) {

    }

    public void messageArrived(String topic, MqttMessage message) throws MqttException {

       String replyTo = new String(message.getPayload());
        message.setQos(0);
        System.out.println(replyTo);
        if (replyTo.equals("0"))
        {
            powerState = replyTo;
            message.setPayload(gson.toJson(powerOff).getBytes());

            client2.publish("test", message);
        }
        else if (replyTo.equals("1")) {
            powerState = replyTo;

            message.setPayload(gson.toJson(storedJSON).getBytes());
            client2.publish("test", message);

            System.out.println(gson.toJson(storedJSON));

        }
        else if (replyTo.equals("query")) {

            message.setPayload(gson.toJson(storedJSON).getBytes());
            client2.publish("test2", message);

            message.setPayload(powerState.getBytes());
            client2.publish("test2", message);

            System.out.println(gson.toJson(storedJSON));
        }

        else if (replyTo.contains("r")) {
            storedJSON = gson.fromJson(replyTo, JSONEntity.class);
            System.out.println(gson.toJson(storedJSON));

        }



    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {

    }





}
