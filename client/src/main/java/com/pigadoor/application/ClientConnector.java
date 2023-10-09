package com.pigadoor.application;

import com.pigadoor.data.Chapter;
import com.pigadoor.data.Coordinates;
import com.pigadoor.data.MeleeWeapon;
import com.pigadoor.data.SpaceMarine;
import com.pigadoor.network.Request;
import com.pigadoor.network.Response;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;
import java.sql.SQLOutput;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * A class responsible for managing client-server communication and user input.
 */
public class ClientConnector {
    private DatagramSocket socket;
    private InetAddress address;
    private static List<String> scripts = new ArrayList<>();
    private static final Set<String> callStack = new LinkedHashSet<>();
    private static String user;
    private static String password;

    private byte[] buffer;

    /**
     * Runs the client application, handling user input and communication with the server.
     */
    public void run() {
        DatagramSocket socket = null;
        boolean isScript = false;
        boolean flag2 = false;
        Scanner scanner = new Scanner(System.in);
        InputManager inputManager = new InputManager();
        try {
            socket = new DatagramSocket();
            socket.setSoTimeout(50000);
            while (true) {
                boolean flag = true;
                // Wait for user input
                isScript = false;
                flag2 = false;
                System.out.print("Enter command: ");
                String command = scanner.nextLine();
                String[] args;
                String[] splitCommand = command.split(" ", 3);
                Request request = new Request();
                switch (splitCommand[0]) {
                    case "register":
                        while (true) {
                            System.out.println("Enter login: ");
                            String login = scanner.nextLine();
                            if (login.isBlank()) {
                                System.out.println("It cannot be empty. Try again.");
                                break;
                            }
                            System.out.println("Enter password: ");
                            String password = scanner.nextLine();
                            if (password.isBlank()) {
                                System.out.println("It cannot be empty. Try again.");
                                break;
                            }
                            System.out.println("Enter password again: ");
                            String password2 = scanner.nextLine();
                            System.out.println(password);
                            if (password.equals(password2)) {
                                System.out.println("login " + login +  " password " + password);
                                request.setCommand("register");
                                request.setLogin(login);
                                request.setPassword(Cryptographer.encrypt(password));
                                break;
                            } else {
                                System.out.println("Passwords are not the same. Try again.");
                            }
                        }
                        break;
                    case "login":
                        if (splitCommand.length < 3) {
                            System.out.println("Incorrect format of command entering. Use sth like [login user password]");
                            flag = false;
                        } else {
                            request.setCommand(splitCommand[0]);
                            request.setLogin(splitCommand[1]);
                            String pwd = Cryptographer.encrypt(splitCommand[2]);
                            request.setPassword(Cryptographer.encrypt(pwd));
                            user = splitCommand[1];
                            password = pwd;
                        }
                        break;
                    case "logout":
                        request.setCommand(splitCommand[0]);
                        user = null;
                        password = null;
                        break;
                    case "clear":
                        request.setCommand("clear");
                        break;
                    case "count_greater_than_height":
                        try {
                            request.setCommand("count_greater_than_height");
                            if (splitCommand.length == 1) throw new NumberFormatException();
                            Float height = Float.parseFloat(splitCommand[1]);
                            request.setHeight(height);
                        } catch (NumberFormatException numberFormatException) {
                            System.out.println("Incorrect arguments. Write help for getting list of commands" +
                                    " and descriptions. Not sent.");
                            flag = false;
                            continue;
                        }
                        break;
                    case "exit":
                        System.out.println("Finishing a program!\n");
                        request.setCommand("save");
                        String send = request.getCommand();
                        // Send request to server
                        byte[] requestData = send.getBytes();
                        DatagramPacket packet = new DatagramPacket(requestData, requestData.length, InetAddress.getByName("localhost"), 5930);
                        socket.send(packet);
                        System.exit(0);
                        break;
                    case "help":
                        request.setCommand("help");
                        break;
                    case "filter_greater_than_health":
                        try {
                            request.setCommand("filter_greater_than_health");
                            if (splitCommand.length == 1) throw new NumberFormatException();
                            Integer health = Integer.parseInt(splitCommand[1]);
                            request.setHealth(health);
                        } catch (NumberFormatException numberFormatException) {
                            System.out.println("Incorrect arguments. Write help for getting list of commands" +
                                    " and descriptions. Not sent.");
                            flag = false;
                            continue;
                        }
                        break;
                    case "info":
                        request.setCommand("info");
                        break;
                    case "insert":
                        if (user != null) {
                            try {
                                request.setCommand("insert");
                                request.setLogin(user);
                                request.setPassword(password);
                                if (splitCommand.length == 1) throw new NumberFormatException();
                                Integer key = Integer.parseInt(splitCommand[1]);
                                request.setKey(key);
                                SpaceMarine spaceMarine1 = inputManager.receiveSpaceMarine();
                                request.setSpaceMarine(serializeToCSV(spaceMarine1));
                                System.out.println(request);
                                System.out.println(user);
                            } catch (NumberFormatException numberFormatException) {
                                System.out.println("Incorrect arguments. Write help for getting list of commands" +
                                        " and descriptions. Not sent.");
                                flag = false;
                                continue;
                            }
                        } else {
                            System.out.println("You need to login before executing this command.");
                            continue;
                        }
                        break;
                    case "print_descending":
                        request.setCommand("print_descending");
                        break;
                    case "remove_greater":
                        if (user != null) {
                            SpaceMarine spaceMarine2 = inputManager.receiveSpaceMarine();
                            request.setSpaceMarine(serializeToCSV(spaceMarine2));
                        } else {
                            System.out.println("You need to login before executing this command.");
                            continue;
                        }
                        break;
                    case "remove_key":
                        try {
                            request.setCommand("remove_key");
                            if (splitCommand.length == 1) throw new NumberFormatException();
                            Integer key = Integer.parseInt(splitCommand[1]);
                            request.setKey(key);
                        } catch (NumberFormatException numberFormatException) {
                            System.out.println("Incorrect arguments. Write help for getting list of commands" +
                                    " and descriptions. Not sent.");
                            flag = false;
                            continue;
                        }
                        break;
                    case "remove_lower_key":
                        try {
                            request.setCommand("remove_lower_key");
                            if (splitCommand.length == 1) throw new NumberFormatException();
                            Integer key = Integer.parseInt(splitCommand[1]);
                            request.setKey(key);
                        } catch (NumberFormatException numberFormatException) {
                            System.out.println("Incorrect arguments. Write help for getting list of commands" +
                                    " and descriptions. Not sent.");
                            flag = false;
                            continue;
                        }
                        break;
                    case "save":
                        System.out.println("Auto-save is enabled. Don't worry!\n");
                        break;
                    case "show":
                        request.setCommand("show");
                        break;
                    case "history":
                        request.setCommand("history");
                        break;
                    case "update":
                        if (user != null) {
                            try {
                                request.setCommand("update");
                                if (splitCommand.length == 1) throw new NumberFormatException();
                                Integer key = Integer.parseInt(splitCommand[1]);
                                request.setKey(key);
                                SpaceMarine spaceMarine1 = inputManager.receiveSpaceMarine();
                                request.setSpaceMarine(serializeToCSV(spaceMarine1));
                            } catch (NumberFormatException numberFormatException) {
                                System.out.println("Incorrect arguments. Write help for getting list of commands" +
                                        " and descriptions. Not sent.");
                                flag = false;
                                continue;
                            }
                        } else {
                            System.out.println("You need to login before executing this command.");
                            continue;
                        }
                        break;
                    case "execute_script":
                        try {
                            request.setCommand("execute_script");
                            if (splitCommand.length == 1) throw new NumberFormatException();

                            if (!callStack.contains(splitCommand[1])) {
                                callStack.add(splitCommand[1]);

                                try {
                                    BufferedReader reader = new BufferedReader(new FileReader(splitCommand[1]));
                                    String command2;
                                    flag2 = true;
                                    while ((command2 = reader.readLine()) != null) {
                                        request.setScript(command2);

                                        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
                                        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteStream);
                                        if (user != null && password != null) {
                                            request.setLogin(user);
                                            request.setPassword(password);
                                        }
                                        objectOutputStream.writeObject(request);

                                        byte[] requestData2 = byteStream.toByteArray();
                                        DatagramPacket packet2 = new DatagramPacket(requestData2, requestData2.length, InetAddress.getByName("localhost"), 5930);
                                        socket.send(packet2);
                                        System.out.println("sent2");

                                        try {
                                            byte[] responseData = new byte[65536];
                                            packet = new DatagramPacket(responseData, responseData.length);
                                            socket.receive(packet);
                                            String message = new String(packet.getData(), 0, packet.getLength());

                                            System.out.println("Response:\n " + message);
                                            System.out.println();
                                        } catch (SocketTimeoutException socketTimeoutException) {
                                            System.out.println("Server is not available at the moment. Try to send command again.");
                                        }

                                    }
                                } catch (FileNotFoundException fileNotFoundException) {
                                    System.out.println("File with script not found. Check path to script and try again.");
                                } catch (IOException ioException) {
                                    System.out.println("File reading problems. Try to check file permissions or syntax and try again.");
                                }
                                callStack.remove(splitCommand[1]);
                            } else {
                                System.out.println("Ring recursion detected. Script executing aborted.");
                            }
                            request.setScript(splitCommand[1]);
                        } catch (NumberFormatException numberFormatException) {
                            System.out.println("Incorrect arguments. Write help for getting list of commands" +
                                    " and descriptions. Not sent.");
                            continue;
                        }
                        break;
                    default:
                        System.out.println("in default. Request command: " + request.getCommand());
                        request.setCommand("unknown");
                        break;

                }
                //String send = request.getCommand() + "=" + (request.getSpaceMarine() != null ? serializeToCSV(request.getSpaceMarine()) : "null") + "=" + request.getKey() + "=" + request.getHealth() + "=" + request.getHeight() + "=" + request.getScript();
                // Send request to server
                //byte[] requestData = send.getBytes();
                if (flag && !flag2) {
                    ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
                    ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteStream);
                    if (user != null && password != null) {
                        request.setLogin(user);
                        request.setPassword(password);
                    }
                    objectOutputStream.writeObject(request);

                    byte[] requestData = byteStream.toByteArray();
                    DatagramPacket packet = new DatagramPacket(requestData, requestData.length, InetAddress.getByName("localhost"), 5930);
                    socket.send(packet);
                    System.out.println("sent");
                    // Receive response from server
                    try {
                        byte[] responseData = new byte[65536];
                        packet = new DatagramPacket(responseData, responseData.length);
                        socket.receive(packet);
                        String message = new String(packet.getData(), 0, packet.getLength());
                        System.out.println("Response:\n " + message);
                        System.out.println();
                    } catch (SocketTimeoutException socketTimeoutException) {
                        System.out.println("Server is not available at the moment. Try to send command again.");
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (socket != null) {
                socket.close();
            }
            scanner.close();
        }
    }

    /**
     * Serializes a SpaceMarine object to a CSV string.
     *
     * @param spaceMarine The SpaceMarine object to serialize.
     * @return A CSV representation of the SpaceMarine object.
     */
    public String serializeToCSV(SpaceMarine spaceMarine) {
        List<String> fields = Arrays.asList(
                String.valueOf(spaceMarine.getId()),
                spaceMarine.getName(),
                String.valueOf(spaceMarine.getCoordinates().getX()),
                String.valueOf(spaceMarine.getCoordinates().getY()),
                spaceMarine.getCreationDate().toString(),
                String.valueOf(spaceMarine.getHealth()),
                String.valueOf(spaceMarine.getHeartCount()),
                String.valueOf(spaceMarine.getHeight()),
                spaceMarine.getMeleeWeapon().toString(),
                String.valueOf(spaceMarine.getChapter().getName()),
                String.valueOf(spaceMarine.getChapter().getParentLegion())

        );
        return fields.stream()
                .map(field -> field.replace(",", "\\,"))
                .collect(Collectors.joining(","));
    }


    // Метод для преобразования CSV-строки в объект SpaceMarine
    /**
     * Deserializes a CSV string to a SpaceMarine object.
     *
     * @param csv The CSV string to deserialize.
     * @return A SpaceMarine object deserialized from the CSV string.
     */
    public static SpaceMarine deserializeFromCSV(String csv) {
        String[] fields = csv.split(",");
        int id = Integer.parseInt(fields[0]);
        String name = fields[1];
        double x = Double.parseDouble(fields[2]);
        float y = Float.parseFloat(fields[3]);
        Coordinates coordinates = new Coordinates(x, y);
        java.time.LocalDateTime creationDate = LocalDateTime.now();
        int health = Integer.parseInt(fields[4]);
        long heartCount = Long.parseLong(fields[5]);
        float height = Float.parseFloat(fields[6]);
        MeleeWeapon meleeWeapon = MeleeWeapon.valueOf(fields[7]);
        Chapter chapter = null;
        if (!fields[8].isEmpty()) {
            String chapterName = fields[8].replace("\\,", ",");
            chapter.setName(chapterName);
        }
        if (!fields[9].isEmpty()) {
            String parentLegion = fields[9].replace("\\,", ",");
            chapter.setParentLegion(parentLegion);
        }
        SpaceMarine spaceMarine = new SpaceMarine();
        spaceMarine.setId(id);
        spaceMarine.setName(name);
        spaceMarine.setCoordinates(coordinates);
        spaceMarine.setCreationDate(creationDate);
        spaceMarine.setHealth(health);
        spaceMarine.setHeartCount(heartCount);
        spaceMarine.setHeight(height);
        spaceMarine.setMeleeWeapon(meleeWeapon);
        spaceMarine.setChapter(chapter);

        return spaceMarine;
    }

}
