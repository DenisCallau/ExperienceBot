package main.helpers;

import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public abstract class ExpTableCreator {

    public static JSONObject createExpTable(String gameFolder) throws IOException {
        String filePath = "/src/main/resources/" + gameFolder + "expTable.json";
        File file = new File(filePath);
        if (file.exists()) {
            InputStream is = new FileInputStream(filePath);
            String jsonText = IOUtils.toString(is, StandardCharsets.UTF_8);
            return new JSONObject(jsonText);
        } else {
            return null;
        }
    }

}
