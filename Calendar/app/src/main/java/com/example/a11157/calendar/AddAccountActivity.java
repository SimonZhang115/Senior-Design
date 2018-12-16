package com.example.a11157.calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectChangeListener;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.contrarywind.interfaces.IPickerViewData;

import java.security.cert.PKIXRevocationChecker;
import java.util.ArrayList;
import java.util.Calendar;


public class AddAccountActivity extends AppCompatActivity {
    int  mYear, mMonth, mDay;
    EditText amount, typeOfBill, typeOfMoney, comment, editDate;
    Button btnOK;
    ImageButton btnBack;

    DatabaseHelper myDb;

    int chooseMonth, chooseDay, chooseYear;

    private OptionsPickerView optionsPickerView;
    private ArrayList<BillBean> options1Items = new ArrayList<BillBean>();
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();

    private OptionsPickerView moneyOptionsPickerView;
    private ArrayList<String> moneyOptions1Items = new ArrayList<String>();
    private ArrayList<ArrayList<String>> moneyOptions2Items = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_account);

        myDb = new DatabaseHelper(this);

        amount = (EditText)findViewById(R.id.amount);
        typeOfBill =(EditText)findViewById(R.id.typeOfBill);
        typeOfMoney =(EditText)findViewById(R.id.typeOfMoney);
        comment = (EditText)findViewById(R.id.comment);
        editDate = (EditText)findViewById(R.id.editDate);
        btnOK = (Button)findViewById(R.id.btnOK);
        btnBack = (ImageButton)findViewById(R.id.btnBack);

        Calendar ca = Calendar.getInstance();
        mYear  = ca.get(Calendar.YEAR);
        mMonth = ca.get(Calendar.MONTH);
        mDay = ca.get(Calendar.DAY_OF_MONTH);

        editDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(AddAccountActivity.this, onDateSetListener, mYear, mMonth, mDay).show();
            }
        });


        initOptionData();
        initOptionPicker();

        typeOfBill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                optionsPickerView.show();
            }
        });

        initMoneyOptionData();
        initMoneyOptionPicker();

        typeOfMoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moneyOptionsPickerView.show();
            }
        });

    }

    public void AddData(View view){

        Log.d("add"," pressed");
        boolean isInserted = myDb.insertAccountData(Double.parseDouble(amount.getText().toString()),
                typeOfBill.getText().toString(),
                typeOfMoney.getText().toString(),
                comment.getText().toString(),
                chooseMonth, chooseDay, chooseYear);
        if(isInserted){
            Log.d("add"," isInserted");
            Toast.makeText(AddAccountActivity.this, "Data Inserted", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(AddAccountActivity.this, DayActivity.class);
            intent.putExtra("position", 1);
            startActivity(intent);
        }else{
            Log.d("add"," Failed");
            Toast.makeText(AddAccountActivity.this, "Failed", Toast.LENGTH_LONG).show();
        }
    }

    private void initOptionPicker(){
        optionsPickerView = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                String tx = options2Items.get(options1).get(options2);
                typeOfBill.setText(tx);

            }
        })      .setTitleText("Type Of Bill")
                .setContentTextSize(20)
                .setSelectOptions(0, 1)
                .isRestoreItem(true)
                .build();
        optionsPickerView.setPicker(options1Items, options2Items);
    }


    private void initOptionData(){
        options1Items.add(new BillBean("clothes"));
        options1Items.add(new BillBean("food"));
        options1Items.add(new BillBean("home"));
        options1Items.add(new BillBean("traffic"));
        options1Items.add(new BillBean("communication"));
        options1Items.add(new BillBean("entertainment"));
        options1Items.add(new BillBean("study"));
        options1Items.add(new BillBean("human"));
        options1Items.add(new BillBean("medical"));
        options1Items.add(new BillBean("financial"));


        ArrayList<String> options2Items_01 = new ArrayList<String>();
        options2Items_01.add("clothes,trousers");
        options2Items_01.add("hat,bag");
        options2Items_01.add("cosmetics,accessories");
        ArrayList<String> options2Items_02 = new ArrayList<String>();
        options2Items_02.add("breakfast,lunch,dinner");
        options2Items_02.add("tobacco,alcohol,coffee");
        options2Items_02.add("fruits,snacks");
        ArrayList<String> options2Items_03 = new ArrayList<String>();
        options2Items_03.add("daily supplies");
        options2Items_03.add("water,electricity,internet");
        options2Items_03.add("rent");
        options2Items_03.add("real estate management");
        options2Items_03.add("maintainance");
        ArrayList<String> options2Items_04 = new ArrayList<String>();
        options2Items_04.add("public transport");
        options2Items_04.add("taxi,rent car");
        options2Items_04.add("car");
        ArrayList<String> options2Items_05 = new ArrayList<String>();
        options2Items_05.add("phone");
        options2Items_05.add("moblie phone");
        options2Items_05.add("Internet");
        options2Items_05.add("mail");
        ArrayList<String> options2Items_06 = new ArrayList<String>();
        options2Items_06.add("sport");
        options2Items_06.add("party");
        options2Items_06.add("play");
        options2Items_06.add("pet");
        options2Items_06.add("vacation");
        ArrayList<String> options2Items_07 = new ArrayList<String>();
        options2Items_07.add("book,newspaper,magazine");
        options2Items_07.add("training");
        options2Items_07.add("equipment");
        ArrayList<String> options2Items_08 = new ArrayList<String>();
        options2Items_08.add("gift for friends");
        options2Items_08.add("gift for parents");
        options2Items_08.add("repay");
        options2Items_08.add("charity");
        ArrayList<String> options2Items_09 = new ArrayList<String>();
        options2Items_09.add("medicine");
        options2Items_09.add("health care");
        options2Items_09.add("beauty");
        options2Items_09.add("treatment");
        ArrayList<String> options2Items_10 = new ArrayList<String>();
        options2Items_10.add("bank fee");
        options2Items_10.add("losses on investments");
        options2Items_10.add("refund");
        options2Items_10.add("consumption tax");
        options2Items_10.add("fine");

        options2Items.add(options2Items_01);
        options2Items.add(options2Items_02);
        options2Items.add(options2Items_03);
        options2Items.add(options2Items_04);
        options2Items.add(options2Items_05);
        options2Items.add(options2Items_06);
        options2Items.add(options2Items_07);
        options2Items.add(options2Items_08);
        options2Items.add(options2Items_09);
        options2Items.add(options2Items_10);
    }

    private void initMoneyOptionPicker(){
        moneyOptionsPickerView = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                String tx = moneyOptions2Items.get(options1).get(options2);
                typeOfMoney.setText(tx);

            }
        })      .setTitleText("Type Of Money")
                .setContentTextSize(20)
                .isRestoreItem(true)
                .build();
        moneyOptionsPickerView.setPicker(moneyOptions1Items, moneyOptions2Items);
    }


    private void initMoneyOptionData(){
        moneyOptions1Items.add("cash");
        moneyOptions1Items.add("credit card");
        moneyOptions1Items.add("debit card");
        moneyOptions1Items.add("other accounts");

        ArrayList<String> moneyOptions2Items_01 = new ArrayList<String>();
        moneyOptions2Items_01.add("cash");
        ArrayList<String> moneyOptions2Items_02 = new ArrayList<String>();
        moneyOptions2Items_02.add("credit card");
        ArrayList<String> moneyOptions2Items_03 = new ArrayList<String>();
        moneyOptions2Items_03.add("debit card");
        ArrayList<String> moneyOptions2Items_04 = new ArrayList<String>();
        moneyOptions2Items_04.add("bus card");
        moneyOptions2Items_04.add("meal card");
        moneyOptions2Items_04.add("paypel");

        moneyOptions2Items.add(moneyOptions2Items_01);
        moneyOptions2Items.add(moneyOptions2Items_02);
        moneyOptions2Items.add(moneyOptions2Items_03);
        moneyOptions2Items.add(moneyOptions2Items_04);
    }



    class BillBean implements IPickerViewData {
        String bill;

        BillBean(String bill){
            this.bill = bill;
        }

        @Override
        public String getPickerViewText() {
            return bill;
        }
    }

    private DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            String data = (month + 1) + "/" + dayOfMonth + "/" + year;
            editDate.setText(data);
            chooseMonth = month + 1;
            chooseDay = dayOfMonth;
            chooseYear = year;
        }
    };
}
