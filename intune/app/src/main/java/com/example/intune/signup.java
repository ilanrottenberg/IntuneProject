package com.example.intune;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class signup extends AppCompatActivity implements View.OnClickListener {
    SQLiteDatabase db;
    EditText SGU_username, SGU_pasword, SGU_aproval;
    Button SGU_sgu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        SGU_username.findViewById(R.id.signUp_username);
        SGU_pasword.findViewById(R.id.signUp_pasword);
        SGU_aproval.findViewById(R.id.signUp_aprovalPasword);
        SGU_sgu.findViewById(R.id.signUp_btnSignUp);
        db = openOrCreateDatabase("users", MODE_PRIVATE, null);
        SGU_sgu.setOnClickListener(this);
    }

    @Override

    public void onClick(View view) {
        if (view == SGU_sgu){
            String username = SGU_username.getText().toString();
            String pasword = SGU_pasword.getText().toString();
            String aproval = SGU_aproval.getText().toString();
            if (username.trim().length() > 0 && pasword.trim().length() > 0 &&
                    aproval.trim().length() > 0){
                if (pasword.equals(aproval)){
                    if(addData(username, pasword)){

                    }
                    startActivity(new Intent(this, SongsList.class));
                }
                else{
                    Toast.makeText(this, "password not match", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
    private void build_tables(){
        db.execSQL("create table if not exists  tbl_users(name text, password text) ");
    }
    private boolean addData(String username, String password) {
        db = openOrCreateDatabase("mahsan_shell_ilan", MODE_PRIVATE, null);

        Cursor cursor = db.rawQuery("select * from tbl_users", null);
        while(cursor.moveToNext()){
            if(cursor.getString(0).equals(username)) {
                Toast.makeText(this, "user exists", Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        db.execSQL("insert into tbl_users values('"+username+"', '"+password+"')");
        return true;
    }
}
