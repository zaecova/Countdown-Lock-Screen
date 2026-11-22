package com.zaecova.countdownwidget;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TextView message = new TextView(this);
        message.setText(getString(R.string.app_instructions));
        message.setTextSize(20);
        message.setTextColor(0xFF241B15);
        message.setPadding(32, 48, 32, 32);
        setContentView(message);
    }
}
