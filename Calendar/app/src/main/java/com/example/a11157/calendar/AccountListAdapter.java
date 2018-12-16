package com.example.a11157.calendar;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class AccountListAdapter extends BaseAdapter {
    private ArrayList<ArrayList<String>> list;
    private Context context;

    public AccountListAdapter(Context context, ArrayList<ArrayList<String>> list){
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

            convertView = View.inflate(context, R.layout.account_listview, null);
            viewHolder.typeOfBill = (TextView)convertView.findViewById(R.id.typeOfBill);
            viewHolder.amount = (TextView)convertView.findViewById(R.id.amount);
            viewHolder.comment = (TextView)convertView.findViewById(R.id.comment);
            viewHolder.date = (TextView)convertView.findViewById(R.id.date);
            viewHolder.typeOfMoney = (TextView)convertView.findViewById(R.id.typeOfMoney);

            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder)convertView.getTag();
        }

        ArrayList<String> bill = list.get(position);

        viewHolder.typeOfBill.setText(bill.get(2));
        viewHolder.amount.setText(bill.get(1));
        viewHolder.comment.setText(bill.get(4));
        viewHolder.date.setText(bill.get(5));
        viewHolder.typeOfMoney.setText(bill.get(3));

        return convertView;
    }

    class ViewHolder{
        TextView typeOfBill;
        TextView amount;
        TextView comment;
        TextView date;
        TextView typeOfMoney;
    }
}
