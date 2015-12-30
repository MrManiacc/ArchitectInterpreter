package me.raynorjames.ai.commands;

import me.raynorjames.ai.ArchitectInterpreter;
import me.raynorjames.ai.utils.ChatHelper;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.Plugin;

import java.util.logging.Level;

/**
 * Created by productionaccount on 12/17/15.
 */
public class ReloadConfigCommand implements CommandExecutor {
    private Plugin plugin;

    public ReloadConfigCommand(Plugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
        if (sender instanceof ConsoleCommandSender) {
            plugin.reloadConfig();
            if (plugin.getConfig().getBoolean("release"))
                plugin.getLogger().log(Level.INFO, "Config file reloaded, release mode set to: TRUE");
            else
                plugin.getLogger().log(Level.INFO, "Config file reloaded, release mode set to: FALSE");
            String args = ChatHelper.convertToString(strings);
            if (args.contains("-rs") || args.contains("-reallocateStructures"))
                ArchitectInterpreter.reallocateStructures();
        }
        return false;
    }
}
