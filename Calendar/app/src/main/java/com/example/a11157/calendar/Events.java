package com.example.a11157.calendar;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Events extends Fragment {
    DatabaseHelper myDb;
    ListView listView;
    Button add;

    ArrayList<ArrayList<String>> copy;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_events, container, false);
        listView = root.findViewById(R.id.listView);
        add = root.findViewById(R.id.add);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddCourseActivity.class);
                startActivity(intent);
            }
        });

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();

        myDb = new DatabaseHelper(getActivity());

        Cursor res = myDb.getAllCourseData();
        res.moveToFirst();

        final ArrayList<ArrayList<String>> courses = new ArrayList<ArrayList<String>>();

        while (!res.isAfterLast()) {
            Log.d("res", "res");
            ArrayList<String> course = new ArrayList<String>();

            String ID = res.getString(0);
            String name = res.getString(1);
            String classroom = res.getString(2);
            String dayOfWeek = res.getString(3);
            int fromHour = res.getInt(4);
            int fromMinute = res.getInt(5);
            int toHour = res.getInt(6);
            int toMinute = res.getInt(7);

            String fromTime;
            if(fromMinute < 10){
                fromTime = fromHour + ":0" + fromMinute;
            }else {
                fromTime = fromHour + ":" + fromMinute;
            }
            String toTime;
            if(toMinute < 10){
                toTime = toHour + ":0" + toMinute;
            }else {
                toTime = toHour + ":" + toMinute;
            }

            Log.d("time", "listview:" + fromHour + ":" + fromMinute + "-" + toHour + ":" + toMinute);

            course.add(ID);
            course.add(name);
            course.add(classroom);
            course.add(dayOfWeek);
            course.add(fromTime);
            course.add(toTime);

            courses.add(course);

            res.moveToNext();
        }
        copy = courses;

        ListAdapter listAdapter = new ListAdapter(getActivity(), courses);
        listView.setAdapter(listAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(view.getContext(), EditCourseActivity.class);
                intent.putStringArrayListExtra("editCourse", courses.get(position));
                startActivity(intent);
            }
        });

        this.registerForContextMenu(listView);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(0, 1, Menu.NONE, "DELETE");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        Log.d("menu", "Event onContextItemSelected");

        if (getUserVisibleHint()) {
            Log.d("menu", "Event visible");

            AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();

            switch (item.getItemId()){
                case 1:
                    int pos = (int)listView.getAdapter().getItemId(menuInfo.position);

                    myDb = new DatabaseHelper(getActivity());
                    int deleteRows = myDb.deleteCourseData(copy.get(pos).get(0));

                    if(deleteRows > 0){
                        Toast.makeText(getActivity(), "One Course Deleted", Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(getActivity(), "One Course Delete Failed", Toast.LENGTH_LONG).show();
                    }

                    onResume();

                    Log.d("menu", "events case1");
                    break;
                default:
                    Log.d("menu", "events default");
                    break;
            }
        }


        return super.onContextItemSelected(item);
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }

}
