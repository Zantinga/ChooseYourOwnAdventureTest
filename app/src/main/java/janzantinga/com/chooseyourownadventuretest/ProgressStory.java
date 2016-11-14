package janzantinga.com.chooseyourownadventuretest;

import java.util.ArrayList;

/**
 * Created by Jan Zantinga on 11/3/2016.
 */

public class ProgressStory {

    public static StoryPoint getNextStoryPoint(int nextStoryPoint) {
        ArrayList<String> storyPoints = new ArrayList<String>();
        switch(nextStoryPoint) {
            case 1:
                storyPoints.add("this is option 1");
                storyPoints.add("which option would you like next?");
                return new StoryPoint(storyPoints, "Option 2", "Option 3", 2, 3);
            case 2:
                storyPoints.add("this is option 2");
                return new StoryPoint(storyPoints, "Option 4", "Option 5", 4, 5);
            default:
                storyPoints.add("No value found!");
                storyPoints.add("No value found2!");
                storyPoints.add("No value found3!");
                storyPoints.add("No value found4!");
                return new StoryPoint(storyPoints, "No value for button 1", "No value for button 2", 0, 0);
        }
    }
}
