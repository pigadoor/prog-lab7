package com.pigadoor.application;

import java.io.IOException;


public class Server {

    /**
     * The main entry point for the server application.
     * This method initializes the server components, listens for client requests, and handles exceptions.
     *
     * @param args The command-line arguments (not used in this application).
     */
    public static void main(String[] args) {
        try {
            DatabaseManager databaseManager = new DatabaseManager();
            CollectionManager collectionManager = CollectionManager.getInstance(databaseManager);
            CommandInvoker commandInvoker = new CommandInvoker(collectionManager);
            commandInvoker.listenForRequests();
        } catch (IOException e) {
            System.out.println("File reading error.");
            System.out.println(e.getMessage());
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found.");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

}