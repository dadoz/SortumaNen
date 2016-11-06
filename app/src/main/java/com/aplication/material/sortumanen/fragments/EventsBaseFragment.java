package com.aplication.material.sortumanen.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.aplication.material.sortumanen.R;
import com.aplication.material.sortumanen.adapters.EventsRecyclerviewAdapter;
import com.aplication.material.sortumanen.models.Event;
import com.aplication.material.sortumanen.viewHolder.EventViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import butterknife.BindView;
import butterknife.ButterKnife;

public abstract class EventsBaseFragment extends Fragment {
    private static final String TAG = "EventsBaseFragment";
    private DatabaseReference mDatabase;
    private FirebaseRecyclerAdapter<Event, EventViewHolder> mAdapter;
    private LinearLayoutManager mManager;

    @BindView(R.id.eventRecyclerviewId)
    RecyclerView mRecycler;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    public EventsBaseFragment() {}

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_events_layout, container, false);
        ButterKnife.bind(this, rootView);

        initFirebaseRef();
        signInAnonymously();
        initView();
        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    /**
     *
     */
    protected void initFirebaseRef() {
        //get database ref
        mDatabase = FirebaseDatabase.getInstance().getReference();

        //loggin in user
        mAuth = FirebaseAuth.getInstance();
        mAuthListener = firebaseAuth -> {
            FirebaseUser user = firebaseAuth.getCurrentUser();
            if (user != null) {
                // User is signed in
                Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
//                updateUI(user);
                return;
            }

            // User is signed out
            Log.d(TAG, "onAuthStateChanged:signed_out");
//                updateUI(user);
        };
    }

    /**
     *
     */
    public void signInAnonymously() {
        mAuth.signInAnonymously()
                .addOnCompleteListener(getActivity(), task -> {
                    Log.d(TAG, "signInAnonymously:onComplete:" + task.isSuccessful());
                    if (!task.isSuccessful()) {
                        Log.w(TAG, "signInAnonymously", task.getException());
                        Toast.makeText(getContext(), "Authentication failed.",
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    /**
     *
     */
    protected void initView() {
        // Set up Layout Manager, reverse layout
        mManager = new LinearLayoutManager(getActivity());
        mManager.setReverseLayout(true);
        mManager.setStackFromEnd(true);
        mRecycler.setHasFixedSize(true);
        mRecycler.setLayoutManager(mManager);

        mAdapter = initFirebaseAdapter();
        mRecycler.setAdapter(mAdapter);
        initFilters();
    }


    /**
     * init firebase adapter
     */
    private FirebaseRecyclerAdapter<Event, EventViewHolder> initFirebaseAdapter() {
        // Set up FirebaseRecyclerAdapter with the Query
        Query eventsQuery = getQuery(mDatabase);
        mAdapter = new EventsRecyclerviewAdapter(Event.class, R.layout.item_event,
                EventViewHolder.class, eventsQuery);
        return mAdapter;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mAdapter != null) {
            mAdapter.cleanup();
        }
    }

//    public String getUid() {
//        return FirebaseAuth.getInstance().getCurrentUser().getUid();
//    }

    /**
     *
     */
    protected abstract void initFilters();

    /**
     *
     * @param databaseReference
     * @return
     */
    public abstract Query getQuery(DatabaseReference databaseReference);
}