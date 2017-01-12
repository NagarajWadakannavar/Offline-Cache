package com.ndtv.retrofitresponsecaching;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ELAA on 25-10-2016.
 */

public class News  implements Parcelable{

    @SerializedName(value="news", alternate={"results", "items"})
    public List<NewsItem> newsList;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(newsList);
    }

    public static final Creator<News> CREATOR = new Creator<News>() {

        public News createFromParcel(Parcel in) {
            News news = new News();
            news.newsList = new ArrayList<>();
            in.readTypedList(news.newsList, NewsItem.CREATOR);
            return news;
        }

        public News[] newArray(int size) {
            return new News[size];
        }
    };
}
