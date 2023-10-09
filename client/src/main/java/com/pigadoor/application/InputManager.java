package com.pigadoor.application;

import java.time.LocalDateTime;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Scanner;

import com.pigadoor.data.Chapter;
import com.pigadoor.data.Coordinates;
import com.pigadoor.data.MeleeWeapon;
import com.pigadoor.data.SpaceMarine;

/**
 * A class that manages user input for creating SpaceMarine objects.
 */
public class InputManager {

    /**
     * Receives and validates a name input from the user.
     *
     * @return The entered name as a string.
     */
    public String receiveName() {
        while (true) {
            try {
                Scanner scanner = new Scanner(System.in);
                System.out.print("Enter a name: ");
                String name = scanner.nextLine().trim();
                if (name.isEmpty()) {
                    System.out.println("This value cannot be empty. Try again");
                    continue;
                }
                return name;
            } catch (InputMismatchException inputMismatchException) {
                System.out.println("This value must be string. Try again.");
            } catch (NoSuchElementException noSuchElementException) {
                System.out.println("Program was stopped successfully.");
                System.exit(0);
            }
        }
    }

    /**
     * Receives and validates an X coordinate input from the user.
     *
     * @return The entered X coordinate as a double.
     */
    public Double receiveX() {
        while (true) {
            try {
                Scanner scanner = new Scanner(System.in);
                System.out.print("Enter X coordinate: ");
                return scanner.nextDouble();
            } catch (InputMismatchException inputMismatchException) {
                System.out.println("This value must be double. Try again.");
            } catch (NoSuchElementException noSuchElementException) {
                System.out.println("Program was stopped successfully.");
                System.exit(0);
            }
        }
    }

    /**
     * Receives and validates a Y coordinate input from the user.
     *
     * @return The entered Y coordinate as a float.
     */
    public Float receiveY() {
        while (true) {
            try {
                Scanner scanner = new Scanner(System.in);
                System.out.print("Enter Y coordinate: ");
                Float y = scanner.nextFloat();
                if (y > 589) {
                    System.out.println("Max value is 589. Try again.");
                    continue;
                }
                return y;
            } catch (InputMismatchException inputMismatchException) {
                System.out.println("This value must be float. Try again.");
            } catch (NoSuchElementException noSuchElementException) {
                System.out.println("Program was stopped successfully.");
                System.exit(0);
            }
        }
    }

    /**
     * Receives and constructs a Coordinates object from user input.
     *
     * @return A Coordinates object with valid X and Y values.
     */
    public Coordinates receiveCoordinates() {
        return new Coordinates(receiveX(), receiveY());
    }

    /**
     * Receives and validates a health input from the user.
     *
     * @return The entered health as an integer.
     */
    public int receiveHealth() {
        while (true) {
            try {
                Scanner scanner = new Scanner(System.in);
                System.out.print("Enter health: ");
                int health = scanner.nextInt();
                if (health <= 0) {
                    System.out.println("Value should be positive. Try again.");
                    continue;
                }
                return health;
            } catch (InputMismatchException inputMismatchException) {
                System.out.println("This value must be int. Try again.");
            } catch (NoSuchElementException noSuchElementException) {
                System.out.println("Program was stopped successfully.");
                System.exit(0);
            }
        }
    }

    /**
     * Receives and validates a heart count input from the user.
     *
     * @return The entered heart count as a long.
     */
    public Long receiveHeartCount() {
        while (true) {
            try {
                Scanner scanner = new Scanner(System.in);
                System.out.print("Enter heart count coordinate: ");
                Long heartCount = scanner.nextLong();
                if (heartCount <= 0 || heartCount > 3) {
                    System.out.println("Value should be from (0; 3] Try again.");
                    continue;
                }
                return heartCount;
            } catch (InputMismatchException inputMismatchException) {
                System.out.println("This value must be long. Try again.");
            } catch (NoSuchElementException noSuchElementException) {
                System.out.println("Program was stopped successfully.");
                System.exit(0);
            }
        }
    }

