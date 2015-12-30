package me.raynorjames.ai.commands;

import me.raynorjames.ai.ArchitectInterpreter;
import me.raynorjames.ai.structure.AIStructure;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.Plugin;

import java.util.logging.Level;

/**
 * Created by productionaccount on 12/16/15.
 */
public class LoadedStructuresCommand implements CommandExecutor {
    public Plugin plugin;

    public LoadedStructuresCommand(Plugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof ConsoleCommandSender) {
            int i = 1;
            for (AIStructure structure : ArchitectInterpreter.getStructures()) {
                plugin.getLogger().log(Level.INFO, "#" + i + ": Name=" + structure.getName() + ", Description=" + structure.getDescription() + ", Permission=" + structure.getPermission() + ", Author=" + structure.getAuthor() + ", Block Count=" + structure.getBlockCount());
                i++;
            }
        }
        return false;
    }
}
