package Devices.Com_Master.Model;



import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

public class SerialHandler {



    public SerialHandler(){

    }

    public void handleSerialInput(String data){
        try {
            data = "{" + data + "}";

            /* Parse the received data to JSON. */
            JsonObject obj = new JsonParser().parse(data).getAsJsonObject();
            /* Check JSON object for ID. */
            if (obj.has("TT")) {

            }
        }
        catch(JsonSyntaxException ex){
        }
    }







}
