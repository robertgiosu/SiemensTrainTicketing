package model;

import java.time.LocalDateTime;

public class Booking {
    private Integer id;
    private String customerName;
    private String customerEmail;
    private Integer routeId;
    private Integer fromStationId;
    private Integer toStationId;
    private Integer seatsBooked;
    private LocalDateTime bookingTime;

    public Booking() {}

    public Booking(String customerName, String customerEmail, Integer routeId, Integer fromStationId, Integer toStationId, Integer seatsBooked, LocalDateTime bookingTime) {
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.routeId = routeId;
        this.fromStationId = fromStationId;
        this.toStationId = toStationId;
        this.seatsBooked = seatsBooked;
        this.bookingTime = bookingTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public Integer getRouteId() {
        return routeId;
    }

    public Integer getFromStationId() {
        return fromStationId;
    }

    public Integer getToStationId() {
        return toStationId;
    }

    public Integer getSeatsBooked() {
        return seatsBooked;
    }

    public LocalDateTime getBookingTime() {
        return bookingTime;
    }
}