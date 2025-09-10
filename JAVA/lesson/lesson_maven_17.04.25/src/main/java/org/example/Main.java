package org.example;


import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.jetbrains.annotations.TestOnly;

import java.io.IOException;

public class Main {

    private static final OkHttpClient client = new OkHttpClient();

    @TestOnly
    public static void main(String[] args) throws IOException {
        Request request = new Request.Builder()
                .url("https://dog.ceo/api/breeds/image/random")
                .build();

        Response response = client.newCall(request).execute();

        String responseCode = String.valueOf(response.code());
        String responseBody = response.body().string();

        //Почему ошибка?
        //System.out.println(response.body().string());
        //System.out.println(response.body().string());

        //Работа с ОС
        //H2 - бд в RAM
        //Мб Room
        //Сохранение по ссылкам
        //Отправка сложных объектов
        //Аннотации как создавать и примеры




        JsonObject json = JsonParser.parseString(responseBody).getAsJsonObject();


        System.out.println(responseCode);
        System.out.println(responseBody);
        System.out.println(json.get("message").getAsString());

    }
}