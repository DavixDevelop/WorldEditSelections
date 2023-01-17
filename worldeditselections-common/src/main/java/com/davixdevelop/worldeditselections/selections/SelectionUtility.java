package com.davixdevelop.worldeditselections.selections;

import com.davixdevelop.worldeditselections.selections.json.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sk89q.minecraft.util.commands.CommandException;
import com.sk89q.worldedit.*;
import com.sk89q.worldedit.entity.Player;
import com.sk89q.worldedit.regions.*;
import com.sk89q.worldedit.regions.selector.ConvexPolyhedralRegionSelector;
import com.sk89q.worldedit.regions.selector.CuboidRegionSelector;
import com.sk89q.worldedit.regions.selector.CylinderRegionSelector;
import com.sk89q.worldedit.regions.selector.EllipsoidRegionSelector;
import com.sk89q.worldedit.regions.selector.ExtendingCuboidRegionSelector;
import com.sk89q.worldedit.regions.selector.Polygonal2DRegionSelector;
import com.sk89q.worldedit.regions.selector.SphereRegionSelector;
import com.sk89q.worldedit.session.SessionManager;

import java.io.*;
import java.nio.file.Files;

/**
 * A utility class for working with WorldEdit selections
 *
 * @author DavixDevelop
 */
public class SelectionUtility {
    Gson gson;

    public SelectionUtility(){
        gson = new GsonBuilder().setPrettyPrinting().create();
    }

    /**
     * Save a WorldEdit selection of a player to the WorldEdit/selections folder
     * @param selectionName The name of the selection
     * @param player The player from which to save the selection
     * @throws CommandException Throws an exception if user didn't make a selection
     */
    public void saveSelection(String selectionName, Player player) throws CommandException {
        try {
            SessionManager sessionManager = WorldEdit.getInstance().getSessionManager();
            LocalSession localSession = sessionManager.findByName(player.getName());
            if(localSession != null) {
                RegionSelector regionSelector = localSession.getRegionSelector(player.getWorld());
                Region region = localSession.getSelection(player.getWorld());
                JsonSelection jsonSelection = null;

                if(regionSelector instanceof ExtendingCuboidRegionSelector){
                    jsonSelection = new JsonExtendCuboid();
                    jsonSelection.fromRegion(region);
                }else if(regionSelector instanceof CuboidRegionSelector){
                    jsonSelection = new JsonCuboid();
                    jsonSelection.fromRegion(region);
                }
                else if(regionSelector instanceof Polygonal2DRegionSelector) {
                    jsonSelection = new JsonPolygon();
                    jsonSelection.fromRegion(region);
                }else if(regionSelector instanceof SphereRegionSelector){
                    jsonSelection = new JsonSphere();
                    jsonSelection.fromRegion(region);
                }
                else if(regionSelector instanceof EllipsoidRegionSelector){
                    jsonSelection = new JsonEllipsoid();
                    jsonSelection.fromRegion(region);
                }else if(regionSelector instanceof CylinderRegionSelector){
                    jsonSelection = new JsonCylinder();
                    jsonSelection.fromRegion(region);
                }else if(regionSelector instanceof ConvexPolyhedralRegionSelector){
                    throw new CommandException("Convex selections not supported");
                }

                if(jsonSelection != null){
                    File dir = WorldEdit.getInstance().getWorkingDirectoryFile("selections");
                    File selectionFile = WorldEdit.getInstance().getSafeSaveFile(player, dir , selectionName, "json");

                    String rawJson = gson.toJson(jsonSelection);

                    //Create parent folder if it does not exist yet
                    createParentFolders(selectionFile);

                    try {
                        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(Files.newOutputStream(selectionFile.toPath()));
                        PrintWriter writer = new PrintWriter(bufferedOutputStream);
                        writer.write(rawJson);
                        writer.close();

                        player.print("Selection saved to worldedit/selections");

                    }catch (Exception ex){
                        throw new CommandException(String.format("Could not save selection: \n %s", ex.getMessage()));
                    }
                }
            }else {
                throw new CommandException("No session found for player");
            }
        }catch (Exception ex){
            throw new CommandException(String.format("No selection made.\n Error: %s", ex));
        }
    }

