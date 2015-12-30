package me.raynorjames.ai.reading;

import me.raynorjames.ai.structure.AIStructure;
import me.raynorjames.ai.structure.IAIStructure;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.bukkit.util.FileUtil;

import java.io.*;
import java.util.List;

/**
 * Created by productionaccount on 12/15/15.
 */
public class Reader implements IReader {
    private AIStructure structure;
    private Processor processor;

    public static int SUCCESS = 0;
    public static int INVALID_FILE = 1;
    public static int INVALID_EXTENSION = 2;
    private File file;

    public Reader(File file) {
        this.file = file;
        this.processor = new Processor();
    }

    public int read() {
        if (!file.exists())
            return INVALID_FILE;
        if (!FilenameUtils.getExtension(file.getPath()).equals("ais"))
            return INVALID_EXTENSION;
        String[] lines;
        try {
            List<String> linesList = FileUtils.readLines(file);
            lines = linesList.toArray(new String[linesList.size()]);
            return read(lines);
        } catch (IOException e) {
            return INVALID_FILE;
        }
    }

    public int read(String[] lines) {
        processor.processData(lines);
        this.structure = processor.getStructure();
        return SUCCESS;
    }

    public AIStructure getStructure() {
        return structure;
    }

    @Override
    public String toString() {
        return "Reader [" +
                "structure=" + structure +
                ", processor=" + processor +
                ", file=" + file +
                ']';
    }
}
