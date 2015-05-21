package com.sysu.lijun.puzzle;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;


public class LauncherActivity extends Activity {

    private Button mBtnQuickStart;
    private Button mBtnQuit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_launcher);

        initView();

        mBtnQuickStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LauncherActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

    }

    private void initView() {
        mBtnQuickStart = (Button) findViewById(R.id.btn_quick_start);
        mBtnQuit = (Button) findViewById(R.id.btn_quit);

    }

}
