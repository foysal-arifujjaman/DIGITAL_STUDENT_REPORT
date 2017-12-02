package com.example.ma.digital_student_report;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import com.example.ma.digital_student_report.Slideshow.ResultViewActivity;
import com.example.ma.digital_student_report.Slideshow.StorageActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    Button bsc_button1;// selected button BSC Engineering
    Button bba_button2;// selected button BBA
    Button english_button3;// selected button ENGLISH
    Button law_button4;// selected button LAW

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Toolbar toolbar = findViewById(R.id.toolbar);
        bsc_button1 = findViewById(R.id.bscButtonId);
        bsc_button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "BSC Engineering", Toast.LENGTH_SHORT).show();

                Intent a = new Intent(MainActivity.this, Main2Activity.class);// goto in deperatment
                startActivity(a);
            }
        });

        bba_button2 = findViewById(R.id.bbaButtonId);
        bba_button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startLevelActivity("BBA");
            }
        });

        english_button3 = findViewById(R.id.englishButtonId);
        english_button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startLevelActivity("ENGLISH");


            }
        });
        law_button4 = findViewById(R.id.lawButtonId);
        law_button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startLevelActivity("LAW");

            }
        });

        setSupportActionBar(toolbar);




        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_about) {

            Intent c = new Intent(MainActivity.this, AboutActivity.class);//goto about
            startActivity(c);

        } else if (id == R.id.nav_gallery) {
            Intent intent = new Intent(this, GellaryActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_location) {
            Toast.makeText(this, "Location", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(this, MapsActivity.class);
            startActivity(intent);


        } else if (id == R.id.nav_contact) {
            Toast.makeText(this, "Contact Us", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(this, Contactus.class);
            startActivity(intent);


        } else if (id == R.id.nav_member) {

            Intent d = new Intent(MainActivity.this, FacultyMember.class);//goto faculty member
            startActivity(d);


        } else if (id == R.id.nav_feedback) {
            Intent d = new Intent(MainActivity.this, FeedbackActivity.class);//goto feedback
            startActivity(d);


        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void startLevelActivity(String data) {
        Intent b = new Intent(MainActivity.this, LevelActivity.class);//goto select level
        b.putExtra("dep",data);
        startActivity(b);
    }
}
