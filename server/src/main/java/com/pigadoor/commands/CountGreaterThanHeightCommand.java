package com.pigadoor.commands;

import com.pigadoor.application.CollectionManager;
import com.pigadoor.data.SpaceMarine;

import java.util.TreeMap;

/**
 * The {@code CountGreaterThanHeightCommand} class represents a command to count the number of elements in the collection
 * with health greater than a specified height.
 */
public class CountGreaterThanHeightCommand extends Command {

    /**
     * The {@link CollectionManager} instance responsible for managing the collection.
     */
    private final CollectionManager collectionManager;

    /**
     * Constructs a {@code CountGreaterThanHeightCommand} with the specified {@link CollectionManager}.
     *
     * @param collectionManager The {@link CollectionManager} responsible for managing the collection.
     */
    public CountGreaterThanHeightCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    /**
     * Executes the count command by counting the elements with health greater than the specified height.
     *
     * @param height The height value used for comparison.
     * @return A message indicating the result of the count operation.
     */
    public String execute(float height) {
        if (collectionManager.getCurrentUser() != null) {
            TreeMap<Integer, SpaceMarine> collection = this.collectionManager.getCollection();
            long number = collection.values()
                    .stream()
                    .filter(e -> e.getHealth() < height)
                    .count();
            return "Command executed. Found " + number + " items.\n";
        } else {
            return "You need to login before using this command.\n";
        }
    }

}
