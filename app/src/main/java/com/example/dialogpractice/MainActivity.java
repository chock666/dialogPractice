package com.example.dialogpractice;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;

import java.util.Calendar;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btndate;
    RadioGroup rg;

    Dialog d;
    Button btngender;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btndate=(Button)findViewById(R.id.birthDateB);
        btndate.setOnClickListener(this);
        btngender=(Button)findViewById(R.id.btnGander);
        btngender.setOnClickListener(this);
    }
    public void createGenderDialog(){
        d= new Dialog(this);
        d.setContentView(R.layout.costumdialog);
        d.setCancelable(true);
        rg=d.findViewById(R.id.rg);
        d.show();



    }


    @Override
    public void onClick(View view) {
        if (view==btndate){
            Calendar Calender1= Calendar.getInstance();
            int year = Calender1.get(Calendar.YEAR);
            int month = Calender1.get(Calendar.MONTH);
            int day = Calender1.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog datePickerDialog = new DatePickerDialog(this,new SetDate(),year,month,day);
            datePickerDialog.show();

        }
        if (view==btngender){
            createGenderDialog();

        }
    }

    private class SetDate implements DatePickerDialog.OnDateSetListener {
        @Override
        public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int day) {
            monthOfYear = monthOfYear +1;
            String str = "You selected :" + day + "/" + monthOfYear +"/" + year;
            btndate.setText(str);

        }
    }
}