package com.techdb.people_mvvm.view;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import com.techdb.people_mvvm.R;
import com.techdb.people_mvvm.databinding.ActivityDetailPeopleBinding;
import com.techdb.people_mvvm.model.People;
import com.techdb.people_mvvm.viewmodel.PeopleDetailViewModel;
import com.techdb.people_mvvm.viewmodel.PeopleDetailViewModelContract;

/**
 * Created by le.quang.hoa on 12/6/16.
 */

public class PeopleDetailActivity extends AppCompatActivity implements PeopleDetailViewModelContract {

    private static final String EXTRA_PEOPLE = "EXTRA_PEOPLE";
    private ActivityDetailPeopleBinding mActivityDetailPeopleBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mActivityDetailPeopleBinding = DataBindingUtil.setContentView(this, R.layout.activity_detail_people);
        setSupportActionBar(mActivityDetailPeopleBinding.toolbar);
        displayHomeAsUpEnabled();
        getExtrasFromIntent();

    }

    public static Intent launchDetail(Context context, People people) {
        Intent intent = new Intent(context, PeopleDetailActivity.class);
        intent.putExtra(EXTRA_PEOPLE, people);
        return intent;
    }

    private void getExtrasFromIntent() {
        People people = (People) getIntent().getSerializableExtra(EXTRA_PEOPLE);
        PeopleDetailViewModel peopleDetailViewModel = new PeopleDetailViewModel(people);
        mActivityDetailPeopleBinding.setPeopleDetailViewModel(peopleDetailViewModel);
        setTitle(people.name.title + "." + people.name.first + "." + people.name.last);
    }

    private void displayHomeAsUpEnabled() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public Context getContext() {
        return null;
    }
}
