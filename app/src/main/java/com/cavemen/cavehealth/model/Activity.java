package com.cavemen.cavehealth.model;

public class Activity {

    private int activityId;
    private String name;
    private String iconResourceName;
    private String description;
    private int bonusPointsGranted;

    public int getActivityId() {
        return activityId;
    }

    public void setActivityId(int activityId) {
        this.activityId = activityId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIconResourceName() {
        return iconResourceName;
    }

    public void setIconResourceName(String iconResourceName) {
        this.iconResourceName = iconResourceName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getBonusPointsGranted() {
        return bonusPointsGranted;
    }

    public void setBonusPointsGranted(int bonusPointsGranted) {
        this.bonusPointsGranted = bonusPointsGranted;
    }
}
