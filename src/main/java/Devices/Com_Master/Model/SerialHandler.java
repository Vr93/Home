package Devices.Com_Master.Model;


import Devices.A01.Model.A01;
import org.json.JSONObject;

public class SerialHandler {

    private A01 A01;

    public SerialHandler(){
        this.A01 = new A01();
    }

    public void handleSerialInput(String data){
        data = "{" + data + "}";
        JSONObject obj = new JSONObject(data);
        /* Check JSON object for ID. */
        if(obj.has("id")){
            String id = obj.getString("id");
            if(id.equalsIgnoreCase("A01")){
                if(obj.has("t")){
                    float temp = Float.parseFloat(obj.getString("t"));
                    A01.setTemperature(temp);
                }
                if(obj.has("h")){
                    float humidity = Float.parseFloat(obj.getString("h"));
                    A01.setHumidity(humidity);
                }
                if(obj.has("p")){
                    float pressure = Float.parseFloat(obj.getString("t"));
                    A01.setTemperature(pressure);
                }
            }
        }


    }
}
