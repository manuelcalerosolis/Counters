package com.example.calero.counters.app;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class HolderData {

    TextView textViewCounter;
    TextView textViewGlobal;
    ImageView imageViewType;
    TextView textViewDuration;

    public TextView getTextViewCounter() {
        return textViewCounter;
    }

    public TextView getTextViewGlobal() {
        return textViewGlobal;
    }

    public TextView getTextViewDuration() {
        return textViewDuration;
    }

    public ImageView getImageViewType() {
        return imageViewType;
    }

    public void setImageViewType( int resId ){
        imageViewType.setImageResource( resId );
    }

    HolderData(View row) {
            this.imageViewType = (ImageView) row.findViewById(R.id.iconTypeCounter);
            this.textViewCounter = (TextView) row.findViewById(R.id.textViewCounter);
            this.textViewGlobal = (TextView) row.findViewById(R.id.textView);
            this.textViewDuration = (TextView) row.findViewById(R.id.textViewDuration);
        }
    }
