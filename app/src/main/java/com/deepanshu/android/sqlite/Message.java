package com.deepanshu.android.sqlite;

import android.content.Context;
import android.widget.Toast;

public class Message {
    Vivzdatabaseadapter vivzdatabaseadapter;
    public static void message(Context context,String message){
        Toast.makeText(context,message,Toast.LENGTH_LONG).show();
    }
}
