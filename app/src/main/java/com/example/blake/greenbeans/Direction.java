package com.example.blake.greenbeans;

/**
 * Created by blake on 11/30/16.
 */

public class Direction {

    String description;
    String skill;
    int time;


    public Direction() {
        this.description = null;
        this.skill = null;
    }

    public Direction(String description, String skill, int time) {
        this.description = description;
        this.skill = skill;
        this.time = time;
    }

    public String getSkill() {
        return skill;
    }

    public String getDescription() {

        return description;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public boolean hasSkill() {
        return skill != null;
    }
}
