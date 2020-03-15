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

public class addSong extends AppCompatActivity {
    EditText songName, composer, style, rating;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_song);
        songName = findViewById(R.id.addName);
        composer = findViewById(R.id.addComposer);
        style = findViewById(R.id.addStyle);
        rating = findViewById(R.id.addRating);
        db = openOrCreateDatabase("db", MODE_PRIVATE, null);

    }

    public void addSong(View view) {
        Intent intent = new Intent();
        intent.putExtra("SongName",songName.getText().toString());
        intent.putExtra("Rating",Double.parseDouble(rating.getText().toString()));
        intent.putExtra("Style",style.getText().toString());
        intent.putExtra("Composer",composer.getText().toString());
        setResult(0, intent);
        String name = songName.getText().toString();
        Double rate = Double.parseDouble(rating.getText().toString());
        String Style = style.getText().toString();
        String Composer = composer.getText().toString();
        if (name.trim().length() > 0 && rate >= 0 &&
                Style.trim().length() > 0 && Composer.trim().length()>0){
                if(addData(name,Style,Composer,rate)){
                    db.execSQL("insert into tblSongs values('"+name+"', '"+rate+"','"+Style+"','"+Composer+"')");
                }
            else{
                Toast.makeText(this, "error", Toast.LENGTH_SHORT).show();
            }
        }


        finish();
    }

    private boolean addData(String name, String style, String composer, Double rating) {

        Cursor cursor = db.rawQuery("SELECT * FROM tblSongs", null);
        while(cursor.moveToNext()){
            if(cursor.getString(0).equals(name) && cursor.getString(1).equals(rating) && cursor.getString(2).equals(style)
            && cursor.getString(3).equals(composer)) {
                Toast.makeText(this, "already exist", Toast.LENGTH_SHORT).show();
                return false;
            }
        }

        return true;
    }



}
