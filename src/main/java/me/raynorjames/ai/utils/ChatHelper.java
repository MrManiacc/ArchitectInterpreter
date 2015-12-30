package me.raynorjames.ai.utils;

import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by productionaccount on 12/15/15.
 */
public class ChatHelper {
    public static String FormattedString(String input) {
        return ChatColor.YELLOW + "[" + ChatColor.BLUE + "ArchitectInterpreter" + ChatColor.YELLOW + "] " + ChatColor.GOLD + input;
    }

    private static List<String> removeFirstTwoArgs(String[] args, int startIndex) {
        List<String> convertedString = new ArrayList<>();
        for (int i = startIndex; i < args.length; i++) convertedString.add(args[i]);
        return convertedString;
    }

    public static String convertToString(String[] args) {
        List<String> stringList = new ArrayList<>();
        for (int i = 0; i < args.length; i++)
            stringList.add(args[i]);
        return convertToString(stringList);
    }

    private static String convertToString(List<String> strings) {
        StringBuilder builder = new StringBuilder();
        for (String s : strings)
            builder.append(s + " ");
        String out = removeLast(builder.toString());
        return out;
    }

    public static String RemoveArgs(String[] args, int startIndex) {
        List<String> list = removeFirstTwoArgs(args, startIndex);
        String convertedString = convertToString(list);
        return convertedString;
    }

    private static String removeLast(String str) {
        if (str.length() > 0) {
            str = str.substring(0, str.length() - 1);
        }
        return str;
    }

}
