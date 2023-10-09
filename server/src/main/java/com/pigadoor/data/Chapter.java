package com.pigadoor.data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

/**
 * The {@code Chapter} class represents a chapter associated with a Space Marine legion.
 */
public class Chapter implements Serializable {

    @Serial
    private static final long serialVersionUID = 2234567L;

    /**
     * The name of the chapter. It cannot be null, and the string cannot be empty.
     */
    private String name; //Поле не может быть null, Строка не может быть пустой

    /**
     * The name of the parent legion (if applicable).
     */
    private String parentLegion;

    /**
     * Constructs an empty {@code Chapter}.
     */
    public Chapter() {}

    /**
     * Constructs a {@code Chapter} with the specified name and parent legion.
     *
     * @param name         The name of the chapter.
     * @param parentLegion The name of the parent legion.
     */
    public Chapter(String name, String parentLegion) {
        this.name = name;
        this.parentLegion = parentLegion;
    }

    /**
     * Gets the name of the chapter.
     *
     * @return The name of the chapter.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the chapter.
     *
     * @param name The name of the chapter.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the name of the parent legion.
     *
     * @return The name of the parent legion.
     */
    public String getParentLegion() {
        return parentLegion;
    }

    /**
     * Sets the name of the parent legion.
     *
     * @param parentLegion The name of the parent legion.
     */
    public void setParentLegion(String parentLegion) {
        this.parentLegion = parentLegion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Chapter chapter = (Chapter) o;
        return Objects.equals(name, chapter.name) && Objects.equals(parentLegion, chapter.parentLegion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, parentLegion);
    }

    @Override
    public String toString() {
        return "Chapter{" +
                "name='" + name + '\'' +
                ", parentLegion='" + parentLegion + '\'' +
                '}';
    }

}
