package me.raynorjames.ai.commands;

import me.raynorjames.ai.ArchitectInterpreter;
import me.raynorjames.ai.json.Json;
import me.raynorjames.ai.structure.AIStructure;
import me.raynorjames.ai.utils.ChatHelper;
import me.raynorjames.ai.utils.ProcessHelper;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.util.logging.Level;

/**
 * Created by productionaccount on 12/16/15.
 */
public class MakeStructureCommand implements CommandExecutor {
    private Plugin plugin;

    public MakeStructureCommand(Plugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof ConsoleCommandSender) {
            if (args.length == 0) {
                plugin.getLogger().log(Level.INFO, "Please provide the name of the structure you wish to output or provide the argument 'all' for all structures.");
            } else if (args.length >= 1) {
                if (!args[0].equalsIgnoreCase("-all") && !args[0].equalsIgnoreCase("-a")) {
                    String search = ChatHelper.convertToString(args);
                    AIStructure structure = ArchitectInterpreter.getStructure(search);
                    if (structure == null)
                        plugin.getLogger().log(Level.INFO, "There is no structure by the name '" + search + "'.");
                    else {
                        Json json = new Json();
                        File structureFile = new File(this.plugin.getDataFolder() + File.separator + "release" + File.separator + structure.getName() + ".json");
                        int status = json.write(structureFile, structure);
                        if (status == Json.SUCCESS)
                            plugin.getLogger().log(Level.INFO, "Successfully wrote structure output file as '" + structure.getName() + ".json in the release folder.");
                        else if (status == Json.COULDNT_CREATE_FILE)
                            plugin.getLogger().log(Level.SEVERE, "Couldn't write file '" + structure.getName() + ".json'!");
                        else if (status == Json.COULDNT_WRITE_TO_FILE)
                            plugin.getLogger().log(Level.SEVERE, "Couldn't write structure to file '" + structure.getName() + ".json'!");
                    }
                } else {
                    Json json = new Json();
                    for (AIStructure structure : ArchitectInterpreter.getStructures()) {
                        File structureFile = new File(this.plugin.getDataFolder() + File.separator + "release" + File.separator + structure.getName() + ".json");
                        int status = json.write(structureFile, structure);
                        if (status == Json.SUCCESS)
                            plugin.getLogger().log(Level.INFO, "Successfully wrote structure output file as '" + structure.getName() + ".json in the release folder.");
                        else if (status == Json.COULDNT_CREATE_FILE)
                            plugin.getLogger().log(Level.SEVERE, "Couldn't write file '" + structure.getName() + ".json'!");
                        else if (status == Json.COULDNT_WRITE_TO_FILE)
                            plugin.getLogger().log(Level.SEVERE, "Couldn't write structure to file '" + structure.getName() + ".json'!");
                    }
                }
            }
        }
        return false;
    }
}
