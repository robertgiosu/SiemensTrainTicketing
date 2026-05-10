package repository;

import repository.DatabaseManager;
import model.Station;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StationRepository {
    private final DatabaseManager dbManager;

    public StationRepository(DatabaseManager dbManager) {
        this.dbManager = dbManager;
    }

    public void save(Station station) {

        String sql = """
                INSERT INTO stations(name)
                VALUES(?)
                """;

        try (Connection connection = dbManager.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {
            statement.setString(1, station.getName());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Station> findAll() {

        List<Station> stations = new ArrayList<>();

        String sql = "SELECT * FROM stations";

        try (Connection connection = dbManager.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {

                Station station = new Station();

                station.setId(resultSet.getInt("id"));
                station.setName(resultSet.getString("name"));

                stations.add(station);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return stations;
    }

    public Station findById(Integer id) {

        String sql = """
                SELECT * FROM stations
                WHERE id = ?
                """;

        try (Connection connection = dbManager.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {

                Station station = new Station();

                station.setId(resultSet.getInt("id"));
                station.setName(resultSet.getString("name"));

                return station;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void update(Station station) {

        String sql = """
                UPDATE stations
                SET name = ?
                WHERE id = ?
                """;

        try (Connection connection = dbManager.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setString(1, station.getName());
            statement.setInt(2, station.getId());

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(Integer id) {

        String sql = """
                DELETE FROM stations
                WHERE id = ?
                """;

        try (Connection connection = dbManager.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setInt(1, id);

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
