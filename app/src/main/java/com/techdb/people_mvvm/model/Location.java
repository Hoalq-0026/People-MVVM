package com.techdb.people_mvvm.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by le.quang.hoa on 12/5/16.
 */

public class Location implements Serializable {

    @SerializedName("street")
    public String street;

    @SerializedName("city")
    public String city;

    @SerializedName("state")
    public String state;

    @SerializedName("zip")
    public String zip;


}
