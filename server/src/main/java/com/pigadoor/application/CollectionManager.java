package com.pigadoor.application;

import com.pigadoor.data.SpaceMarine;

import javax.xml.crypto.Data;
import java.time.LocalDate;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;
/**
 * Manages the collection of SpaceMarine objects.
 */
public class CollectionManager {

    /**
     * The collection of SpaceMarine objects stored in a TreeMap with keys as IDs.
     */
    private TreeMap<Integer, SpaceMarine> collection = new TreeMap<>();

    /**
     * The creation date of the collection.
     */
    private final java.time.LocalDate creationDate;


    /**
     * The singleton instance of CollectionManager.
     */
    private static CollectionManager INSTANCE;

    private String currentUser;

    /**
     * Private constructor for CollectionManager. Initializes the collection from a specified filepath.
     *
     * @param databaseManager object for working with database for storing collection
     */
    private CollectionManager(DatabaseManager databaseManager) {
        this.collection = databaseManager.downloadCollection();
        System.out.println("Collection downloaded. Items number: " + this.collection.size());
        this.creationDate = LocalDate.now();
    }

    /**
     * Returns the singleton instance of CollectionManager, creating it if necessary.
     *
     * @param databaseManager object for working with database for storing collection
     * @return The CollectionManager instance.
     */
    public static CollectionManager getInstance(DatabaseManager databaseManager) {
        if (databaseManager == null) {
            System.err.println("Cannot load collection.");
            System.exit(1);
        }
        return Objects.requireNonNullElseGet(INSTANCE, () -> new CollectionManager(databaseManager));
    }

    /**
     * Gets the creation date of the collection.
     *
     * @return The creation date.
     */
    public LocalDate getCreationDate() {
        return creationDate;
    }

    /**
     * Gets the maximum ID in the collection.
     *
     * @return The maximum ID.
     */
    public int getMaxId() {
        int maxID = 0;
        for (Map.Entry<Integer, SpaceMarine> treemapEntry : collection.entrySet()) {
            SpaceMarine spacemarine = treemapEntry.getValue();
            if (spacemarine.getId() > maxID) {
                maxID = spacemarine.getId();
            }
        }
        return maxID;
    }

    /**
     * Gets the collection of SpaceMarine objects.
     *
     * @return The collection.
     */
    public synchronized TreeMap<Integer, SpaceMarine> getCollection() {
        return collection;
    }

    public void setCollection(TreeMap<Integer, SpaceMarine> collection) {
        this.collection = collection;
    }

    public String getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(String currentUser) {
        this.currentUser = currentUser;
    }
}
