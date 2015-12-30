package me.raynorjames.ai.commands;

import javafx.scene.shape.Arc;
import me.raynorjames.ai.ArchitectInterpreter;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.Plugin;

import java.util.logging.Level;

/**
 * Created by productionaccount on 12/16/15.
 */
public class ReallocateStructuresCommand implements CommandExecutor {
    public Plugin plugin;

    public ReallocateStructuresCommand(Plugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof ConsoleCommandSender) {
            int size = ArchitectInterpreter.reallocateStructures();
            plugin.getLogger().log(Level.INFO, size + " structures have been reallocated!");
        }
        return false;
    }
}
