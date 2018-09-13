package com.example.annu.webservice.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.annu.webservice.EmployeeClickListener;
import com.example.annu.webservice.R;

public class EmployeeViewHolder extends RecyclerView.ViewHolder {
    TextView txtName, txtAddress, txtPhone;
    ImageButton btnDelete;
    EmployeeClickListener mListener;//2 step

    public EmployeeViewHolder(@NonNull View itemView, EmployeeClickListener listener) {//EmployeeClickListener listener 2nd step
        super( itemView );
        mListener = listener;//3rd step
        txtName = itemView.findViewById( R.id.txtName );
        txtAddress = itemView.findViewById( R.id.txtAddress );
        txtPhone = itemView.findViewById( R.id.txtPhone );
        btnDelete = itemView.findViewById( R.id.btnDelete );

        btnDelete.setOnClickListener( new View.OnClickListener( ) {
            @Override
            public void onClick(View view) {
                int i = getAdapterPosition( );
                mListener.onDeleteClick( i );
            }
        } );

        itemView.setOnClickListener( new View.OnClickListener( ) {
            @Override
            public void onClick(View view) {
                int i = getAdapterPosition( );
                mListener.onItemClick( i );
            }
        } );
    }
}
