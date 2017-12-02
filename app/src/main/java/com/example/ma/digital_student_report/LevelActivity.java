package com.example.ma.digital_student_report;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.ma.digital_student_report.Slideshow.ResultViewActivity;

public class LevelActivity extends AppCompatActivity {


    Button level1_1Button8;//level 1-1
    Button level1_2Button9;//level 1-2

    Button level2_1Button10;//level 2-1
    Button level2_2Button11;// level 2-2

    Button level3_1Button12;//level 3-1
    Button level3_2Button13;//level 3-2

    Button level4_1Button14;//level 4-1
    Button level4_2Button15;//level 4-2

    String dep;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level);

        Intent intent = getIntent();
        dep = intent.getStringExtra("dep");


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled( true);



        level1_1Button8 = (Button) findViewById(R.id.level1_1ButtonId);
        level1_1Button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startResultActivity("11");

            }
        });
        level1_2Button9 = (Button) findViewById(R.id.level1_2ButtonId);
        level1_2Button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startResultActivity("12");

            }
        });
        level2_1Button10 = (Button) findViewById(R.id.level2_1ButtonId);
        level2_1Button10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startResultActivity("21");

            }
        });
        level2_2Button11 = (Button) findViewById(R.id.level2_2ButtonId);
        level2_2Button11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startResultActivity("22");

            }
        });
        level3_1Button12 = (Button) findViewById(R.id.level3_1ButtonId);
        level3_1Button12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startResultActivity("31");

            }
        });
        level3_2Button13 = (Button) findViewById(R.id.level3_2ButtonId);
        level3_2Button13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startResultActivity("32");

            }
        });
        level4_1Button14 = (Button) findViewById(R.id.level4_1ButtonId);
        level4_1Button14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startResultActivity("41");

            }
        });
        level4_2Button15 = (Button) findViewById(R.id.level4_2ButtonId);
        level4_2Button15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startResultActivity("42");
            }
        });

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home)
        {
            this.finish();
        }

        return super.onOptionsItemSelected(item);
    }

    private void startResultActivity(String level){
        Intent b = new Intent(this, ResultViewActivity.class);
        b.putExtra("dep",dep);
        b.putExtra("lvl",level);
        startActivity(b);
    }
}
