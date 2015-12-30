package me.raynorjames.ai.structure;

import me.raynorjames.ai.json.Json;
import me.raynorjames.ai.reading.Reader;
import org.apache.commons.io.FileUtils;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;

/**
 * Created by productionaccount on 12/15/15.
 */
public class StructureHandler {
    private HashMap<String, AIStructure> structures = new HashMap<>();
    private Plugin plugin;

    public StructureHandler(File folder, Plugin plugin, boolean isRelease) {
        this.plugin = plugin;
        readFiles(folder, isRelease);
    }

    public AIStructure getStructure(String name) {
        return structures.get(name);
    }

    public List<AIStructure> getStructures() {
        return new ArrayList<>(structures.values());
    }

    public int reallocate() {
        structures.clear();
        File folder;
        int status = 0;
        if (plugin.getConfig().getBoolean("release")) {
            folder = new File(this.plugin.getDataFolder() + File.separator + "release");
            status = readFiles(folder, true);
        } else {
            folder = new File(this.plugin.getDataFolder() + File.separator + "dev");
            status = readFiles(folder, false);
        }
        return status;
    }

    private int readFiles(File folder, boolean isRelease) {
        if (!isRelease) {
            List<File> files = (List<File>) FileUtils.listFiles(folder, new String[]{"ais"}, true);
            int size = 0;
            for (File file : files) {
                plugin.getLogger().log(Level.INFO, "Found: " + file.getPath());
                Reader reader = new Reader(file);
                int status = reader.read();
                if (status == Reader.SUCCESS) {
                    structures.put(reader.getStructure().getName(), reader.getStructure());
                    if (reader.getStructure().getName().equalsIgnoreCase("undefined"))
                        reader.getStructure().setName("undefined(" + new Random().nextFloat() * 999999 + ")");
                    size++;
                } else if (status == Reader.INVALID_FILE) {
                    System.out.println("invalid file: " + file.getPath());
                } else if (status == Reader.INVALID_EXTENSION)
                    System.out.println("invalid extension: " + file.getPath());
            }
            return size;
        } else {
            List<File> files = (List<File>) FileUtils.listFiles(folder, new String[]{"json"}, true);
            int size = 0;
            for (File file : files) {
                plugin.getLogger().log(Level.INFO, "Found: " + file.getPath());
                Json json = new Json();
                AIStructure structure = json.read(file);
                if (structure != null) {
                    structures.put(structure.getName(), structure);
                    if (structure.getName().equalsIgnoreCase("undefined"))
                        structure.setName("undefined(" + new Random().nextFloat() * 999999 + ")");
                    size++;
                } else {
                    System.out.println("invalid file: " + file.getPath());
                }
            }
            return size;
        }
    }

    @Override
    public String toString() {
        return "StructureHandler [" +
                "structures=" + structures +
                ']';
    }
}
