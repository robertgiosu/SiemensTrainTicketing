package ui;

import repository.DatabaseManager;

import java.util.Properties;

public class MainApp {
    public static void main(String[] args) {
        try {
            Properties dbProps = new Properties();
            dbProps.load(MainApp.class.getResourceAsStream("/database.properties"));
            DatabaseManager dbManager = new DatabaseManager(dbProps);
            dbManager.initializeDatabase();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
