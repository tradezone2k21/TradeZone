package com.example.everythingstore.buyer.register;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;


import com.example.everythingstore.R;
import com.google.android.material.tabs.TabLayout;

import androidx.fragment.app.FragmentManager;


public class BuyerRegister extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager2 pager2;
    RegisterBuyerFragmentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyer_register);

        tabLayout=findViewById(R.id.register_buyer_tab_layout);
        pager2=findViewById(R.id.register_buyer_view_pager);

        FragmentManager fm=getSupportFragmentManager();
        adapter=new RegisterBuyerFragmentAdapter(fm,getLifecycle());
        pager2.setAdapter(adapter);

        tabLayout.addTab(tabLayout.newTab().setText("First"));
        tabLayout.addTab(tabLayout.newTab().setText("Second"));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                pager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        pager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
//                super.onPageSelected(position);
                tabLayout.selectTab(tabLayout.getTabAt(position));
            }
        });

    }
}