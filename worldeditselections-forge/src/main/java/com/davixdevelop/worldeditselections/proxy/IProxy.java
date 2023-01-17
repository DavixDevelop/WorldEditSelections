package com.davixdevelop.worldeditselections.proxy;

import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;

/**
 * Interface for a sided proxy
 *
 * @author DavixDevelop
 */
public interface IProxy {
    void postInit(FMLPostInitializationEvent event);
}
