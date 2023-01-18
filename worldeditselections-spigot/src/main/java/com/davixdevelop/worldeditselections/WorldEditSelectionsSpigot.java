package com.davixdevelop.worldeditselections;

import com.davixdevelop.worldeditselections.command.SelectionsCommand;
import com.davixdevelop.worldeditselections.logger.JavaLogger;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.extension.platform.CommandManager;
import com.sk89q.worldedit.util.command.Dispatcher;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Main class for the WorldEditSelection Spigot plugin
 *
 * @author DavixDevelop
 */
public final class WorldEditSelectionsSpigot extends JavaPlugin {

    @Override
    public void onLoad() {
        WorldEditSelections.init(new JavaLogger(getLogger()));
        WorldEditSelections.getLogger().sendDebug("Loading WorldEditSelections Plugin");
        CommandManager commandManager= WorldEdit.getInstance().getPlatformManager().getCommandManager();
        Dispatcher dispatcher = commandManager.getDispatcher();
        dispatcher.registerCommand(new SelectionsCommand(true), "/selection");
    }
}
