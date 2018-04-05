import com.google.gson.Gson;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class PowerHandler {
   private String result;
    private Gson gson = new Gson();
    private JSONEntity storedJSON;
    private JSONEntity powerOff = new JSONEntity(0,0,0);
    MQTTService service;








    PowerHandler()  {
    }

    public void parseMessage (String string) throws MqttException {


    if (string.equals("0")) {
        result = gson.toJson(powerOff);
    } else if (string.equals('1')) {
        result = gson.toJson(storedJSON);
    } else if (string.contains("r")) {
        this.storedJSON = gson.fromJson(string, JSONEntity.class);

}
System.out.println(result);
    }


}
