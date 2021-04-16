package com.saftzy.latihanuki;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<ToDo> list = new ArrayList<ToDo>();
    ToDoAdapter toDoAdapter;
    DatabaseHelper myDB;
    RecyclerView rvTodo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDB = new DatabaseHelper(this);
        list.addAll(myDB.getAllData());
        rvTodo = findViewById(R.id.rvTodo);
        rvTodo.setLayoutManager(new LinearLayoutManager(this));
        toDoAdapter = new ToDoAdapter(MainActivity.this, list);
        toDoAdapter.notifyDataSetChanged();
        rvTodo.setAdapter(toDoAdapter);


        FloatingActionButton btn = (FloatingActionButton)findViewById(R.id.fab);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(
                        MainActivity.this, AddTodo.class
                );
                startActivity(intent);
            }
        });
    }
}