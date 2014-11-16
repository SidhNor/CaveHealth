package com.cavemen.cavehealth.model;

/**
 * Created by vivascu on 11/16/2014.
 */
public class Achievement {
    public static final int LEVEL_BRONZE = 1;
    public static final int LEVEL_SILVER = 2;
    public static final int LEVEL_GOLD = 3;
    int id;
    String name;
    int level;
    String description;

    private String iconResourceName;

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

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
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
}
