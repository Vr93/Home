package Server.Model;

import com.pi4j.system.SystemInfo;

import java.io.IOException;

public class ServerInformation {

    public static float getServerCPUTemperature() throws IOException, InterruptedException {
        return SystemInfo.getCpuTemperature();
    }
}
