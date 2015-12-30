package me.raynorjames.ai;

import me.raynorjames.ai.builders.Builder;
import me.raynorjames.ai.commands.LoadedStructuresCommand;
import me.raynorjames.ai.commands.MakeStructureCommand;
import me.raynorjames.ai.commands.ReallocateStructuresCommand;
import me.raynorjames.ai.commands.ReloadConfigCommand;
import me.raynorjames.ai.json.Json;
import me.raynorjames.ai.structure.AIStructure;
import me.raynorjames.ai.structure.IAIStructure;
import me.raynorjames.ai.structure.StructureHandler;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

/**
 * Created by productionaccount on 12/15/15.
 */
public class ArchitectInterpreter extends JavaPlugin {
    private static StructureHandler structureHandler = null;
    private static ArchitectInterpreter instance;

    public void onEnable() {
        ArchitectInterpreter.instance = this;
        getDataFolder().mkdir();
        this.saveDefaultConfig();
        File dev = new File(getDataFolder() + File.separator + "dev");
        File release = new File(getDataFolder() + File.separator + "release");
        if (!dev.exists())
            dev.mkdir();
        if (!release.exists())
            release.mkdir();
        if (!this.getConfig().getBoolean("release"))
            structureHandler = new StructureHandler(new File(this.getDataFolder() + File.separator + "dev"), this, false);
        else
            structureHandler = new StructureHandler(new File(this.getDataFolder() + File.separator + "release"), this, true);

        getCommand("makestructure").setExecutor(new MakeStructureCommand(this));
        getCommand("loadedstructures").setExecutor(new LoadedStructuresCommand(this));
        getCommand("reallocatestructures").setExecutor(new ReallocateStructuresCommand(this));
        getCommand("reloadconfig").setExecutor(new ReloadConfigCommand(this));
    }

    public static AIStructure getStructure(String name) {
        return structureHandler.getStructure(name);
    }

    public static List<AIStructure> getStructures() {
        return structureHandler.getStructures();
    }

    public static int reallocateStructures() {
        return structureHandler.reallocate();
    }
}
