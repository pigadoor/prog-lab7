package com.pigadoor.commands;

import com.pigadoor.application.CollectionManager;
import com.pigadoor.data.SpaceMarine;
import com.sun.source.tree.Tree;

import java.util.Map;
import java.util.TreeMap;

/**
 * The {@code ShowCommand} class represents a command to display all elements of the collection.
 */
public class ShowCommand extends Command {

    /**
     * The {@link CollectionManager} instance responsible for managing the collection.
     */
    private final CollectionManager collectionManager;

    /**
     * Constructs a {@code ShowCommand} with the specified {@link CollectionManager}.
     *
     * @param collectionManager The {@link CollectionManager} responsible for managing the collection.
     */
    public ShowCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    /**
     * Executes the show command by displaying all elements of the collection.
     *
     * @return A string representation of all elements in the collection.
     */
    public String execute() {
        if (collectionManager.getCurrentUser() != null) {
            TreeMap<Integer, SpaceMarine> collection = this.collectionManager.getCollection();
            StringBuilder result = new StringBuilder();
            int counter = 0;
            for (Map.Entry<Integer, SpaceMarine> entry : collection.entrySet()) {
                result.append(entry.getKey()).append(" : ").append(entry.getValue()).append("\n");
                counter++;
                if (counter == 50) break;
            }
            return result.toString();
        } else {
            return "You need to login before using this command.\n";
        }
    }

}
