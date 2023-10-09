package com.pigadoor.commands;

import com.pigadoor.application.CollectionManager;
import com.pigadoor.application.DatabaseManager;

/**
 * The {@code SaveCommand} class represents a command to save the collection to a file.
 */
public class SaveCommand extends Command {

    /**
     * The {@link CollectionManager} instance responsible for managing the collection.
     */
    private final CollectionManager collectionManager;

    /**
     * Constructs a {@code SaveCommand} with the specified {@link CollectionManager}.
     *
     * @param collectionManager The {@link CollectionManager} responsible for managing the collection.
     */
    public SaveCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    /**
     * Executes the save command by saving the collection to a file.
     *
     * @return A message indicating that the collection has been saved.
     */
    public String execute() {
        DatabaseManager.saveCollection(this.collectionManager.getCollection());
        System.out.println("Collection saved.");
        return "Collection saved.\n";
    }

}
