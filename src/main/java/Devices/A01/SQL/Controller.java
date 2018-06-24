package Devices.A01.SQL;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class Controller {

    @GetMapping(value = "/A01/dataDay")
    public String[] A01_DataDay(){
        A01_SQL sql = new A01_SQL();
        ArrayList<String> arrayList = sql.selectA01Data(1);
        String[] data = new String[arrayList.size()];
        for(int i = 0; i < arrayList.size(); i++){
            String jsonString = arrayList.get(i);
            data[i] = jsonString;
        }
        return data;
    }

    @GetMapping(value = "/A01/dataWeek")
    public String[] A01_DataWeek(){
        A01_SQL sql = new A01_SQL();
        ArrayList<String> arrayList = sql.selectA01Data(8);
        String[] data = new String[arrayList.size()];
        for(int i = 0; i < arrayList.size(); i++){
            String jsonString = arrayList.get(i);
            data[i] = jsonString;
        }
        return data;
    }

    @GetMapping(value = "/A01/dataMonth")
    public String[] A01_DataMonth(){
        A01_SQL sql = new A01_SQL();
        ArrayList<String> arrayList = sql.selectA01Data(31);
        String[] data = new String[arrayList.size()];
        for(int i = 0; i < arrayList.size(); i++){
            String jsonString = arrayList.get(i);
            data[i] = jsonString;
        }
        return data;
    }

    @GetMapping(value = "/A01/dataYear")
    public String[] A01_DataYear(){
        A01_SQL sql = new A01_SQL();
        ArrayList<String> arrayList = sql.selectA01Data(365);
        String[] data = new String[arrayList.size()];
        for(int i = 0; i < arrayList.size(); i++){
            String jsonString = arrayList.get(i);
            data[i] = jsonString;
        }
        return data;
    }

    @GetMapping(value = "/A01/database_interval")
    public long[] A01_Interval_Setting(){
        A01_SQL sql = new A01_SQL();
        return sql.selectDatabaseIntervalA01();
    }
}
