package com.example.a119or112btn;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;

import android.content.Intent;
import android.net.Uri;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn;

        btn = (Button)findViewById(R.id.layout_5);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // 전화 걸기
                Intent mIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:119"));
                startActivity(mIntent);
            }
        });

    }
}