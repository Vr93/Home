package Server.Controller;

import Server.Model.ServerInformation;
import com.mysql.fabric.Server;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Controller
public class ServerController {

    @RequestMapping(value="/server/cputemperature", method = RequestMethod.GET)
    @ResponseBody
    public String getCPUTemperature() {
        String retVal;
        try {
            retVal = Float.toString(ServerInformation.getServerCPUTemperature());
        }
        catch(IOException | InterruptedException ex){
            retVal = "Error, could not get CPU Temp!";
        }
        return retVal;
    }

    @RequestMapping(value="/server/platformname", method = RequestMethod.GET)
    @ResponseBody
    public String getPlatformName() {
        return ServerInformation.getPlatformName();
    }

    @RequestMapping(value="/server/serialnumber", method = RequestMethod.GET)
    @ResponseBody
    public String getSerialNumber() {
        try {
            return ServerInformation.getSerialNumber();
        }
        catch(IOException | InterruptedException ex){
            return "Error, could not get serial number!";
        }
    }

    @RequestMapping(value="/server/cpurevision", method = RequestMethod.GET)
    @ResponseBody
    public String getCPURevision() {
        try {
            return ServerInformation.getCPURevision();
        }
        catch(IOException | InterruptedException ex){
            return "Error, could not get cpu revision!";
        }
    }

    @RequestMapping(value="/server/cpuarchitecture", method = RequestMethod.GET)
    @ResponseBody
    public String getCPUArchitecture() {
        try {
            return ServerInformation.getCPUArchitecture();
        }
        catch(IOException | InterruptedException ex){
            return "Error, could not get cpu architecture!";
        }
    }

    @RequestMapping(value="/server/cpupart", method = RequestMethod.GET)
    @ResponseBody
    public String getCPUPart() {
        try {
            return ServerInformation.getCPUPart();
        }
        catch(IOException | InterruptedException ex){
            return "Error, could not get cpu part!";
        }
    }

    @RequestMapping(value="/server/cpuvoltage", method = RequestMethod.GET)
    @ResponseBody
    public String getCPUVoltage() {
        try {
            return ServerInformation.getCPUVoltage();
        }
        catch(IOException | InterruptedException ex){
            return "Error, could not get cpu voltage!";
        }
    }

    @RequestMapping(value="/server/modelname", method = RequestMethod.GET)
    @ResponseBody
    public String getModelName() {
        try {
            return ServerInformation.getModelName();
        }
        catch(IOException | InterruptedException ex){
            return "Error, could not get model name!";
        }
    }

    @RequestMapping(value="/server/processor", method = RequestMethod.GET)
    @ResponseBody
    public String getProcessor() {
        try {
            return ServerInformation.getProcessor();
        }
        catch(IOException | InterruptedException ex){
            return "Error, could not get processor!";
        }
    }

    @RequestMapping(value="/server/hardware", method = RequestMethod.GET)
    @ResponseBody
    public String getHardware() {
        try {
            return ServerInformation.getHardware();
        }
        catch(IOException | InterruptedException ex){
            return "Error, could not get hardware!";
        }
    }

    @RequestMapping(value="/server/hardwarerevision", method = RequestMethod.GET)
    @ResponseBody
    public String getHardwareRevision() {
        try {
            return ServerInformation.getHardwareRevision();
        }
        catch(IOException | InterruptedException ex){
            return "Error, could not get hardware revision!";
        }
    }

    @RequestMapping(value="/server/hardfloatabi", method = RequestMethod.GET)
    @ResponseBody
    public String isHardFloatABI() {
        try {
            return ServerInformation.isHardFloatABI();
        }
        catch(IOException | InterruptedException ex){
            return "Error, could not get hard float abi!";
        }
    }

    @RequestMapping(value="/server/boardtype", method = RequestMethod.GET)
    @ResponseBody
    public String getBoardType() {
        try {
            return ServerInformation.getBoardType();
        }
        catch(IOException | InterruptedException ex){
            return "Error, could not get board type!";
        }
    }

    @RequestMapping(value="/server/totalmemory", method = RequestMethod.GET)
    @ResponseBody
    public String getTotalMemory() {
        try {
            return ServerInformation.getTotalMemory();
        }
        catch(IOException | InterruptedException ex){
            return "Error, could not get total memory!";
        }
    }

    @RequestMapping(value="/server/usedmemory", method = RequestMethod.GET)
    @ResponseBody
    public String getUsedMemory() {
        try {
            return ServerInformation.getUsedMemory();
        }
        catch(IOException | InterruptedException ex){
            return "Error, could not get used memory!";
        }
    }

    @RequestMapping(value="/server/freememory", method = RequestMethod.GET)
    @ResponseBody
    public String getFreeMemory() {
        try {
            return ServerInformation.getFreeMemory();
        }
        catch(IOException | InterruptedException ex){
            return "Error, could not get free memory!";
        }
    }

    @RequestMapping(value="/server/sharedmemory", method = RequestMethod.GET)
    @ResponseBody
    public String getSharedMemory() {
        try {
            return ServerInformation.getSharedMemory();
        }
        catch(IOException | InterruptedException ex){
            return "Error, could not get shared memory!";
        }
    }

    @RequestMapping(value="/server/memorybuffers", method = RequestMethod.GET)
    @ResponseBody
    public String getMemoryBuffers() {
        try {
            return ServerInformation.getMemoryBuffers();
        }
        catch(IOException | InterruptedException ex){
            return "Error, could not get memory buffers!";
        }
    }
}
