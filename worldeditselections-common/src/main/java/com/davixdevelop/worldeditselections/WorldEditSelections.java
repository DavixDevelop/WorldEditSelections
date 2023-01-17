package com.davixdevelop.worldeditselections;

import com.davixdevelop.worldeditselections.logger.ILogger;
import com.davixdevelop.worldeditselections.selections.SelectionUtility;
import com.sk89q.worldedit.WorldEdit;

public class WorldEditSelections {
    static WorldEditSelections instance;
    static ILogger logger;

    SelectionUtility selectionUtility;

    public WorldEditSelections(){
        selectionUtility = new SelectionUtility();
    }

    public static WorldEditSelections getInstance(){
        return instance;
    }

    public static void init(WorldEditSelections instance, ILogger logger) {
        WorldEditSelections.instance = instance;
        WorldEditSelections.logger = logger;
    }

    public SelectionUtility getUtility() {
        return selectionUtility;
    }

    public static ILogger getLogger() {
        return logger;
    }
}
