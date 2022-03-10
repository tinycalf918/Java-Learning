package com.example.nio;

import okhttp3.*;

import java.io.IOException;

public class HttpClient {

    public static void main(String[] args) {
        String url = "http://localhost:8808/test";

//        String response = syncGet(url);
//        System.out.println(response);
        asyncGet(url);
    }

    private static void asyncGet(String url) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().get().url(url).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println("调用失败：" + e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response != null && response.isSuccessful()) {
                    System.out.println("调用成功：" + response.body().string());
                }
            }
        });
    }

    private static String syncGet(String url) {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder().get().url(url).build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
            if (response != null && response.isSuccessful()) {
                return response.body().string();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (response != null) {
                response.body().close();
            }
        }
        return null;
    }

}
