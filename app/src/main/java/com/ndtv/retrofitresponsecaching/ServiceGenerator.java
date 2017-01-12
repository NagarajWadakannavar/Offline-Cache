package com.ndtv.retrofitresponsecaching;

import android.content.Context;

import java.io.IOException;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static okhttp3.internal.Internal.logger;

public class ServiceGenerator {
    private static ServiceGenerator sInstance;
    private OkHttpClient httpClient;
    private Retrofit.Builder builder;
    private final static int CACHE_SIZE = 10 * 1024 * 1024;// 10 MB
    public static final String API_BASE_URL = "http://ww";


    public static ServiceGenerator getsInstance(Context context) {
        if (sInstance == null)
            synchronized (ServiceGenerator.class) {
                if (sInstance == null)
                    sInstance = new ServiceGenerator(context);
            }
        return sInstance;
    }

    private ServiceGenerator() {

    }


    public <S> S createService(Class<S> serviceClass) {
        Retrofit retrofit = builder.client(httpClient).build();
        return retrofit.create(serviceClass);
    }

    private ServiceGenerator(final Context context) {
        builder = new Retrofit.Builder().baseUrl(API_BASE_URL).addConverterFactory(GsonConverterFactory.create());
        httpClient = new OkHttpClient.Builder()
                .cache(new Cache(context.getApplicationContext().getCacheDir(), CACHE_SIZE))
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request();
//                        if (MainActivity.isConnected(context)) {
//
//                           // request = request.newBuilder().header("Cache-Control", "public, max-age=" + 60).build();
//                            request = request.newBuilder().header("ETag", "ab0c5a0b5aeecb1807a89e4e5a8dc25d:1484198710").build();
//
//                        } else {
//                            request = request.newBuilder().header("Cache-Control", "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7).build();
//                        }
                        return chain.proceed(request);
                    }
                })
                .addInterceptor(new ApplicationInterceptor())
                .addNetworkInterceptor(new NetworkInterceptor())
                .build();
    }

    private class NetworkInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();

            long t1 = System.nanoTime();
            logger.info(String.format("Sending request %s on %s%n%s",
                    request.url(), chain.connection(), request.headers()));

            Response response = chain.proceed(request);

            long t2 = System.nanoTime();
            logger.info(String.format("Received response for %s in %.1fms%n%s",
                    response.request().url(), (t2 - t1) / 1e6d, response.headers()));

            return response;
        }
    }

    private class ApplicationInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();

            long t1 = System.nanoTime();
            logger.info(String.format("Sending request %s on %s%n%s",
                    request.url(), chain.connection(), request.headers()));

            Response response = chain.proceed(request);

            long t2 = System.nanoTime();
            logger.info(String.format("Received response for %s in %.1fms%n%s",
                    response.request().url(), (t2 - t1) / 1e6d, response.headers()));

            return response;
        }
    }


}
