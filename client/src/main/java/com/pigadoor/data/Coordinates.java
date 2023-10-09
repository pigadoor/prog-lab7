package com.pigadoor.data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

/**
 * Represents a set of coordinates with x and y values.
 */
public class Coordinates implements Serializable {

    @Serial
    private static final long serialVersionUID = 3234567L;

    /**
     * The x-coordinate. Cannot be null.
     */
    private Double x; //Поле не может быть null

    /**
     * The y-coordinate. Must be less than or equal to 589 and cannot be null.
     */
    private Float y; //Максимальное значение поля: 589, Поле не может быть null

    /**
     * Default constructor for Coordinates.
     */
    public Coordinates() {}

    /**
     * Constructs a Coordinates object with the specified x and y values.
     *
     * @param x The x-coordinate. Cannot be null.
     * @param y The y-coordinate. Must be less than or equal to 589 and cannot be null.
     */
    public Coordinates(Double x, Float y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Get the x-coordinate.
     *
     * @return The x-coordinate.
     */
    public Double getX() {
        return x;
    }

    /**
     * Set the x-coordinate.
     *
     * @param x The new x-coordinate. Cannot be null.
     */
    public void setX(Double x) {
        this.x = x;
    }

    /**
     * Get the y-coordinate.
     *
     * @return The y-coordinate.
     */
    public Float getY() {
        return y;
    }

    /**
     * Set the y-coordinate.
     *
     * @param y The new y-coordinate. Must be less than or equal to 589 and cannot be null.
     */
    public void setY(Float y) {
        this.y = y;
    }

    /**
     * Compares this Coordinates object to another object for equality.
     *
     * @param o The object to compare to.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinates that = (Coordinates) o;
        return Objects.equals(x, that.x) && Objects.equals(y, that.y);
    }

    /**
     * Generate a hash code for this Coordinates object.
     *
     * @return The hash code.
     */
    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    /**
     * Get a string representation of this Coordinates object.
     *
     * @return A string representation in the format "Coordinates{x=x, y=y}".
     */
    @Override
    public String toString() {
        return "Coordinates{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

}
