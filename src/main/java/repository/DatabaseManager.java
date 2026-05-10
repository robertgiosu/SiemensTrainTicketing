package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DatabaseManager {
    private final Properties jdbcProps;
    private Connection instance = null;

    public DatabaseManager(Properties props) {
        this.jdbcProps = props;
    }

    /**
     * Get a new connection to the database
     * @return Connection
     */
    private Connection getNewConnection() {
        //extract db configuration properties
        String url = jdbcProps.getProperty("jdbc.url");
        String user = jdbcProps.getProperty("jdbc.user");
        String password = jdbcProps.getProperty("jdbc.pass");
        Connection conn = null;
        //get connection depending on the type of database used
        try {
            if (user != null && password != null)
                conn = DriverManager.getConnection(url, user, password);
            else
                conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println("Error getting connection " + e.getMessage());
        }
        return conn;
    }

    /**
     * Get a connection to the database
     * @return Connection
     */
    public Connection getConnection() {
        //if connection is null or closed, get a new connection
        //otherwise return the existing connection
        try {
            if (instance == null || instance.isClosed())
                instance = getNewConnection();
        } catch (SQLException e) {
            System.out.println("Error getting connection " + e.getMessage());
        }
        return instance;
    }

    public void closeConnection() throws SQLException {
        if (instance != null && !instance.isClosed()) {
            instance.close();
        }
    }

    public void initializeDatabase() {
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {

            statement.execute("""
                    CREATE TABLE IF NOT EXISTS stations (
                        id INTEGER PRIMARY KEY AUTOINCREMENT,
                        name TEXT NOT NULL
                    );
                    """);

            statement.execute("""
                    CREATE TABLE IF NOT EXISTS trains (
                        id INTEGER PRIMARY KEY AUTOINCREMENT,
                        train_code TEXT NOT NULL,
                        capacity INTEGER NOT NULL
                    );
                    """);

            statement.execute("""
                    CREATE TABLE IF NOT EXISTS routes (
                        id INTEGER PRIMARY KEY AUTOINCREMENT,
                        train_id INTEGER NOT NULL,
                        FOREIGN KEY (train_id) REFERENCES trains(id)
                    );
                    """);

            statement.execute("""
                    CREATE TABLE IF NOT EXISTS route_stops (
                        id INTEGER PRIMARY KEY AUTOINCREMENT,
                        route_id INTEGER NOT NULL,
                        station_id INTEGER NOT NULL,
                        stop_order INTEGER NOT NULL,
                        arrival_time TEXT,
                        departure_time TEXT,
                        FOREIGN KEY (route_id) REFERENCES routes(id),
                        FOREIGN KEY (station_id) REFERENCES stations(id)
                    );
                    """);

            statement.execute("""
                    CREATE TABLE IF NOT EXISTS bookings (
                        id INTEGER PRIMARY KEY AUTOINCREMENT,
                        customer_name TEXT NOT NULL,
                        customer_email TEXT NOT NULL,
                        route_id INTEGER NOT NULL,
                        from_station_id INTEGER NOT NULL,
                        to_station_id INTEGER NOT NULL,
                        seats_booked INTEGER NOT NULL,
                        booking_time TEXT NOT NULL,
                        FOREIGN KEY (route_id) REFERENCES routes(id),
                        FOREIGN KEY (from_station_id) REFERENCES stations(id),
                        FOREIGN KEY (to_station_id) REFERENCES stations(id)
                    );
                    """);

            statement.execute("""
                    CREATE TABLE IF NOT EXISTS delays (
                        id INTEGER PRIMARY KEY AUTOINCREMENT,
                        route_id INTEGER NOT NULL,
                        delay_minutes INTEGER NOT NULL,
                        created_at TEXT NOT NULL,
                        FOREIGN KEY (route_id) REFERENCES routes(id)
                    );
                    """);

            System.out.println("Database initialized successfully.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
