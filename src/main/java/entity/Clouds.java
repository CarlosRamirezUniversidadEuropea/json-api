package entity;

import com.google.gson.annotations.SerializedName;

public class Clouds {

    @SerializedName("all")
    private double percent;

    public Clouds(){}

    public double getPercent() {
        return percent;
    }

    public void setPercent(double percent) {
        this.percent = percent;
    }
}