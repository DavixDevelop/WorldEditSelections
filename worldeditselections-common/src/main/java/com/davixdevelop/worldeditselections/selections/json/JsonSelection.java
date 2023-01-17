package com.davixdevelop.worldeditselections.selections.json;

import com.davixdevelop.worldeditselections.selections.SelectionUtility;
import com.sk89q.worldedit.regions.Region;
import com.sk89q.worldedit.regions.RegionSelector;
import com.sk89q.worldedit.world.World;

public class JsonSelection implements IJsonSelection {
    SelectionUtility.SELECTION_TYPE selectionType;

    public JsonSelection(){

    }

    @Override
    public SelectionUtility.SELECTION_TYPE getSelectionType() {
        return selectionType;
    }

    @Override
    public void setSelectionType(SelectionUtility.SELECTION_TYPE selectionType) {
        this.selectionType = selectionType;
    }

    @Override
    public void fromRegion(Region region) {

    }

    @Override
    public RegionSelector getRegionSelector(World world) {
        return null;
    }
}
