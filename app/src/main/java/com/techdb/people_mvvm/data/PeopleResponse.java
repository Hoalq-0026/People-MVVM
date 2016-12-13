package com.techdb.people_mvvm.data;

import com.google.gson.annotations.SerializedName;
import com.techdb.people_mvvm.model.People;

import java.util.List;

/**
 * Created by le.quang.hoa on 12/5/16.
 */

public class PeopleResponse {

    @SerializedName("results")
    private List<People> mPeopleList;

    public List<People> getPeopleList() {
        return mPeopleList;
    }

    public void setPeopleList(List<People> peopleList) {
        mPeopleList = peopleList;
    }
}
