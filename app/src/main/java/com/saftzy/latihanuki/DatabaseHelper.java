package com.saftzy.latihanuki;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    //nama database
    public static final String DATABASE_NAME="Todo.db";
    //nama table
    public static final String TABLE_NAME="todo_table";
    //versi database
    private static final int DATABASE_VERSION= 1;
    //table column
    public static final String COL_1="ID";
    public static final String COL_2="TITLE";
    public static final String COL_3="DESCR";
    public static final String COL_4="DATE";
    public static final String COL_5="KEYTODO";

    public DatabaseHelper( Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
        SQLiteDatabase db= this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table todo_table(id integer primary key autoincrement," +
                "title text, " +
                "descr text," +
                "date text," +
                "keytodo text);");

        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,"Pergi Mancing");
        contentValues.put(COL_3,"Mancing di tambak");
        contentValues.put(COL_4,"14-04-2021");
        contentValues.put(COL_5,"1321");

        db.insert(TABLE_NAME, null, contentValues);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        onCreate(db);
    }

    public List<ToDo> getAllData(){
        List<ToDo> todos = new ArrayList<>();
        String selectQuery = "SELECT * FROM TODO_TABLE";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()){
            do {
                ToDo todo = new ToDo();
                todo.setIdtodo(cursor.getString(0));
                todo.setTitletodo(cursor.getString(1));
                todo.setDesctodo(cursor.getString(2));
                todo.setDatetodo(cursor.getString(3));

                todos.add(todo);
            }while (cursor.moveToNext());
        }
        db.close();
        return todos;
    }

    //metode untuk memasukan data
    public boolean insertData(String title, String descr, String date){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, title);
        contentValues.put(COL_3, descr);
        contentValues.put(COL_4, date);

        long result = db.insert(TABLE_NAME,null, contentValues);
        if(result == -1){
            return false;
        }else{
            return true;
        }
    }
    //untuk mengambil data
    public boolean updateData(String title, String descr, String date, String id) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, id);
        contentValues.put(COL_2, title);
        contentValues.put(COL_3, descr);
        contentValues.put(COL_4, date);

        long result = db.update(TABLE_NAME, contentValues, "ID = ?", new String[]{id});

        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }
    //untuk delete data
    public  int deleteData(String id){
        SQLiteDatabase db = this.getWritableDatabase();

        return db.delete(TABLE_NAME,"ID = ? "  ,new String[]{id});
    }
}
