package Server.Model;

import com.pi4j.platform.PlatformManager;
import com.pi4j.system.SystemInfo;

import java.io.IOException;

public class ServerInformation {

    public static float getServerCPUTemperature() throws IOException, InterruptedException {
        return SystemInfo.getCpuTemperature();
    }

    public static String getPlatformName() {
        return PlatformManager.getPlatform().getLabel();
    }

    public static String getSerialNumber() throws IOException, InterruptedException {
        return SystemInfo.getSerial();
    }

    public static String getCPURevision() throws IOException, InterruptedException {
        return SystemInfo.getCpuRevision();
    }

    public static String getCPUArchitecture() throws IOException, InterruptedException {
        return SystemInfo.getCpuArchitecture();
    }

    public static String getCPUPart() throws IOException, InterruptedException {
        return SystemInfo.getCpuPart();
    }

    public static String getCPUVoltage() throws IOException, InterruptedException {
        return Float.toString(SystemInfo.getCpuVoltage());
    }

    public static String getModelName() throws IOException, InterruptedException {
        return SystemInfo.getModelName();
    }

    public static String getProcessor() throws IOException, InterruptedException {
        return SystemInfo.getProcessor();
    }

    public static String getHardware() throws IOException, InterruptedException {
        return SystemInfo.getHardware();
    }

    public static String getHardwareRevision() throws IOException, InterruptedException {
        return SystemInfo.getRevision();
    }

    public static String isHardFloatABI() throws IOException, InterruptedException {
        return Boolean.toString(SystemInfo.isHardFloatAbi());
    }

    public static String getBoardType() throws IOException, InterruptedException {
        return SystemInfo.getBoardType().name();
    }

}
