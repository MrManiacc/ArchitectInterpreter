package me.raynorjames.ai.reading;

import me.raynorjames.ai.structure.*;
import me.raynorjames.ai.utils.ProcessHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by productionaccount on 12/15/15.
 */
public class Processor implements IProcessor {
    private AIStructure structure;
    private Random random = new Random();
    private boolean nameSet = false;

    public Processor() {
        structure = new AIStructure();
    }

    public void processData(String[] lines) {
        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];
            if (line.startsWith("origin") && line.endsWith(";")) {
                processOrigin(line, i);
            }
            if (line.startsWith("single") && line.endsWith(";")) {
                Primitives.Single single = processSingle(line, i);
                if (single != null)
                    structure.addSingle(single);
            }
            if (line.startsWith("column") && line.endsWith(";")) {
                Primitives.Column column = processColumn(line, i);
                if (column != null)
                    structure.addColumn(column);
            }
            if (line.startsWith("cube") && line.endsWith(";")) {
                Primitives.Cube cube = processCube(line, i);
                if (cube != null)
                    structure.addCube(cube);
            }
            if (line.startsWith("flat") && line.endsWith(";")) {
                Primitives.Flat flat = processFlat(line, i);
                if (flat != null)
                    structure.addFlat(flat);
            }
            if (line.startsWith("name") && line.endsWith(";"))
                processName(line);
            if (line.startsWith("permission") && line.endsWith(";"))
                processPermissions(line);
            if (line.startsWith("author") && line.endsWith(";"))
                processAuthor(line);
            if (line.startsWith("description") && line.endsWith(";"))
                processDescription(line);
        }
    }

    public void processDescription(String line) {
        String description = ProcessHelper.getDescription(line);
        structure.setDescription(description);
    }

    public void processAuthor(String line) {
        String author = ProcessHelper.getAuthor(line);
        structure.setAuthor(author);
    }

    //EX: permission(structure.use);
    public void processPermissions(String line) {
        String permission = ProcessHelper.getPermission(line);
        structure.setPermission(permission);
    }

    //EX: name(my awesome structure);
    public void processName(String line) {
        String name = ProcessHelper.getName(line);
        structure.setName(name);
    }

    //first = worldX, second = worldY, third = worldZ
    //EX: origin(231, 321, 3534);
    public void processOrigin(String line, int lineNumber) {
        int[] data = ProcessHelper.getProcessedDataFromRaw(line, 6);
        if (data.length < 3)
            System.out.println("Error on line '" + lineNumber + "'.");
        else
            structure.setOrigin(data[0], data[1], data[2]);
    }

    //first = relativeX, second = relativeY, third = relativeZ, fourth = block id, fifth = block meta
    //EX: single(4, 23, 2, 1, 0);
    public Primitives.Single processSingle(String line, int lineNumber) {
        int[] data = ProcessHelper.getProcessedDataFromRaw(line, 6);
        Block block = new Block();
        if (data.length < 4) {
            System.out.println("Error on line '" + lineNumber + "'.");
            return null;
        } else {
            if (data.length == 4)
                block.setID(data[3], (byte) 0);
            else if (data.length == 5)
                block.setID(data[3], (byte) data[4]);
            block.setPosition(data[0], data[1], data[2]);
            block.setBuildOrder(lineNumber);
            return new Primitives.Single(block);
        }
    }

    //first = relativeX, second = relativeY, third = relativeZ, fourth = block id, fifth = block meta, sixth = column height
    //EX: column(4, 3, 2, 2, 0, 5);
    public Primitives.Column processColumn(String line, int lineNumber) {
        int data[] = ProcessHelper.getProcessedDataFromRaw(line, 6);
        if (data.length != 6) {
            System.out.println("Error on line '" + lineNumber + "'.");
            return null;
        } else {
            List<Block> blocks = new ArrayList<>();
            for (int i = 0; i < data[5]; i++) {
                Block block = new Block();
                block.setPosition(data[0], data[1] + i, data[2]);
                block.setID(data[3], (byte) data[4]);
                block.setBuildOrder(lineNumber);
                blocks.add(block);
            }
            return new Primitives.Column(blocks);
        }
    }

    //first = start relativeX, second = start relativeY, third = start relativeZ, fourth = end relativeX, fifth = end relativeY, sixth = end relativeZ, seventh = block id, eighth = block meta
    //EX: cube(0, 0, 0, 10, 10, 10, 1, 0);
    public Primitives.Cube processCube(String line, int lineNumber) {
        int data[] = ProcessHelper.getProcessedDataFromRaw(line, 4);
        if (data.length != 8) {
            System.out.println("Error on line '" + lineNumber + "'.");
            return null;
        } else {
            List<Block> blocks = new ArrayList<>();
            for (int x = data[0]; x < data[3]; x++) {
                for (int y = data[1]; y < data[4]; y++) {
                    for (int z = data[2]; z < data[5]; z++) {
                        Block block = new Block();
                        block.setPosition(x, y, z);
                        block.setID(data[6], (byte) data[7]);
                        block.setBuildOrder(lineNumber);
                        blocks.add(block);
                    }
                }
            }
            return new Primitives.Cube(blocks);
        }
    }

    //first = start relativeX, second = start relativeY, third = start relativeZ, fourth = end relativeX, fifth = end relativeZ, sixth = block id, eighth = block meta
    //EX: flat(0, 0, 0, 10, 10, 1, 0);
    public Primitives.Flat processFlat(String line, int lineNumber) {
        int data[] = ProcessHelper.getProcessedDataFromRaw(line, 4);
        if (data.length != 7) {
            System.out.println("Error on line '" + lineNumber + "'.");
            return null;
        } else {
            List<Block> blocks = new ArrayList<>();
            for (int x = data[0]; x < data[2]; x++) {
                for (int y = data[4]; y < data[4] + 1; y++) {
                    for (int z = data[1]; z < data[3]; z++) {
                        Block block = new Block();
                        block.setPosition(x, y, z);
                        block.setID(data[5], (byte) data[6]);
                        block.setBuildOrder(lineNumber);
                        blocks.add(block);
                    }
                }
            }
            return new Primitives.Flat(blocks);
        }
    }

    public void processForLoop(String line, int lineNumber) {

    }

    public void processEndForLoop(String line, int lineNumber) {

    }

    public AIStructure getStructure() {
        return structure;
    }

    @Override
    public String toString() {
        return "Processor [" +
                "structure=" + structure +
                ", random=" + random +
                ", nameSet=" + nameSet +
                ']';
    }
}
