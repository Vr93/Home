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

    public static String getTotalMemory() throws IOException, InterruptedException {
        /* This is in bytes, convert to MB before sent to client. */
        return Long.toString(SystemInfo.getMemoryTotal()/(1024*100)) + " MB";
    }

    public static String getUsedMemory() throws IOException, InterruptedException {
        /* This is in bytes, convert to MB before sent to client. */
        return Long.toString(SystemInfo.getMemoryUsed()/(1024*100)) + " MB";
    }

    public static String getFreeMemory() throws IOException, InterruptedException {
        /* This is in bytes, convert to MB before sent to client. */
        return Long.toString(SystemInfo.getMemoryFree()/(1024*100)) + " MB";
    }

    public static String getSharedMemory() throws IOException, InterruptedException {
        /* This is in bytes, convert to MB before sent to client. */
        return Long.toString(SystemInfo.getMemoryShared()/(1024*100)) + " MB";
    }

    public static String getMemoryBuffers() throws IOException, InterruptedException {
        /* This is in bytes, convert to MB before sent to client. */
        return Long.toString(SystemInfo.getMemoryBuffers()/(1024*100)) + " MB";
    }

    public static String getMemoryCached() throws IOException, InterruptedException {
        /* This is in bytes, convert to MB before sent to client. */
        return Long.toString(SystemInfo.getMemoryCached()/(1024*100)) + " MB";
    }

    public static String getSDRamCVoltage() throws IOException, InterruptedException {
        return Float.toString(SystemInfo.getMemoryVoltageSDRam_C()) + " V";
    }

    public static String getSDRamIVoltage() throws IOException, InterruptedException {
        return Float.toString(SystemInfo.getMemoryVoltageSDRam_I()) + " V";
    }

    public static String getSDRamPVoltage() throws IOException, InterruptedException {
        return Float.toString(SystemInfo.getMemoryVoltageSDRam_P()) + " V";
    }


}
