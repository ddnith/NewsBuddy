package funflee.ddnith.com.newsbuddy;

import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

/**
 * Created by DEEPAK on 6/11/2015.
 */
public class NewsClient {
    private final static String API_BASE_URL = "https://api.myjson.com/bins/46jjg";
    private AsyncHttpClient client;

    public NewsClient(){
        this.client= new AsyncHttpClient();
    }
    private String getApiUrl(){
        return API_BASE_URL;
    }
    public void getNewsList(JsonHttpResponseHandler handler){
        Log.d("NewsBuddy","fetchAllNews getApiUrl "+getApiUrl());

        client.get(getApiUrl(),null,handler);
    }

 }

