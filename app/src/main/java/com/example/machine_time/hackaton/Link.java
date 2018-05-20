package com.example.machine_time.hackaton;


import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.Headers;
import retrofit.http.POST;

public interface Link {
    @Headers("Content-Type: application/json")
    @POST("auth.php")
    Call<Object> getSt(@Body User user);

    @Headers("Content-Type: application/json")
    @POST("reg.php")
    Call<Object> reg(@Body UserReg userReg);

    @Headers("Content-Type: application/json")
    @POST("requests.php")
    Call<Object> services(@Body Services services);


}
