package com.example.ma.digital_student_report;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {


    Button cse_button5;// selected button CSE
    Button ece_button6;// selected button ECE
    Button eee_button7;// selected button EEE

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        // adding back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        cse_button5 = (Button) findViewById(R.id.cseButtonId);
        cse_button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startLevelActivity("CSE");

            }
        });

        ece_button6 = (Button) findViewById(R.id.eceButtonId);
        ece_button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startLevelActivity("ECE");
            }
        });

        eee_button7 = (Button) findViewById(R.id.eeeButtonId);
        eee_button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startLevelActivity("EEE");
            }
        });


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            this.finish();
        }

        return super.onOptionsItemSelected(item);
    }

    private void startLevelActivity(String data) {
        //Toast.makeText(getApplicationContext(), data, Toast.LENGTH_SHORT).show();
        Intent b = new Intent(this, LevelActivity.class);//goto select level
        b.putExtra("dep", data);
        startActivity(b);
    }
}
