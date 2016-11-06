package janzantinga.com.chooseyourownadventuretest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static GridView storySoFar;
    public static Button button1, button2;
    public static int storyPath;
    public static ArrayList<String> storyArray;
    public static ArrayAdapter<String> gridViewArrayAdapter;
    public static StoryPoint storyPoint;

    //TODO: ADD TYPEFACES

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialize();

        storyArray.add("hello");
        storyPoint.setButton1Info("option 1");
        storyPoint.setButton2Info("option 2");
        storyPoint.setButton1Value(1);
        storyPoint.setButton2Value(2);
        button1.setText(storyPoint.getButton1Info());
        button2.setText(storyPoint.getButton2Info());
        gridViewArrayAdapter.notifyDataSetChanged();

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                button1.setVisibility(View.INVISIBLE);
                button2.setVisibility(View.INVISIBLE);
                storyPoint = ProgressStory.getNextStoryPoint(storyPoint.getButton1Value());
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
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("Debug", "onResume has been called.");
    }

    @Override
    protected void onDestroy() {
        // The activity is about to be destroyed
        super.onDestroy();
        Log.d("Debug", "onDestroy has been called!");
    }
}
