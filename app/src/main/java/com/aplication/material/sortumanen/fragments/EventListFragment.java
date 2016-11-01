package com.aplication.material.sortumanen.fragments;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

import org.joda.time.DateTime;

public class EventListFragment extends EventsBaseFragment{
    @Override
    protected void initFilters() {
    }

    @Override
    public Query getQuery(DatabaseReference databaseReference) {
        long fromTimestamp = new DateTime().minusDays(1).toInstant().getMillis();
        long toTimestamp = new DateTime().plusDays(1).toInstant().getMillis();
        return databaseReference
                .child("events")
                .orderByChild("timestamp")
                .startAt(Long.toString(fromTimestamp))
                .endAt(Long.toString(toTimestamp));
    }


}
