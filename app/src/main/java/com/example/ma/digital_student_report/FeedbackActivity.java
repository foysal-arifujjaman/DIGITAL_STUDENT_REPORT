package com.example.ma.digital_student_report;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ma.digital_student_report.exview.MyExpandable;


public class FeedbackActivity extends AppCompatActivity {


    MyExpandable expand1;
    MyExpandable expand2;
    MyExpandable expand3;


    TextView mem_name_1;
    TextView mem_name_2;
    TextView mem_name_3;

    TextView mem_roll_1;
    TextView mem_roll_2;
    TextView mem_roll_3;

    Button email1;
    Button email2;
    Button email3;


    Button call1;
    Button call2;
    Button call3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exlistbase);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        expand1 = findViewById(R.id.expandable1);
        expand2 = findViewById(R.id.expandable2);
        expand3 = findViewById(R.id.expandable3);

        mem_name_1 = expand1.findViewById(R.id.feedbackName);
        mem_name_1.setText("Shekh Farzana");
        mem_name_2 = expand2.findViewById(R.id.feedbackName);
        mem_name_2.setText("Sujia Begum");
        mem_name_3 = expand3.findViewById(R.id.feedbackName);
        mem_name_3.setText("Arifujjaman Foysal");


        mem_roll_1 = expand1.findViewById(R.id.feedbackRoll);
        mem_roll_1.setText("Roll: 24003");
        mem_roll_2 = expand2.findViewById(R.id.feedbackRoll);
        mem_roll_2.setText("Roll:24022");
        mem_roll_3 = expand3.findViewById(R.id.feedbackRoll);
        mem_roll_3.setText("roll:25008");

        email1 = expand1.findViewById(R.id.btn_email);
        email2 = expand2.findViewById(R.id.btn_email);
        email3 = expand3.findViewById(R.id.btn_email);


        email1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sent_mail("farzanatanna@gmail.com");
            }
        });


        email2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sent_mail("sujiashammi3@gmail.com");
            }
        });


        email3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sent_mail("afoysalstudent@gmail.com");
            }
        });


        call1 = expand1.findViewById(R.id.call);
        call2 = expand2.findViewById(R.id.call);
        call3 = expand3.findViewById(R.id.call);


        call1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                make_call("01779082905");
            }
        });


        call2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                make_call("01771689444");

            }
        });


        call3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                make_call("01684409099");

            }
        });


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {

            case 1: {

                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getApplicationContext(), "Permission granted", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(getApplicationContext(), "Permission denied", Toast.LENGTH_SHORT).show();
                }
                return;
            }
        }
    }

    public boolean isPermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(android.Manifest.permission.CALL_PHONE)
                    == PackageManager.PERMISSION_GRANTED) {

                return true;
            } else {

                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, 1);
                return false;
            }
        } else { //permission is automatically granted on sdk<23 upon installation

            return true;
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            this.finish();
        }

        return super.onOptionsItemSelected(item);
    }


    private void sent_mail(String email) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/html");
        intent.putExtra(Intent.EXTRA_EMAIL, email);
        intent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
        intent.putExtra(Intent.EXTRA_TEXT, "I'm email body.");
        startActivity(Intent.createChooser(intent, "Send Email"));
    }


    private void make_call(String number) {
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + number));
        if (isPermissionGranted()) {
            startActivity(intent);
        }

    }
}
