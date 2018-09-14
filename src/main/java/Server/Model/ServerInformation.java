package Server.Model;

import com.pi4j.platform.PlatformManager;
import com.pi4j.system.SystemInfo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ServerInformation {

    public static float getServerCPUTemperature() throws IOException, InterruptedException {
        return SystemInfo.getCpuTemperature();
    }
    

    public static String[] getServerInformation(){
        ArrayList<String> arrList = Shell.doShellCommand("cat /proc/cpuinfo");
        String[] data = new String[arrList.size()];
        for(int i = 0; i < arrList.size(); i++){
            data[i] = arrList.get(i);
        }
        return data;
    }


}
