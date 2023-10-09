package com.pigadoor.commands;

import com.pigadoor.application.CollectionManager;
import com.pigadoor.data.SpaceMarine;
import com.sun.source.tree.Tree;

import java.util.TreeMap;

/**
 * The {@code UpdateCommand} class represents a command to update an element in the collection by its key.
 */
public class UpdateCommand extends Command {

    /**
     * The {@link CollectionManager} instance responsible for managing the collection.
     */
    private final CollectionManager collectionManager;

    /**
     * Constructs an {@code UpdateCommand} with the specified {@link CollectionManager}.
     *
     * @param collectionManager The {@link CollectionManager} responsible for managing the collection.
     */
    public UpdateCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    /**
     * Executes the update command by updating an element in the collection by its key.
     *
     * @param key          The key of the element to be updated.
     * @param newSpaceMarine The new {@link SpaceMarine} object to replace the existing one.
     * @return A message indicating whether the item has been updated or not.
     */
    public String execute(Integer key, SpaceMarine newSpaceMarine) {
        newSpaceMarine.setOwner(collectionManager.getCurrentUser());
        if (collectionManager.getCurrentUser() != null) {
            TreeMap<Integer, SpaceMarine> collection = this.collectionManager.getCollection();
            return collection.containsKey(key) && collection.get(key).getOwner().equals(
                    collectionManager.getCurrentUser()) ? collection.compute(key, (k, v) -> newSpaceMarine) != null ?
                    "Item has been updated.\n" : "Item hasn't been updated.\n" :
                    "Item hasn't been updated because given key is not contains in the collection.\n";
        } else {
            return "You need to login before using this command.\n";
        }
    }

}
