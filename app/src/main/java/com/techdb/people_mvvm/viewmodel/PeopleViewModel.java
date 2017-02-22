package com.techdb.people_mvvm.viewmodel;

import android.content.Context;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.support.annotation.NonNull;
import android.view.View;

import com.techdb.people_mvvm.PeopleApp;
import com.techdb.people_mvvm.R;
import com.techdb.people_mvvm.data.PeopleResponse;
import com.techdb.people_mvvm.data.PeopleService;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * Created by le.quang.hoa on 12/6/16.
 */

public class PeopleViewModel implements PeopleViewModelContract.ViewModel {

    public ObservableInt mPeopleProgress;
    public ObservableInt mPeopleList;
    public ObservableInt mPeopleLabel;
    public ObservableField<String> mMessageLabel;

    private PeopleViewModelContract.MainView mMainView;
    private Context mContext;
    private Subscription mSubscription;

    public PeopleViewModel(@NonNull PeopleViewModelContract.MainView mainView, @NonNull Context context) {
        this.mMainView = mainView;
        this.mContext = context;
        this.mPeopleProgress = new ObservableInt(View.GONE);
        this.mPeopleList = new ObservableInt(View.GONE);
        this.mPeopleLabel = new ObservableInt(View.VISIBLE);

        mMessageLabel = new ObservableField<>(mContext.getResources().getString(R.string.default_loading_people));
    }


    public void onClickFabLoad(View view) {
        initializeViews();
        fetchPeopleList();
    }

    public void initializeViews() {
        mPeopleProgress.set(View.VISIBLE);
        mPeopleList.set(View.GONE);
        mPeopleLabel.set(View.VISIBLE);
    }

    private void fetchPeopleList() {
        final String URL = "http://api.randomuser.me/?results=10&nat=en";
        unSubscribeFromObservable();
        PeopleApp app = PeopleApp.create(mContext);
        PeopleService peopleService = app.getPeopleService();
        mSubscription = peopleService.fetchPeople(10, "en")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(app.subscribleScheduler())
                .subscribe(new Action1<PeopleResponse>() {
                    @Override
                    public void call(PeopleResponse peopleResponse) {
                        mPeopleProgress.set(View.GONE);
                        mPeopleLabel.set(View.GONE);
                        mPeopleList.set(View.VISIBLE);

                        if (mMainView != null) {
                            mMainView.loadData(peopleResponse.getPeopleList());
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        throwable.printStackTrace();
                        mMessageLabel.set(mContext.getString(R.string.error_loading_people));
                        mPeopleProgress.set(View.GONE);
                        mPeopleList.set(View.GONE);
                        mPeopleLabel.set(View.VISIBLE);
                    }
                });

    }

    @Override
    public void destroy() {
        reset();
    }

    private void reset() {
        unSubscribeFromObservable();
        mSubscription = null;
        mContext = null;
        mMainView = null;
    }

    private void unSubscribeFromObservable() {
        if (mSubscription != null && mSubscription.isUnsubscribed()) {
            mSubscription.unsubscribe();
        }
    }
}
