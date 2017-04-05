package com.a40333.bharrin4.lab2_bharrin4;

import java.io.Serializable;

/**
 * Created by User on 3/1/2017.
 */

public class Team implements Serializable {
    String opposingName;
    String opposingLogo;
    String opposingMascot;
    String opposingRec;
    String ndName;
    String ndMascot;
    String ndRec;
    String ndLogo;
    String date;

    //... define all the strings that you need to fill all the TextViews  of activity_detail.

    public Team (String [] gameStats) {
        setOpposingName(gameStats[0]);
        setOpposingLogo(gameStats[1]);
        setOpposingMascot(gameStats[2]);
        setOpposingRec(gameStats[3]);
        setNdName(gameStats[4]);
        setNdMascot(gameStats[5]);
        setNdRec(gameStats[6]);
        setNdLogo(gameStats[7]);
        setDate(gameStats[8]);
    }



    public void setOpposingName(String opposing_name) {
        this.opposingName = opposing_name;
    }

    public String getOpposingName() {
        return this.opposingName;
    }

    public void setOpposingLogo(String opposing_logo) {
        this.opposingLogo = opposing_logo;
    }

    public String getOpposingLogo() {
        return this.opposingLogo;
    }

    public void setOpposingMascot(String opposing_mascot) {
        this.opposingMascot = opposing_mascot;
    }

    public String getOpposingMascot() {
        return this.opposingMascot;
    }

    public void setOpposingRec(String opposing_rec) {
        this.opposingRec = opposing_rec;
    }

    public String getOpposingRec() {
        return this.opposingRec;
    }

    public void setNdName(String nd_name) {
        this.ndName = nd_name;
    }

    public String getNdName() {
        return this.ndName;
    }

    public void setNdMascot(String nd_mascot) {
        this.ndMascot = nd_mascot;
    }

    public String getNdMascot() {
        return this.ndMascot;
    }

    public void setNdRec(String nd_rec) {
        this.ndRec = nd_rec;
    }

    public String getNdRec() {
        return this.ndRec;
    }

    public void setNdLogo(String nd_logo) {
        this.ndLogo = nd_logo;
    }

    public String getNdLogo() {
        return this.ndLogo;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return this.date;
    }
}
