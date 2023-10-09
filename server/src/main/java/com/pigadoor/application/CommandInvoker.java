package com.pigadoor.application;

import com.pigadoor.commands.*;
import com.pigadoor.data.Chapter;
import com.pigadoor.data.Coordinates;
import com.pigadoor.data.MeleeWeapon;
import com.pigadoor.data.SpaceMarine;
import com.pigadoor.network.Request;

import java.io.*;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;

/**
 * Manages incoming requests and executes commands based on those requests.
 */
public class CommandInvoker {

    private final CollectionManager collectionManager;

    /**
     * Creates a CommandInvoker with a reference to the CollectionManager.
     *
     * @param collectionManager The CollectionManager.
     */
    public CommandInvoker(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    public void listenForRequests() throws IOException, ClassNotFoundException {
        DatagramChannel channel = DatagramChannel.open();
        channel.configureBlocking(false);
        DatagramSocket socket = channel.socket();
        socket.bind(new InetSocketAddress(5930));

        Selector selector = Selector.open();
        channel.register(selector, SelectionKey.OP_READ);

        Scanner scanner = new Scanner(System.in);
        LinkedList<String> history = new LinkedList<>();

        ForkJoinPool queryThreadPool = new ForkJoinPool(); // For multi-threaded reading of queries

        while (true) {
            try {
                if (System.in.available() > 0) {
                    String input = scanner.nextLine();
                    if (input.equals("save")) {
                        DatabaseManager.saveCollection(collectionManager.getCollection());
                        System.out.println("Collection saved.");
                    } else if (input.equals("exit")) {
                        System.out.println("Server will be disabled now.");
                        DatabaseManager.saveCollection(collectionManager.getCollection());
                        System.exit(0);
                    } else {
                        System.out.println("Unknown server command.");
                    }
                }
            } catch (NoSuchElementException noSuchElementException) {
                System.err.println("Incorrect input. Started saving of collection and finishing server disabling.");
                DatabaseManager.saveCollection(collectionManager.getCollection());
                System.exit(1);
            }

            int readyChannels = selector.select();
            if (readyChannels == 0) {
                continue;
            }

            Set<SelectionKey> selectedKeys = selector.selectedKeys();
            Iterator<SelectionKey> keyIterator = selectedKeys.iterator();

            while (keyIterator.hasNext()) {
                SelectionKey key = keyIterator.next();
                if (key.isReadable()) {
                    DatagramChannel readChannel = (DatagramChannel) key.channel();
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    SocketAddress clientAddress = readChannel.receive(buffer);
                    byte[] receiveData = buffer.array();
                    ByteArrayInputStream byteStream = new ByteArrayInputStream(receiveData);
                    Request request = new Request();

                    try (ObjectInputStream objectInputStream = new ObjectInputStream(byteStream)) {
                        request = (Request) objectInputStream.readObject();
                        if (clientAddress != null) {
                            // Multi-threaded processing of requests
                            Request finalRequest = request;
                            queryThreadPool.execute(() -> processRequest(finalRequest, clientAddress, history));
                        }
                    } catch (IOException | ClassNotFoundException e) {
                        System.out.println("IO exception");
                    }
                }
                keyIterator.remove();
            }
        }
    }

    private void processRequest(Request request, SocketAddress clientAddress, LinkedList<String> history) {
        boolean flagSave = true;
        String response = "";
        switch (request.getCommand()) {
            case "login":
                boolean result = new LoginCommand().execute(request.getLogin(), request.getPassword());
                response = result ? "Welcome, " + request.getLogin() + "\n" : "Login or/and password are incorrect.\n";
                this.collectionManager.setCurrentUser(request.getLogin());
                break;
            case "logout":
                this.collectionManager.setCurrentUser(null);
                response = "Goodbye!\n";
                break;
            case "register":
                boolean regResult = new RegisterCommand().execute(request.getLogin(), request.getPassword());
                if (regResult) {
                    response = "Account sucessfully created. Now you can login\n";
                } else {
                    response = "Cannot create account.\n";
            }
                break;
            case "clear":
                this.collectionManager.setCurrentUser(request.getLogin());
                response = new ClearCommand(this.collectionManager).execute();
                break;
            case "count_greater_than_height":
                this.collectionManager.setCurrentUser(request.getLogin());
                if (request.getHeight() == null) {
                    response = "Incorrect argument. Height should be defined.\n";
                } else {
                    response = new CountGreaterThanHeightCommand(this.collectionManager).execute(request.getHeight());
                }
                break;
            case "exit":
                response = "Command is unavailable.\n";
                break;
            case "help":
                this.collectionManager.setCurrentUser(request.getLogin());
                response = new HelpCommand(this.collectionManager).execute();
                break;
            case "filter_greater_than_health":
                this.collectionManager.setCurrentUser(request.getLogin());
                if (request.getHealth() == null) {
                    response = "Incorrect argument. Health should be defined.\n";
                } else {
                    response = new FilterGreaterThanHealthCommand(this.collectionManager)
                            .execute(request.getHealth());
                }
                break;
            case "info":
                this.collectionManager.setCurrentUser(request.getLogin());
                response = new InfoCommand(this.collectionManager).execute();
                break;
            case "insert":
                this.collectionManager.setCurrentUser(request.getLogin());
                if (request.getKey() == null || request.getSpaceMarine() == null || request.getKey() <= 0) {
                    response = "Incorrect arguments. Key and space marine for insertion should be defined correctly.\n";
                } else {
                    SpaceMarine spaceMarine1 = deserializeFromCSV(request.getSpaceMarine());
                    spaceMarine1.setId(request.getKey());
                    response = new InsertCommand(this.collectionManager).execute(request.getKey(),
                            spaceMarine1);
                }
                break;
            case "print_descending":
                this.collectionManager.setCurrentUser(request.getLogin());
                response = new PrintDescendingCommand(this.collectionManager).execute();
                break;
            case "remove_greater":
                this.collectionManager.setCurrentUser(request.getLogin());
                if (request.getSpaceMarine() == null) {
                    response = "Incorrect argument. Space marine should be defined.\n";
                } else {
                    response = new RemoveGreaterCommand(this.collectionManager).execute(deserializeFromCSV(request.getSpaceMarine()));
                }
                break;
            case "remove_key":
                this.collectionManager.setCurrentUser(request.getLogin());
                if (request.getKey() == null || request.getKey() <= 0) {
                    response = "Incorrect argument. Key should be defined correctly.\n";
                } else {
                    response = new RemoveKeyCommand(this.collectionManager).execute(request.getKey());
                }
                break;
            case "remove_lower_key":
                this.collectionManager.setCurrentUser(request.getLogin());
                if (request.getKey() == null || request.getKey() <= 0) {
                    response = "Incorrect argument. Key should be defined correctly.\n";
                } else {
                    response = new RemoveLowerKeyCommand(this.collectionManager).execute(request.getKey());
                }
                break;
            case "save":
                this.collectionManager.setCurrentUser(request.getLogin());
                response = new SaveCommand(collectionManager).execute();
                break;
            case "show":
                this.collectionManager.setCurrentUser(request.getLogin());
                response = new ShowCommand(this.collectionManager).execute();
                break;
            case "history":
                this.collectionManager.setCurrentUser(request.getLogin());
                response = history.toString();
                break;
            case "update":
                this.collectionManager.setCurrentUser(request.getLogin());
                if (request.getKey() == null || request.getSpaceMarine() == null || request.getKey() <= 0) {
                    response = "Incorrect arguments. Key and space marine for updating should be defined correctly.\n";
                } else {
                    SpaceMarine spaceMarine2 = deserializeFromCSV(request.getSpaceMarine());
                    spaceMarine2.setId(request.getKey());
                    response = new UpdateCommand(this.collectionManager).execute(request.getKey(),
                            spaceMarine2);
                    break;
                }
            case "execute_script":
                this.collectionManager.setCurrentUser(request.getLogin());
                if (request.getScript().equals("null"))
                    request.setScript(request.getScript().replace("\u0000", "").replace("\n", ""));
                System.out.println("RRR: " + request);
                if (request.getScript() == null) {
                    response = "Incorrect arguments. Key and space marine for updating should be defined correctly.\n";
                } else {
                    String content = request.getScript();
                    response = new ExecuteScriptCommand(this.collectionManager).execute(content);
                    flagSave = false;
//                    System.out.println("response: " + response);
                    break;
                }
            default:
                response = "Unknown command. Write help for getting list of available commands.\n";
                break;
        }

        byte[] responseData = response.getBytes(StandardCharsets.UTF_8);
        int maxPacketSize = 9400; // Максимальный размер UDP датаграммы (в байтах)
        DatagramChannel channel = null;

        try {
            channel = DatagramChannel.open();

            for (int offset = 0; offset < responseData.length; offset += maxPacketSize) {
                int length = Math.min(maxPacketSize, responseData.length - offset);
                byte[] packetData = Arrays.copyOfRange(responseData, offset, offset + length);
                ByteBuffer sendBuffer = ByteBuffer.wrap(packetData);

                try {
                    channel.send(sendBuffer, clientAddress);
                } catch (IOException e) {
                    System.out.println("Output too big");
                }
            }
            if (flagSave) {
                DatabaseManager.saveCollection(collectionManager.getCollection());
            }
        } catch (IOException e) {
            System.out.println("IO exception");
        } finally {
            if (channel != null) {
                try {
                    channel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Serializes a SpaceMarine object to a CSV-formatted string.
     *
     * @param spaceMarine The SpaceMarine object to serialize.
     * @return The CSV-formatted string representation of the SpaceMarine.
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
                spaceMarine.getChapter() != null ? spaceMarine.getChapter().getName() : ""
        );
        return fields.stream()
                .map(field -> field.replace(",", "\\,"))
                .collect(Collectors.joining(","));
    }

    // Метод для преобразования CSV-строки в объект SpaceMarine
    /**
     * Deserializes a CSV-formatted string into a SpaceMarine object.
     *
     * @param csv The CSV-formatted string.
     * @return The deserialized SpaceMarine object.
     */
    public static SpaceMarine deserializeFromCSV(String csv) {
        String[] fields = csv.split(",");
        int id = Integer.parseInt(fields[0]);
        String name = fields[1];
        double x = Double.parseDouble(fields[2]);
        float y = Float.parseFloat(fields[3]);
        Coordinates coordinates = new Coordinates(x, y);
        java.time.LocalDateTime creationDate = LocalDateTime.now();
        int health = Integer.parseInt(fields[5]);
        long heartCount = Long.parseLong(fields[6]);
        float height = Float.parseFloat(fields[7]);
        MeleeWeapon meleeWeapon = MeleeWeapon.valueOf(fields[8]);
        Chapter chapter = new Chapter();
        if (!fields[9].isEmpty()) {
            String chapterName = fields[9].replace("\\,", ",");
            chapter.setName(chapterName);
        }
        if (!fields[10].isEmpty()) {
            String parentLegion = fields[10].replace("\\,", ",");
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
