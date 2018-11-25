package com.example.zxq.elework;

import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import static com.example.zxq.elework.R.id.bottom_tools_view;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        //BottomNavigationView navigationView = (BottomNavigationView)findViewById(R.id
        //.bottom_tools_view);
        //navigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

//    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
//            = new BottomNavigationView.OnNavigationItemSelectedListener() {
//        @Override
//        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//            switch (item.getItemId()) {
//                case R.id.menu_bottom_tools_eat:
//                    return true;
//                case R.id.menu_bottom_tools_order:
//                    return true;
//                case R.id.menu_bottom_tools_personal:
//                    return true;
//            }
//            return false;
//        }
//
//    };

}
