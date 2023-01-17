package com.davixdevelop.worldeditselections;

import com.davixdevelop.worldeditselections.logger.ILogger;
import com.davixdevelop.worldeditselections.selections.SelectionUtility;


/**
 * The main class of the WorldEditSelections library
 *
 * @author DavixDevelop
 */
public class WorldEditSelections {
    static WorldEditSelections instance;
    static ILogger logger;

    SelectionUtility selectionUtility;

    public WorldEditSelections(){
        selectionUtility = new SelectionUtility();
    }

    /**
     * Get the static instance of the library
     * @return Static instance the WorldEditSelections library
     */
    public static WorldEditSelections getInstance(){
        return instance;
    }

    /**
     * Initializes the WorldEditSelections library. Must be called first, before trying to use the library
     * @param logger Simple implementation of a logger through which to send error/debug messages
     */
    public static void init(ILogger logger) {
        WorldEditSelections.instance = new WorldEditSelections();
        WorldEditSelections.logger = logger;
    }

    /**
     * Get the selection utility
     * @return Instance of the SelectionUtility
     */
    public SelectionUtility getUtility() {
        return selectionUtility;
    }

    /**
     * Return a simple implementation of a logger
     * @return Simple implementation of a logger through which to send error/debug messages
     */
    public static ILogger getLogger() {
        return logger;
    }
}
