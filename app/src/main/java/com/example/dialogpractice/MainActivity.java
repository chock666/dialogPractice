package com.example.dialogpractice;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.Calendar;


public class MainActivity extends AppCompatActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {
    RadioGroup rg;
    SharedPreferences sp;
    Dialog d;
    Button btngender, btnSave, btnSaveGender, btndate;
    EditText etFN, etLN, etA;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btndate = (Button) findViewById(R.id.birthDateB);
        etFN = findViewById(R.id.nameET);
        etA = findViewById(R.id.adressET);
        etLN = findViewById(R.id.lastNameET);

        btndate.setOnClickListener(this);
        btngender = (Button) findViewById(R.id.btnGander);
        btngender.setOnClickListener(this);
        sp = getSharedPreferences("details1", 0);
        btnSave = findViewById(R.id.btnSave);
        btnSave.setOnClickListener(this);
        String fname= sp.getString("firstName",null);
        String lname= sp.getString("lastName",null);
        String adress= sp.getString("adress",null);
        String bd= sp.getString("date",null);
        String gen= sp.getString("gender",null);
        etFN.setText(fname);
        etLN.setText(lname);
        etA.setText(adress);
        btndate.setText(bd);
        btngender.setText(gen);




    }

    public void createGenderDialog() {
        d = new Dialog(this);
        d.setContentView(R.layout.costumdialog);
        d.setTitle("Select your gender");
        d.setCancelable(false);
        btnSaveGender = (Button) d.findViewById(R.id.saveButton);
        btnSaveGender.setOnClickListener(this);
        rg = (RadioGroup) d.findViewById(R.id.rg);
        rg.setOnCheckedChangeListener(this);
        d.show();


    }


    @Override
    public void onClick(View view) {
        if (view == btndate) {
            Calendar Calender1 = Calendar.getInstance();
            int year = Calender1.get(Calendar.YEAR);
            int month = Calender1.get(Calendar.MONTH);
            int day = Calender1.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new SetDate(), year, month, day);
            datePickerDialog.show();

        }
        if (view == btngender) {
            createGenderDialog();

        }
        if (view == btnSaveGender) {
            d.dismiss();
        }
        if (view == btnSave) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("save");
            builder.setMessage("do u want to save the details?");
            builder.setCancelable(true);
            builder.setPositiveButton("yes", new HandleAlertDialogListener());
            builder.setNegativeButton("No", new HandleAlertDialogListener());
            AlertDialog dialog = builder.create();
            dialog.show();


             }
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        if (radioGroup == rg) {
            if (i == R.id.radio_male) {
                btngender.setText("male");
            } else if (i == R.id.radio_female) {
                btngender.setText("female");

            }
        }
    }

    private class SetDate implements DatePickerDialog.OnDateSetListener {
        @Override
        public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int day) {
            monthOfYear = monthOfYear + 1;
            String str = "You selected :" + day + "/" + monthOfYear + "/" + year;
            btndate.setText(str);

        }
    }


    private class HandleAlertDialogListener implements DialogInterface.OnClickListener {
        public void onClick(DialogInterface dialog, int which) {
            if(which==-1){
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("firstName", etFN.getText().toString());
                editor.putString("lastName", etLN.getText().toString());
                editor.putString("adress", etA.getText().toString());
                editor.putString("date", btndate.getText().toString());
                editor.putString("gender", btngender.getText().toString());

                editor.commit();
                Toast.makeText(MainActivity.this, "u agreed to save the details " , Toast.LENGTH_LONG).show();

            }
            else{
                Toast.makeText(MainActivity.this, "unfortunately u did not chose to save the details " , Toast.LENGTH_LONG).show();

            }


        }
    }
}