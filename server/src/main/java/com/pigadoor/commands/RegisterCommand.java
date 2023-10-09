package com.pigadoor.commands;

import com.pigadoor.application.DatabaseManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RegisterCommand {

    public boolean execute(String username, String password) {
        try {
            String login = "SELECT COUNT(*) FROM account WHERE login = ?";
            PreparedStatement preparedStatement = DatabaseManager.getConnection().prepareStatement(login);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            int amount = resultSet.getInt(1);
            System.out.println(amount);
            if (amount == 0) {
                String register = "INSERT INTO account (login, password) values (?, ?)";
                PreparedStatement preparedStatement2 = DatabaseManager.getConnection().prepareStatement(register);
                preparedStatement2.setString(1, username);
                preparedStatement2.setString(2, password);
                preparedStatement2.execute();
                return true;
            } else {
                System.out.println("User is already exist.");
                return false;
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            System.out.println("User is already exist.");
            return false;
        }
    }

}
