package com.techdb.people_mvvm.viewmodel;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.support.v4.app.ActivityOptionsCompat;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.techdb.people_mvvm.R;
import com.techdb.people_mvvm.model.People;
import com.techdb.people_mvvm.view.PeopleDetailActivity;

/**
 * Created by le.quang.hoa on 12/6/16.
 */

public class ItemPeopleViewModel extends BaseObservable {

    private People mPeople;
    private Context mContext;

    public ItemPeopleViewModel(Context context, People people) {
        mPeople = people;
        mContext = context;
    }

    public String getFullName() {
        this.mPeople.fullName = mPeople.name.title + "." + mPeople.name.first + "." + mPeople.name.last;
        return mPeople.fullName;
    }

    public String getCell() {
        return mPeople.cell;
    }

    public String getEmail() {
        return mPeople.email;
    }

    public String getPictureProfile() {
        return mPeople.picture.medium;
    }

    @BindingAdapter("imageUrl")
    public static void setImageUrl(ImageView imageView, String url) {
        Glide.with(imageView.getContext()).load(url).into(imageView);
    }

    public void onItemClick(View view) {
        Intent intent = PeopleDetailActivity.launchDetail(mContext, mPeople);
        Activity activity = (Activity)mContext;
        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, view, mContext.getString(R.string.app_name));

        mContext.startActivity(intent,options.toBundle());
    }

    public void setPeople(People people) {
        mPeople = people;
    }

}
