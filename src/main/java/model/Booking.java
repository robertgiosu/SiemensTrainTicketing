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

    public void setId(Integer id) {
        this.id = id;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public void setRouteId(Integer routeId) {
        this.routeId = routeId;
    }

    public void setFromStationId(Integer fromStationId) {
        this.fromStationId = fromStationId;
    }

    public void setToStationId(Integer toStationId) {
        this.toStationId = toStationId;
    }

    public void setSeatsBooked(Integer seatsBooked) {
        this.seatsBooked = seatsBooked;
    }

    public void setBookingTime(LocalDateTime bookingTime) {
        this.bookingTime = bookingTime;
    }

    @Override
    public String toString() {
        return "Booking [id=" + id + ", customerName=" + customerName + ", customerEmail=" + customerEmail + ", routeId=" + routeId + ", fromStationId=" + fromStationId + ", toStationId=" + toStationId + ", seatsBooked=" + seatsBooked + ", bookingTime=" + bookingTime + "]";
    }
}