package com.pigadoor.commands;

import com.pigadoor.application.CollectionManager;
import com.pigadoor.application.IDGenerator;
import com.pigadoor.data.SpaceMarine;

import java.util.TreeMap;

/**
 * The {@code RemoveGreaterCommand} class represents a command to remove elements from the collection that have keys
 * greater than the key of a specified element.
 */
public class RemoveGreaterCommand extends Command {

    /**
     * The {@link CollectionManager} instance responsible for managing the collection.
     */
    private CollectionManager collectionManager;

    /**
     * Constructs a {@code RemoveGreaterCommand} with the specified {@link CollectionManager}.
     *
     * @param collectionManager The {@link CollectionManager} responsible for managing the collection.
     */
    public RemoveGreaterCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    /**
     * Executes the remove command by removing elements with keys greater than the key of the specified element.
     *
     * @param spaceMarine The {@link SpaceMarine} object used for comparison.
     * @return A message indicating the result of the remove operation.
     */
    public String execute(SpaceMarine spaceMarine) {
        spaceMarine.setOwner(collectionManager.getCurrentUser());
        if (collectionManager.getCurrentUser() != null) {
            TreeMap<Integer, SpaceMarine> collection = this.collectionManager.getCollection();
            int initialSize = collection.size();
            collection.entrySet().removeIf(entry -> {
                if (entry.getValue().getId() > spaceMarine.getId() &&
                        entry.getValue().getOwner().equals(this.collectionManager.getCurrentUser())) {
                    IDGenerator.removeId(entry.getValue().getId());
                    return true; // Удаляем элемент из коллекции
                }
                return false; // Не удаляем элемент из коллекции
            });
            return "Command executed. Removed: " + (initialSize - collection.size()) + " items.\n";
        } else {
            return "You need to login before using this command.\n";
        }
    }
}
