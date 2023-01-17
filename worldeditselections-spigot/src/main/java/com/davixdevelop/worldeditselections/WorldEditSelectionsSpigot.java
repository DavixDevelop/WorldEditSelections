package com.davixdevelop.worldeditselections;

import com.davixdevelop.worldeditselections.command.SelectionsCommand;
import com.davixdevelop.worldeditselections.logger.JavaLogger;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.extension.platform.CommandManager;
import com.sk89q.worldedit.util.command.Dispatcher;
import org.bukkit.plugin.java.JavaPlugin;

public final class WorldEditSelectionsSpigot extends JavaPlugin {

    @Override
    public void onLoad() {
        WorldEditSelections.init(new WorldEditSelections(), new JavaLogger(getLogger()));
        WorldEditSelections.getLogger().sendDebug("Loading WorldEditSelections Plugin");
        CommandManager commandManager= WorldEdit.getInstance().getPlatformManager().getCommandManager();
        Dispatcher dispatcher = commandManager.getDispatcher();
        dispatcher.registerCommand(new SelectionsCommand(true), "/selection");
    }

    @Override
    public void onEnable() {

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
