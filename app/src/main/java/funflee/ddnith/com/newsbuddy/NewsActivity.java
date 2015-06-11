package funflee.ddnith.com.newsbuddy;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class NewsActivity extends Activity {

    private ListView lvNews;
    private NewsAdapter adapterNews;
    NewsClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("NewsBuddy","onCreate");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        lvNews = (ListView)findViewById(R.id.lvNews);
        ArrayList<News> aNews = new ArrayList<News>();
        adapterNews = new NewsAdapter(this,aNews);
        lvNews.setAdapter(adapterNews);
        fetchAllNews();

    }

    private void fetchAllNews() {
        Log.d("NewsBuddy","fetchAllNews");

        client = new NewsClient();
        client.getNewsList(new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject responseBody) {
                JSONArray items = null;
                Log.d("NewsBuddy","Success ");
                try {
                    // Get the news json array
                    items = responseBody.getJSONArray("NewsItem");
                    Log.d("NewsBuddy","items json array length "+items.length());

                    // Parse json array into array of model objects
                    ArrayList<News> newsList = News.fromJson(items);
                    Log.d("NewsBuddy","newsList size "+newsList.size());

                    // Load model objects into the adapter
                    for (News news : newsList) {
                        adapterNews.add(news); // add news through the adapter
                    }
                    adapterNews.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        });
    }

}
