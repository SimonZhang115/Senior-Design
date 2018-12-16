package com.example.a11157.calendar;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.contrarywind.view.WheelView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddCourseActivity extends AppCompatActivity {
    DatabaseHelper myDb;
    EditText ID, name, classroom, fromTime, toTime;
    CheckBox monCheckBox, tueCheckBox, wedCheckBox, thuCheckBox, friCheckBox, satCheckBox, sunCheckBox;
    //Button btnOK;

    TimePickerView fromTimePickerView, toTimePickerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);

        myDb = new DatabaseHelper(this);

        ID = (EditText)findViewById(R.id.ID);
        name = (EditText)findViewById(R.id.name);
        classroom = (EditText)findViewById(R.id.classroom);
        fromTime = (EditText)findViewById(R.id.fromTime);
        toTime = (EditText)findViewById(R.id.toTime);
        monCheckBox = (CheckBox)findViewById(R.id.monCheckBox);
        tueCheckBox = (CheckBox)findViewById(R.id.tueCheckBox);
        wedCheckBox = (CheckBox)findViewById(R.id.wedCheckBox);
        thuCheckBox = (CheckBox)findViewById(R.id.thuCheckBox);
        friCheckBox = (CheckBox)findViewById(R.id.friCheckBox);
        satCheckBox = (CheckBox)findViewById(R.id.satCheckBox);
        sunCheckBox = (CheckBox)findViewById(R.id.sunCheckBox);
        //btnOK = (Button)findViewById(R.id.btnOK);


        fromTimePickerView = new TimePickerBuilder(AddCourseActivity.this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                DateFormat df = new SimpleDateFormat("hh:mm");
                String time = df.format(date);
                fromTime.setText(time);
            }
        })      .setTitleText("time from")
                .setType(new boolean[] {false, false, false, true, true, false})
                .build();

        fromTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fromTimePickerView.show();
            }
        });

        toTimePickerView = new TimePickerBuilder(AddCourseActivity.this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                DateFormat df = new SimpleDateFormat("hh:mm");
                String time = df.format(date);
                toTime.setText(time);
            }
        })      .setTitleText("time to")
                .setType(new boolean[] {false, false, false, true, true, false})
                .build();

        toTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toTimePickerView.show();
            }
        });

    }

    public void AddData(View view){
        String strFromTime = fromTime.getText().toString();
        String[] strsFromTime = strFromTime.split(":");
        int fromHour = Integer.parseInt(strsFromTime[0]);
        int fromMinute = Integer.parseInt(strsFromTime[1]);
        String strToTime = toTime.getText().toString();
        String[] strsToTime = strToTime.split(":");
        int toHour = Integer.parseInt(strsToTime[0]);
        int toMinute = Integer.parseInt(strsToTime[1]);

        StringBuffer stringBuffer = new StringBuffer();
        if(monCheckBox.isChecked()){
            stringBuffer.append("Mon ");
        }
        if(tueCheckBox.isChecked()){
            stringBuffer.append("Tue ");
        }
        if(wedCheckBox.isChecked()){
            stringBuffer.append("Wed ");
        }
        if(thuCheckBox.isChecked()){
            stringBuffer.append("Thu ");
        }
        if(friCheckBox.isChecked()){
            stringBuffer.append("Fri ");
        }
        if(satCheckBox.isChecked()){
            stringBuffer.append("Sat ");
        }
        if(sunCheckBox.isChecked()){
            stringBuffer.append("Sun ");
        }

        Log.d("add"," pressed");
        boolean isInserted = myDb.insertCourseData(ID.getText().toString(),
                name.getText().toString(),
                classroom.getText().toString(),
                stringBuffer.toString(),
                fromHour, fromMinute, toHour, toMinute);
        if(isInserted){
            Log.d("add"," isInserted");
            Toast.makeText(AddCourseActivity.this, "Data Inserted", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(AddCourseActivity.this, DayActivity.class);
            intent.putExtra("position", 0);
            startActivity(intent);
        }else{
            Log.d("add"," Failed");
            Toast.makeText(AddCourseActivity.this, "Failed", Toast.LENGTH_LONG).show();
        }

    }




}
