package janzantinga.com.chooseyourownadventuretest;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jan Zantinga on 11/3/2016.
 */

public class ProgressStory {

    public static StoryPoint getNextStoryPoint(int nextStoryPoint) {
        List<String> storyPoints = new ArrayList<String>();
        switch(nextStoryPoint) {
            case 1:
                break;
            default:
                return new StoryPoint();
        }
        return new StoryPoint();
    }
}
