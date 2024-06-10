package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class AddActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    EditText title_input,desc_input,date,time;
    Button button;
    Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        title_input=findViewById(R.id.title_input);
        desc_input=findViewById(R.id.desc_input);
        date=findViewById(R.id.date_input);
        time=findViewById(R.id.time_input);

        button=findViewById(R.id.button);
        spinner=findViewById(R.id.spinner2);
        ArrayAdapter<CharSequence> adap=ArrayAdapter.createFromResource(this,R.array.types, android.R.layout.simple_spinner_item);
        adap.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adap);
        spinner.setOnItemSelectedListener(this);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDatabaseHelper myDB=new MyDatabaseHelper(AddActivity.this);
                Log.d("hox","29x");
                myDB.addEvent(title_input.getText().toString().trim(),desc_input.getText().toString().trim(),date.getText().toString().trim(),spinner.getSelectedItem().toString().trim(),time.getText().toString().trim());
                Log.d("hox","30x");
                Intent intent=new Intent(AddActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String choice=parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}