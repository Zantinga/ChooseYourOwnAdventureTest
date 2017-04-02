package janzantinga.com.chooseyourownadventuretest;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;

import java.util.ArrayList;

public class MainActivity extends Activity {
    public GridView storySoFar;
    public Button button1, button2;
    public static int storyPath;
    public static ArrayList<String> storyArray;
    public static ArrayAdapter<String> gridViewArrayAdapter;
    public static StoryPoint storyPoint;

    //TODO: ADD TYPEFACES

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TitleView tView = new TitleView(this);
        setContentView(tView); //sets the view to the splash screen
    }
}
