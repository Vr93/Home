package Server.Model;


import java.util.ArrayList;

public class ServerInformation {

    public static String getServerCPUTemperature(){
        ArrayList<String> tempArrList = Shell.doShellCommand("cat /sys/class/thermal/thermal_zone0/temp");
        float value = Float.parseFloat(tempArrList.get(1)) / 1000;
        return String.valueOf(value);
    }


    public static String[] getServerInformation(){
        ArrayList<String> arrList = Shell.doShellCommand("cat /proc/cpuinfo");
        String[] data = new String[arrList.size()];
        for(int i = 0; i < arrList.size(); i++){
            data[i] = arrList.get(i);
        }
        return data;
    }

    public static String[] getServerStorage(){
        ArrayList<String> arrList = Shell.doShellCommand("df -Bm");
        String[] data = new String[arrList.size()];
        /* The data from shell is unorganized, with many whitespaces etc,
        Reorganize the data and store it in string array. */
        for(int i = 0; i < data.length; i++){
            System.out.println(i);
            String[] split = arrList.get(i).split( " ");
            for(String splitData: split) {
                if(!splitData.equalsIgnoreCase("")) {
                    if(!data[i].equalsIgnoreCase("")) {
                        data[i] = data[i] + "," + splitData;
                    }
                    else{
                        data[i] = splitData;
                    }
                }
            }
        }
        return data;
    }


}
