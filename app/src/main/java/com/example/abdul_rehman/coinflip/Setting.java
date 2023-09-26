package com.example.abdul_rehman.coinflip;

import android.content.Intent;
import android.preference.PreferenceFragment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

public class Setting extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_setting);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getFragmentManager().beginTransaction().replace(android.R.id.content,new Setting.mFrag()).commit();
    }

    public static class mFrag extends PreferenceFragment {
        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.pref_main);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent s = new Intent(getApplicationContext(),MainActivity.class);
        s.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        s.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(s);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}
