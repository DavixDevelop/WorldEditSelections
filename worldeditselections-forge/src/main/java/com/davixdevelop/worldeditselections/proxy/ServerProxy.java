package com.davixdevelop.worldeditselections.proxy;

import com.davixdevelop.worldeditselections.WorldEditSelections;
import com.davixdevelop.worldeditselections.command.SelectionsCommand;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.extension.platform.CommandManager;
import com.sk89q.worldedit.util.command.Dispatcher;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Sided proxy used on servers
 *
 * @author DavixDevelop
 */
@SideOnly(value = Side.SERVER)
public class ServerProxy implements IProxy {
    public void postInit(FMLPostInitializationEvent event){
        WorldEditSelections.getLogger().sendDebug("Initializing WorldEditSelections for server");
        CommandManager commandManager = WorldEdit.getInstance().getPlatformManager().getCommandManager();
        Dispatcher dispatcher = commandManager.getDispatcher();
        dispatcher.registerCommand(new SelectionsCommand(true), "/selection");
    }
}
