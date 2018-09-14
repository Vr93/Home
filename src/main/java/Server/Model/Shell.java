package Server.Model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Shell {

    public static ArrayList<String> doShellCommand(String exeString){
        ArrayList<String> data = new ArrayList<>();
        try {
            Process p = Runtime.getRuntime().exec(exeString);
            p.waitFor();

            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(p.getInputStream()));

            String line = "";
            while ((line = reader.readLine()) != null) {
                data.add(line);
            }
            return data;
        }
        catch(InterruptedException | IOException ex){
            data.add("Error, could not perform task!");
            data.add(ex.getMessage());
            return data;
        }
    }
}
