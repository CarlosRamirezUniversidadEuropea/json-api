package entity;

public class MyCity {

    private Double temperature;
    private String name;
    private Double latitude;
    private Double longitude;

    public MyCity(Double temperature, String name, Double latitude, Double longitude) {
        this.temperature = temperature;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
}
