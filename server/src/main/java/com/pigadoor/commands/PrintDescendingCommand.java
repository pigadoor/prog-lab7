package com.pigadoor.commands;

import com.pigadoor.application.CollectionManager;
import com.pigadoor.data.SpaceMarine;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * The {@code PrintDescendingCommand} class represents a command to print elements in descending order of their keys.
 */
public class PrintDescendingCommand extends Command {

    /**
     * The {@link CollectionManager} instance responsible for managing the collection.
     */
    private CollectionManager collectionManager;

    /**
     * Constructs a {@code PrintDescendingCommand} with the specified {@link CollectionManager}.
     *
     * @param collectionManager The {@link CollectionManager} responsible for managing the collection.
     */
    public PrintDescendingCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    /**
     * Executes the print command by printing elements in descending order of their keys.
     *
     * @return A string representation of the elements in descending order.
     */
    public String execute() {
        if (collectionManager.getCurrentUser() != null) {
            TreeMap<Integer, SpaceMarine> collection = this.collectionManager.getCollection();
            List<SpaceMarine> result = collection.entrySet()
                    .stream()
                    .sorted(Map.Entry.comparingByKey(Comparator.reverseOrder()))
                    .map(Map.Entry::getValue)
                    .toList();
            return result.toString();
        } else {
            return "You need to login before using this command.\n";
        }
    }

}
