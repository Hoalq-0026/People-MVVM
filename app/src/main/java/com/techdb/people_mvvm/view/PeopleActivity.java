package com.techdb.people_mvvm.view;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.techdb.people_mvvm.R;
import com.techdb.people_mvvm.databinding.ActivityPeopleBinding;
import com.techdb.people_mvvm.model.People;
import com.techdb.people_mvvm.viewmodel.PeopleViewModel;
import com.techdb.people_mvvm.viewmodel.PeopleViewModelContract;

import java.util.List;

/**
 * Created by le.quang.hoa on 12/6/16.
 */

public class PeopleActivity extends AppCompatActivity implements PeopleViewModelContract.MainView {

    private ActivityPeopleBinding mActivityPeopleBinding;
    private PeopleViewModel mPeopleViewModel;
    private PeopleViewModelContract.MainView mMainView = this;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initDataBinding();
        setSupportActionBar(mActivityPeopleBinding.toolbar);
        setupListPeopleView(mActivityPeopleBinding.listPeople);

    }

    private void initDataBinding() {
        mActivityPeopleBinding = DataBindingUtil.setContentView(this, R.layout.activity_people);
        mPeopleViewModel = new PeopleViewModel(mMainView, getContext());
        mActivityPeopleBinding.setMainViewModel(mPeopleViewModel);
    }

    private void setupListPeopleView(RecyclerView listPeople) {
        PeopleAdapter adapter = new PeopleAdapter();
        listPeople.setAdapter(adapter);
        listPeople.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void loadData(List<People> peoples) {
        PeopleAdapter peopleAdapter = (PeopleAdapter) mActivityPeopleBinding.listPeople.getAdapter();
        peopleAdapter.setPeopleList(peoples);
    }

    @Override
    public Context getContext() {
        return PeopleActivity.this;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPeopleViewModel.destroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_github) {
            startActivityActionView();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void startActivityActionView() {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/LarryPham")));
    }
}
