package com.example.kibb.xpera.damiz.supplier;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.kibb.xpera.damiz.bean.Book;
import com.example.kibb.xpera.damiz.config.Constants;
import com.google.android.agera.Result;
import com.google.android.agera.Supplier;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by KIBB on 8/30/2017.
 */

public class BookSupplier implements Supplier<Result<List<Book>>> {
    private String key;
    private OkHttpClient client= new OkHttpClient();
    public BookSupplier(String key){this.key= key;}
    public void setKey(String key){this.key= key;}

    private List<Book> getBooks(){
        HttpUrl httpUrl= HttpUrl.parse(Constants.API_URL).newBuilder()
                .addQueryParameter("q",key)
                .addQueryParameter("start","0")
                .addQueryParameter("count","30").build();
        Request request = new Request.Builder().url(httpUrl).build();
        try{
            Response response= client.newCall(request).execute();
            JSONObject jsonObject= new JSONObject(response.body().string());
            JSONArray jsonArray= jsonObject.optJSONArray("books");
            Gson gson= new Gson();
            List<Book> books= gson.fromJson(jsonArray.toString(), new TypeToken<List<Book>>(){}.getType());
            return books;
        }catch (Exception e){
            Log.v("Log","error in getBooks "+ e.getMessage());
            return  null;
        }
    }
    @NonNull
    @Override
    public Result<List<Book>> get(){
        List<Book> books= getBooks();
        if (books == null){
            return Result.failure();
        }else {
            return Result.success(books);
        }
    }
}
