package com.techdb.people_mvvm.viewmodel;

import android.databinding.BindingAdapter;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.techdb.people_mvvm.model.People;

/**
 * Created by le.quang.hoa on 12/6/16.
 */

public class PeopleDetailViewModel {

    private People mPeople;

    public PeopleDetailViewModel(People people) {
        this.mPeople = people;
    }

    public String getFullUserName() {
        mPeople.fullName = mPeople.name.title + "." + mPeople.name.first + "." + mPeople.name.last;
        return mPeople.fullName;
    }

    public String getUserName() {
        return mPeople.username.username;
    }

    public int getEmailVisibility() {
        return mPeople.hasEmail() ? View.VISIBLE : View.GONE;
    }

    public String getCell() {
        return mPeople.cell;
    }

    public String getEmail() {
        return mPeople.email;
    }

    public String getPictureProfile() {
        return mPeople.picture.large;
    }

    public String getAddress() {
        return mPeople.location.street + " " + mPeople.location.city + " " + mPeople.location.state;
    }

    public String getGender() {
        return mPeople.gender;
    }

    @BindingAdapter("imageUrl")
    public static void loadImage(ImageView imageView, String imageUrl) {
        Glide.with(imageView.getContext()).load(imageUrl).into(imageView);
    }
}
