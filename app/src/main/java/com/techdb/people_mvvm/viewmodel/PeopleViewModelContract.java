package com.techdb.people_mvvm.viewmodel;

import android.content.Context;

import com.techdb.people_mvvm.model.People;

import java.util.List;

/**
 * Created by le.quang.hoa on 12/6/16.
 */

public interface PeopleViewModelContract {

    interface MainView {
        void loadData(List<People> peoples);

        Context getContext();
    }

    interface ViewModel {
        void destroy();
    }
}
