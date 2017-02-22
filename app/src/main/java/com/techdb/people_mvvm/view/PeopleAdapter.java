package com.techdb.people_mvvm.view;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.techdb.people_mvvm.R;
import com.techdb.people_mvvm.databinding.ItemPeopleBinding;
import com.techdb.people_mvvm.model.People;
import com.techdb.people_mvvm.viewmodel.ItemPeopleViewModel;

import java.util.Collections;
import java.util.List;

/**
 * Created by le.quang.hoa on 12/6/16.
 */

public class PeopleAdapter extends RecyclerView.Adapter<PeopleAdapter.PeopleAdapterViewHolder> {

    private List<People> mPeopleList;

    public PeopleAdapter() {
        mPeopleList = Collections.emptyList();
    }


    @Override
    public PeopleAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemPeopleBinding itemPeopleBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
            R.layout.item_people, parent, false);

        return new PeopleAdapterViewHolder(itemPeopleBinding);
    }

    @Override
    public void onBindViewHolder(PeopleAdapterViewHolder holder, int position) {
        holder.bindPeople(mPeopleList.get(position));
    }

    @Override
    public int getItemCount() {
        return mPeopleList.size();
    }

    public void setPeopleList(List<People> peopleList) {
        mPeopleList = peopleList;
        notifyDataSetChanged();
    }

    public static class PeopleAdapterViewHolder extends RecyclerView.ViewHolder {
        ItemPeopleBinding mItemPeopleBinding;

        public PeopleAdapterViewHolder(ItemPeopleBinding itemPeopleBinding) {
            super(itemPeopleBinding.itemPeople);
            this.mItemPeopleBinding = itemPeopleBinding;
        }

        public void bindPeople(People people) {
            if (mItemPeopleBinding.getPeopleViewModel() == null) {
                mItemPeopleBinding.setPeopleViewModel(new ItemPeopleViewModel(itemView.getContext(), people));
            } else {
                mItemPeopleBinding.getPeopleViewModel().setPeople(people);
            }
            mItemPeopleBinding.executePendingBindings();
        }
    }
}
