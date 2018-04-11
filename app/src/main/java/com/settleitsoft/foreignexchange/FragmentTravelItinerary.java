package com.settleitsoft.foreignexchange;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class FragmentTravelItinerary extends Fragment {

    private View travelItineraryLayout;
    private EditText departureDate, returnDate;
    private EmbeddedDatepicker departureDatepicker, returnDatepicker;
    private FloatingActionButton countryAddButton, countryRemoveButton;
    private Spinner countriesSpinner;
    private String currCountrieSelected;
    private CountriesToAdapter countriesAdapter;

    // Metodo principal o constructor
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Infla la vista correspondiente
        travelItineraryLayout   = inflater.inflate(R.layout.fragment_travel_itinerary, container, false);

        // Obtiene items del diseño
        departureDate           = travelItineraryLayout.findViewById(R.id.departureDate_Edit);
        returnDate              = travelItineraryLayout.findViewById(R.id.returnDate_Edit);
        countryAddButton        = travelItineraryLayout.findViewById(R.id.country_add_Button);
        countryRemoveButton     = travelItineraryLayout.findViewById(R.id.country_remove_Button);
        countriesSpinner        = travelItineraryLayout.findViewById(R.id.countries_spinner);

        // Instancia los objetos EmbeddedDatepicker correspondiente a cada EditText
        departureDatepicker  = new EmbeddedDatepicker(getActivity(), departureDate);
        returnDatepicker     = new EmbeddedDatepicker(getActivity(), returnDate);

        // Configura los eventos de escucha
        listenerEventsSetup();

        // Configura la lista del combo paises
        countriesAdapter = new CountriesToAdapter(getActivity());
        countriesSpinner.setAdapter(countriesAdapter.getAdapter());

        return travelItineraryLayout;
    }

    /* Metodo que configura los eventos de escucha de los
     * items del diseño.
     */
    private void listenerEventsSetup(){

        // Declara el evento de escucha del item departureDate_Edit
        departureDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                departureDatepicker.show();
            }
        });

        // Declara el evento de escucha del item returnDate_Edit
        returnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                returnDatepicker.show();
            }
        });

        // Declara el evento de escucha del item countrie_add_Button
        countryAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currCountrieSelected = countriesSpinner.getSelectedItem().toString();

                if( currCountrieSelected.equals(getResources().getString(R.string.country_select_prompt)) ){
                    Toast.makeText(getActivity(),R.string.country_select_Text,Toast.LENGTH_LONG).show();
                }else{

                }
            }
        });
    }

}
