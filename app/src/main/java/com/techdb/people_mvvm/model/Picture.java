package com.techdb.people_mvvm.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by le.quang.hoa on 12/5/16.
 */

public class Picture implements Serializable {

    @SerializedName("large")
    public String large;

    @SerializedName("medium")
    public String medium;

    @SerializedName("thumbnail")
    public String thumbnail;
}
