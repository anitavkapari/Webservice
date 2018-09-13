package com.example.annu.webservice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btnAdd, btnGet, btnUpdate,btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        setContentView( R.layout.activity_main );
        btnAdd=findViewById( R.id.btnAdd );
        btnGet=findViewById( R.id.btnGet );
        btnUpdate=findViewById( R.id.btnUpdate );
        btnDelete=findViewById( R.id.btnDelete );
    }

    public void addEmployee(View view) {
        Intent intent=new Intent( this, AddActivity.class );
        startActivity( intent );
    }

    public void viewEmployee(View view) {
        Intent intent=new Intent( this,GetActivity.class );
        startActivity( intent );
    }

    /*public void UpdateEmployee(View view) {
        Intent intent=new Intent( this,UpdateActivity.class );
        startActivity( intent );
    }*/



}
