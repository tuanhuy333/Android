package com.example.retrofit2.Retrofit2;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;


public interface DataClient {

    //gui file
    @Multipart
    @POST("image_multipart.php")
    Call<String>upload_image(@Part MultipartBody.Part image);//kem theo file luon


    @FormUrlEncoded
    @POST("insert.php")
    Call<String>insert_data(@Field("ten_image") String tenanh); //@value cua @Field phai giong trong insert.php

}
