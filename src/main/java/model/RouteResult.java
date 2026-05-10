package model;

public class RouteResult {
    private String message;

    public RouteResult(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}