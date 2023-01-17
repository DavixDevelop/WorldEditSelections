package com.davixdevelop.worldeditselections;

import com.davixdevelop.worldeditselections.logger.Log4jLogger;
import com.davixdevelop.worldeditselections.logger.ILogger;
import com.davixdevelop.worldeditselections.proxy.IProxy;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * Main class for the WorldEditSelections Forge mod
 *
 * @author DavixDevelop
 */
@Mod(
        modid = WorldEditSelectionsForge.MOD_ID,
        name = WorldEditSelectionsForge.MOD_NAME,
        version = WorldEditSelectionsForge.VERSION,
        dependencies = WorldEditSelectionsForge.WORLD_EDIT
)
public class WorldEditSelectionsForge {

    public static final String MOD_ID = "worldeditselections-forge";
    public static final String MOD_NAME = "WorldEditSelections";
    public static final String VERSION = "1.0";

    public static final String WORLD_EDIT = "required-after:worldedit;";

    private static ILogger logger;
    public static ILogger getLogger() {return logger;}

    @SidedProxy(clientSide = "com.davixdevelop.worldeditselections.proxy.ClientProxy", serverSide = "com.davixdevelop.worldeditselections.proxy.ServerProxy")
    public static IProxy proxy;
    @Mod.Instance(value = MOD_ID)
    public static WorldEditSelectionsForge instance;

    public static WorldEditSelectionsForge getInstance() {
        return instance;
    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        logger = new Log4jLogger(event.getModLog());
        WorldEditSelections.init(logger);
    }

    @Mod.EventHandler
    public void postinit(FMLPostInitializationEvent event) {
        proxy.postInit(event);
    }
}
