package com.example.catchco2ifyoucan;

public class SingleItem {
    int ID;
    String job;
    double CO2;

    public SingleItem(int ID, String job, double CO2) {
        this.ID = ID;
        this.job = job;
        this.CO2 = CO2;
    }
    public int getID() {
        return ID;
    }
    public void setID(int ID) {
        this.ID = ID;
    }
    public String getJob() {
        return job;
    }
    public void setJob(String job) {
        this.job = job;
    }
    public double getCO2() {
        return CO2;
    }
    public void setCO2(double CO2) {
        this.CO2 = CO2;
    }
}

