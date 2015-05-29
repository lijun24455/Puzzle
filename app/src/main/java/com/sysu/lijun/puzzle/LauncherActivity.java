package com.sysu.lijun.puzzle;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.sysu.lijun.Context.LevelConfig;
import com.sysu.lijun.umeng;

import java.io.File;


public class LauncherActivity extends Activity {

    private Button mBtnQuickStart;
    private Button mBtnChoseLevel;
    private Button mBtnQuit;

    private SharedPreferences mPreferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_launcher);


        Log.i("test", umeng.getDeviceInfo(this));
        mPreferences = getSharedPreferences("User sp", MODE_PRIVATE);
        editor = mPreferences.edit();

        initView();

        mBtnQuickStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putInt("LEVEL", LevelConfig.LEVEL_DEFAULT);
                editor.commit();
                Intent intent = new Intent(LauncherActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        mBtnChoseLevel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LauncherActivity.this, ChoseLevelActivity.class);
                startActivity(intent);
            }
        });

        mBtnQuit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



    }

    private void initView() {
        mBtnQuickStart = (Button) findViewById(R.id.btn_quick_start);
        mBtnQuit = (Button) findViewById(R.id.btn_quit);
        mBtnChoseLevel = (Button) findViewById(R.id.btn_chose_level);

    }

}
