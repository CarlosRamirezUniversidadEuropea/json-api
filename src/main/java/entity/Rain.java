package entity;

import com.google.gson.annotations.SerializedName;

public class Rain {

    @SerializedName("3h")
    private double mililitersInThreeHours;

    public Rain() {
    }

    public double getMililitersInThreeHours() {
        return mililitersInThreeHours;
    }

    public void setMililitersInThreeHours(double mililitersInThreeHours) {
        this.mililitersInThreeHours = mililitersInThreeHours;
    }
}