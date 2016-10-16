package com.chan.yellowpeach;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.chan.apt.annotations.JoinView;
import com.chan.lib.Peach;
import com.chan.lib.YellowPeach;

public class Main2Activity extends AppCompatActivity {

    @JoinView
    View mView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        mView = findViewById(R.id.text);

        Peach peach = YellowPeach.bind(this);
        peach.setVisible(mView);
    }
}
