package com.aplication.material.sortumanen.viewHolder;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.aplication.material.sortumanen.R;
import com.aplication.material.sortumanen.animators.ExpandCardviewAnimator;
import com.aplication.material.sortumanen.models.Event;

import java.lang.ref.WeakReference;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Locale;


public class EventViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private final TextView eventType;
    private final View eventTypeView;
    private final TextView eventTimestamp;
    private final TextView eventUrl;
    private final ExpandCardviewAnimator expandCardviewAnimator;
    private final View eventExpandedContentLayout;
    private TextView eventTitle;
    private TextView eventContent;

    public EventViewHolder(View itemView) {
        super(itemView);
        eventTitle = (TextView) itemView.findViewById(R.id.eventTitleId);
        eventTypeView = itemView.findViewById(R.id.eventTypeLayoutId);
        eventContent = (TextView) itemView.findViewById(R.id.eventContentId);
        eventTimestamp = (TextView) itemView.findViewById(R.id.eventTimestampId);
        eventType = (TextView) itemView.findViewById(R.id.eventTypeId);
        eventUrl = (TextView) itemView.findViewById(R.id.eventUrlId);
        eventExpandedContentLayout = itemView.findViewById(R.id.eventExpandedContentLayoutId);
        expandCardviewAnimator = new ExpandCardviewAnimator((CardView) itemView, new WeakReference<>(itemView.getContext()));
        itemView.setOnClickListener(this);
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
        eventType.setTextColor(getColorByType(model.type));
        eventTypeView.setBackgroundColor(getColorByType(model.type));
        eventExpandedContentLayout.setBackgroundColor(getColorByType(model.type));
        eventUrl.setText(model.url == null ?
                itemView.getContext().getString(R.string.no_website_availble) : model.url);
    }

    /**
     *
     * @param type
     * @return
     */
    private int getColorByType(String type) {
        Context ctx = itemView.getContext();
        switch (type) {
            case "eventi-culturali":
                return ContextCompat.getColor(ctx, R.color.indigo_400);
            case "enogastronomia":
                return ContextCompat.getColor(ctx, R.color.amber_400);
            case "fiere-mercati-e-tartufo":
                return ContextCompat.getColor(ctx, R.color.brown_400);
            default:
                return ContextCompat.getColor(ctx, R.color.indigo_400);
        }
    }

    @Override
    public void onClick(View v) {
        if (expandCardviewAnimator != null) {
            boolean isExpanded = expandCardviewAnimator.toggleCardView();
            eventExpandedContentLayout.setVisibility(isExpanded ? View.VISIBLE: View.GONE);
            eventTitle.setMaxLines(isExpanded ? 4 : 2); //FIXME please
            eventContent.setMaxLines(isExpanded ? 2 : 1); //FIXME please
        }
    }
}
