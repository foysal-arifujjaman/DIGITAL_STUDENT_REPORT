package com.example.ma.digital_student_report.Slideshow;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.ma.digital_student_report.R;
import com.example.ma.digital_student_report.ResultAdapter;

public class ResultViewActivity extends AppCompatActivity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_view);
        listView = findViewById(R.id.result_list);

        Intent intent = getIntent();
        String  dep = intent.getStringExtra("dep");
        String  lvl = intent.getStringExtra("lvl");
        listView.setAdapter(new ResultAdapter(getBaseContext(),dep,lvl));

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled( true);

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
