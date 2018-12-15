package Devices.Com_Master.Model;


import Devices.A01.Model.A01;
import Devices.A02.Model.A02;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

public class SerialHandler {

    private static A01 A01;
    private static A02 A02;

    public SerialHandler(){
        this.A01 = new A01();
        this.A02 = new A02();
    }

    public void handleSerialInput(String data){
        try {
            data = "{" + data + "}";

            /* Parse the received data to JSON. */
            JsonObject obj = new JsonParser().parse(data).getAsJsonObject();
            /* Check JSON object for ID. */
            if (obj.has("id")) {
                String id = obj.get("id").toString();
                System.out.println("id is: " + id);
                /* A01, Outdoor Weather Station. */
                if (id.contains("A01")) {
                   // A01_Data(obj);
                }
                /* A02, Indoor monitoring station. */
                if (id.contains("A02")) {
                   // A02_Data(obj);
                }
                /* For new devices, add it here..*/
                else if (id.equalsIgnoreCase("newdevice...")) {
                    //....
                }
            }
        }
        catch(JsonSyntaxException ex){
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
        if(json.has("e")){
            String error = json.get("e").toString();
            A01.setError(Integer.valueOf(error));
        }
    }

    private void A02_Data(JsonObject json){
        if(json.has("t")){
            String temperature = json.get("t").toString();
            A02.setTemperature(Float.valueOf(temperature));
        }
        if(json.has("h")){
            String humidity = json.get("h").toString();
            A02.setHumidity(Float.valueOf(humidity));
        }
        if(json.has("p")){
            String pressure = json.get("p").toString();
            A02.setPressure(Float.valueOf(pressure));
        }
        if(json.has("e")){
            String error = json.get("e").toString();
            A02.setError(Integer.valueOf(error));
        }
    }

    public static A01 getA01(){
        return A01;
    }

    public static A02 getA02(){
        return A02;
    }
}
