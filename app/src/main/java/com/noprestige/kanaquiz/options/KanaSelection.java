package com.noprestige.kanaquiz.options;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.noprestige.kanaquiz.AppTools;
import com.noprestige.kanaquiz.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

public class KanaSelection extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabbed_screen);

        AppTools.initializeManagers(getApplicationContext());

        ViewPager viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(new KanaSelectionPager(getSupportFragmentManager(), this));
        ((TabLayout) findViewById(R.id.tabLayout)).setupWithViewPager(viewPager);
    }
}
