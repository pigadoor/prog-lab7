package com.pigadoor.commands;

import com.pigadoor.application.DatabaseManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginCommand {

    public boolean execute(String username, String password) {
        try {
            String login = "SELECT COUNT(*) FROM account WHERE login = ? AND password = ?";
            PreparedStatement preparedStatement = DatabaseManager.getConnection().prepareStatement(login);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            int amount = resultSet.getInt(1);
            if (amount == 1) {
                return true;
            } else if(amount > 1) {
                System.out.println("Database error. Username is not unique. Try later.");
                return false;
            } else throw new SQLException();
        } catch (SQLException sqlException) {
            System.out.println("User not found. You can register this account.");
            return false;
        }
    }
}
