package com.pigadoor.data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * The {@code SpaceMarine} class represents a Space Marine unit.
 */
public class SpaceMarine implements Serializable {

    @Serial
    private static final long serialVersionUID = 4234567L;

    /**
     * The unique identifier for the Space Marine. It should be greater than 0 and generated automatically.
     */
    private int id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически

    /**
     * The name of the Space Marine. It cannot be null, and the string cannot be empty.
     */
    private String name; //Поле не может быть null, Строка не может быть пустой

    /**
     * The coordinates of the Space Marine. It cannot be null.
     */
    private Coordinates coordinates; //Поле не может быть null

    /**
     * The creation date of the Space Marine. It cannot be null and is generated automatically.
     */
    private java.time.LocalDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически

    /**
     * The health of the Space Marine. It should be greater than 0.
     */
    private int health; //Значение поля должно быть больше 0

    /**
     * The number of hearts the Space Marine has. It cannot be null, should be greater than 0, and
     * should not exceed 3.
     */
    private Long heartCount; //Поле не может быть null, Значение поля должно быть больше 0, Максимальное значение поля: 3

    /**
     * The height of the Space Marine.
     */
    private float height;

    /**
     * The melee weapon carried by the Space Marine. It cannot be null.
     */
    private MeleeWeapon meleeWeapon; //Поле не может быть null

    /**
     * The chapter to which the Space Marine belongs. It can be null.
     */
    private Chapter chapter; //Поле может быть null

    private String owner;

    /**
     * Constructs an empty {@code SpaceMarine}.
     */
    public SpaceMarine() {}

    /**
     * Constructs a {@code SpaceMarine} with the specified attributes.
     *
     * @param id           The unique identifier.
     * @param name         The name of the Space Marine.
     * @param coordinates  The coordinates of the Space Marine.
     * @param creationDate The creation date.
     * @param health       The health value.
     * @param heartCount   The number of hearts.
     * @param height       The height.
     * @param meleeWeapon  The melee weapon.
     * @param chapter      The chapter.
     */
    public SpaceMarine(int id, String name, Coordinates coordinates, LocalDateTime creationDate, int health,
                       Long heartCount, float height, MeleeWeapon meleeWeapon, Chapter chapter, String owner) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.health = health;
        this.heartCount = heartCount;
        this.height = height;
        this.meleeWeapon = meleeWeapon;
        this.chapter = chapter;
        this.owner = owner;
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

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SpaceMarine that = (SpaceMarine) o;
        return id == that.id && health == that.health && Float.compare(that.height, height) == 0 &&
                Objects.equals(name, that.name) && Objects.equals(coordinates, that.coordinates) &&
                Objects.equals(creationDate, that.creationDate) && Objects.equals(heartCount, that.heartCount) &&
                meleeWeapon == that.meleeWeapon && Objects.equals(chapter, that.chapter) && Objects.equals(owner, that.owner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, coordinates, creationDate, health, heartCount, height, meleeWeapon, chapter, owner);
    }

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
                ", owner=" + owner +
                '}';
    }

}
