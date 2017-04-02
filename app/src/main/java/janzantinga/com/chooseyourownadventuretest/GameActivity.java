package janzantinga.com.chooseyourownadventuretest;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;

import java.util.ArrayList;

/**
 * Created by Jan Zantinga on 4/1/2017.
 */

public class GameActivity extends Activity {
    public GridView storySoFar;
    public Button button1, button2;
    public static int storyPath;
    public static ArrayList<String> storyArray;
    public static ArrayAdapter<String> gridViewArrayAdapter;
    public static StoryPoint storyPoint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        initialize();

        storyArray.add("hello");
        storyPoint.setButton1Info("option 1");
        storyPoint.setButton2Info("option 2");
        storyPoint.setButton1Value(1);
        storyPoint.setButton2Value(2);
        button1.setText(storyPoint.getButton1Info());
        button2.setText(storyPoint.getButton2Info());
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    }

    public void initialize() {
        storyArray = new ArrayList<String>();
        storyPoint = new StoryPoint(new ArrayList<String>(), "optionA", "optionB", 1, 2);

        gridViewArrayAdapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1, storyArray
        );

        storySoFar = (GridView) findViewById(R.id.gridView1);
        storySoFar.setAdapter(gridViewArrayAdapter);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("Debug", "onStart has been called");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("Debug", "onPause has been called.");

        SharedPreferences settings = getPreferences(0);
        SharedPreferences.Editor editor = settings.edit();

        editor.putInt("story_array_size", storyArray.size());
        for(int i=0; i < storyArray.size(); i++) {
            editor.putString("storyArray_" + i, storyArray.get(i));
        }

        //COMMENT THIS TO TEST FROM BEGINNING
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        ArrayList<String> storyPointToSave = storyPoint.getNewStoryPoints();
        editor.putInt("story_point_size", storyPointToSave.size());
        for(int i=0; i < storyPointToSave.size(); i++) {
            editor.putString("storyPoint_" + i, storyPointToSave.get(i));
        }
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

        editor.putString("button1Info", storyPoint.getButton1Info());
        editor.putString("button2Info", storyPoint.getButton2Info());
        editor.putInt("button1Value", storyPoint.getButton1Value());
        editor.putInt("button2Value", storyPoint.getButton2Value());

        editor.putInt("storyPath", storyPath);

        editor.commit();
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("Debug", "onResume has been called.");
        setContentView(R.layout.activity_game); // Set View
        initialize(); // Sets up blank fields

        SharedPreferences settings = getPreferences(0);

        storyPath = settings.getInt("storyPath", 0);
        if(storyPath == 0) {
            initialize();

            storyArray.add("hello");
            storyPoint.setButton1Info("option 1");
            storyPoint.setButton2Info("option 2");
            storyPoint.setButton1Value(1);
            storyPoint.setButton2Value(2);
            button1.setText(storyPoint.getButton1Info());
            button2.setText(storyPoint.getButton2Info());
            gridViewArrayAdapter.notifyDataSetChanged();
        }
        if(storyPath != 0) {

            int story_array_size = settings.getInt("story_array_size", 0);
            for (int i = 0; i < story_array_size; i++) {
                storyArray.add(settings.getString("storyArray_" + i, "Error"));
            }

            gridViewArrayAdapter.notifyDataSetChanged();

            storyPoint.setButton1Value(settings.getInt("button1Value", 0));
            storyPoint.setButton2Value(settings.getInt("button2Value", 0));
            storyPoint.setButton1Info(settings.getString("button1Info", "Error"));
            storyPoint.setButton2Info(settings.getString("button2Info", "Error"));
            button1.setText(storyPoint.getButton1Info());
            button2.setText(storyPoint.getButton2Info());

            gridViewArrayAdapter.notifyDataSetChanged();
            storySoFar.post(new Runnable() {
                @Override
                public void run() {
                    // Select the last row so it will scroll into view...
                    storySoFar.setSelection(gridViewArrayAdapter.getCount() - 1);
                }
            });
        }

        gridViewArrayAdapter.notifyDataSetChanged();

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                button1.setVisibility(View.INVISIBLE);
                button2.setVisibility(View.INVISIBLE);
                storyPoint = ProgressStory.getNextStoryPoint(storyPoint.getButton1Value());
                storyPath = storyPoint.getButton1Value();
                final ArrayList<String> newStoryArray = storyPoint.getNewStoryPoints();

                for(int i = 0; i < newStoryArray.size(); i++) {
                    storyArray.add(newStoryArray.get(i));
                    gridViewArrayAdapter.notifyDataSetChanged();
                    storySoFar.post(new Runnable() {
                        @Override
                        public void run() {
                            // Select the last row so it will scroll into view...
                            storySoFar.setSelection(gridViewArrayAdapter.getCount() - 1);
                        }
                    });
                }
                button1.setText(storyPoint.getButton1Info());
                button2.setText(storyPoint.getButton2Info());
                button1.setVisibility(View.VISIBLE);
                button2.setVisibility(View.VISIBLE);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                button1.setVisibility(View.INVISIBLE);
                button2.setVisibility(View.INVISIBLE);
                storyPoint = ProgressStory.getNextStoryPoint(storyPoint.getButton2Value());
                storyPath = storyPoint.getButton2Value();
                final ArrayList<String> newStoryArray = storyPoint.getNewStoryPoints();

                gridViewArrayAdapter.notifyDataSetChanged();

                for(int i = 0; i < newStoryArray.size(); i++) {
                    storyArray.add(newStoryArray.get(i));
                    gridViewArrayAdapter.notifyDataSetChanged();
                    storySoFar.post(new Runnable() {
                        @Override
                        public void run() {
                            // Select the last row so it will scroll into view...
                            storySoFar.setSelection(gridViewArrayAdapter.getCount() - 1);
                        }
                    });
                }
                button1.setText(storyPoint.getButton1Info());
                button2.setText(storyPoint.getButton2Info());
                button1.setVisibility(View.VISIBLE);
                button2.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    protected void onDestroy() {
        // The activity is about to be destroyed
        super.onDestroy();
        Log.d("Debug", "onDestroy has been called!");
    }
}
