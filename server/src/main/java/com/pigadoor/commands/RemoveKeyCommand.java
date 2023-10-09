package com.pigadoor.commands;

import com.pigadoor.application.CollectionManager;
import com.pigadoor.application.IDGenerator;
import com.pigadoor.data.SpaceMarine;

import java.util.TreeMap;

/**
 * The {@code RemoveKeyCommand} class represents a command to remove an element from the collection by its key.
 */
public class RemoveKeyCommand extends Command {

    /**
     * The {@link CollectionManager} instance responsible for managing the collection.
     */
    private final CollectionManager collectionManager;

    /**
     * Constructs a {@code RemoveKeyCommand} with the specified {@link CollectionManager}.
     *
     * @param collectionManager The {@link CollectionManager} responsible for managing the collection.
     */
    public RemoveKeyCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    /**
     * Executes the remove command by removing an element from the collection by its key.
     *
     * @param key The key of the element to be removed.
     * @return A message indicating whether the item has been removed or not.
     */
    public String execute(Integer key) {
        if (collectionManager.getCurrentUser() != null) {
            TreeMap<Integer, SpaceMarine> collection = this.collectionManager.getCollection();
            boolean isRemoved = collection.entrySet().removeIf(entry -> {
                if (entry.getKey().equals(key) &&
                        entry.getValue().getOwner().equals(this.collectionManager.getCurrentUser())) {
                    IDGenerator.removeId(entry.getValue().getId());
                    return true;
                } else {
                    return false;
                }
            });
            return isRemoved ? "Item has been removed from collection.\n" :
                    "Item has not been removed from collection because this ID is not exists.\n";
        } else {
            return "You need to login before using this command.\n";
        }
    }
}
