package model;

public class Train {
    private Integer id;
    private String trainCode;
    private Integer capacity;

    public Train() {}

    public Train(Integer id, String trainCode, Integer capacity) {
        this.id = id;
        this.trainCode = trainCode;
        this.capacity = capacity;
    }

    public Train(String trainCode, Integer capacity) {
        this.trainCode = trainCode;
        this.capacity = capacity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTrainCode() {
        return trainCode;
    }

    public void setTrainCode(String trainCode) {
        this.trainCode = trainCode;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }
}