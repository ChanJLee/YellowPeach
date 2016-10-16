package com.chan.yellowpeach;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.chan.apt.annotations.JoinView;
import com.chan.lib.Peach;
import com.chan.lib.YellowPeach;


public class MainActivity extends AppCompatActivity {

    @JoinView
    View mViewC;

    private Peach mPeach;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mViewC = findViewById(R.id.viewC);

        mPeach = YellowPeach.bind(this);
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewHolder holder = new ViewHolder(findViewById(R.id.viewA));
                ViewHolder.ViewHolderA viewHolder = holder.new ViewHolderA(findViewById(R.id.viewB));
                viewHolder.foo();
                holder.foo();
                mPeach.setVisible(mViewC);
            }
        });

        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                startActivity(intent);
            }
        });
    }

    public class ViewHolder {
        @JoinView
        View mView;
        private Peach mPeach;

        public ViewHolder(View view) {
            mView = view;
            mPeach = YellowPeach.bind(this);
        }

        public void foo() {
            mPeach.setVisible(mView);
        }

        public class ViewHolderA {
            @JoinView
            View mView;
            private Peach mPeach;

            public ViewHolderA(View view) {
                mView = view;
                mPeach = YellowPeach.bind(this);
            }

            public void foo() {
                mPeach.setVisible(mView);
            }
        }
    }
}
