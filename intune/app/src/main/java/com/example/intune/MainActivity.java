package com.example.intune;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    SQLiteDatabase db, dbSongs;
    EditText username, pasword;
    Button btn_login, btn_signup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_login = findViewById(R.id.bt_login);
        btn_login.setOnClickListener(this);
        btn_signup = findViewById(R.id.bt_login_signUp);
        btn_signup.setOnClickListener(this);
        username = findViewById(R.id.Login_username);
        pasword = findViewById(R.id.LoginPasword);
        db = openOrCreateDatabase("db", MODE_PRIVATE, null);
        build_tables();
    }
    private void build_tables(){
        db.execSQL("create table if not exists  tbl_users(name text, password text) ;");
        db.execSQL("create table if not exists  tblSongs(name text, rating text, style text, composer text) ;");

    }


    @Override
    public void onClick(View view) {
        if(view == btn_login) {
            Cursor cursor = db.rawQuery("SELECT * FROM tbl_users", null);
            cursor.moveToFirst();
            boolean isUserExist = false;
            while (!cursor.isAfterLast()) {
                if (cursor.getString(0).equals(username.getText().toString())
                        && cursor.getString(1).equals(pasword.getText().toString())) {
                    isUserExist = true;
                    Log.d("TAG", "onClick: here");
                    startActivity(new Intent(MainActivity.this, SongsList.class));
//                    break;
                }
                cursor.moveToNext();
            }
//            Toast.makeText(this, "user not exsist",Toast.LENGTH_SHORT).show();
            if(!isUserExist)
                Toast.makeText(this, "user not exsist",
                        Toast.LENGTH_SHORT).show();
        }

        if(view == btn_signup){
            startActivity(new Intent(MainActivity.this, signup.class));
        }
    }
}

