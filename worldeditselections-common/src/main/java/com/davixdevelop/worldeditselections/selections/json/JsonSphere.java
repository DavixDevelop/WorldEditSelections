package com.davixdevelop.worldeditselections.selections.json;

import com.davixdevelop.worldeditselections.selections.SelectionUtility;
import com.sk89q.worldedit.regions.RegionSelector;
import com.sk89q.worldedit.regions.selector.SphereRegionSelector;
import com.sk89q.worldedit.world.World;

public class JsonSphere extends JsonEllipsoid{
    public JsonSphere(){
        setSelectionType(SelectionUtility.SELECTION_TYPE.SPHERE);
    }

    @Override
    public RegionSelector getRegionSelector(World world) {
        return new SphereRegionSelector(world, SelectionUtility.toVector(getCenter()), (int) getRadius().getX());
    }
}
