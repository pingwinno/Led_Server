/* Settings JSON object */

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Settings {
    private Gson gson = new Gson();
private String serverIP;
private String primaryID;
private String secondaryID;
private String secondaryTopic;
private String login;
private String password;



    public String getServerIP() {
        return serverIP;
    }

    public String getPrimaryID() {
        return primaryID;
    }

    public String getSecondaryID() {
        return secondaryID;
    }

    public String getPrimaryTopic() {
        return primaryTopic;
    }

    public String getSecondaryTopic() {
        return secondaryTopic;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    private String primaryTopic;



}
