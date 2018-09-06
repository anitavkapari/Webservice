package com.example.annu.webservice.service;

import com.example.annu.webservice.model.BaseResponse;
import com.example.annu.webservice.model.Employee;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiInterface {
    @POST("/myapp/api/employees/single")
    public Call<BaseResponse> addEmployee(@Body Employee employee);

}
