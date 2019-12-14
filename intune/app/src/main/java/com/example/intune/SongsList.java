package com.example.intune;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class SongsList extends AppCompatActivity {
    List<Song> songList;
    ListView lst_songs;
    Song_Adapter songAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_songs_list);

        songList = new ArrayList<Song>();
        Log.d("TAG", "onCreate:" + R.id.lst_songs);

        lst_songs = findViewById(R.id.lst_songs);
        Song s1 = new Song("imagine", 5 ,"pop", "john lenon");
        Song s2 = new Song("all the things you are", 5, "jazz", "vernon duke");
        Song s3 = new Song("mashiah", 4, "rock", "shalom hanoh");
        Song s4 = new Song("alone toghther" ,4, "jazz", "Arthur schwartz");
        Song s5 = new Song("carolina in my mind", 5, "folk", "james taylor");

        songList.add(s1);
        songList.add(s2);
        songList.add(s3);
        songList.add(s4);
        songList.add(s5);

        songAdapter = new Song_Adapter(SongsList.this, songList);
        lst_songs.setAdapter(songAdapter);
    }
}
