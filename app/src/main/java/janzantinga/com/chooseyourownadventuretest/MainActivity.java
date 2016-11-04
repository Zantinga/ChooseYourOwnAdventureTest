package janzantinga.com.chooseyourownadventuretest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static GridView storySoFar;
    public static Button button1, button2;
    public static int storyPath;
    public static List<String> storyArray;
    public static ArrayAdapter<String> gridViewArrayAdapter;
    public static StoryPoint storyPoint;

    //TODO: ADD TYPEFACES

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialize();

        storyArray.add("hello");
        gridViewArrayAdapter.notifyDataSetChanged();

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                button1.setVisibility(View.INVISIBLE);
                button2.setVisibility(View.INVISIBLE);
                storyPoint = ProgressStory.getNextStoryPoint(storyPoint.getButton1Value());

                gridViewArrayAdapter.notifyDataSetChanged();

                storySoFar.post(new Runnable() {
                    @Override
                    public void run() {
                        // Select the last row so it will scroll into view...
                        storySoFar.setSelection(gridViewArrayAdapter.getCount() - 1);
                    }
                });
                button1.setText(storyPoint.getButton1Value());
                button2.setText(storyPoint.getButton2Value());
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

                gridViewArrayAdapter.notifyDataSetChanged();

                storySoFar.post(new Runnable() {
                    @Override
                    public void run() {
                        // Select the last row so it will scroll into view...
                        storySoFar.setSelection(gridViewArrayAdapter.getCount() - 1);
                    }
                });
                button1.setText(storyPoint.getButton1Value());
                button2.setText(storyPoint.getButton2Value());
                button1.setVisibility(View.VISIBLE);
                button2.setVisibility(View.VISIBLE);utton2.setVisibility(View.VISIBLE);
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

//        final List<String> storyArray = new ArrayList<String>();
//
//        final ArrayAdapter<String> gridViewArrayAdapter = new ArrayAdapter<String>(
//                this, android.R.layout.simple_list_item_1, storyArray
//        );
//
//        storySoFar = (GridView) findViewById(R.id.gridView1);
//        storySoFar.setAdapter(gridViewArrayAdapter);
//        button1 = (Button) findViewById(R.id.button1);
//        button2 = (Button) findViewById(R.id.button2);
//        button1Value = 1;
//        button2Value = 2;
//
//        storyArray.add(getResources().getString(R.string.intro));
//        gridViewArrayAdapter.notifyDataSetChanged();
//
//        button1.setText(R.string.optionA);
//        button1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                button1.setVisibility(View.INVISIBLE);
//                storyArray.add(updateStory(button1Value));
//                gridViewArrayAdapter.notifyDataSetChanged();
//
//                storySoFar.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        // Select the last row so it will scroll into view...
//                        storySoFar.setSelection(gridViewArrayAdapter.getCount() - 1);
//                    }
//                });
//                button1.setText(updateButton1(button1Value));
//                button2.setText(updateButton2(button1Value));
//                button1.setVisibility(View.VISIBLE);
//            }
//        });
//        button2.setText(R.string.optionB);
//        button2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                button2.setVisibility(View.INVISIBLE);
//                storyArray.add(updateStory(button2Value));
//                gridViewArrayAdapter.notifyDataSetChanged();
//
//                storySoFar.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        // Select the last row so it will scroll into view...
//                        storySoFar.setSelection(gridViewArrayAdapter.getCount() - 1);
//                    }
//                });
//                button1.setText(updateButton1(button2Value));
//                button2.setText(updateButton2(button2Value));
//                button2.setVisibility(View.VISIBLE);
//            }
//        });
//    }

    //TODO: RETURN ARRAYLIST OF STRING
//    protected String updateStory(int storyValue) {
//        switch(storyValue) {
//            case 1:
//                button1Value = 3;
//                button2Value = 2;
//                return getResources().getString(R.string.optionAScenario);
//            case 2:
//                button1Value = 4;
//                button2Value = 1;
//                return getResources().getString(R.string.optionBScenario);
//            case 3:
//                button1Value = 5;
//                button2Value = 5;
//                return getResources().getString(R.string.optionCScenario);
//            case 5:
//                return getResources().getString(R.string.optionDScenario);
//        }
//        return "oops, no story value found!";
//    }

//    protected String updateButton1(int buttonValue) {
//        switch(buttonValue) {
//            case 3:
//                return getResources().getString(R.string.optionC);
//            case 4:
//                return getResources().getString(R.string.optionE);
//            case 5:
//                return getResources().getString(R.string.optionG);
//        }
//        return "oops, no button value found!";
//    }
//
//    protected String updateButton2(int buttonValue) {
//        switch(buttonValue) {
//            case 3:
//                return getResources().getString(R.string.optionD);
//            case 4:
//                return getResources().getString(R.string.optionF);
//            case 5:
//                return getResources().getString(R.string.optionH);
//        }
//        return "oops, no button value found!";
//    }


}
