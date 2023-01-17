package com.davixdevelop.worldeditselections.selections.json;

import com.davixdevelop.worldeditselections.selections.SelectionUtility;
import com.sk89q.worldedit.Vector2D;
import com.sk89q.worldedit.regions.CylinderRegion;
import com.sk89q.worldedit.regions.Region;
import com.sk89q.worldedit.regions.RegionSelector;
import com.sk89q.worldedit.regions.selector.CylinderRegionSelector;
import com.sk89q.worldedit.world.World;

public class JsonCylinder extends JsonSelection {
    int minY;
    int maxY;
    JsonVector center;
    JsonVector radius;

    public JsonCylinder(){
        setSelectionType(SelectionUtility.SELECTION_TYPE.CYLINDER);
    }

    @Override
    public void fromRegion(Region region) {
        CylinderRegion cylinderRegion = (CylinderRegion) region;
        minY = cylinderRegion.getMinimumY();
        maxY = cylinderRegion.getMaximumY();
        center = SelectionUtility.fromVector(cylinderRegion.getCenter());
        radius = SelectionUtility.fromVector2D(cylinderRegion.getRadius());
    }

    @Override
    public RegionSelector getRegionSelector(World world) {
        return new CylinderRegionSelector(world,new Vector2D(center.getX(), center.getZ()),new Vector2D(radius.getX(), radius.getZ()),minY,maxY);
    }
}
