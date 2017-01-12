package com.ndtv.retrofitresponsecaching;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface APIService {

//    interface HeaderKeys {
//        String ACCESS_TOKEN = "accessToken";
//    }
//
//    interface QueryKeys {
//        String PAGE = "page";
//        String PAGE_NUMBER = "pagenumber";
//        String PAGE_SIZE = "pagesize";
//        String LANGUAGE = "language";
//        String ALL_FIELDS = "allfields";
//        String DESCRIPTION = "description";
//        String TAGS = "tags";
//        String EXCLUDE_ID = "excludeid";
//        String ID = "id";
//    }
//
//    int PAGE_SIZE = 21;


    @GET
    Call<News> downloadNews(@Url String url/*, @Query(QueryKeys.PAGE) String page, @Query(QueryKeys.PAGE_NUMBER) String pageNumber, @Query(QueryKeys.PAGE_SIZE) String size, @Query(QueryKeys.LANGUAGE) String language*/);


}


