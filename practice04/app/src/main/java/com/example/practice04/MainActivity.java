package com.example.practice04;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;

import android.media.AudioManager;
import android.media.SoundPool;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    SoundPool soundf;
    Button btn;
    int tom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        soundf = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        tom = soundf.load(this, R.raw.ex, 1);
        btn = (Button) findViewById(R.id.button);

        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                soundf.play(tom, 1, 1, 0, 0, 1);
            }
        });
    }
}
