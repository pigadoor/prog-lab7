package com.pigadoor.commands;

import com.pigadoor.application.CollectionManager;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.LinkedHashSet;
import java.util.Set;

import static com.pigadoor.application.CommandInvoker.deserializeFromCSV;

/**
 * The {@code ExecuteScriptCommand} class represents a command to execute a script containing a sequence of commands.
 * It reads and executes the commands from the script file.
 */
public class ExecuteScriptCommand extends Command {

    /**
     * The {@link CollectionManager} instance responsible for managing the collection.
     */
    private final CollectionManager collectionManager;
    private static final Set<String> callStack = new LinkedHashSet<>();

    /**
     * Constructs an {@code ExecuteScriptCommand} with the specified {@link CollectionManager}.
     *
     * @param collectionManager The {@link CollectionManager} responsible for managing the collection.
     */
    public  ExecuteScriptCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    /**
     * Executes the script command by reading and executing commands from the specified script file.
     *
     * @param command The path to the script file.
     * @return A message indicating the result of script execution.
     */
    public String execute(String command) {
        if (collectionManager.getCurrentUser() != null) {
//            if (!callStack.contains(script)) {
//                callStack.add(script);
                // do script
                StringBuilder results = new StringBuilder();
//                try {
//                    BufferedReader reader = new BufferedReader(new FileReader(script));
                    String[] splitCommand;
//                    String command;
//                    while ((command = reader.readLine()) != null) {
                        splitCommand = command.trim().split(" ", 3);
                        System.out.println("STR: " + Arrays.toString(splitCommand));
                        switch (splitCommand[0]) {
                            case "logout":
                                this.collectionManager.setCurrentUser(null);
                                results.append("You logged out.\n");
                                break;
                            case "help":
                                results.append(new HelpCommand(this.collectionManager).execute())
                                        .append("\n");
                                break;
                            case "info":
                                results.append(new InfoCommand(this.collectionManager).execute())
                                        .append("\n");
                                break;
                            case "show":
                                results.append(new ShowCommand(this.collectionManager).execute())
                                        .append("\n");
                                break;
                            case "insert":
                                try {
                                    results.append(new InsertCommand(this.collectionManager)
                                                    .execute(Integer.parseInt(splitCommand[1]), deserializeFromCSV(splitCommand[2])))
                                            .append("\n");
                                } catch (ArrayIndexOutOfBoundsException | InputMismatchException exception) {
                                    results.append("Incorrect command read. Try again.\n");
                                }
                                break;
                            case "update":
                                try {
                                    results.append(new UpdateCommand(this.collectionManager)
                                                    .execute(Integer.parseInt(splitCommand[1]), deserializeFromCSV(splitCommand[2])))
                                            .append("\n");
                                } catch (ArrayIndexOutOfBoundsException | InputMismatchException exception) {
                                    results.append("Incorrect command read. Try again.\n");
                                }
                                break;
                            case "remove_by_id":
                                try {
                                    results.append(new RemoveKeyCommand(this.collectionManager)
                                                    .execute(Integer.parseInt(splitCommand[1])))
                                            .append("\n");
                                } catch (ArrayIndexOutOfBoundsException | InputMismatchException exception) {
                                    results.append("Incorrect command read. Try again.\n");
                                }
                                break;
                            case "clear":
                                results.append(new ClearCommand(this.collectionManager).execute())
                                        .append("\n");
                                break;
//                            case "save":
//                                results.append("Auto-save is enabled. You don't need this command.").append("\n");
//                                break;
                            case "execute_script":
                                try {
                                    results.append(new ExecuteScriptCommand(this.collectionManager)
                                            .execute(splitCommand[5].replace("\u0000", "").replace("\n", ""))).append("\n");
                                } catch (ArrayIndexOutOfBoundsException | InputMismatchException exception) {
                                    results.append("Incorrect command read. Try again.\n");
                                }
                                break;
                            case "exit":
                                results.append("This command is not available from script.\n");
                                break;
                            case "count_greater_than_height":
                                try {
                                    results.append(new CountGreaterThanHeightCommand(this.collectionManager).execute(
                                            Float.parseFloat(splitCommand[1]))).append("\n");
                                } catch (ArrayIndexOutOfBoundsException | InputMismatchException exception) {
                                    results.append("Incorrect command read. Try again.\n");
                                }
                                break;
                            case "remove_greater":
                                try {
                                    results.append(new RemoveGreaterCommand(this.collectionManager)
                                                    .execute(deserializeFromCSV(splitCommand[1])))
                                            .append("\n");
                                } catch (ArrayIndexOutOfBoundsException | InputMismatchException exception) {
                                    results.append("Incorrect command read. Try again.\n");
                                }
                                break;
                            case "remove_lower_key":
                                try {
                                    results.append(new RemoveLowerKeyCommand(this.collectionManager)
                                                    .execute(Integer.parseInt(splitCommand[1])))
                                            .append("\n");
                                } catch (ArrayIndexOutOfBoundsException | InputMismatchException exception) {
                                    results.append("Incorrect command read. Try again.\n");
                                }
                                break;
                            case "filter_greater_than_health":
                                try {
                                    results.append(new FilterGreaterThanHealthCommand(this.collectionManager)
                                            .execute(Integer.parseInt(splitCommand[1]))).append("\n");
                                } catch (ArrayIndexOutOfBoundsException | InputMismatchException exception) {
                                    results.append("Incorrect command read. Try again.\n");
                                }
                                break;
                            case "print_descending":
                                results.append(new PrintDescendingCommand(this.collectionManager).execute()).append("\n");
                                break;
                            case "history":
                                results.append("This command cannot be executed in script.\n");
                                break;
                            default:
//                                reader.readLine();
                                break;
                        }
//                    }
//                } catch (FileNotFoundException fileNotFoundException) {
//                    return "File with script not found. Check path to script and try again.\n";
//                } catch (IOException ioException) {
//                    return "File reading problems. Try to check file permissions or syntax and try again.\n";
//                } catch (OutOfMemoryError outOfMemoryError) {
//                    callStack.clear();
//                    return "Script size too large. Max JVM memory: " + Runtime.getRuntime().maxMemory() + "\n";
//                }
//                callStack.remove(script);
                return results.toString();
//            } else {
//                return "Ring recursion detected. Script executing aborted.\n";
//            }
        } else {
            return "You need to login before using this command.\n";
        }
    }

}
