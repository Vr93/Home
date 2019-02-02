package Devices.TT.Model;

import Event.Database.Event_Database;
import Devices.TT.Database.TT_Database;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class TT_Model {

    private ArrayList<TT_device> devices;

    public TT_Model(){
        devices = new ArrayList<>();
        fetchDevicesFromSQL();
    }

    private void fetchDevicesFromSQL(){
        TT_Database sql = new TT_Database();
        ArrayList<JsonObject> devices = sql.getDevices();
        for(JsonObject obj: devices){
            if(obj.has("uid")){
                String uid = obj.get("uid").toString();
                addDevice(uid);
            }
        }
    }

    /**
     * When data is received, update the specific device with this information.
     * @param obj
     * @throws JsonSyntaxException
     */
    public void updateDevice(JsonObject obj) throws JsonSyntaxException {

        boolean deviceFound = false;

        String uid = obj.get("TT").toString();

        for(TT_device dev: devices){
            /* Check the received deviceNumber against the list holding the devices. */
            if(dev.getDeviceUID().equals(uid)){
                /* If found, update the values. The device will post values to database itself. */
                if(obj.has("t")) dev.setTemperature(Float.valueOf(obj.get("t").toString()));
                if(obj.has("h")) dev.setHumidity(Float.valueOf(obj.get("h").toString()));
                if(obj.has("p")) dev.setPressure(Float.valueOf(obj.get("p").toString()));
                if(obj.has("v")) dev.setVoltage(Float.valueOf(obj.get("v").toString()));
                deviceFound = true;
            }
        }
        /* If the device is not found, store a event message in the database. */
        if(!deviceFound) {
            Event_Database event = new Event_Database();
            event.insertEvent("Data received from TT:" + uid + " , but no configuration found.");
        }

    }



    /**
     * Adds a TT device to the list.
     * @param uid
     */
    public void addDevice(String uid){
        getDevices().add(new TT_device(uid));
    }

    /**
     * Returns the list of all active TT devices.
     * @return ArrayList<TT_device>
     */
    public ArrayList<TT_device> getDevices() {
        return devices;
    }
}
