package com.cavemen.cavehealth.model;

public class GroupActivity {

    private int id;
    private String name;
    private String description;
    private String iconResourceName;
    private int bonusPointsGranted;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIconResourceName() {
        return iconResourceName;
    }

    public void setIconResourceName(String iconResourceName) {
        this.iconResourceName = iconResourceName;
    }

    public int getBonusPointsGranted() {
        return bonusPointsGranted;
    }

    public void setBonusPointsGranted(int bonusPointsGranted) {
        this.bonusPointsGranted = bonusPointsGranted;
    }
}
