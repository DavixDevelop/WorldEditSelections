package com.davixdevelop.worldeditselections.proxy;

import com.davixdevelop.worldeditselections.WorldEditSelectionsForge;
import com.davixdevelop.worldeditselections.command.SelectionsCommand;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.extension.platform.CommandManager;
import com.sk89q.worldedit.util.command.Dispatcher;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Sided proxy used on clients
 *
 * @author DavixDevelop
 */
@SideOnly(value = Side.CLIENT)
public class ClientProxy implements IProxy {
    @Override
    public void postInit(FMLPostInitializationEvent event) {
        WorldEditSelectionsForge.getLogger().sendDebug("Initializing WorldEditSelections for client ");
        CommandManager commandManager = WorldEdit.getInstance().getPlatformManager().getCommandManager();
        Dispatcher dispatcher = commandManager.getDispatcher();
        dispatcher.registerCommand(new SelectionsCommand(false), "/selection");
    }
}
