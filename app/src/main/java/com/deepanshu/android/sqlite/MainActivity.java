package com.deepanshu.android.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Vivzdatabaseadapter vivzdatabaseadapter;
    EditText user, pass, singleuserdetail, up_dlt;
    Button Listbtn, Singleuserdetailbtn;

    //ist vedio for add and view data
    //2nd vedio for select
    //3 rd vedio for dlt and update
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        vivzdatabaseadapter = new Vivzdatabaseadapter(this);
        //SQLiteDatabase sqLiteDatabase= vivzdatabaseadapter.getWritableDatabase();
        user = findViewById(R.id.username);
        pass = findViewById(R.id.password);
        Listbtn = findViewById(R.id.addUser);
        up_dlt = findViewById(R.id.up_dlt);
        singleuserdetail = findViewById(R.id.singleUser);
        Singleuserdetailbtn = findViewById(R.id.singleUserViewDetail);
    }

    public void addUser(View view) {
        String user1 = user.getText().toString().trim();
        String pass1 = pass.getText().toString().trim();
        long id = vivzdatabaseadapter.insertData(user1, pass1);
        if (id < 0) {
            Message.message(this, "unsuccessfull");
        } else {
            Message.message(this, "successfull");
        }
    }

    public void view_detail(View view) {
        String data = vivzdatabaseadapter.getAllData();
        Message.message(this, data);
    }

    public void singleuserview_detail(View view) {
        String namepass = singleuserdetail.getText().toString();
        String namepass1[] = namepass.split(" ");
        String name = namepass1[0];
        String pass = namepass1[1];
        //String s2=vivzdatabaseadapter.getData(name);// for find fulll deteail of any user
        String s2 = vivzdatabaseadapter.getData(name, pass);//for find the id
        Message.message(this, s2);
    }

    public void upDate(View view) {
        String nameUpdate = up_dlt.getText().toString();
        String name_New_Old[] = nameUpdate.split(" ");
        String old_name = name_New_Old[0];
        String new_name = name_New_Old[1];
        //update vivztable set name='deep' wher namme='deepanshu'
        int count = vivzdatabaseadapter.upDateName(old_name, new_name);
        if (count > 0) {
            String updatemsg = "update successfully";
            Message.message(this, updatemsg);
        } else
            Message.message(this, "updation not successfully");
    }

    public void Delete(View view) {
        String namepass = up_dlt.getText().toString();
        String namepass1[] = namepass.split(" ");
        String dlt_name = namepass1[0];
        int count = vivzdatabaseadapter.deleteRow(dlt_name);
        if (count > 0) {
            String updatemsg = "delete successfully";
            Message.message(this, updatemsg);
        } else
            Message.message(this, "updation not successfully");

    }
}
