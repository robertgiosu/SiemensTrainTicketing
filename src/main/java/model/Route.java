package model;

public class Route {
    private Integer id;
    private Integer trainId;

    public Route() {}

    public Route(Integer id, Integer trainId) {
        this.id = id;
        this.trainId = trainId;
    }

    public Route(Integer trainId) {
        this.trainId = trainId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTrainId() {
        return trainId;
    }

    public void setTrainId(Integer trainId) {
        this.trainId = trainId;
    }
}