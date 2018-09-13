package com.example.annu.webservice;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.annu.webservice.model.BaseResponse;
import com.example.annu.webservice.model.Employee;
import com.example.annu.webservice.service.ApiClient;
import com.example.annu.webservice.service.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateActivity extends AppCompatActivity {
    EditText edtName,edtAddress,edtSalary,edtPhone,edtDesignation;
    ProgressBar progress;
    Employee employee;
    Button btnUpdate;
    int id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_update );
        employee =getIntent().getParcelableExtra( GetActivity.DATA );
        id=employee.getId();
        edtName=findViewById( R.id.edtName );
//        edtName.setOnClickListener( (View.OnClickListener) this );
        edtAddress=findViewById( R.id.edtAddress );
        edtSalary=findViewById( R.id.edtSalary );
        edtPhone=findViewById( R.id.edtPhone );
        edtDesignation=findViewById( R.id.edtDesignation );
        progress=findViewById( R.id.progress );

        edtAddress.setText( employee.getAddress() );
        edtName.setText( employee.getName() );
        edtPhone.setText( employee.getPhoneNumber() );
        edtDesignation.setText( employee.getDesignation() );
        edtSalary.setText( String.valueOf( employee.getSalary()) );

    }

    public void updateEmployee(View view) {
         String name=edtName.getText().toString().trim();
        String address=edtAddress.getText().toString().trim();
        String phoneNumber=edtPhone.getText().toString().trim();
        String salString=edtSalary.getText().toString().trim();
        String designation=edtDesignation.getText().toString().trim();
        if (TextUtils.isEmpty( name)|| TextUtils.isEmpty( address )||TextUtils.isEmpty( phoneNumber )
                ||TextUtils.isEmpty( salString )||TextUtils.isEmpty( designation)){
            Toast.makeText( this,"please the enter the field", Toast.LENGTH_SHORT ).show();
            return;
        }
        long salary= Long.parseLong( salString );
        Employee employee= new Employee(name,address,phoneNumber,salary,designation);
        employee.setId( id );
        ApiInterface apiInterface= ApiClient.getClient().create( ApiInterface.class );
        Call<BaseResponse> call=apiInterface.updateEmployee(id, employee );
        progress.setVisibility( View.VISIBLE );

        call.enqueue( new Callback<BaseResponse>( ) {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                BaseResponse baseResponse=response.body();
                Toast.makeText( UpdateActivity.this,baseResponse.getMessage(),Toast.LENGTH_SHORT).show();
                progress.setVisibility(View.GONE);
                setResult( RESULT_OK );
                finish();
               /* edtName.setText( " " );
                edtAddress.setText( " " );
                edtPhone.setText( " " );
                edtSalary.setText( " " );
                edtDesignation.setText( " " );*/


            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                Toast.makeText( UpdateActivity.this, t.getMessage( ), Toast.LENGTH_SHORT ).show( );
                progress.setVisibility( View.GONE );
            }
        } );

    }
}
