package com.pigadoor.application;

import com.pigadoor.data.Chapter;
import com.pigadoor.data.Coordinates;
import com.pigadoor.data.MeleeWeapon;
import com.pigadoor.data.SpaceMarine;
import com.sun.source.tree.Tree;
import org.postgresql.util.PSQLException;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Properties;
import java.util.TreeMap;

/**
 * Manages the serialization and deserialization of the collection of SpaceMarine objects.
 */
public class DatabaseManager {

    //private final InputStream inputStream = getClassLoader().getResourceAsStream("server/helios_db.properties");
    //private Properties properties;
    private static Connection connection;

    public DatabaseManager() throws IOException {
        //InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("helios_db.properties");
        //Properties properties = System.getProperties();
        //properties.load(inputStream);
        while (true) {
            try {
                connection = DriverManager.getConnection(
                        (String) "jdbc:postgresql://pg:5432/studs",
                        (String) "s333420",
                        (String) "LH2oVz64DfnuoIDb"
                );
                System.out.println("Connection was established  successfully.");
                break;
            } catch (PSQLException psqlException) {
                System.out.println("Database is not available at the moment therefore SSH tunnel inactive. Reconnecting...");
            } catch (SQLException sqlException) {
                System.out.println("Database is not available at the moment. Reconnecting...");
            }
        }
    }

    public TreeMap<Integer, SpaceMarine> downloadCollection() {
        TreeMap<Integer, SpaceMarine> collection = new TreeMap<>();
        try (ResultSet answer = this.getConnection().createStatement().
                executeQuery("SELECT * FROM spacemarine")) {
            while (answer.next()) {
                int id = answer.getInt("id");
                String name = answer.getString("name");
                Coordinates coordinates = new Coordinates(answer.getDouble("x"), answer.getFloat("y"));
                LocalDateTime creationDate = LocalDateTime.parse(answer.getString("creation_date"),
                        DateTimeFormatter.ISO_LOCAL_DATE_TIME);
                int health = answer.getInt("health");
                long heartCount = answer.getLong("heart_count");
                float height = answer.getFloat("height");
                MeleeWeapon meleeWeapon = MeleeWeapon.valueOf(answer.getString("melee_weapon").toUpperCase());
                Chapter chapter = new Chapter(answer.getString("chapter_name"),
                        answer.getString("parent_legion"));
                String owner = answer.getString("owner");
                SpaceMarine spaceMarine = new SpaceMarine(id, name, coordinates, creationDate, health, heartCount,
                        height, meleeWeapon, chapter, owner);
                if (validateSpaceMarine(spaceMarine)) {
                    collection.put(spaceMarine.getId(), spaceMarine);
                }
            }
            return collection;
        } catch (SQLException e) {
            e.printStackTrace();
            return new TreeMap<>();
        }
    }

    public static boolean saveCollection(TreeMap<Integer, SpaceMarine> collection) {
        Collection<SpaceMarine> values = collection.values();
        try (Statement request = connection.createStatement()) {
            connection.setAutoCommit(false);
            request.addBatch("DELETE FROM spacemarine");
            for (SpaceMarine spaceMarine: values) {
                String sql = "INSERT INTO spacemarine (name, x, y, creation_date, health, heart_count, height, melee_weapon, chapter_name, parent_legion, owner) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, spaceMarine.getName());
                preparedStatement.setDouble(2, spaceMarine.getCoordinates().getX());
                preparedStatement.setFloat(3, spaceMarine.getCoordinates().getY());
                preparedStatement.setString(4, spaceMarine.getCreationDate().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
                preparedStatement.setInt(5, spaceMarine.getHealth());
                preparedStatement.setLong(6, spaceMarine.getHeartCount());
                preparedStatement.setFloat(7, spaceMarine.getHeight());
                preparedStatement.setString(8, spaceMarine.getMeleeWeapon().toString());
                preparedStatement.setString(9, spaceMarine.getChapter().getName());
                preparedStatement.setString(10, spaceMarine.getChapter().getParentLegion());
                preparedStatement.setString(11, spaceMarine.getOwner());
                request.addBatch(preparedStatement.toString());
            }
            request.executeBatch();
            connection.commit();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private static  boolean validateSpaceMarine(SpaceMarine spaceMarine) {
        if (!IDGenerator.checkIfIDUnique(spaceMarine.getId())) return false;
        if (spaceMarine.getId() <= 0) return false;
        if (spaceMarine.getName() == null) return false;
        if (spaceMarine.getName().isEmpty()) return false;
        if (spaceMarine.getCoordinates() == null) return false;
        if (spaceMarine.getCoordinates().getX() == null) return false;
        if (spaceMarine.getCoordinates().getY() == null) return false;
        if (spaceMarine.getCoordinates().getY() > 589) return false;
        if (spaceMarine.getCreationDate() == null) return false;
        if (spaceMarine.getHealth() <= 0) return false;
        if (spaceMarine.getHeartCount() == null) return false;
        if (spaceMarine.getHeartCount() <= 0) return false;
        if (spaceMarine.getHeartCount() > 3) return false;
        if (spaceMarine.getMeleeWeapon() == null) return false;
        if (spaceMarine.getChapter() != null) {
            if (spaceMarine.getChapter().getName() == null) {
                return false;
            }
            if (spaceMarine.getChapter().getName().isEmpty()) {
                return false;
            }
        }
        if (spaceMarine.getOwner() == null) return false;
        return true;
    }

    public static Connection getConnection() {
        return connection;
    }
}
