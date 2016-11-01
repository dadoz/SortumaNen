package com.aplication.material.sortumanen.adapters;

import com.aplication.material.sortumanen.models.Event;
import com.aplication.material.sortumanen.viewHolder.EventViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
public class EventsRecyclerviewAdapter extends FirebaseRecyclerAdapter<Event, EventViewHolder> {
    /**
     * recyclerviewAdapter
     */
    public EventsRecyclerviewAdapter(Class<Event> modelClass, int modelLayout, Class<EventViewHolder> viewHolderClass, Query ref) {
        super(modelClass, modelLayout, viewHolderClass, ref);
    }

    public EventsRecyclerviewAdapter(Class<Event> modelClass, int modelLayout, Class<EventViewHolder> viewHolderClass, DatabaseReference ref) {
        super(modelClass, modelLayout, viewHolderClass, ref);
    }

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
//    // [END Event_stars_transaction]}
