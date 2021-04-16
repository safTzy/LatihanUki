package com.saftzy.latihanuki;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AddTodo extends AppCompatActivity {
    protected Cursor cursor;

    DatabaseHelper myDb;
    Button btnSubmit, btnCancel;
    EditText titleTodo, descTodo, dateTodo;
    DatePickerDialog.OnDateSetListener date;
    Calendar myCalender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_todo);

        myDb = new DatabaseHelper(this);
        titleTodo = (EditText) findViewById(R.id.edtTitle);
        descTodo = (EditText) findViewById(R.id.edtDes);
        dateTodo = (EditText) findViewById(R.id.edtDate);

        btnSubmit = (Button) findViewById(R.id.btnAdd);
        btnCancel = (Button) findViewById(R.id.btnCancel);

        myDb = new DatabaseHelper(this);
        myCalender = Calendar.getInstance();

        date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                myCalender.set(Calendar.YEAR, year);
                myCalender.set(Calendar.MONTH, month);
                myCalender.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel(); // updateLabel
            }
        };
        dateTodo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(AddTodo.this, date,
                        myCalender.get(Calendar.YEAR),
                        myCalender.get(Calendar.MONTH),
                        myCalender.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = titleTodo.getText().toString();
                String decs = descTodo.getText().toString();
                String date = dateTodo.getText().toString();

                if (title.equals("") || decs.equals("") || date.equals("")){
                    if (title.equals("")){
                        titleTodo.setError("Judul harus diisi");
                    }if (decs.equals("")){
                        descTodo.setError("Deskripsi harus diisi");
                    }
                    if (date.equals("")){
                        dateTodo.setError("Tanggal harus diisi");
                    }

                    }else {
                        boolean isInserted = myDb.insertData(title, decs, date);
                        if (isInserted){
                            Toast.makeText(AddTodo.this,"Data berhasil ditambahkan ", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(AddTodo.this,"Data gagal ditambahkan ", Toast.LENGTH_SHORT).show();
                        }
                        startActivity(new Intent(AddTodo.this,MainActivity.class));
                        finish();
                }
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddTodo.this,MainActivity.class));
                finish();
            }
        });
    }
    //untuk mengaudate text
    private void updateLabel(){
        String myformat = "dd-mm-yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(myformat, Locale.US);
        dateTodo.setText(simpleDateFormat.format(myCalender.getTime()));
    }
}