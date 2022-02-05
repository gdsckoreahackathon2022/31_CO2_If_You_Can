package com.example.catchco2ifyoucan;

import android.telecom.Call;
import android.util.Log;

import java.io.IOException;

import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class FileUploadUtils {
    public static void sendGPS(String ID, double latitude, double longitude, double transport) {
        String json = "{\"user_id\":\"" + ID + "\",\"lon\":" + Double.toString(longitude) + ",\"lat\":" + Double.toString(latitude) + "}";
        Log.e("qwe", json);
        RequestBody requestBody = new FormBody.Builder()
                .add("user_id", ID)
                .add("lon", Double.toString(longitude))
                .add("lat", Double.toString(latitude))
                .add("transport", Double.toString(transport))
                .build();
        Request request = new Request.Builder()
                .url("http://192.168.0.42:8000/catchCO2/")
                .post(requestBody)
                .build();

        OkHttpClient client = new OkHttpClient();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(okhttp3.Call call, Response response) throws IOException {
                Log.e("qwe", "TEST : " + response.body().string());
            }
        });
    }

    public static void sendJob(String ID, String job, int counter) {
        RequestBody requestBody = new FormBody.Builder()
                .add("user_id", ID)
                .add("act", job)
                .add("freq", Integer.toString(counter))
                .build();
        Request request = new Request.Builder()
                .url("http://192.168.0.42:8000/pybo/")
                .post(requestBody)
                .build();

        OkHttpClient client = new OkHttpClient();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(okhttp3.Call call, Response response) throws IOException {
                Log.e("qwe", "TEST : " + response.body().string());
            }
        });
    }
}
