package com.example.sst2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView tv_id, tv_name, btn_room, btn_matching;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_id =findViewById(R.id.tv_id);
        tv_name =findViewById(R.id.tv_name);
        btn_room = findViewById(R.id.btn_room);
        btn_room.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RoomActivity.class);
                startActivity(intent); //액티비티 이동
            }
        });
        btn_matching = findViewById(R.id.btn_matching);
        btn_matching.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MatchingActivity.class);
                startActivity(intent); //액티비티 이동
            }
        });

        Intent intent = getIntent();
        String userID = intent.getStringExtra("userID");
        String userName = intent.getStringExtra("userName");



        tv_id.setText(userID);
        tv_name.setText(userName);


    }
}