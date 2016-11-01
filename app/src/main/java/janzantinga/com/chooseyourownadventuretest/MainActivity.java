package janzantinga.com.chooseyourownadventuretest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public static TextView storySoFar;
    public static Button button1, button2;
    public static int storyPath;
    public static int button1Value, button2Value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        storySoFar = (TextView) findViewById(R.id.storySoFar);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button1Value = 1;
        button2Value = 2;

        storySoFar.setMovementMethod(new ScrollingMovementMethod());

        storySoFar.setText(R.string.intro);
        button1.setText(R.string.optionA);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateStory(button1Value);
            }
        });
        button2.setText(R.string.optionB);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateStory(button2Value);
            }
        });
    }

//    // Setup variables
//    public void initialize() {
//        storySoFar = (TextView) findViewById(R.id.storySoFar);
//        storySoFar.setText(R.string.intro);
//    }

    protected void updateStory(int storyValue) {
        switch(storyValue) {
            case 1:
//                storySoFar.append("\r\n");
//                storySoFar.append("test");
                // TODO: FIGURE OUT WHY I CAN NOT APPEND R.STRING.OPTIONASCENARIO
                break;
            case 2:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        // The activity is about to be destroyed
        super.onDestroy();
        Log.d("Debug", "onDestroy has been called!");
    }
}
