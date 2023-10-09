package com.pigadoor.commands;

import com.pigadoor.application.CollectionManager;
import com.pigadoor.application.IDGenerator;
import com.pigadoor.data.SpaceMarine;

/**
 * The {@code InsertCommand} class represents a command to insert a new element into the collection.
 * It generates a unique ID for the element and adds it to the collection.
 */
public class InsertCommand extends Command {

    /**
     * The {@link CollectionManager} instance responsible for managing the collection.
     */
    private final CollectionManager collectionManager;

    /**
     * Constructs an {@code InsertCommand} with the specified {@link CollectionManager}.
     *
     * @param collectionManager The {@link CollectionManager} responsible for managing the collection.
     */
    public InsertCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    /**
     * Executes the insert command by adding a new element to the collection.
     *
     * @param key         The key associated with the new element.
     * @param spaceMarine The {@link SpaceMarine} object to be inserted.
     * @return A message indicating that the item has been added.
     */
    public String execute(Integer key, SpaceMarine spaceMarine) {
        System.out.println("here");
        if (collectionManager.getCurrentUser() != null) {
            spaceMarine.setId(IDGenerator.generateID());
            spaceMarine.setOwner(collectionManager.getCurrentUser());
            this.collectionManager.getCollection().put(IDGenerator.generateID(), spaceMarine);
            IDGenerator.saveId(spaceMarine.getId());
            return "Item has been added.\n";
        } else {
            return "You need to login before using this command.\n";
        }
    }


}
