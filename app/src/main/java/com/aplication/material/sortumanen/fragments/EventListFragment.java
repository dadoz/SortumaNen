package com.aplication.material.sortumanen.fragments;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

public class EventListFragment extends EventsBaseFragment{
    @Override
    public Query getQuery(DatabaseReference databaseReference) {
        return databaseReference.child("events");
    }
}
