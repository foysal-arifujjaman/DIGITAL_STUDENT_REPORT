package com.example.ma.digital_student_report;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import com.bumptech.glide.Glide;
import com.robertlevonyan.views.expandable.Expandable;

import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by ma on 10-Nov-17.
 *
 *faculty member use base adapter in CustomAdapter.java,sample_view.xml and activity_faculty_member.xml file
 */

public class CustomAdapter extends BaseAdapter {

    private String [] teacher_name;
    private String[] designation;
    private static Context context;
    private LayoutInflater inflater;
    private ListView listView;
    public int[] picture ;

    CustomAdapter (Context context, String [] teacher_name,int [] picture, String[] designation,ListView listView){
        this.context = context;
        this.teacher_name = teacher_name;
        this.designation = designation ;
        this.picture = picture;
        this.listView = listView;
        initlistaction();
    }

    private void initlistaction() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Toast.makeText(context, teacher_name[position], Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getCount() {
        return teacher_name.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        if (convertView == null){
            inflater = LayoutInflater.from(context);
            holder= new ViewHolder();
            convertView = inflater.inflate(R.layout.sample_view,parent,false);
            convertView.setTag(holder);
            holder.imageView =  convertView.findViewById(R.id.imageViewid);
            holder.tname = convertView.findViewById(R.id.teacherNameid);
            holder.tdes = convertView.findViewById(R.id.degisnation);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }



        Glide.with(context).load(picture[position]).into(holder.imageView);
        holder.tname.setText(teacher_name[position]);
        holder.tdes.setText(designation[position]);
        return convertView;
    }

    class ViewHolder{
        CircleImageView imageView;
        TextView tname;
        TextView tdes;
    }
}
