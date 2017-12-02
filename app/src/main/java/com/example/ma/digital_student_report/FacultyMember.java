package com.example.ma.digital_student_report;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;
// faculty member use base adapter in CustomAdapter.java,sample_view.xml and activity_faculty_member.xml file
public class FacultyMember extends AppCompatActivity {

    private ListView listView;
    private String [] teacher_name;
    private String [] degisnation;
    public int[] picture = new int[]{R.drawable.shamim_sir, R.drawable.vc_sir, R.drawable.rishi_kesh,
            R.drawable.khaled_sir, R.drawable.awal_sir, R.drawable.sushanta_sir,
            R.drawable.rupa_sir, R.drawable.tonmay_sir, R.drawable.rajarshi_sir,R.drawable.farida_madam,
            R.drawable.rajib_karmakar_sir,R.drawable.asif_sir,R.drawable.golam_murtoza_sir,R.drawable.ashiqul_islam};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_member);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled( true);

        teacher_name = getResources().getStringArray(R.array.t_name);
        degisnation = getResources().getStringArray(R.array.designation_mobile);

        listView =  findViewById(R.id.listViewid);
        CustomAdapter adapter = new CustomAdapter (this,teacher_name,picture,degisnation,listView);
        listView.setAdapter(adapter);

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
