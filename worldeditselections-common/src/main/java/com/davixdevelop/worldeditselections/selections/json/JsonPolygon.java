package com.davixdevelop.worldeditselections.selections.json;

import com.davixdevelop.worldeditselections.selections.SelectionUtility;
import com.sk89q.worldedit.BlockVector2D;
import com.sk89q.worldedit.regions.Polygonal2DRegion;
import com.sk89q.worldedit.regions.Region;
import com.sk89q.worldedit.regions.RegionSelector;
import com.sk89q.worldedit.regions.selector.Polygonal2DRegionSelector;
import com.sk89q.worldedit.world.World;

import java.util.ArrayList;
import java.util.List;

public class JsonPolygon extends JsonSelection{
    int minY;
    int maxY;
    List<JsonBlockVector> points;

    public JsonPolygon(){
        setSelectionType(SelectionUtility.SELECTION_TYPE.POLYGON);
    }

    @Override
    public void fromRegion(Region region) {
        Polygonal2DRegion polygonal2DRegion = (Polygonal2DRegion) region;
        minY = polygonal2DRegion.getMinimumY();
        maxY = polygonal2DRegion.getMaximumY();
        List<BlockVector2D> polyPoints = polygonal2DRegion.getPoints();
        points = new ArrayList<>();

        for(BlockVector2D vector2D : polyPoints)
            points.add(SelectionUtility.fromBlockVector(vector2D.toVector()));
    }

    @Override
    public RegionSelector getRegionSelector(World world) {
        List<BlockVector2D> polyPoints = new ArrayList<>();
        for(JsonBlockVector vector : points)
            polyPoints.add(new BlockVector2D(vector.getX(), vector.getZ()));

        return new Polygonal2DRegionSelector(world, polyPoints, minY, maxY);
    }
}
