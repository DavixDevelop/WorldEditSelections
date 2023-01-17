package com.davixdevelop.worldeditselections.selections.json;

import com.davixdevelop.worldeditselections.selections.SelectionUtility;
import com.sk89q.worldedit.regions.CuboidRegion;
import com.sk89q.worldedit.regions.selector.CuboidRegionSelector;
import com.sk89q.worldedit.regions.Region;
import com.sk89q.worldedit.regions.RegionSelector;
import com.sk89q.worldedit.world.World;

public class JsonCuboid extends JsonSelection {
    private JsonBlockVector pos1;
    private JsonBlockVector pos2;

    public JsonCuboid(){
        setSelectionType(SelectionUtility.SELECTION_TYPE.CUBOID);
    }

    @Override
    public void fromRegion(Region region) {
        CuboidRegion cuboidRegion = (CuboidRegion) region;
        pos1 = SelectionUtility.fromBlockVector(cuboidRegion.getPos1());
        pos2 = SelectionUtility.fromBlockVector(cuboidRegion.getPos2());
    }

    @Override
    public RegionSelector getRegionSelector(World world) {
        if(pos1 != null && pos2 != null)
            return new CuboidRegionSelector(world, SelectionUtility.toBlockVector(pos1), SelectionUtility.toBlockVector(pos2));
        return null;
    }

    public JsonBlockVector getPos1() {
        return pos1;
    }

    public JsonBlockVector getPos2() {
        return pos2;
    }
}
