package me.raynorjames.ai.utils;


import me.raynorjames.ai.structure.Block;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by productionaccount on 12/15/15.
 */
public class ProcessHelper {

    public static String getRawData(String line, int dataTypeLength) {
        String processed = line.substring(dataTypeLength + 1, line.length() - 2);
        return processed;
    }

    public static String[] getUnprocessedData(String processLine) {
        String noSpaces = processLine.replace(" ", "");
        String[] chars = noSpaces.split(",");
        return chars;
    }

    public static String[] getUnprocessedDataFromRaw(String line, int dataTypeLength) {
        String raw = getRawData(line, dataTypeLength);
        String[] UnprocessedData = getUnprocessedData(raw);
        return UnprocessedData;
    }

    public static int[] getProcessedData(String[] unprocessedData) {
        int[] intArray = new int[unprocessedData.length];
        for (int i = 0; i < unprocessedData.length; i++) intArray[i] = Integer.parseInt(unprocessedData[i]);
        return intArray;
    }

    public static int[] getProcessedDataFromRaw(String line, int dataTypeLength) {
        String[] UnprocessedData = getUnprocessedDataFromRaw(line, dataTypeLength);
        return getProcessedData(UnprocessedData);
    }

    public static String getName(String line) {
        return line.substring(5, line.length() - 2);
    }

    public static String getPermission(String line) {
        return line.substring(11, line.length() - 2);
    }

    public static String getAuthor(String line) {
        return line.substring(7, line.length() - 2);
    }

    public static String getDescription(String line) {
        return line.substring(12, line.length() - 2);
    }

    public static List<Integer> toListArray(HashMap<Integer, List<Block>> var) {
        List<Integer> integers = new ArrayList<>();
        for (int i : var.keySet())
            integers.add(i);
        return integers;
    }

    public static int[] toIntArray(List<Integer> integers) {
        int[] ints = new int[integers.size()];
        for (int i = 0; i < ints.length; i++)
            ints[i] = integers.get(i);
        return ints;
    }

    public static int[] sort(int[] asd) {
        int[] sorted = asd.clone();
        for (int i = 0; i < sorted.length; i++) {
            for (int j = i + 1; j < sorted.length; j++) {
                if ((sorted[i] > sorted[j]) && (i != j)) {
                    int temp = sorted[j];
                    sorted[j] = sorted[i];
                    sorted[i] = temp;
                }
            }
        }
        return sorted;
    }

    public static int[] sortedFromHashMap(HashMap<Integer, List<Block>> var) {
        List<Integer> integers = toListArray(var);
        int[] unsorted = toIntArray(integers);
        int[] sorted = sort(unsorted);
        return sorted;
    }

}
