package com.settleitsoft.foreignexchange;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;

public class FragmentTravelItinerary extends Fragment {

    private View travelItineraryLayout;

    // Metodo principal o constructor
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Obtiene items del diseño
        travelItineraryLayout  = inflater.inflate(R.layout.fragment_travel_itinerary, container, false);
        return travelItineraryLayout;
    }
}
