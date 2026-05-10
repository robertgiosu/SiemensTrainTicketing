package model;

import java.time.LocalTime;

public class RouteStop {
    private Integer id;
    private Integer routeId;
    private Integer stationId;
    private Integer stopOrder;
    private LocalTime arrivalTime;
    private LocalTime departureTime;

    public RouteStop() {}

    public RouteStop(Integer routeId, Integer stationId, Integer stopOrder, LocalTime arrivalTime, LocalTime departureTime) {
        this.routeId = routeId;
        this.stationId = stationId;
        this.stopOrder = stopOrder;
        this.arrivalTime = arrivalTime;
        this.departureTime = departureTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRouteId() {
        return routeId;
    }

    public void setRouteId(Integer routeId) {
        this.routeId = routeId;
    }

    public Integer getStationId() {
        return stationId;
    }

    public void setStationId(Integer stationId) {
        this.stationId = stationId;
    }

    public Integer getStopOrder() {
        return stopOrder;
    }

    public void setStopOrder(Integer stopOrder) {
        this.stopOrder = stopOrder;
    }

    public LocalTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public LocalTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalTime departureTime) {
        this.departureTime = departureTime;
    }

    @Override
    public String toString() {
        return "RouteStop{" + "id=" + id + ", routeId=" + routeId + ", stationId=" + stationId + ", stopOrder=" + stopOrder + ", arrivalTime=" + arrivalTime + ", departureTime=" + departureTime + '}';
    }
}
