package com.example.test.ui.alarm;

import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;


import com.example.test.R;


public class AlarmFragment extends Fragment {

    MediaPlayer player;
    ConstraintLayout alarmscreen;

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (player != null) {
            player.release();
            player = null;
        }
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.activity_alarm, container, false);


        alarmscreen = (ConstraintLayout) rootView.findViewById(R.id.alarmscreen);
        final int myRed = ContextCompat.getColor(getActivity(), R.color.myRed);
        final Button button1 = rootView.findViewById(R.id.button2);
        final Button button2 = rootView.findViewById(R.id.button3);

        player = MediaPlayer.create(getActivity(), R.raw.siren);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player.start();
                alarmscreen.setBackgroundColor(myRed);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (player.isPlaying()) {
                    player.reset();
                    alarmscreen.setBackgroundColor(Color.WHITE);
                    player = MediaPlayer.create(getActivity(), R.raw.siren);
                }
            }
        });
      return rootView;

    }
}
