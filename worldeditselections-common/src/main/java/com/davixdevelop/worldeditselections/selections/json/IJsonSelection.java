package com.davixdevelop.worldeditselections.selections.json;

import com.davixdevelop.worldeditselections.selections.SelectionUtility;
import com.sk89q.worldedit.regions.Region;
import com.sk89q.worldedit.regions.RegionSelector;
import com.sk89q.worldedit.world.World;

public interface IJsonSelection {
    SelectionUtility.SELECTION_TYPE getSelectionType();
    void setSelectionType(SelectionUtility.SELECTION_TYPE selectionType);
    void fromRegion(Region region);
    RegionSelector getRegionSelector(World world);
}
