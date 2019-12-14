package com.example.intune;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class Song_Adapter extends ArrayAdapter<Song> {
    Context context;
    List<Song>songList;

    public Song_Adapter(Context context, List<Song>songList){
        super(context,0,songList);
        this.context = context;
        this.songList = songList;
    }

    public Song_Adapter(SongsList context, List<Song> songList) {
        super(context,0,songList);
        this.context=context;
        this.songList=songList;
    }

    @Override
    public View getView(int position,  View convertView,  ViewGroup parent) {
        View item_view_in_listview = convertView;
        item_view_in_listview = LayoutInflater.from(getContext()).inflate(R.layout.row_listview_songs
        ,parent, false);

        Song tmp = songList.get(position);
        TextView name = item_view_in_listview.findViewById(R.id.tv_name);
        TextView rate = item_view_in_listview.findViewById(R.id.tv_rate);
        TextView style = item_view_in_listview.findViewById(R.id.tv_style);
        TextView composer = item_view_in_listview.findViewById(R.id.tv_composer);

        name.setText(tmp.getName());
        rate.setText(String.valueOf(tmp.getRate()));
        style.setText(tmp.getStyle());
        composer.setText(tmp.getComposer());
        return item_view_in_listview;
    }
}
