package com.example.userlap.servicecenter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Main_Service extends AppCompatActivity {
    Button Add;
    Button Delete;
    Button Edit;
    Button Search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__service);
        Add=(Button)findViewById(R.id.AddService);
        Delete=(Button)findViewById(R.id.DeleteService);
        Edit=(Button)findViewById(R.id.EditService);
        Search=(Button)findViewById(R.id.SearchService);

        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Main_Service.this,Add_Service.class);
                startActivity(intent);
            }
        });
        Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Main_Service.this,View_Service.class);
                startActivity(intent);
            }
        });
        Edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Main_Service.this,Edit_Service.class);
                startActivity(intent);
            }
        });

        Search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Main_Service.this,search_service.class);
                startActivity(intent);
            }
        });

    }
}