    /**
     * Load a saved WorldEdit selection and set's it to the player
     * @param selectionName The name of the selection
     * @param player The player to which to set to the selection
     * @throws CommandException Throws and error if no session exists for the player or if any other exception occurred
     */
    public void loadSelection(String selectionName, Player player) throws CommandException{
        try {
            File dir = WorldEdit.getInstance().getWorkingDirectoryFile("selections");
            File selectionFile = WorldEdit.getInstance().getSafeSaveFile(player, dir , selectionName, "json");

            if(!selectionFile.exists()) {
                throw new CommandException(String.format("Selection with name: %s does not exist", selectionName));
            }

            InputStream inputStream = Files.newInputStream(selectionFile.toPath());
            Reader reader = new InputStreamReader(inputStream);

            JsonSelection jsonSelection = gson.fromJson(reader, JsonSelection.class);
            reader.close();

            RegionSelector regionSelector = null;

            SessionManager sessionManager = WorldEdit.getInstance().getSessionManager();
            LocalSession localSession = sessionManager.findByName(player.getName());

            if(localSession == null) {
                throw new CommandException("No session found for player");
            }

            inputStream = Files.newInputStream(selectionFile.toPath());
            reader = new InputStreamReader(inputStream);

            switch (jsonSelection.getSelectionType()){
                case CUBOID:
                    JsonCuboid cuboid = gson.fromJson(reader, JsonCuboid.class);
                    regionSelector = cuboid.getRegionSelector(player.getWorld());
                    break;
                case EXTEND:
                    JsonExtendCuboid extendCuboid = gson.fromJson(reader, JsonExtendCuboid.class);
                    regionSelector = extendCuboid.getRegionSelector(player.getWorld());
                    break;
                case POLYGON:
                    JsonPolygon polygon = gson.fromJson(reader, JsonPolygon.class);
                    regionSelector = polygon.getRegionSelector(player.getWorld());
                    break;
                case ELLIPSOID:
                    JsonEllipsoid ellipsoid = gson.fromJson(reader, JsonEllipsoid.class);
                    regionSelector = ellipsoid.getRegionSelector(player.getWorld());
                    break;
                case SPHERE:
                    JsonSphere sphere = gson.fromJson(reader, JsonSphere.class);
                    regionSelector = sphere.getRegionSelector(player.getWorld());
                    break;
                case CYLINDER:
                    JsonCylinder cylinder = gson.fromJson(reader, JsonCylinder.class);
                    regionSelector = cylinder.getRegionSelector(player.getWorld());
                    break;
            }

            reader.close();

            if(regionSelector != null){
                    localSession.setRegionSelector(player.getWorld(), regionSelector);
                    localSession.describeCUI(player);
                    player.print("Selection set");
            }

        }catch (Exception ex){
            throw new CommandException(ex.getMessage());
        }
    }

    public static void createParentFolders(File file) throws CommandException{
        //Create parent folder if it does not exist yet
        File parentFolder = file.getParentFile();
        if(parentFolder != null && !parentFolder.exists()){
            if(!parentFolder.mkdirs()){
                throw new CommandException("Could not create selections folder in WorldEdit config folder");
            }
        }
    }

    public static BlockVector toBlockVector(JsonBlockVector jsonBlockVector){
        return new BlockVector(jsonBlockVector.getX(), jsonBlockVector.getY(), jsonBlockVector.getZ());
    }
    public static Vector toVector(JsonVector vector){
        return new Vector(vector.getX(), vector.getY(), vector.getZ());
    }

    public static JsonVector fromVector(Vector vector){
        return new JsonVector(vector.getX(), vector.getY(), vector.getZ());
    }

    public static JsonVector fromVector2D(Vector2D vector2D){
        return new JsonVector(vector2D.getX(), 0.0, vector2D.getZ());
    }

    public static JsonBlockVector fromBlockVector(Vector vector){
        return new JsonBlockVector(vector.getBlockX(), vector.getBlockY(), vector.getBlockZ());
    }

    public enum SELECTION_TYPE {
        CUBOID,
        EXTEND,
        POLYGON,
        ELLIPSOID,
        SPHERE,
        CYLINDER
    }
}
