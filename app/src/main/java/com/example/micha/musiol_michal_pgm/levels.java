package com.example.micha.musiol_michal_pgm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class levels extends AppCompatActivity {

    Button b1, b2, b3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_levels);

        Button b1 = (Button) findViewById(R.id.pl);
        Button b2 = (Button) findViewById(R.id.ps);
        Button b3 = (Button) findViewById(R.id.pt);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(levels.this, GameActivity.class);
                i.putExtra("lvl",1);
                startActivity(i);
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(levels.this, GameActivity.class);
                i.putExtra("lvl",2);
                startActivity(i);
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(levels.this, GameActivity.class);
                i.putExtra("lvl",3);
                startActivity(i);
            }
        });
    }
}
