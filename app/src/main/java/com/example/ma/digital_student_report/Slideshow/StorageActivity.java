package com.example.ma.digital_student_report.Slideshow;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.ma.digital_student_report.R;

import java.io.File;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class StorageActivity extends AppCompatActivity {

    private static File dir1;
    private static File dir2;


    private static ViewPager mPager;
    private static int currentPage = 0;
    private ArrayList<Uri> images;
    private final String[] okFileExtensions = new String[]{"jpg", "png", "gif", "jpeg"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gellary);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled( true);

        images = new ArrayList<>();

        dir1 = new File(Environment.getExternalStorageDirectory().getPath());



        String location = getIntent().getStringExtra("location");

        dir1 = new File(dir1.getPath() + "/"+location);

        mPager = findViewById(R.id.pager);
        if (dir1.exists()&& dir1.isDirectory()){
            for (File file : dir1.listFiles()) {

                if (accept(file)) {

                    images.add(Uri.fromFile(file));
                }

            }
            startslide();
        }else {
            Toast.makeText(this, "folder not exits", Toast.LENGTH_SHORT).show();
        }

    }


    public boolean accept(File file) {
        for (String extension : okFileExtensions) {
            if (file.getName().toLowerCase().endsWith(extension)) {
                return true;
            }
        }
        return false;
    }


    private void startslide() {


        if (images.size()==0){
            Toast.makeText(this, "no images in the folder", Toast.LENGTH_SHORT).show();
            return;
        }
        // Auto start of viewpager

        images.clear();
        for (File file : dir1.listFiles()) {

            if (accept(file)) {

                images.add(Uri.fromFile(file));
            }

        }
        mPager.setAdapter(new ViewPagerAdapter(this, images));

        currentPage =0;
        mPager.setCurrentItem(currentPage);

        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == images.size()) {
                    currentPage = 0;
                }
                mPager.setCurrentItem(currentPage++);
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 2000, 2000);

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
