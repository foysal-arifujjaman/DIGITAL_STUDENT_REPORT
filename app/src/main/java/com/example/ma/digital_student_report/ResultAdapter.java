package com.example.ma.digital_student_report;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by ma on 10-Nov-17.
 * <p>
 * faculty member use base adapter in CustomAdapter.java,sample_view.xml and activity_faculty_member.xml file
 */

public class ResultAdapter extends BaseAdapter {


    private static Context context;
    private LayoutInflater inflater;

    private LinkedHashMap<String, DataModel> data;

    public ResultAdapter(Context context, String dep, String lvl) {
        this.context = context;

        data = new LinkedHashMap<>();
        fetchData(dep, lvl);
    }


    private void fetchData(String dep, String lvl) {


        DatabaseReference db = GetFirebaseInstance.GetInstance().getReference("results");
        Query query = db.orderByChild("current_sem").equalTo(lvl + "_" + dep.toLowerCase());
        query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                addSnapToData(dataSnapshot);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                addSnapToData(dataSnapshot);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                data.remove(dataSnapshot.getKey());
                notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void addSnapToData(DataSnapshot dataSnapshot) {
        DataModel dataModel = new DataModel();
        String name = dataSnapshot.child("name").getValue() == null ? "Not Available" : dataSnapshot.child("name").getValue().toString();
        String roll = dataSnapshot.child("roll").getValue() == null ? "Not Available" : dataSnapshot.child("roll").getValue().toString();
        String due = dataSnapshot.child("due").getValue() == null ? "Not Available" : dataSnapshot.child("due").getValue().toString();
        String r11 = dataSnapshot.child("11").getValue() == null ? "Not Available" : dataSnapshot.child("11").getValue().toString();
        String r12 = dataSnapshot.child("12").getValue() == null ? "Not Available" : dataSnapshot.child("12").getValue().toString();
        String r21 = dataSnapshot.child("21").getValue() == null ? "Not Available" : dataSnapshot.child("21").getValue().toString();
        String r22 = dataSnapshot.child("22").getValue() == null ? "Not Available" : dataSnapshot.child("22").getValue().toString();
        String r31 = dataSnapshot.child("31").getValue() == null ? "Not Available" : dataSnapshot.child("31").getValue().toString();
        String r32 = dataSnapshot.child("32").getValue() == null ? "Not Available" : dataSnapshot.child("32").getValue().toString();
        String r41 = dataSnapshot.child("41").getValue() == null ? "Not Available" : dataSnapshot.child("41").getValue().toString();
        String r42 = dataSnapshot.child("42").getValue() == null ? "Not Available" : dataSnapshot.child("42").getValue().toString();

        dataModel.setName(name);
        dataModel.setRoll(roll);
        dataModel.setDue(due);
        dataModel.setR11(r11);
        dataModel.setR12(r12);
        dataModel.setR21(r21);
        dataModel.setR22(r22);
        dataModel.setR31(r31);
        dataModel.setR32(r32);
        dataModel.setR41(r41);
        dataModel.setR42(r42);

        data.put(dataSnapshot.getKey().toString(), dataModel);
        notifyDataSetChanged();
    }


    @Override
    public int getCount() {
        return data.size();
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
        if (convertView == null) {
            inflater = LayoutInflater.from(context);
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.result_list_base, parent, false);
            convertView.setTag(holder);


            holder.name = convertView.findViewById(R.id.reslt_name);
            holder.roll = convertView.findViewById(R.id.reslt_roll);
            holder.due = convertView.findViewById(R.id.reslt_due);

            holder.r11 = convertView.findViewById(R.id.one_one);
            holder.r12 = convertView.findViewById(R.id.one_two);

            holder.r21 = convertView.findViewById(R.id.two_one);
            holder.r22 = convertView.findViewById(R.id.two_two);

            holder.r31 = convertView.findViewById(R.id.three_one);
            holder.r32 = convertView.findViewById(R.id.three_two);

            holder.r41 = convertView.findViewById(R.id.four_one);
            holder.r42 = convertView.findViewById(R.id.four_two);


        } else {
            holder = (ViewHolder) convertView.getTag();
        }


        ArrayList<DataModel> list = new ArrayList<DataModel>(data.values());

        holder.name.setText("Name: " + list.get(position).getName());
        holder.roll.setText("Roll: " + list.get(position).getRoll());
        holder.due.setText("Due: " + list.get(position).getDue());

        holder.r11.setText("1-1: " + list.get(position).getR11());
        holder.r12.setText("1-2: " + list.get(position).getR12());

        holder.r21.setText("2-1: " + list.get(position).getR21());
        holder.r22.setText("2-2: " + list.get(position).getR22());

        holder.r31.setText("3-1: " + list.get(position).getR31());
        holder.r32.setText("3-2: " + list.get(position).getR32());

        holder.r41.setText("4-1: " + list.get(position).getR41());
        holder.r42.setText("4-2: " + list.get(position).getR42());


        return convertView;
    }

    class ViewHolder {
        TextView name;
        TextView roll;
        TextView due;

        TextView r11;
        TextView r12;

        TextView r21;
        TextView r22;

        TextView r31;
        TextView r32;

        TextView r41;
        TextView r42;
    }


    private class DataModel {
        String name;
        String roll;
        String due;
        String r11;
        String r12;
        String r21;
        String r22;
        String r31;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getRoll() {
            return roll;
        }

        public void setRoll(String roll) {
            this.roll = roll;
        }

        public String getDue() {
            return due;
        }

        public void setDue(String due) {
            this.due = due;
        }

        public String getR11() {
            return r11;
        }

        public void setR11(String r11) {
            this.r11 = r11;
        }

        public String getR12() {
            return r12;
        }

        public void setR12(String r12) {
            this.r12 = r12;
        }

        public String getR21() {
            return r21;
        }

        public void setR21(String r21) {
            this.r21 = r21;
        }

        public String getR22() {
            return r22;
        }

        public void setR22(String r22) {
            this.r22 = r22;
        }

        public String getR31() {
            return r31;
        }

        public void setR31(String r31) {
            this.r31 = r31;
        }

        public String getR32() {
            return r32;
        }

        public void setR32(String r32) {
            this.r32 = r32;
        }

        public String getR41() {
            return r41;
        }

        public void setR41(String r41) {
            this.r41 = r41;
        }

        public String getR42() {
            return r42;
        }

        public void setR42(String r42) {
            this.r42 = r42;
        }

        String r32;
        String r41;
        String r42;
    }
}
