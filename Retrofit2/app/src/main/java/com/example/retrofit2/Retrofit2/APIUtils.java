package com.example.retrofit2.Retrofit2;

public class APIUtils {
    public static final String base_URL="http://192.168.1.96:8080/Upload_image_multipart/";


    public static DataClient getData(){
        return RetrofitClient.getClient(base_URL).create(DataClient.class);
    }
}
