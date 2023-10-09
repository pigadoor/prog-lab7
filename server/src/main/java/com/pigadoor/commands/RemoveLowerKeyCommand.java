package com.pigadoor.commands;

import com.pigadoor.application.CollectionManager;
import com.pigadoor.application.IDGenerator;
import com.pigadoor.data.SpaceMarine;

import java.util.Map;
import java.util.TreeMap;

/**
 * The {@code RemoveLowerKeyCommand} class represents a command to remove elements from the collection whose keys
 * are lower than a specified key.
 */
public class RemoveLowerKeyCommand extends Command {

    /**
     * The {@link CollectionManager} instance responsible for managing the collection.
     */
    private final CollectionManager collectionManager;

    /**
     * Constructs a {@code RemoveLowerKeyCommand} with the specified {@link CollectionManager}.
     *
     * @param collectionManager The {@link CollectionManager} responsible for managing the collection.
     */
    public RemoveLowerKeyCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    /**
     * Executes the remove command by removing elements from the collection whose keys are lower than the specified key.
     *
     * @param key The key used for comparison.
     * @return A message indicating the result of the remove operation.
     */
    public String execute(Integer key) {
        if (collectionManager.getCurrentUser() != null) {
            TreeMap<Integer, SpaceMarine> collection = this.collectionManager.getCollection();
            int initialSize = collection.size();
            TreeMap<Integer, SpaceMarine> copy = new TreeMap<>();
            for (Map.Entry<Integer, SpaceMarine> entry : collection.entrySet()) {
                if (entry.getKey() < key && !entry.getValue().getOwner().equals(collectionManager.getCurrentUser())) {
                    copy.put(entry.getKey(), entry.getValue());
                    IDGenerator.removeId(entry.getValue().getId());
                }
            }
            this.collectionManager.setCollection(copy);
            return "Command executed. Removed " + (initialSize - collection.size()) + " items.\n";
        } else {
            return "You need to login before using this command.\n";
        }
    }

}
