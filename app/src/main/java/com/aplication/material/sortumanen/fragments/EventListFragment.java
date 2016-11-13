package com.aplication.material.sortumanen.fragments;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.aplication.material.sortumanen.R;
import com.aplication.material.sortumanen.managers.StatusManager;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

import org.joda.time.DateTime;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EventListFragment extends EventsBaseFragment implements View.OnClickListener {
    @BindView(R.id.eventFilterLabelContainerId)
    View eventFilterLabelContainer;
    @BindView(R.id.filtersButtonId)
    View filtersButton;
    @BindView(R.id.eventMainContentFrameLayoutId)
    ViewGroup eventMainContentFrameLayout;

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    protected void initFilters() {
        filtersButton.setOnClickListener(this);
    }

    @Override
    public Query getQuery(DatabaseReference databaseReference) {
        long fromTimestamp = new DateTime().minusDays(1).toInstant().getMillis();
        long toTimestamp = new DateTime().toInstant().getMillis();
        return databaseReference
                .child("events")
                .orderByChild("timestamp")
                .startAt(Long.toString(fromTimestamp))
                .endAt(Long.toString(toTimestamp));
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.filtersButtonId:
                initFilter();
                break;
            case R.id.dayFilterLayoutId:
                Toast.makeText(getContext(), "day toggle", Toast.LENGTH_SHORT).show();
                break;
            case R.id.monthFilterLayoutId:
                Toast.makeText(getContext(), "month toggle", Toast.LENGTH_SHORT).show();
                break;
        }
    }


    /**
     * todo move on filter manager
     */
    private void initFilter() {
        StatusManager.getInstance().setFilterMode();
        eventFilterLabelContainer.setVisibility(View.GONE);
        getActivity().getLayoutInflater().inflate(R.layout.event_filters_layout, eventMainContentFrameLayout);
        setActionbarOnFilterMode(true);
        setViewBinds();
    }

    /**
     *
     */
    private void removeFilter() {
        eventFilterLabelContainer.setVisibility(View.VISIBLE);
        eventMainContentFrameLayout.removeView(eventMainContentFrameLayout.findViewById(R.id.filterLayoutId));
        setActionbarOnFilterMode(false);
    }

    /**
     * FIXME handle this better
     */
    public void onBackPressed() {
        removeFilter();
    }

    /**
     *
     */
    private void setActionbarOnFilterMode(boolean isFilterMode) {
        ActionBar actionbar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        actionbar.setDisplayShowTitleEnabled(!isFilterMode);
        actionbar.setElevation(0);
        actionbar.setDisplayShowHomeEnabled(isFilterMode);
        actionbar.setDisplayHomeAsUpEnabled(isFilterMode);
    }

    /**
     *
     */
    private void setViewBinds() {
        View dayFilterLayout = getView().findViewById(R.id.dayFilterLayoutId);
        View monthFilterLayout = getView().findViewById(R.id.monthFilterLayoutId);
        monthFilterLayout.setOnClickListener(this);
        dayFilterLayout.setOnClickListener(this);
    }


}
