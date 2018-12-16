package Devices.Com_Master.Model;



import Devices.TT.Model.TT_Model;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

public class SerialReceiveHandler {

    private TT_Model tt_devices; /* TT devices (Temperature, humidity, pressure sensors. */

    public SerialReceiveHandler(){
        this.tt_devices = new TT_Model(); /* TT devices (Temperature, humidity, pressure sensors. */
    }

    public void handleSerialInput(String data){
        try {
            data = "{" + data + "}";

            /* Parse the received data to JSON. */
            JsonObject obj = new JsonParser().parse(data).getAsJsonObject();
            /* Check JSON object for TT Devices. */
            if (obj.has("TT")) {
                tt_devices.updateDevice(obj);
            }
        }
        catch(JsonSyntaxException ex){
            ex.printStackTrace();
        }
    }







}
