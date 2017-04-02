package janzantinga.com.chooseyourownadventuretest;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.view.MotionEvent;
import android.view.View;

//extends View because we're making our own custom view
public class TitleView extends View {

    private Bitmap titleGraphic;
    private boolean screenPressed;
    private RectF screenBounds;
    Context myContext;
    private int screenW;
    private int screenH;

    public TitleView(Context context) {
        super(context);
        myContext = context;
        titleGraphic = BitmapFactory.decodeResource(getResources(), R.drawable.crumpled_paper);
        screenBounds = new RectF();
    }

    @Override
    public void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        screenW = w;
        screenH = h;
    }

    //overriding onDraw method because I'm adding my own graphic
    @Override
    protected void onDraw(Canvas canvas) {
        //sets the rectangle coordinates
        screenBounds.set(0, 0, screenW, screenH);

        //scales the titlegraphic to the screen
        canvas.drawBitmap(titleGraphic, null, screenBounds, null);
    }

    //handles when user touches screen
    public boolean onTouchEvent(MotionEvent event) {
        int eventAction = event.getAction();

        switch (eventAction){
            case MotionEvent.ACTION_DOWN://screen pressed
                screenPressed = true;
                break;
            case MotionEvent.ACTION_MOVE://finger moves while pressed
                break;
            case MotionEvent.ACTION_UP://screen unpressed
                if(screenPressed){
                    Intent gameIntent = new Intent(myContext, GameActivity.class);
                    myContext.startActivity(gameIntent);
                    System.exit(0); //I added this so that we can never get back to the splash screen after the start
                }
                screenPressed = false;
                break;
        }
        invalidate();
        return true;
    }
}
