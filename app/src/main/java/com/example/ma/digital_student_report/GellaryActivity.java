package com.example.ma.digital_student_report;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.ma.digital_student_report.Slideshow.StorageActivity;

import java.io.File;
import java.util.ArrayList;

public class GellaryActivity extends AppCompatActivity {


    ImageView academic;
    ImageView hostel;
    ImageView library;
    ImageView n_program;
    ImageView rage_day;
    ImageView picnic;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gellary);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled( true);

        academic = findViewById(R.id.academic);
        hostel = findViewById(R.id.hostel);
        library = findViewById(R.id.library);
        n_program = findViewById(R.id.n_program);
        rage_day = findViewById(R.id.rage_day);
        picnic = findViewById(R.id.picnic);

        academic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startgellary("academic");
            }
        });

        hostel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startgellary("hostel");
            }
        });

        library.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startgellary("library");
            }
        });

        n_program.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startgellary("n_program");
            }
        });

        rage_day.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startgellary("r_days");
            }
        });

        picnic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startgellary("p_toure");
            }
        });
    }


    private void startgellary(String s){
        Intent intent = new Intent(GellaryActivity.this,StorageActivity.class);
        intent.putExtra("location",s);
        startActivity(intent);

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home)
        {
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
