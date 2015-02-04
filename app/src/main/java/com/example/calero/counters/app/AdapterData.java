package com.example.calero.counters.app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class AdapterData extends ArrayAdapter<ModelData> {

    private ArrayList<ModelData> arrayListModel;
    private LayoutInflater layoutInflater;

    public ArrayList<ModelData> getArrayListModel() {
        return arrayListModel;
    }

    public void setArrayListModel(ArrayList<ModelData> arrayListModel) {
        this.arrayListModel = arrayListModel;
    }

    public AdapterData(Context context, ArrayList<ModelData> arrayListModel) {
        super(context, 0, arrayListModel);
        setArrayListModel(arrayListModel);
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return getArrayListModel().size();
    }

    @Override
    public long getItemId(int position) {
        return position+1;
    }

    @Override
    public View getView(int position, View fragmentDataRow, ViewGroup parent) {

        HolderData dataHolder;
        View viewRow;

        if (fragmentDataRow == null) {
            viewRow     = layoutInflater.inflate(R.layout.fragment_data_row, parent, false);
            dataHolder  = new HolderData(viewRow);
            viewRow.setTag(dataHolder);
        } else {
            viewRow     = fragmentDataRow;
            dataHolder  = (HolderData) viewRow.getTag();
        }

        ModelData modelData = getArrayListModel().get(position);
        if (modelData != null) {
//            dataHolder.getImageViewType().setImageResource(modelData.getIntTypeIcon());
            dataHolder.setImageViewType(modelData.getIntTypeIcon());
            dataHolder.getTextViewGlobal().setText(modelData.getRowDetails());
            dataHolder.getTextViewCounter().setText(modelData.getCounterString());
            dataHolder.getTextViewDuration().setText( getContext().getResources().getText(R.string.duration_counter ) + " : " + modelData.getDurationString());
        }

        return viewRow;

    }
}
