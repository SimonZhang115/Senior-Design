package com.example.a11157.calendar;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
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


public class Accounts extends Fragment {
    DatabaseHelper myDb;
    ListView listView;
    Button add;

    ArrayList<ArrayList<String>> copy;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_accounts, container, false);
        listView = root.findViewById(R.id.listView);

        add = root.findViewById(R.id.add);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddAccountActivity.class);
                startActivity(intent);
            }
        });

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();

        myDb = new DatabaseHelper(getActivity());

        Cursor res = myDb.getAllAccountData();
        res.moveToFirst();

        final ArrayList<ArrayList<String>> bills = new ArrayList<ArrayList<String>>();

        while (!res.isAfterLast()) {
            //Log.d("res", "res");
            ArrayList<String> bill = new ArrayList<String>();

            int ID = res.getInt(0);
            double amount = res.getDouble(1);
            String typeOfBill = res.getString(2);
            String typeOfMoney = res.getString(3);
            String comment = res.getString(4);
            int month = res.getInt(5);
            int day = res.getInt(6);
            int year = res.getInt(7);

            bill.add(String.valueOf(ID));
            bill.add(String.valueOf(amount));
            bill.add(typeOfBill);
            bill.add(typeOfMoney);
            bill.add(comment);
            String date = month + "/" + day + "/" + year;
            bill.add(date);

            bills.add(bill);

            res.moveToNext();
        }
        copy = bills;

        AccountListAdapter listAdapter = new AccountListAdapter(getActivity(), bills);
        listView.setAdapter(listAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(view.getContext(), EditAccountActivity.class);
                intent.putStringArrayListExtra("editAccount", bills.get(position));
                startActivity(intent);
            }
        });

        this.registerForContextMenu(listView);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(1, 1, Menu.NONE, "DELETE");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        Log.d("menu", "Account onContextItemSelected");

        if (getUserVisibleHint()){
            Log.d("menu", "Account visible");
            AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();

            switch (item.getItemId()){
                case 1:
                    int pos = (int)listView.getAdapter().getItemId(menuInfo.position);

                    myDb = new DatabaseHelper(getActivity());
                    int deleteRows = myDb.deleteAccountData(Integer.parseInt(copy.get(pos).get(0)));

                    if(deleteRows > 0){
                        Toast.makeText(getActivity(), "One Account Deleted", Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(getActivity(), "One Account Delete Failed", Toast.LENGTH_LONG).show();
                    }

                    onResume();

                    Log.d("menu", "accounts case1");
                    break;
                default:
                    Log.d("menu", "accounts default");
                    break;

            }
        }


        return super.onContextItemSelected(item);
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
