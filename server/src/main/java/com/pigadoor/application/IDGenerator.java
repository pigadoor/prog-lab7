package com.pigadoor.application;

import java.net.InetSocketAddress;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Generates and manages unique IDs.
 */
public class IDGenerator {

    private static final Set<Integer> IDs = new LinkedHashSet<>();

    private IDGenerator() {}

    /**
     * Saves an ID to the set of used IDs.
     *
     * @param id The ID to save.
     */
    public static void saveId(Integer id) {
        IDs.add(id);
    }

    /**
     * Removes an ID from the set of used IDs.
     *
     * @param id The ID to remove.
     */
    public static void removeId(Integer id) {
        IDs.remove(id);
    }

    /**
     * Removes all stored IDs.
     */
    public static void removeAllIds() {
        IDs.clear();
    }

    /**
     * Checks if a given ID is unique (not in use).
     *
     * @param id The ID to check.
     * @return True if the ID is unique; false otherwise.
     */
    public static boolean checkIfIDUnique(Integer id) {
        return !IDs.contains(id);
    }

    /**
     * Generates a new unique ID that is not currently in use.
     *
     * @return A unique ID.
     */
    public static Integer generateID() {
        Integer currentId = 1;
        boolean flag = false;
        for (long id : IDs) {
            flag = true;
            if (IDs.contains(currentId)) {
                currentId++;
            } else {
                return currentId;
            }
        }
        if (flag) {
            return currentId + 1;
        } else {
            return currentId;
        }
    }

}