package com.example.ma.digital_student_report;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;


public class Contactus extends AppCompatActivity {
    ImageView home;
    ImageView internet;
    ImageView telephone;
    ImageView fax;
    ImageView mobile;
    ImageView mail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactus);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled( true);

        home = findViewById(R.id.image_home);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Address", Toast.LENGTH_SHORT).show();
            }
        });

        internet = findViewById(R.id.image_internet);
        internet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Website", Toast.LENGTH_SHORT).show();

            }
        });
        telephone = findViewById(R.id.image_telephone);
        telephone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Telephone number", Toast.LENGTH_SHORT).show();
            }
        });

        fax = findViewById(R.id.image_fax);
        fax.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Fax", Toast.LENGTH_SHORT).show();
            }
        });

        mobile = findViewById(R.id.image_mobile);
        mobile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Mobile number", Toast.LENGTH_SHORT).show();
            }
        });

        mail = findViewById(R.id.image_email);
        mail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Email", Toast.LENGTH_SHORT).show();
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
}
