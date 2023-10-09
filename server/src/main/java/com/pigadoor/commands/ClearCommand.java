package com.pigadoor.commands;

import com.pigadoor.application.CollectionManager;
import com.pigadoor.data.SpaceMarine;

import java.util.Map;
import java.util.TreeMap;

/**
 * The {@code ClearCommand} class represents a command to clear all items from the collection.
 * It removes all elements from the collection managed by the {@link CollectionManager}.
 */
public class ClearCommand extends Command {

    /**
     * The {@link CollectionManager} instance responsible for managing the collection.
     */
    private final CollectionManager collectionManager;

    /**
     * Constructs a {@code ClearCommand} with the specified {@link CollectionManager}.
     *
     * @param collectionManager The {@link CollectionManager} responsible for managing the collection.
     */
    public ClearCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    /**
     * Executes the clear command by removing all items from the collection.
     *
     * @return A message indicating that all collection items have been removed.
     */
    public String execute() {
        if (collectionManager.getCurrentUser() != null) {
            TreeMap<Integer, SpaceMarine> collection = this.collectionManager.getCollection();
            TreeMap<Integer, SpaceMarine> copy = new TreeMap<>();
            for (Map.Entry<Integer, SpaceMarine> entry : collection.entrySet()) {
                if (!entry.getValue().getOwner().equals(collectionManager.getCurrentUser())) {
                    copy.put(entry.getKey(), entry.getValue());
                }
            }
            this.collectionManager.setCollection(copy);
            return "All collection items which are belongs to current user has been removed.\n";
        } else {
            return "You need to login before using this command.\n";
        }
    }

}
