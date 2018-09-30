package com.example.userlap.servicecenter;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class search_service extends AppCompatActivity {

    DatabaseHelper mydb3;
    Button searchService;
    EditText searchLocation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_service);
        mydb3=new DatabaseHelper(this);
        searchLocation=(EditText)findViewById(R.id.LocationSRCH);
        searchService=(Button)findViewById((R.id.SearchBTNService));
        search();
    }

    public void search(){
        searchService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res=mydb3.getServiceBtLocation(searchLocation.getText().toString());
                if(res.getCount()==0){
                    ShowMsg("Error","Nothing Found on Databse");
                    //Toast.makeText(View_Service.this,"Count=0",Toast.LENGTH_LONG).show();
                    return;
                }
                StringBuffer buffer=new StringBuffer();
                while(res.moveToNext()){
                    //Toast.makeText(View_Service.this,"while",Toast.LENGTH_LONG).show();
                    buffer.append("ID:"+res.getString(0)+"\n");
                    buffer.append("Service Name:"+res.getString(1)+"\n");
                    buffer.append("HotLine:"+res.getString(2)+"\n");
                    buffer.append("Location:"+res.getString(3)+"\n\n");
                    Log.d("builder",res.getString(0));
                    Log.d("builder",res.getString(1));
                    Log.d("builder",res.getString(2));
                    Log.d("builder",res.getString(3));

                }

                ShowMsg("Search Results",buffer.toString());
            }
        });
    }
    public void ShowMsg(String Title,String Msg){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(Title);
        builder.setMessage(Msg);
        builder.show();
        //Toast.makeText(View_Service.this,"ShowMsg",Toast.LENGTH_LONG).show();
    }
}
