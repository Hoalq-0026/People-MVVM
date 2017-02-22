package com.techdb.people_mvvm.data;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by le.quang.hoa on 12/5/16.
 */

public interface PeopleService {

    @GET("/")
    Observable<PeopleResponse> fetchPeople(@Query("results") int numberResult, @Query("nat") String position);
}
