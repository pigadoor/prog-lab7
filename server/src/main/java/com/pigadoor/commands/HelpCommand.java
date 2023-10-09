package com.pigadoor.commands;

import com.pigadoor.application.CollectionManager;

/**
 * The {@code HelpCommand} class represents a command to display help information about available commands.
 * It provides a list of available commands and their descriptions.
 */
public class HelpCommand extends Command {

    /**
     * The {@link CollectionManager} instance responsible for managing the collection.
     */
    private final CollectionManager collectionManager;

    /**
     * Constructs a {@code HelpCommand} with the specified {@link CollectionManager}.
     *
     * @param collectionManager The {@link CollectionManager} responsible for managing the collection.
     */
    public HelpCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    /**
     * Executes the help command by providing information about available commands.
     *
     * @return A string containing descriptions of available commands.
     */
    public String execute() {
        return """
                help : вывести справку по доступным командам
                login user password: авторизоваться в системе под указанными данными
                register : зарегистрировать новый аккаунт
                logout : выйти из системы
                info : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)
                show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении
                insert null {element} : добавить новый элемент с заданным ключом
                update id {element} : обновить значение элемента коллекции, id которого равен заданному
                remove_key null : удалить элемент из коллекции по его ключу
                clear : очистить коллекцию
                execute_script file_name : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.
                exit : завершить программу
                remove_greater {element} : удалить из коллекции все элементы, превышающие заданный
                history : вывести последние 8 команд (без их аргументов)
                remove_lower_key null : удалить из коллекции все элементы, ключ которых меньше, чем заданный
                count_greater_than_height height : вывести количество элементов, значение поля height которых меньше заданного
                filter_greater_than_health health : вывести элементы, значение поля health которых больше заданного
                print_descending : вывести элементы коллекции в порядке убывания
                """;
    }

}
