package me.raynorjames.ai.json;

import com.google.gson.Gson;
import me.raynorjames.ai.structure.AIStructure;
import me.raynorjames.ai.structure.IAIStructure;

import java.io.*;

/**
 * Created by productionaccount on 12/16/15.
 */
public class Json implements IJson {
    public static int SUCCESS = 0;
    public static int COULDNT_CREATE_FILE = 1;
    public static int COULDNT_WRITE_TO_FILE = 2;

    @Override
    public AIStructure read(File file) {
        Gson gson = new Gson();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            AIStructure obj = gson.fromJson(br, AIStructure.class);
            return obj;
        } catch (FileNotFoundException e) {
            return null;
        }
    }

    @Override
    public int write(File file, Object object) {
        if (!file.exists())
            try {
                file.createNewFile();
            } catch (IOException e) {
                return COULDNT_CREATE_FILE;
            }
        Gson gson = new Gson();
        String json = gson.toJson(object);
        try {
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(json);
            fileWriter.close();
        } catch (IOException e) {
            return COULDNT_WRITE_TO_FILE;
        }
        return SUCCESS;
    }
}