    /**
     * Receives and validates a height input from the user.
     *
     * @return The entered height as a float.
     */
    public Float receiveHeight() {
        while (true) {
            try {
                Scanner scanner = new Scanner(System.in);
                System.out.print("Enter height: ");
                return scanner.nextFloat();
            } catch (InputMismatchException inputMismatchException) {
                System.out.println("This value must be float. Try again.");
            } catch (NoSuchElementException noSuchElementException) {
                System.out.println("Program was stopped successfully.");
                System.exit(0);
            }
        }
    }

    /**
     * Receives and validates a melee weapon input from the user.
     *
     * @return The selected MeleeWeapon enum value.
     */
    public MeleeWeapon receiveMeleeWeapon() {
        while (true) {
            try {
                Scanner scanner = new Scanner(System.in);
                System.out.println("Variants:\n     CHAIN_SWORD,\n" +
                        "    POWER_SWORD,\n" +
                        "    MANREAPER,\n" +
                        "    LIGHTING_CLAW,\n" +
                        "    POWER_FIST;\n");
                System.out.print("Enter melee weapon: ");
                String enter = scanner.nextLine().trim().toUpperCase(Locale.ROOT);
                if (enter.equals("")) {
                    System.out.println("This value cannot be empty. Try again");
                    continue;
                }
                switch (enter) {
                    case "CHAIN_SWORD" -> {
                        return MeleeWeapon.CHAIN_SWORD;
                    }
                    case "POWER_SWORD" -> {
                        return MeleeWeapon.POWER_SWORD;
                    }
                    case "MANREAPER" -> {
                        return MeleeWeapon.MANREAPER;
                    }
                    case "LIGHTING_CLAW" -> {
                        return MeleeWeapon.LIGHTING_CLAW;
                    }
                    case "POWER_FIST" -> {
                        return MeleeWeapon.POWER_FIST;
                    }
                    default -> System.out.println("Incorrect enter. Value should be a string from list. Try again.");
                }
            } catch (InputMismatchException inputMismatchException) {
                System.out.println("Incorrect enter. Value should be a string from list. Try again.");
            } catch (NoSuchElementException noSuchElementException) {
                System.out.println("Program was stopped successfully.");
                System.exit(0);
            }
        }
    }

    /**
     * Receives and validates a chapter name input from the user.
     *
     * @return The entered chapter name as a string.
     */
    public String receiveChapterName() {
        while (true) {
            try {
                Scanner scanner = new Scanner(System.in);
                System.out.print("Enter a chapter name: ");
                String name = scanner.nextLine().trim();
                if (name.isEmpty()) {
                    System.out.println("This value cannot be empty. Try again");
                    continue;
                }
                return name;
            } catch (InputMismatchException inputMismatchException) {
                System.out.println("This value must be string. Try again.");
            } catch (NoSuchElementException noSuchElementException) {
                System.out.println("Program was stopped successfully.");
                System.exit(0);
            }
        }
    }

    /**
     * Receives and validates a parent legion input from the user.
     *
     * @return The entered parent legion as a string.
     */
    public String receiveParentLegion() {
        while (true) {
            try {
                Scanner scanner = new Scanner(System.in);
                System.out.print("Enter a parent legion: ");
                return scanner.nextLine().trim();
            } catch (InputMismatchException inputMismatchException) {
                System.out.println("This value must be string. Try again.");
            } catch (NoSuchElementException noSuchElementException) {
                System.out.println("Program was stopped successfully.");
                System.exit(0);
            }
        }
    }

    /**
     * Receives and constructs a Chapter object from user input.
     *
     * @return A Chapter object with valid name and parent legion values.
     */
    public Chapter receiveChapter() {
        return new Chapter(receiveChapterName(), receiveParentLegion());
    }

    /**
     * Receives and constructs a SpaceMarine object from user input.
     *
     * @return A SpaceMarine object with valid attributes.
     */
    public SpaceMarine receiveSpaceMarine() {
        return new SpaceMarine(0, receiveName(), receiveCoordinates(), LocalDateTime.now(), receiveHealth(),
                receiveHeartCount(), receiveHeight(), receiveMeleeWeapon(), receiveChapter());
    }

}
