package com.example.a11157.calendar;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ListAdapter extends BaseAdapter {
    private ArrayList<ArrayList<String>> list;
    private Context context;

    public ListAdapter(Context context, ArrayList<ArrayList<String>> list){
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if(convertView == null){
            viewHolder = new ViewHolder();

            convertView = View.inflate(context, R.layout.listview, null);
            viewHolder.textViewID = (TextView)convertView.findViewById(R.id.ID);
            viewHolder.textViewName = (TextView)convertView.findViewById(R.id.name);
            viewHolder.textViewClassroom = (TextView)convertView.findViewById(R.id.classroom);
            viewHolder.textViewDayOfWeek = (TextView)convertView.findViewById(R.id.dayOfWeek);
            viewHolder.textViewFromTime = (TextView)convertView.findViewById(R.id.fromTime);
            viewHolder.textViewToTime = (TextView)convertView.findViewById(R.id.toTime);

            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder)convertView.getTag();
        }

        ArrayList<String> course = list.get(position);

        viewHolder.textViewID.setText(course.get(0));
        viewHolder.textViewName.setText(course.get(1));
        viewHolder.textViewClassroom.setText(course.get(2));
        viewHolder.textViewDayOfWeek.setText(course.get(3));
        viewHolder.textViewFromTime.setText(course.get(4));
        viewHolder.textViewToTime.setText(course.get(5));

        return convertView;
    }

    class ViewHolder{
        TextView textViewID;
        TextView textViewName;
        TextView textViewClassroom;
        TextView textViewDayOfWeek;
        TextView textViewFromTime;
        TextView textViewToTime;
    }
}
