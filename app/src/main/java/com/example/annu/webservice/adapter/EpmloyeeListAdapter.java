package com.example.annu.webservice.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.annu.webservice.EmployeeClickListener;
import com.example.annu.webservice.R;
import com.example.annu.webservice.model.Employee;

import java.util.List;

public class EpmloyeeListAdapter extends RecyclerView.Adapter<EmployeeViewHolder> {
    List<Employee> mEmployeeList;
    EmployeeClickListener mListener;

    public EpmloyeeListAdapter(List<Employee> employees, EmployeeClickListener listener) {
        mEmployeeList = employees;
        mListener = listener;//initialise

    }

    @NonNull
    @Override
    public EmployeeViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from( viewGroup.getContext( ) ).inflate( R.layout.employee_list, viewGroup, false );
        EmployeeViewHolder employeeViewHolder = new EmployeeViewHolder( view, mListener );
        return employeeViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeeViewHolder employeeViewHolder, int i) {
        Employee employee = mEmployeeList.get( i );
        employeeViewHolder.txtName.setText( employee.getName( ) );
        employeeViewHolder.txtAddress.setText( employee.getAddress( ) );
        employeeViewHolder.txtPhone.setText( employee.getPhoneNumber( ) );

    }

    @Override
    public int getItemCount() {
        return mEmployeeList.size( );
    }

    public Employee getItemAtPosion(int i) {
        return mEmployeeList.get( i );
    }
}
