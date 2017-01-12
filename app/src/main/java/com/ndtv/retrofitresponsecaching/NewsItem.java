package com.ndtv.retrofitresponsecaching;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;


/**
 * Created by ELAA on 25-10-2016.
 * Modified by Nagaraj
 */


public class NewsItem implements Parcelable {
    public int type;

    @SerializedName(value = "news_id", alternate = {"id", "guid"})
    public String id;
    public String title;
    public String link;
    public String pubDate;

    @SerializedName(value = "thumbnail_image", alternate = {"thumb_image", "thumbnail"})
    public String thumbnail;
    public String by_line;
    @SerializedName(value = "full_image", alternate = {"story_image", "photo"})
    public String storyImage;
    public String label;
    public String keywords;
    public String description;

    public boolean isTrending;

    public void setTrending(boolean trending) {

        isTrending = trending;
    }

    protected NewsItem(Parcel in) {
        id = in.readString();
        title = in.readString();
        link = in.readString();
        pubDate = in.readString();
        thumbnail = in.readString();
        by_line = in.readString();
        storyImage = in.readString();
        label = in.readString();
        keywords = in.readString();
        description = in.readString();
    }

    public static final Creator<NewsItem> CREATOR = new Creator<NewsItem>() {
        @Override
        public NewsItem createFromParcel(Parcel in) {
            return new NewsItem(in);
        }

        @Override
        public NewsItem[] newArray(int size) {
            return new NewsItem[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(title);
        dest.writeString(link);
        dest.writeString(pubDate);
        dest.writeString(thumbnail);
        dest.writeString(by_line);
        dest.writeString(storyImage);
        dest.writeString(label);
        dest.writeString(keywords);
        dest.writeString(description);
    }

    public NewsItem(int type) {
        this.type = type;
    }

    public NewsItem() {
    }

    @Override
    public boolean equals(Object obj) {
        if (id != null)
            return id.equals(((NewsItem) obj).id);
        return type == ((NewsItem) obj).type;
    }
}
