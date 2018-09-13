package com.example.annu.webservice;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.annu.webservice.adapter.EpmloyeeListAdapter;
import com.example.annu.webservice.model.BaseResponse;
import com.example.annu.webservice.model.Employee;
import com.example.annu.webservice.service.ApiClient;
import com.example.annu.webservice.service.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetActivity extends AppCompatActivity implements EmployeeClickListener {
    RecyclerView rvEmployee;
    ProgressBar progress;
    List<Employee> employees;
    ApiInterface apiInterface;//declare
    EpmloyeeListAdapter employeeListAdapter;
    //Constens
    public static final String DATA= "data";
    public static final int REQUEST_UPDATE= 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_get );
        rvEmployee = findViewById( R.id.rvEmployee );
        progress = findViewById( R.id.progress );
        rvEmployee.setLayoutManager( new LinearLayoutManager( this ) );

        apiInterface = ApiClient.getClient( ).create( ApiInterface.class );//
       fetchAllEmployee();
    }

    private void fetchAllEmployee() {
        Call<List<Employee>> callEmployee = apiInterface.getAllEmployee( );
        progress.setVisibility( View.VISIBLE );

        callEmployee.enqueue( new Callback<List<Employee>>( ) {
            @Override
            public void onResponse(Call<List<Employee>> call, Response<List<Employee>> response) {
                employees = response.body( );
                progress.setVisibility( View.GONE );
                employeeListAdapter = new EpmloyeeListAdapter( employees, GetActivity.this );
                rvEmployee.setAdapter( employeeListAdapter );
            }

            @Override
            public void onFailure(Call<List<Employee>> call, Throwable t) {
                progress.setVisibility( View.GONE );

            }
        } );
    }

    @Override
    public void onDeleteClick(int i) {
        final Employee employee = employeeListAdapter.getItemAtPosion( i );
        int emloyeeId = employee.getId( );
        Call<BaseResponse> callDelete = apiInterface.deleteEmployee( emloyeeId );
        progress.setVisibility( View.VISIBLE );
        callDelete.enqueue( new Callback<BaseResponse>( ) {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                progress.setVisibility( View.GONE );
                BaseResponse baseResponse = response.body( );
                employees.remove( employee );
                employeeListAdapter.notifyDataSetChanged( );
                Toast.makeText( GetActivity.this, baseResponse.getMessage(), Toast.LENGTH_SHORT ).show();
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                progress.setVisibility( View.GONE );

            }
        } );
    }

    @Override
    public void onItemClick(int i) {
        Employee employee = employeeListAdapter.getItemAtPosion( i );
        Intent intent =new Intent( this,UpdateActivity.class );
        intent.putExtra( DATA,employee );
        startActivityForResult( intent,REQUEST_UPDATE );
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult( requestCode, resultCode, data );
        if (requestCode==REQUEST_UPDATE && requestCode==RESULT_OK){
            fetchAllEmployee();
        }
    }
}
