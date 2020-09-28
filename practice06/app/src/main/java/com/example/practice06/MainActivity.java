package com.example.practice06;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonA = (Button)findViewById(R.id.buttonA);
        buttonA.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getApplicationContext(), "A 버튼 눌러짐", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, Sub1Activity.class);
                startActivity(intent);
            }
        });

        Button buttonB = (Button)findViewById(R.id.buttonB);
        buttonB.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "B 버튼 눌러짐", Toast.LENGTH_SHORT).show();
            }
        });

        Button buttonC = (Button) findViewById(R.id.buttonC);
        buttonC.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "C 버튼 눌러짐", Toast.LENGTH_SHORT).show();
            }
        });
    }
}




