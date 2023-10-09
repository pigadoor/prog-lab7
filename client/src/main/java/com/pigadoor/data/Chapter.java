package com.pigadoor.data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

/**
 * Represents a chapter, with a name and an optional parent legion.
 */
public class Chapter implements Serializable {

    @Serial
    private static final long serialVersionUID = 2234567L;
    /**
     * The name of the chapter. Cannot be null and must not be an empty string.
     */
    private String name; //Поле не может быть null, Строка не может быть пустой

    /**
     * The parent legion of the chapter, which can be null.
     */
    private String parentLegion;

    /**
     * Default constructor for Chapter.
     */
    public Chapter() {}

    /**
     * Constructs a Chapter object with the specified name and parent legion.
     *
     * @param name         The name of the chapter. Cannot be null and must not be an empty string.
     * @param parentLegion The parent legion of the chapter, which can be null.
     */
    public Chapter(String name, String parentLegion) {
        this.name = name;
        this.parentLegion = parentLegion;
    }

    /**
     * Get the name of the chapter.
     *
     * @return The name of the chapter.
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name of the chapter.
     *
     * @param name The new name of the chapter. Cannot be null and must not be an empty string.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the parent legion of the chapter.
     *
     * @return The parent legion of the chapter, which can be null.
     */
    public String getParentLegion() {
        return parentLegion;
    }

    /**
     * Set the parent legion of the chapter.
     *
     * @param parentLegion The new parent legion of the chapter, which can be null.
     */
    public void setParentLegion(String parentLegion) {
        this.parentLegion = parentLegion;
    }

    /**
     * Compares this Chapter object to another object for equality.
     *
     * @param o The object to compare to.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Chapter chapter = (Chapter) o;
        return Objects.equals(name, chapter.name) && Objects.equals(parentLegion, chapter.parentLegion);
    }

    /**
     * Generate a hash code for this Chapter object.
     *
     * @return The hash code.
     */
    @Override
    public int hashCode() {
        return Objects.hash(name, parentLegion);
    }

    /**
     * Get a string representation of this Chapter object.
     *
     * @return A string representation in the format "Chapter{name='name', parentLegion='parentLegion'}".
     */
    @Override
    public String toString() {
        return "Chapter{" +
                "name='" + name + '\'' +
                ", parentLegion='" + parentLegion + '\'' +
                '}';
    }

}
