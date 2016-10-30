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

        // [START create_database_reference]
        mDatabase = FirebaseDatabase.getInstance().getReference();
        // [END create_database_reference]

        //loggin in user
        mAuth = FirebaseAuth.getInstance();
        // [START auth_state_listener]
        mAuthListener = firebaseAuth -> {
            FirebaseUser user = firebaseAuth.getCurrentUser();
            if (user != null) {
                // User is signed in
                Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
            } else {
                // User is signed out
                Log.d(TAG, "onAuthStateChanged:signed_out");
            }
            // [START_EXCLUDE]
//                updateUI(user);
            // [END_EXCLUDE]
        };

        signInAnonymously();

        initView();
        return rootView;
    }

    // [START on_start_add_listener]
    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }
    // [END on_start_add_listener]

    // [START on_stop_remove_listener]
    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
    // [END on_stop_remove_listener]
    /**
     *
     */
    public void signInAnonymously() {
        mAuth.signInAnonymously()
                .addOnCompleteListener(getActivity(), task -> {
                    Log.d(TAG, "signInAnonymously:onComplete:" + task.isSuccessful());

                    // If sign in fails, display a message to the user. If sign in succeeds
                    // the auth state listener will be notified and logic to handle the
                    // signed in user can be handled in the listener.
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
     * @param databaseReference
     * @return
     */
    public abstract Query getQuery(DatabaseReference databaseReference);


    /**
     * recyclerviewAdapter
     */
    private static class EventsRecyclerviewAdapter extends FirebaseRecyclerAdapter<Event, EventViewHolder> {
        public EventsRecyclerviewAdapter(Class<Event> modelClass, int modelLayout, Class<EventViewHolder> viewHolderClass, Query ref) {
            super(modelClass, modelLayout, viewHolderClass, ref);
        }

        public EventsRecyclerviewAdapter(Class<Event> modelClass, int modelLayout, Class<EventViewHolder> viewHolderClass, DatabaseReference ref) {
            super(modelClass, modelLayout, viewHolderClass, ref);
        }

//        @Override
//        public Event getItem(int position) {
//            //order by timestamp (invert item pos)
//            return super.getItem(super.getItemCount() - (position + 1));
//        }

        @Override
        protected void populateViewHolder(EventViewHolder viewHolder, Event model, int position) {
            final DatabaseReference eventRef = getRef(position);

            // Set click listener for the whole Event view
            final String eventKey = eventRef.getKey();
            viewHolder.bindToEvent(model);

//                viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
            // Launch EventDetailActivity
//                        Intent intent = new Intent(getActivity(), EventDetailActivity.class);
//                        intent.putExtra(EventDetailActivity.EXTRA_Event_KEY, EventKey);
//                        startActivity(intent);
//                    }
//                });

            //stars events
            // Determine if the current user has liked this Event and set UI accordingly
//                if (model.stars.containsKey(getUid())) {
//                    viewHolder.starView.setImageResource(R.drawable.ic_toggle_star_24);
//                } else {
//                    viewHolder.starView.setImageResource(R.drawable.ic_toggle_star_outline_24);
//                }
            // Bind Event to ViewHolder, setting OnClickListener for the star button
//                viewHolder.bindToEvent(model, new View.OnClickListener() {
//                    @Override
//                    public void onClick(View starView) {
//                        // Need to write to both places the Event is stored
//                        DatabaseReference globalEventRef = mDatabase.child("Events").child(EventRef.getKey());
//                        DatabaseReference userEventRef = mDatabase.child("user-Events").child(model.uid).child(EventRef.getKey());
//
//                        // Run two transactions
//                        onStarClicked(globalEventRef);
//                        onStarClicked(userEventRef);
//                    }
//                });
        }
    }


    // [START Event_stars_transaction]
//    private void onStarClicked(DatabaseReference EventRef) {
//        EventRef.runTransaction(new Transaction.Handler() {
//            @Override
//            public Transaction.Result doTransaction(MutableData mutableData) {
//                Event event = mutableData.getValue(Event.class);
//                if (event == null) {
//                    return Transaction.success(mutableData);
//                }
//                if (event.stars.containsKey(getUid())) {
//                    // Unstar the Event and remove self from stars
//                    event.starCount = event.starCount - 1;
//                    event.stars.remove(getUid());
//                } else {
//                    // Star the Event and add self to stars
//                    event.starCount = event.starCount + 1;
//                    event.stars.put(getUid(), true);
//                }
//                //Set value and report transaction success
//                mutableData.setValue(event);
//                return Transaction.success(mutableData);
//            }
//
//            @Override
//            public void onComplete(DatabaseError databaseError, boolean b,
//                                   DataSnapshot dataSnapshot) {
//                // Transaction completed
//                Log.d(TAG, "EventTransaction:onComplete:" + databaseError);
//            }
//        });
//    }
//    // [END Event_stars_transaction]

}