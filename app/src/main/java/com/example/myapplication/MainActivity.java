package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager2 pager2;
    FragmentAdapter adapter;
    DrawerLayout dl;
//    ActionBarDrawerToggle abdt;

    FloatingActionButton add_btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("HIx");
        Log.d("lix","45x");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * Add button
         **/
        add_btn=findViewById(R.id.fab);
        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,AddActivity.class);
                startActivity(intent);
            }
        });

        Log.d("lix","45x");
        Toolbar toolbar = findViewById(R.id.tb);
        Log.d("lix","69x");
        setSupportActionBar(toolbar);
        Log.d("lix","69x");
        dl = findViewById(R.id.dl);
        NavigationView navigationView = findViewById(R.id.navigationView);
        Log.d("lix","69x");
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if(id==R.id.item1){
                    Intent intent=new Intent(MainActivity.this,MainActivity.class);
                    startActivity(intent);
                    Toast.makeText(MainActivity.this, "Home", Toast.LENGTH_SHORT).show();
                }else if(id==R.id.item2){
                    Intent intent=new Intent(MainActivity.this,Calendar.class);
                    startActivity(intent);
                    Toast.makeText(MainActivity.this, "Calendar", Toast.LENGTH_SHORT).show();
                }
                dl.closeDrawer(GravityCompat.START);
                return true;
            }
        });


        ActionBarDrawerToggle abdt = new ActionBarDrawerToggle(this,dl,toolbar,R.string.Open, R.string.Close);
        dl.addDrawerListener(abdt);
        abdt.syncState();

//        if(savedInstanceState==null){
//            getSupportFragmentManager().beginTransaction().replace(R.id.frame, new HomeFragment()).commit();
//            navigationView.setCheckedItem(R.id.item1);
//        }
        



        tabLayout = findViewById(R.id.tab_layout);
        pager2 = findViewById(R.id.view_pager2);

        FragmentManager fm = getSupportFragmentManager();
        adapter = new FragmentAdapter(fm, getLifecycle());
        pager2.setAdapter(adapter);

        tabLayout.addTab(tabLayout.newTab().setText("Study Plan"));
        tabLayout.addTab(tabLayout.newTab().setText("Lectures"));
        tabLayout.addTab(tabLayout.newTab().setText("Exams"));
        tabLayout.addTab(tabLayout.newTab().setText("Assign."));




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
                tabLayout.selectTab(tabLayout.getTabAt(position));
            }
        });




    }
    @Override
    public void onBackPressed() {
        if (dl.isDrawerOpen(GravityCompat.START)) {
            dl.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }




}