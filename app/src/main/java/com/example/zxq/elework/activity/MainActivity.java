package com.example.zxq.elework.activity;

import android.os.Bundle;

import com.example.zxq.elework.R;
import com.example.zxq.elework.activity.baseActivity.BaseActivity;

public class MainActivity extends BaseActivity {

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
