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
    public static int button1Value, button2Value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final List<String> storyArray = new ArrayList<String>();

        final ArrayAdapter<String> gridViewArrayAdapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1, storyArray
        );

        storySoFar = (GridView) findViewById(R.id.gridView1);
        storySoFar.setAdapter(gridViewArrayAdapter);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button1Value = 1;
        button2Value = 2;

        //storySoFar.setMovementMethod(new ScrollingMovementMethod());

        //storySoFar.add("test1");
        storyArray.add(getResources().getString(R.string.intro));
        gridViewArrayAdapter.notifyDataSetChanged();

        button1.setText(R.string.optionA);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                button1.setVisibility(View.INVISIBLE);
                storyArray.add(updateStory(button1Value));
                gridViewArrayAdapter.notifyDataSetChanged();

                storySoFar.post(new Runnable() {
                    @Override
                    public void run() {
                        // Select the last row so it will scroll into view...
                        storySoFar.setSelection(gridViewArrayAdapter.getCount() - 1);
                    }
                });
                button1.setText(updateButton1(button1Value));
                button2.setText(updateButton2(button1Value));
            }
        });
        button2.setText(R.string.optionB);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                storyArray.add(updateStory(button2Value));
                gridViewArrayAdapter.notifyDataSetChanged();

                storySoFar.post(new Runnable() {
                    @Override
                    public void run() {
                        // Select the last row so it will scroll into view...
                        storySoFar.setSelection(gridViewArrayAdapter.getCount() - 1);
                    }
                });
            }
        });
    }

//    // Setup variables
//    public void initialize() {
//        storySoFar = (TextView) findViewById(R.id.storySoFar);
//        storySoFar.setText(R.string.intro);
//    }

    protected String updateStory(int storyValue) {
        switch(storyValue) {
            case 1:
                button1Value = 3;
                button2Value = 4;
                return getResources().getString(R.string.optionAScenario);
            case 2:
                return getResources().getString(R.string.optionBScenario);
        }
        return "oops, no story value found!";
    }

    protected String updateButton1(int buttonValue) {
        switch(buttonValue) {
            case 3:
                return getResources().getString(R.string.optionC);
        }
        return "oops, no button value found!";
    }

    protected String updateButton2(int buttonValue) {
        switch(buttonValue) {
            case 3:
                return getResources().getString(R.string.optionD);
        }
        return "oops, no button value found!";
    }

    @Override
    protected void onDestroy() {
        // The activity is about to be destroyed
        super.onDestroy();
        Log.d("Debug", "onDestroy has been called!");
    }
}
