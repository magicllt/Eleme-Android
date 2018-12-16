package com.example.zxq.elework.view.impl;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.zxq.elework.R;
import com.example.zxq.elework.view.base.BaseActivity;

import java.net.Inet4Address;

public class MainActivity extends BaseActivity {

    TakeAwayFragment takeAwayFragment = new TakeAwayFragment();

    MyTabFragment myTabFragment = new MyTabFragment();


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.menu_bottom_tools_eat:
                    replaceFragment(takeAwayFragment);
                    return true;
                case R.id.menu_bottom_tools_order:
                    replaceFragment(takeAwayFragment);
                    return true;
                case R.id.menu_bottom_tools_personal:
                    replaceFragment(myTabFragment);
                    return true;
            }
            return false;
        }
    };

    static  public void actionStart(Context context){
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.activity_main_bottom_tools_view);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.setSelectedItemId(navigation.getMenu().getItem(0).getItemId());
    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.main_framelayout, fragment);
        transaction.commit();
    }
}
