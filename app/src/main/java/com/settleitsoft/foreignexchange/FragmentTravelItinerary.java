package com.settleitsoft.foreignexchange;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class FragmentTravelItinerary extends Fragment {

    private View travelItineraryLayout;
    private EditText departureDate, returnDate;
    private EmbeddedDatepicker departureDatepicker, returnDatepicker;

    // Metodo principal o constructor
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Obtiene items del diseño
        travelItineraryLayout   = inflater.inflate(R.layout.fragment_travel_itinerary, container, false);
        departureDate           = travelItineraryLayout.findViewById(R.id.departureDate_Edit);
        returnDate              = travelItineraryLayout.findViewById(R.id.returnDate_Edit);

        // Instancia los objetos EmbeddedDatepicker correspondiente a cada EditText
        departureDatepicker  = new EmbeddedDatepicker(getActivity(), departureDate);
        returnDatepicker     = new EmbeddedDatepicker(getActivity(), returnDate);

        // Configura los eventos de escucha
        listenerEditTextSetup();

        return travelItineraryLayout;
    }

    /* Metodo que configura los eventos de escucha de los
     * EditText correspondientes.
     */
    private void listenerEditTextSetup(){

        // Declara el evento de escucha del item EditText departureDate_Edit
        departureDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                departureDatepicker.show();
            }
        });

        // Declara el evento de escucha del item EditText returnDate_Edit
        returnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                returnDatepicker.show();
            }
        });
    }
}
