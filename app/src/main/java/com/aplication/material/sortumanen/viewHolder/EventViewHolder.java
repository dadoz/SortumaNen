package com.aplication.material.sortumanen.viewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.aplication.material.sortumanen.R;
import com.aplication.material.sortumanen.models.Event;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class EventViewHolder extends RecyclerView.ViewHolder {
    private final TextView eventType;
    private final TextView eventTimestamp;
    private final TextView eventUrl;
    private TextView eventTitle;
    private TextView eventContent;

    public EventViewHolder(View itemView) {
        super(itemView);
        eventTitle = (TextView) itemView.findViewById(R.id.eventTitleId);
        eventContent = (TextView) itemView.findViewById(R.id.eventContentId);
        eventTimestamp = (TextView) itemView.findViewById(R.id.eventTimestampId);
        eventType = (TextView) itemView.findViewById(R.id.eventTypeId);
        eventUrl = (TextView) itemView.findViewById(R.id.eventUrlId);
    }

    /**
     * binding data to viewholder from model
     * @param model
     */
    public void bindToEvent(Event model) {
        eventTitle.setText(model.title);
        eventContent.setText(model.content);
        try {
            Calendar cal = Calendar.getInstance(Locale.ITALIAN);
            cal.setTimeInMillis(Long.parseLong(model.timestamp));
            String date = DateFormat.getDateInstance(DateFormat.DEFAULT, Locale.ITALIAN).format(cal.getTime());
            eventTimestamp.setText(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        eventType.setText(model.type);
        eventUrl.setText(model.url);
    }
}
