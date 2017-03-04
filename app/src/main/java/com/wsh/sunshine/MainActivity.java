package com.wsh.sunshine;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.wsh.sunshine.utils.XLog;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init(){
        XLog.enableLog();
        XLog.i("---init---");
    }
}
