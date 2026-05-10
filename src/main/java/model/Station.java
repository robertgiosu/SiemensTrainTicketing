package model;

public class Station {
    private Integer id;
    private String name;

    public Station() {}

    public Station(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Station(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Station{" + "id=" + id + ", name=" + name + '}';
    }
}