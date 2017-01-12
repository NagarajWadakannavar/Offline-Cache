package com.ndtv.retrofitresponsecaching;

import android.databinding.BindingAdapter;
import android.text.TextUtils;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by NAGARAJ on 5/5/2016.
 */
public class BindingUtils {



    @BindingAdapter({"listImage"})
    public static void listImage(ImageView view, String url) {
        if (TextUtils.isEmpty(url))
            url = null;
        Picasso.with(view.getContext()).load(url).noFade()
                .into(view);

    }




}
