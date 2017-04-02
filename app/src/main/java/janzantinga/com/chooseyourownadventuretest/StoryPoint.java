package janzantinga.com.chooseyourownadventuretest;

import java.util.ArrayList;

class StoryPoint {

    private ArrayList<String> newStoryPoints;
    private String button1Info, button2Info;
    private int button1Value, button2Value;

    StoryPoint(ArrayList<String> newStoryPoints, String button1Info, String button2Info,
               int button1Value, int button2Value) {
        this.newStoryPoints = newStoryPoints;
        this.button1Info = button1Info;
        this.button2Info = button2Info;
        this.button1Value = button1Value;
        this.button2Value = button2Value;
    }

    ArrayList<String> getNewStoryPoints() {
        return newStoryPoints;
    }

    public void setNewStoryPoints(ArrayList<String> newStoryPoints) {
        this.newStoryPoints = newStoryPoints;
    }

    String getButton1Info() {
        return button1Info;
    }

    void setButton1Info(String button1Info) {
        this.button1Info = button1Info;
    }

    String getButton2Info() {
        return button2Info;
    }

    void setButton2Info(String button2Info) {
        this.button2Info = button2Info;
    }

    int getButton1Value() {
        return button1Value;
    }

    void setButton1Value(int button1Value) {
        this.button1Value = button1Value;
    }

    int getButton2Value() {
        return button2Value;
    }

    void setButton2Value(int button2Value) {
        this.button2Value = button2Value;
    }
}
