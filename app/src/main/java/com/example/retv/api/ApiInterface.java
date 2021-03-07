package com.example.retv.api;

import com.example.retv.model.ValueModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;


public interface ApiInterface {


    @GET("{symbol}")
    Call<ValueModel> getData(@Path("symbol") String companySymbol);
}
