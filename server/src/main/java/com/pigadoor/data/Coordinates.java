package com.pigadoor.data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

/**
 * The {@code Coordinates} class represents the coordinates of a location.
 */
public class Coordinates implements Serializable {

    @Serial
    private static final long serialVersionUID = 3234567L;

    /**
     * The x-coordinate. It cannot be null.
     */
    private Double x; //Поле не может быть null

    /**
     * The y-coordinate. It cannot be null and must be within the range [-Float.MAX_VALUE, 589].
     */
    private Float y; //Максимальное значение поля: 589, Поле не может быть null

    /**
     * Constructs an empty {@code Coordinates}.
     */
    public Coordinates() {}

    /**
     * Constructs {@code Coordinates} with the specified x and y coordinates.
     *
     * @param x The x-coordinate.
     * @param y The y-coordinate.
     */
    public Coordinates(Double x, Float y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Gets the x-coordinate.
     *
     * @return The x-coordinate.
     */
    public Double getX() {
        return x;
    }

    /**
     * Sets the x-coordinate.
     *
     * @param x The x-coordinate.
     */
    public void setX(Double x) {
        this.x = x;
    }

    /**
     * Gets the y-coordinate.
     *
     * @return The y-coordinate.
     */
    public Float getY() {
        return y;
    }

    /**
     * Sets the y-coordinate.
     *
     * @param y The y-coordinate.
     */
    public void setY(Float y) {
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinates that = (Coordinates) o;
        return Objects.equals(x, that.x) && Objects.equals(y, that.y);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "Coordinates{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

}
