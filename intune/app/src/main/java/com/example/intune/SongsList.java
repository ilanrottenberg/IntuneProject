package com.example.intune;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.view.Menu;

import java.util.ArrayList;
import java.util.List;

public class SongsList extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemLongClickListener {
    List<Song> songList;
    ListView lst_songs;
    Song_Adapter songAdapter;
    Button btBack;
    Song currLongClickSong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_songs_list);

        songList = new ArrayList<Song>();
        Log.d("TAG", "onCreate:" + R.id.lst_songs);
        btBack = findViewById(R.id.back);
        btBack.setOnClickListener(this);

        lst_songs = findViewById(R.id.lst_songs);
        Song s1 = new Song("imagine", 5, "pop", "john lenon");
        Song s2 = new Song("all the things you are", 5, "jazz", "vernon duke");
        Song s3 = new Song("mashiah", 4, "rock", "shalom hanoh");
        Song s4 = new Song("alone toghther", 4, "jazz", "Arthur schwartz");
        Song s5 = new Song("carolina in my mind", 5, "folk", "james taylor");

        songList.add(s1);
        songList.add(s2);
        songList.add(s3);
        songList.add(s4);
        songList.add(s5);

        songAdapter = new Song_Adapter(SongsList.this, songList);
        lst_songs.setAdapter(songAdapter);

        lst_songs.setOnItemLongClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == btBack) {
            startActivity(new Intent(this, MainActivity.class));
        }
    }


    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
        songList.remove(i);
        songAdapter = new Song_Adapter(SongsList.this, songList);
        lst_songs.setAdapter(songAdapter);
        return true;

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        item.getItemId();
        if (item.getItemId() == R.id.add){
            startActivityForResult(new Intent(this, addSong.class), 0);
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Song song = new Song(data.getStringExtra("SongName"),data.getDoubleExtra("Rating",1.0),
                data.getStringExtra("Style"), data.getStringExtra("Composer"));
        songList.add(song);
        songAdapter = new Song_Adapter(SongsList.this, songList);
        lst_songs.setAdapter(songAdapter);
    }

}
