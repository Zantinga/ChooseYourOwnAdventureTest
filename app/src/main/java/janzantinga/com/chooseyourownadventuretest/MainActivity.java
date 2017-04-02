package janzantinga.com.chooseyourownadventuretest;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TitleView tView = new TitleView(this);
        setContentView(tView); //sets the view to the splash screen
    }
}
