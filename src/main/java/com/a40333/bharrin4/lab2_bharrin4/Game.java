package com.a40333.bharrin4.lab2_bharrin4;

import java.io.Serializable;

/**
 * Created by User on 4/3/2017.
 */

public class Game implements Serializable {
    //int _id;
    String gameTime;
    String gameLocation;
    String opposingName;
    String scoreID;
    String finalString;
    String date;

    public Game(String [] gamestats) {
        this.opposingName = gamestats[0];
        this.gameTime = gamestats[1];
        this.gameLocation = gamestats[2];
        this.scoreID = gamestats[3];
        this.finalString = gamestats[4];
        this.date = gamestats[5];
    }

    public void setOpposingName(String opposing_name) {
        this.opposingName = opposing_name;
    }

    public String getOpposingName() {
        return this.opposingName;
    }

    public void setGameTime(String game_time) {
        this.gameTime = game_time;
    }

    public String getGameTime() {
        return this.gameTime;
    }

    public void setGameLocation(String game_location) {
        this.gameLocation = game_location;
    }

    public String getGameLocation() {
        return this.gameLocation;
    }

    public void setScoreID(String score_id) {
        this.scoreID = score_id;
    }

    public String getScoreID() {
        return this.scoreID;
    }

    public void setFinalString(String final_string) {
        this.finalString = final_string;
    }

    public String getFinalString() {
        return this.finalString;
    }

    /*public void setCamera(String camera) {
        this.camera = camera;
    }

    public String getCamera() {
        return this.camera;
    } */

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return this.date;
    }


}
