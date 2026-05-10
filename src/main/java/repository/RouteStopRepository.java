package repository;

import repository.DatabaseManager;
import model.RouteStop;

import java.sql.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class RouteStopRepository {
    private final DatabaseManager dbManager;

    public RouteStopRepository(DatabaseManager dbManager) {
        this.dbManager = dbManager;
    }

    public void save(RouteStop routeStop) {

        String sql = """
                INSERT INTO route_stops(
                    route_id,
                    station_id,
                    stop_order,
                    arrival_time,
                    departure_time
                )
                VALUES(?, ?, ?, ?, ?)
                """;

        try (Connection connection = dbManager.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setInt(1, routeStop.getRouteId());
            statement.setInt(2, routeStop.getStationId());
            statement.setInt(3, routeStop.getStopOrder());

            statement.setString(
                    4,
                    routeStop.getArrivalTime() != null
                            ? routeStop.getArrivalTime().toString()
                            : null
            );

            statement.setString(
                    5,
                    routeStop.getDepartureTime() != null
                            ? routeStop.getDepartureTime().toString()
                            : null
            );

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<RouteStop> findAll() {

        List<RouteStop> stops = new ArrayList<>();

        String sql = """
                SELECT * FROM route_stops
                ORDER BY route_id, stop_order
                """;

        try (Connection connection = dbManager.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {

                RouteStop stop = mapResultSet(resultSet);

                stops.add(stop);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return stops;
    }

    public List<RouteStop> findByRouteId(Integer routeId) {

        List<RouteStop> stops = new ArrayList<>();

        String sql = """
                SELECT * FROM route_stops
                WHERE route_id = ?
                ORDER BY stop_order
                """;

        try (Connection connection = dbManager.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setInt(1, routeId);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                RouteStop stop = mapResultSet(resultSet);

                stops.add(stop);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return stops;
    }

    public RouteStop findById(Integer id) {

        String sql = """
                SELECT * FROM route_stops
                WHERE id = ?
                """;

        try (Connection connection = dbManager.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return mapResultSet(resultSet);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void update(RouteStop routeStop) {

        String sql = """
                UPDATE route_stops
                SET
                    route_id = ?,
                    station_id = ?,
                    stop_order = ?,
                    arrival_time = ?,
                    departure_time = ?
                WHERE id = ?
                """;

        try (Connection connection = dbManager.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setInt(1, routeStop.getRouteId());
            statement.setInt(2, routeStop.getStationId());
            statement.setInt(3, routeStop.getStopOrder());

            statement.setString(
                    4,
                    routeStop.getArrivalTime() != null
                            ? routeStop.getArrivalTime().toString()
                            : null
            );

            statement.setString(
                    5,
                    routeStop.getDepartureTime() != null
                            ? routeStop.getDepartureTime().toString()
                            : null
            );

            statement.setInt(6, routeStop.getId());

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(Integer id) {

        String sql = """
                DELETE FROM route_stops
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

    private RouteStop mapResultSet(ResultSet resultSet)
            throws SQLException {

        RouteStop stop = new RouteStop();

        stop.setId(resultSet.getInt("id"));
        stop.setRouteId(resultSet.getInt("route_id"));
        stop.setStationId(resultSet.getInt("station_id"));
        stop.setStopOrder(resultSet.getInt("stop_order"));

        String arrival = resultSet.getString("arrival_time");
        String departure = resultSet.getString("departure_time");

        if (arrival != null) {
            stop.setArrivalTime(LocalTime.parse(arrival));
        }

        if (departure != null) {
            stop.setDepartureTime(LocalTime.parse(departure));
        }

        return stop;
    }
}