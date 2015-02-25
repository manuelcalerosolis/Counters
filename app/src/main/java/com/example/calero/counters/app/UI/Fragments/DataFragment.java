package com.example.calero.counters.app.UI.Fragments;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.calero.counters.app.AdapterData;
import com.example.calero.counters.app.Repositories.RepositoryCounter;
import com.example.calero.counters.app.ModelData;
import com.example.calero.counters.app.R;

import java.util.ArrayList;



public class DataFragment extends Fragment {

    private ListView listViewData;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragmentdata, container, false);
        listViewData = (ListView) view.findViewById(R.id.listView);

        loadCounters();

        return view;
    }

    private void loadCounters(){

        RepositoryCounter repositoryCounter = new RepositoryCounter();
        Cursor cursorCounters = repositoryCounter.cursorCounters();

        ArrayList<ModelData> listViewValues = new ArrayList<ModelData>();

        if(cursorCounters!=null && cursorCounters.moveToFirst() ){
            do{
                listViewValues.add( new ModelData(cursorCounters));
            }while (cursorCounters.moveToNext());
        }

        AdapterData adapter = new AdapterData(this.getActivity(), listViewValues );
        listViewData.setAdapter(adapter);

    }

}

