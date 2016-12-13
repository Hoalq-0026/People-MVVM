package com.techdb.people_mvvm.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by le.quang.hoa on 12/5/16.
 */

public class Name implements Serializable {

    @SerializedName("title")
    public String title;

    @SerializedName("first")
    public String first;

    @SerializedName("last")
    public String last;
}
