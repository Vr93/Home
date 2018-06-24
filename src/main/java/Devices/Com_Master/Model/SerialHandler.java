package Devices.Com_Master.Model;


import Devices.A01.Model.A01;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class SerialHandler {

    private static A01 A01;

    public SerialHandler(){
        this.A01 = new A01();
    }

    public void handleSerialInput(String data){
        data = "{" + data + "}";
        JsonObject obj = new JsonParser().parse(data).getAsJsonObject();
        /* Check JSON object for ID. */
        if(obj.has("id")){
            String id = obj.get("id").toString();
            System.out.println("id is: " + id);
            if(id.contains("A01")){
                A01_Data(obj);
            }
            else if(id.equalsIgnoreCase("newdevice...")){
                //....
            }
        }
    }


    private void A01_Data(JsonObject json){
        if(json.has("t")){
            String temperature = json.get("t").toString();
            A01.setTemperature(Float.valueOf(temperature));
        }
        if(json.has("h")){
            String humidity = json.get("h").toString();
            A01.setHumidity(Float.valueOf(humidity));
        }
        if(json.has("p")){
            String pressure = json.get("p").toString();
            A01.setPressure(Float.valueOf(pressure));
        }
    }

    public static A01 getA01(){
        return A01;
    }
}
