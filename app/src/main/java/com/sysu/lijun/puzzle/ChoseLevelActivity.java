package com.sysu.lijun.puzzle;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.GridLayout;

import com.sysu.lijun.Context.LevelConfig;
import com.sysu.lijun.Context.PreferencesItems;


public class ChoseLevelActivity extends Activity {

    private GridLayout gridLayout;
    private SharedPreferences mPreferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_chose_level);

        gridLayout = (GridLayout) findViewById(R.id.gl_chose_level);

        mPreferences = getSharedPreferences(PreferencesItems.PREFERENCES_TITLE, MODE_PRIVATE);
        editor = mPreferences.edit();

    }

    public void selectLevel(View view) {

        switch (view.getId()){
            case R.id.btn_level_3x3:
                editor.putInt(PreferencesItems.CURRENT_USER_LEVEL, LevelConfig.LEVEL_3X3);
                break;
            case R.id.btn_level_4x4:
                editor.putInt(PreferencesItems.CURRENT_USER_LEVEL, LevelConfig.LEVEL_4X4);
                break;
            case R.id.btn_level_5x5:
                editor.putInt(PreferencesItems.CURRENT_USER_LEVEL, LevelConfig.LEVEL_5X5);
                break;
            case R.id.btn_level_6x6:
                editor.putInt(PreferencesItems.CURRENT_USER_LEVEL, LevelConfig.LEVEL_6X6);
                break;
        }
        editor.commit();
        Intent intent = new Intent(ChoseLevelActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
