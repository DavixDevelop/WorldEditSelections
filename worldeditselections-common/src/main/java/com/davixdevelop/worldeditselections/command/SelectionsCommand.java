package com.davixdevelop.worldeditselections.command;

import com.davixdevelop.worldeditselections.WorldEditSelections;
import com.sk89q.minecraft.util.commands.CommandException;
import com.sk89q.minecraft.util.commands.CommandLocals;
import com.sk89q.worldedit.entity.Player;
import com.sk89q.worldedit.extension.platform.Actor;
import com.sk89q.worldedit.util.auth.AuthorizationException;
import com.sk89q.worldedit.util.command.CommandCallable;
import com.sk89q.worldedit.util.command.Description;
import com.sk89q.worldedit.util.command.SimpleDescription;

import java.util.ArrayList;
import java.util.List;

/**
 * WorldEdit command to save/load WorldEdit selections
 *
 * @author DavixDevelop
 */
public class SelectionsCommand implements CommandCallable {

    boolean isRemote;

    /**
     * @return Returns true if command is run on a remote server
     */
    public boolean isRemote() {
        return isRemote;
    }

    public SelectionsCommand(boolean isRemote){
        this.isRemote = isRemote;
    }

    @Override
    public Object call(String arguments, CommandLocals locals, String[] parentCommands) throws CommandException {
        Actor player = locals.get(Actor.class);

        if(isRemote){
            if(!testPermission(locals))
                return false;
        }

        if(arguments.length() == 0){
            List<String> suggestions = getSuggestions(arguments, locals);
            player.print("Usage of command: ");
            for(String suggestion : suggestions)
                player.print(String.format("//selection %s" ,suggestion));

            return false;
        }

        String[] args = arguments.split(" ");

        if(args.length == 1)

            throw new CommandException("Specify selection name");

        if(args.length != 2)
            throw new CommandException("Invalid usage of command");

        String command = args[0];
        String selection_name = args[1];

        if(player instanceof Player) {

            try {
                switch (command){
                    case "save":
                        WorldEditSelections.getInstance().getUtility().saveSelection(selection_name, (Player) player);
                        break;
                    case "load":
                        WorldEditSelections.getInstance().getUtility().loadSelection(selection_name, (Player) player);
                        break;
                    default:
                        return new CommandException(String.format("Invalid command: %s. (Expected save/load)", command));
                }
            }catch (CommandException ex){
                WorldEditSelections.getLogger().sendError((isRemote ? String.format("[%s]: ", player.getName()) : "") + ex.getMessage());
                player.printError(ex.getMessage());
            }
        }

        return true;
    }

    @Override
    public Description getDescription() {
        SimpleDescription simpleDescription = new SimpleDescription();
        simpleDescription.setDescription("Save/load WorldEdit selections");
        return simpleDescription;
    }

    @Override
    public boolean testPermission(CommandLocals locals) {
        if(isRemote){
            Actor player = locals.get(Actor.class);
            try {
                player.checkPermission("worldedit.selections");
                return true;

            }catch (AuthorizationException ex){
                WorldEditSelections.getLogger().sendError(ex.getMessage());
                player.printError("You do not have the permission to run this command");
                return false;
            }
        }
        return true;
    }

    @Override
    public List<String> getSuggestions(String arguments, CommandLocals locals) {
        if(arguments.length() == 0) {
            List<String> suggestions = new ArrayList<>();
            suggestions.add("save <selection_name> (No white spaces)");
            suggestions.add("load <selection_name>");
            return suggestions;
        }

        return new ArrayList<>();
    }
}
