package com.davixdevelop.worldeditselections.selections.json;

import com.davixdevelop.worldeditselections.selections.SelectionUtility;
import com.sk89q.worldedit.regions.RegionSelector;
import com.sk89q.worldedit.regions.selector.ExtendingCuboidRegionSelector;
import com.sk89q.worldedit.world.World;

public class JsonExtendCuboid extends JsonCuboid{
    public JsonExtendCuboid(){
        setSelectionType(SelectionUtility.SELECTION_TYPE.EXTEND);
    }

    @Override
    public RegionSelector getRegionSelector(World world) {
        return new ExtendingCuboidRegionSelector(world, SelectionUtility.toBlockVector(getPos1()), SelectionUtility.toBlockVector(getPos2()));
    }
}
