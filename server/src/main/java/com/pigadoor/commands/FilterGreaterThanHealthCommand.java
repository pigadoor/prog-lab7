package com.pigadoor.commands;

import com.pigadoor.application.CollectionManager;
import com.pigadoor.data.SpaceMarine;

import java.util.List;
import java.util.TreeMap;

/**
 * The {@code FilterGreaterThanHealthCommand} class represents a command to filter elements in the collection based on health.
 * It returns elements whose health is greater than the specified value.
 */
public class FilterGreaterThanHealthCommand extends Command {

    /**
     * The {@link CollectionManager} instance responsible for managing the collection.
     */
    private CollectionManager collectionManager;

    /**
     * Constructs a {@code FilterGreaterThanHealthCommand} with the specified {@link CollectionManager}.
     *
     * @param collectionManager The {@link CollectionManager} responsible for managing the collection.
     */
    public FilterGreaterThanHealthCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    /**
     * Executes the filter command by returning elements in the collection with health greater than the specified value.
     *
     * @param health The health value used for filtering.
     * @return A string representation of the filtered elements.
     */
    public String execute(int health) {
        if (collectionManager.getCurrentUser() != null) {
            TreeMap<Integer, SpaceMarine> collection = this.collectionManager.getCollection();
            List<SpaceMarine> result = collection.values()
                    .stream()
                    .filter(sm -> sm.getHealth() > health)
                    .toList();
            return result.toString();
        } else {
            return "You need to login before using this command.\n";
        }
    }

}
