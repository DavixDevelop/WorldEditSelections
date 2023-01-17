package com.davixdevelop.worldeditselections.selections.json;

import com.davixdevelop.worldeditselections.selections.SelectionUtility;
import com.sk89q.worldedit.regions.EllipsoidRegion;
import com.sk89q.worldedit.regions.Region;
import com.sk89q.worldedit.regions.RegionSelector;
import com.sk89q.worldedit.regions.selector.EllipsoidRegionSelector;
import com.sk89q.worldedit.world.World;

public class JsonEllipsoid extends JsonSelection {
    JsonVector center;
    JsonVector radius;

    public JsonVector getCenter() {
        return center;
    }

    public JsonVector getRadius() {
        return radius;
    }

    public JsonEllipsoid(){
        setSelectionType(SelectionUtility.SELECTION_TYPE.ELLIPSOID);
    }

    @Override
    public void fromRegion(Region region) {
        EllipsoidRegion ellipsoidRegion = (EllipsoidRegion) region;
        center = SelectionUtility.fromVector(ellipsoidRegion.getCenter());
        radius = SelectionUtility.fromVector(ellipsoidRegion.getRadius());
    }

    @Override
    public RegionSelector getRegionSelector(World world) {
        return new EllipsoidRegionSelector(world, SelectionUtility.toVector(center), SelectionUtility.toVector(radius));
    }
}
