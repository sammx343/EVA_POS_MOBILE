package Classes;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by smayor on 19/10/2017.
 */

public class TypeWritter extends AppCompatActivity {
    CharSequence mText;
    String startingText;
    int mIndex;
    long mDelay;
    TextView textView;
    public boolean write = true;
    public void animateText(CharSequence text, long delay, TextView tv, String startingText) {
        mText = text;
        mIndex = 0;
        mDelay = delay;
        textView = tv;
        this.startingText = startingText;
        textView.setText(startingText);
        mHandler.removeCallbacks(characterAdder);
        mHandler.postDelayed(characterAdder, mDelay);
    }

    private Handler mHandler = new Handler();
    private Runnable characterAdder = new Runnable() {
        @Override
        public void run() {
            if(write){
                textView.setText(startingText + " " + mText.subSequence(0, mIndex++));
                if(mIndex <= mText.length()) {
                    mHandler.postDelayed(characterAdder, mDelay);
                }
            }
        }
    };
}
