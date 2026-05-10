package repository;

import model.Route;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RouteRepository {
    private final DatabaseManager dbManager;

    public RouteRepository(DatabaseManager dbManager) {
        this.dbManager = dbManager;
    }

    public void save(Route route) {

        String sql = """
                INSERT INTO routes(train_id)
                VALUES(?)
                """;

        try (Connection connection = dbManager.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setInt(1, route.getTrainId());

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Route> findAll() {

        List<Route> routes = new ArrayList<>();

        String sql = "SELECT * FROM routes";

        try (Connection connection = dbManager.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {

                Route route = new Route();

                route.setId(resultSet.getInt("id"));
                route.setTrainId(resultSet.getInt("train_id"));

                routes.add(route);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return routes;
    }

    public Route findById(Integer id) {

        String sql = """
                SELECT * FROM routes
                WHERE id = ?
                """;

        try (Connection connection = dbManager.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {

                Route route = new Route();

                route.setId(resultSet.getInt("id"));
                route.setTrainId(resultSet.getInt("train_id"));

                return route;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void update(Route route) {

        String sql = """
                UPDATE routes
                SET train_id = ?
                WHERE id = ?
                """;

        try (Connection connection = dbManager.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setInt(1, route.getTrainId());
            statement.setInt(2, route.getId());

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(Integer id) {

        String sql = """
                DELETE FROM routes
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