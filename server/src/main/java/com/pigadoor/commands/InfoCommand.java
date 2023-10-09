package com.pigadoor.commands;

import com.pigadoor.application.CollectionManager;
import com.pigadoor.data.SpaceMarine;

import java.util.TreeMap;

/**
 * The {@code InfoCommand} class represents a command to retrieve information about the collection.
 * It provides details such as the collection type, number of items, and initialization date.
 */
public class InfoCommand extends Command {

    /**
     * The {@link CollectionManager} instance responsible for managing the collection.
     */
    private final CollectionManager collectionManager;

    /**
     * Constructs an {@code InfoCommand} with the specified {@link CollectionManager}.
     *
     * @param collectionManager The {@link CollectionManager} responsible for managing the collection.
     */
    public InfoCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    /**
     * Executes the info command and retrieves information about the collection.
     *
     * @return A string containing details about the collection.
     */
    public String execute() {
        if (collectionManager.getCurrentUser() != null) {
            return new StringBuilder()
                    .append("Type of collection: ")
                    .append(collectionManager.getCollection().getClass().getName()).append("\n")
                    .append("Number of items: ").append(collectionManager.getCollection().size()).append("\n")
                    .append("Initialization date: ").append(collectionManager.getCreationDate()).toString();
        } else {
            return "You need to login before using this command.\n";
        }
    }

}
