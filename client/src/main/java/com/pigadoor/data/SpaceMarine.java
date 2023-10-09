package com.pigadoor.data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Represents a Space Marine character with various attributes.
 */
public class SpaceMarine implements Serializable {

    @Serial
    private static final long serialVersionUID = 4234567L;

    /**
     * The unique identifier for the Space Marine. Automatically generated and must be greater than 0.
     */
    private int id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически

    /**
     * The name of the Space Marine. Cannot be null and must not be an empty string.
     */
    private String name; //Поле не может быть null, Строка не может быть пустой

    /**
     * The coordinates of the Space Marine. Cannot be null.
     */
    private Coordinates coordinates; //Поле не может быть null

    /**
     * The creation date of the Space Marine. Automatically generated and cannot be null.
     */
    private LocalDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически

    /**
     * The health points of the Space Marine. Must be greater than 0.
     */
    private int health; //Значение поля должно быть больше 0

    /**
     * The count of hearts possessed by the Space Marine. Cannot be null, must be greater than 0, and has a maximum value of 3.
     */
    private Long heartCount; //Поле не может быть null, Значение поля должно быть больше 0, Максимальное значение поля: 3

    /**
     * The height of the Space Marine.
     */
    private float height;

    /**
     * The melee weapon of the Space Marine. Cannot be null.
     */
    private MeleeWeapon meleeWeapon; //Поле не может быть null

    /**
     * The chapter to which the Space Marine belongs. Can be null.
     */
    private Chapter chapter; //Поле может быть null

    /**
     * Default constructor for SpaceMarine.
     */
    public SpaceMarine() {}

    /**
     * Constructs a SpaceMarine object with the specified attributes.
     *
     * @param id           The unique identifier for the Space Marine. Automatically generated and must be greater than 0.
     * @param name         The name of the Space Marine. Cannot be null and must not be an empty string.
     * @param coordinates  The coordinates of the Space Marine. Cannot be null.
     * @param creationDate The creation date of the Space Marine. Automatically generated and cannot be null.
     * @param health       The health points of the Space Marine. Must be greater than 0.
     * @param heartCount   The count of hearts possessed by the Space Marine. Cannot be null, must be greater than 0, and has a maximum value of 3.
     * @param height       The height of the Space Marine.
     * @param meleeWeapon  The melee weapon of the Space Marine. Cannot be null.
     * @param chapter      The chapter to which the Space Marine belongs. Can be null.
     */
    public SpaceMarine(int id, String name, Coordinates coordinates, LocalDateTime creationDate, int health,
                       Long heartCount, float height, MeleeWeapon meleeWeapon, Chapter chapter) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.health = health;
        this.heartCount = heartCount;
        this.height = height;
        this.meleeWeapon = meleeWeapon;
        this.chapter = chapter;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public Long getHeartCount() {
        return heartCount;
    }

    public void setHeartCount(Long heartCount) {
        this.heartCount = heartCount;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public MeleeWeapon getMeleeWeapon() {
        return meleeWeapon;
    }

    public void setMeleeWeapon(MeleeWeapon meleeWeapon) {
        this.meleeWeapon = meleeWeapon;
    }

    public Chapter getChapter() {
        return chapter;
    }

    public void setChapter(Chapter chapter) {
        this.chapter = chapter;
    }

    /**
     * Compares this SpaceMarine object to another object for equality.
     *
     * @param o The object to compare to.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SpaceMarine that = (SpaceMarine) o;
        return id == that.id && health == that.health && Float.compare(that.height, height) == 0 &&
                Objects.equals(name, that.name) && Objects.equals(coordinates, that.coordinates) &&
                Objects.equals(creationDate, that.creationDate) && Objects.equals(heartCount, that.heartCount) &&
                meleeWeapon == that.meleeWeapon && Objects.equals(chapter, that.chapter);
    }

    /**
     * Generates a hash code for this SpaceMarine object.
     *
     * @return The hash code.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, name, coordinates, creationDate, health, heartCount, height, meleeWeapon, chapter);
    }

    /**
     * Returns a string representation of this SpaceMarine object.
     *
     * @return A string representation in the format "SpaceMarine{id=id, name='name', ...}".
     */
    @Override
    public String toString() {
        return "SpaceMarine{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", coordinates=" + coordinates +
                ", creationDate=" + creationDate +
                ", health=" + health +
                ", heartCount=" + heartCount +
                ", height=" + height +
                ", meleeWeapon=" + meleeWeapon +
                ", chapter=" + chapter +
                '}';
    }

}
