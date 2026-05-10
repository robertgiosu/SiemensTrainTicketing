package repository;

import model.Booking;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class BookingRepository {
    private final DatabaseManager dbManager;

    public BookingRepository(DatabaseManager dbManager) {
        this.dbManager = dbManager;
    }

    public void save(Booking booking) {

        String sql = """
                INSERT INTO bookings(
                    customer_name,
                    customer_email,
                    route_id,
                    from_station_id,
                    to_station_id,
                    seats_booked,
                    booking_time
                )
                VALUES(?, ?, ?, ?, ?, ?, ?)
                """;

        try (Connection connection = dbManager.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setString(1, booking.getCustomerName());
            statement.setString(2, booking.getCustomerEmail());
            statement.setInt(3, booking.getRouteId());
            statement.setInt(4, booking.getFromStationId());
            statement.setInt(5, booking.getToStationId());
            statement.setInt(6, booking.getSeatsBooked());

            statement.setString(
                    7,
                    booking.getBookingTime().toString()
            );

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Booking> findAll() {

        List<Booking> bookings = new ArrayList<>();

        String sql = "SELECT * FROM bookings";

        try (Connection connection = dbManager.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {

                Booking booking = mapResultSet(resultSet);

                bookings.add(booking);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bookings;
    }

    public List<Booking> findByRouteId(Integer routeId) {

        List<Booking> bookings = new ArrayList<>();

        String sql = """
                SELECT * FROM bookings
                WHERE route_id = ?
                """;

        try (Connection connection = dbManager.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setInt(1, routeId);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                Booking booking = mapResultSet(resultSet);

                bookings.add(booking);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bookings;
    }

    private Booking mapResultSet(ResultSet resultSet)
            throws SQLException {

        Booking booking = new Booking();

        booking.setId(resultSet.getInt("id"));

        booking.setCustomerName(
                resultSet.getString("customer_name")
        );

        booking.setCustomerEmail(
                resultSet.getString("customer_email")
        );

        booking.setRouteId(
                resultSet.getInt("route_id")
        );

        booking.setFromStationId(
                resultSet.getInt("from_station_id")
        );

        booking.setToStationId(
                resultSet.getInt("to_station_id")
        );

        booking.setSeatsBooked(
                resultSet.getInt("seats_booked")
        );

        booking.setBookingTime(
                LocalDateTime.parse(
                        resultSet.getString("booking_time")
                )
        );

        return booking;
    }
}