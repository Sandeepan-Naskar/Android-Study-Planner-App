package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;
import com.google.android.material.navigation.NavigationView;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Calendar extends AppCompatActivity {

    MyDatabaseHelper myDB;

    DrawerLayout dl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("hix");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

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
                    Intent intent=new Intent(Calendar.this,MainActivity.class);
                    startActivity(intent);
                    Toast.makeText(Calendar.this, "Home", Toast.LENGTH_SHORT).show();
                }else if(id==R.id.item2){
                    Intent intent=new Intent(Calendar.this,Calendar.class);
                    startActivity(intent);
                    Toast.makeText(Calendar.this, "Calendar", Toast.LENGTH_SHORT).show();
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
        final CompactCalendarView compactCalendarView = (CompactCalendarView) findViewById(R.id.compactcalendar_view);
        // Set first day of week to Monday, defaults to Monday so calling setFirstDayOfWeek is not necessary
        // Use constants provided by Java Calendar class
        //compactCalendarView.setFirstDayOfWeek(Calendar.MONDAY);
        myDB=new MyDatabaseHelper(this);
        Cursor cursor=myDB.readAllData();
        while (cursor.moveToNext()){
            String date=cursor.getString(4);
            String type=cursor.getString(1);
            Date dat=new Date();
            try {
                dat=new SimpleDateFormat("dd/MM/yyyy").parse(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            long datmi=dat.getTime();
            Event e=new Event(Color.GREEN, datmi,type);
            compactCalendarView.addEvent(e);
        }
        List<Event> events = compactCalendarView.getEvents(new Date());
        int sp_no=0;
        int lec_no=0;
        int ass_no=0;
        int exam_no=0;
        Log.d("hixy","blah");
        if(events.size()==0){

        }else{
            for(int i=0;i<events.size();i++){
                Log.d("hixy",events.get(i).toString());
                if(events.get(i).getData().equals("Study Plan")) sp_no++;
                else if (events.get(i).getData().equals("Exam")) exam_no++;
                else if (events.get(i).getData().equals("Assignment")) ass_no++;
                else if (events.get(i).getData().equals("Lecture")) lec_no++;

            }
        }

        TextView sp_text=findViewById(R.id.sp_no);
        TextView lec_text=findViewById(R.id.lect_no);
        TextView ass_text=findViewById(R.id.ass_no);
        TextView exam_text=findViewById(R.id.exam_no);
        TextView date_sel=findViewById(R.id.datepicked);


        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String strDate = dateFormat.format(new Date());

        date_sel.setText(strDate);
        sp_text.setText(sp_no+"");
        lec_text.setText(lec_no+"");
        ass_text.setText(ass_no+"");
        exam_text.setText(exam_no+"");



        // Add event 1 on Sun, 07 Jun 2015 18:20:51 GMT



        // Added event 2 GMT: Sun, 07 Jun 2015 19:10:51 GMT



        // Query for events on Sun, 07 Jun 2015 GMT.
        // Time is not relevant when querying for events, since events are returned by day.
        // So you can pass in any arbitary DateTime and you will receive all events for that day.
//        List<Event> events = compactCalendarView.getEvents(1433701251000L); // can also take a Date object

        // events has size 2 with the 2 events inserted previously
//        Log.d(TAG, "Events: " + events);

        // define a listener to receive callbacks when certain events happen.
        compactCalendarView.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {
                List<Event> events = compactCalendarView.getEvents(dateClicked);
                int sp_no=0;
                int lec_no=0;
                int ass_no=0;
                int exam_no=0;
                Log.d("hixy","blah");
                if(events.size()==0){

                }else{
                    for(int i=0;i<events.size();i++){
                        Log.d("hixy",events.get(i).toString());
                        if(events.get(i).getData().equals("Study Plan")) sp_no++;
                        else if (events.get(i).getData().equals("Exam")) exam_no++;
                        else if (events.get(i).getData().equals("Assignment")) ass_no++;
                        else if (events.get(i).getData().equals("Lecture")) lec_no++;

                    }
                }

                TextView sp_text=findViewById(R.id.sp_no);
                TextView lec_text=findViewById(R.id.lect_no);
                TextView ass_text=findViewById(R.id.ass_no);
                TextView exam_text=findViewById(R.id.exam_no);
                TextView date_sel=findViewById(R.id.datepicked);


                DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                String strDate = dateFormat.format(dateClicked);

                date_sel.setText(strDate);
                sp_text.setText(sp_no+"");
                lec_text.setText(lec_no+"");
                ass_text.setText(ass_no+"");
                exam_text.setText(exam_no+"");


                //Log.d(TAG, "Day was clicked: " + dateClicked + " with events " + events);
            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {

                String[] arrOfStr = firstDayOfNewMonth.toString().split(" ", 5);
                String month=arrOfStr[1];
                System.out.println("yoxyo");
                System.out.println(arrOfStr[1]);
                TextView mon=findViewById(R.id.month);
                mon.setText(month);

                ///Log.d(TAG, "Month was scrolled to: " + firstDayOfNewMonth);
            }
        });
    }
}