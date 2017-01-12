package com.ndtv.retrofitresponsecaching;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.ndtv.retrofitresponsecaching.databinding.ActivityMainBinding;

import retrofit2.Call;
import retrofit2.Callback;

public class MainActivity extends AppCompatActivity implements RecyclerViewOnItemClickListener.Listener<NewsItem> {
    private final static String TAG = MainActivity.class.getName();
    private APIService newsAPI;
    private Call<News> call;
    private final static String URL = "http://www.astroawani.com/api/article/listing/client_key/awani-app-iphone-ff61c07ed4d1d18160931333cd048632/?category=latest-stories&extra_params=category,description,fullimage,feed_meta,full-description,by_line,device,rss,label&format=json";
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        newsAPI = ServiceGenerator.getsInstance(this).createService(APIService.class);
        downloadFirstPage();
    }


    protected void downloadFirstPage() {
        call = newsAPI.downloadNews(URL);
        call.enqueue(new Callback<News>() {
            @Override
            public void onResponse(Call<News> call, retrofit2.Response<News> response) {
                if (response.body() == null) {
                    Log.i(TAG, "Response body is null");
                    return;
                }
                if (response.isSuccessful()) {
                    binding.progressBar.setVisibility(View.GONE);
                    binding.recyclerView.setAdapter(new NewsListAdapter(MainActivity.this,response.body().newsList,MainActivity.this));
                    Log.i(TAG, "Response is successful");

                } else {
                    Log.i(TAG, "Response is not successful");

                }
            }

            @Override
            public void onFailure(Call<News> call, Throwable t) {
                Log.i(TAG, "On error = " + t.getMessage());

            }
        });
    }

    public static boolean isConnected(Context context) {
        ConnectivityManager conn = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = conn.getActiveNetworkInfo();
        if (networkInfo != null) return networkInfo.isConnected();

        return false;
    }


    @Override
    public void onItemClick(NewsItem item, View v, int position) {

    }
}
