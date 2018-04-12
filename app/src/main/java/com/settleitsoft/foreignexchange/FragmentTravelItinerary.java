package com.settleitsoft.foreignexchange;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;
import java.util.ArrayList;

public class FragmentTravelItinerary extends Fragment {

    private View travelItineraryLayout;
    private EditText departureDate, returnDate;
    private EmbeddedDatepicker departureDatepicker, returnDatepicker;
    private FloatingActionButton countryAddButton, countryRemoveButton;
    private Spinner countriesSpinner;
    private Button fileTravelButton, cancelButton;
    private String selectedCountry, selectedCountryList;
    private CountriesToAdapter countriesAdapter;
    private SelectCountryToAdapter selectCountryAdapter;
    private ArrayList<String> selectedCountryArray;
    private Toast messageToast;
    private ListView countriesListView;

    // Metodo principal o constructor
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Infla la vista correspondiente
        travelItineraryLayout = inflater.inflate(R.layout.fragment_travel_itinerary, container, false);

        // Configura titulo del toolbar
        ForeignExchange.setupToolbarText(R.string.create_travel_itinerary_title);

        // Obtiene items del diseño
        departureDate         = travelItineraryLayout.findViewById(R.id.departureDate_Edit);
        returnDate            = travelItineraryLayout.findViewById(R.id.returnDate_Edit);
        countryAddButton      = travelItineraryLayout.findViewById(R.id.country_add_Button);
        countryRemoveButton   = travelItineraryLayout.findViewById(R.id.country_remove_Button);
        countriesSpinner      = travelItineraryLayout.findViewById(R.id.countries_spinner);
        countriesListView     = travelItineraryLayout.findViewById(R.id.countries_List);
        fileTravelButton      = travelItineraryLayout.findViewById(R.id.fileTravelItinerary_Button);
        cancelButton          = travelItineraryLayout.findViewById(R.id.cancel_Button);

        // Instancia los objetos necesarios para la correcta interaccion con el usuario
        departureDatepicker  = new EmbeddedDatepicker(getActivity(), departureDate);
        returnDatepicker     = new EmbeddedDatepicker(getActivity(), returnDate);
        selectCountryAdapter = new SelectCountryToAdapter(getActivity());

        // Configura el objeto Toast para los mensajes de usuario.
        messageToast = Toast.makeText(getActivity(),"", Toast.LENGTH_LONG );

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
                selectedCountry = countriesSpinner.getSelectedItem().toString();
                if( selectedCountry.equals(getResources().getString(R.string.country_select_prompt)) ){
                    // Mensaje de aviso al usuario
                    setupMessageToast(R.string.country_select_Text);
                }else{
                    selectedCountryArray = selectCountryAdapter.getCountriesArray();
                    if( selectedCountryArray.contains(selectedCountry) ){
                        // Mensaje de aviso al usuario
                        setupMessageToast(R.string.country_selected_exist_Text);
                    }else{
                        countriesListView.setAdapter(selectCountryAdapter.getAdapter(selectedCountry,true));
                    }
                }
            }
        });

        // Declara el evento de escucha del item country_remove_Button
        countryRemoveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if( selectedCountryList == "" || selectedCountryList == null ){
                    // Mensaje de aviso al usuario
                    setupMessageToast(R.string.country_no_selected_list_Text);
                }else{
                    countriesListView.setAdapter(selectCountryAdapter.getAdapter(selectedCountryList,false));
                    selectedCountryList = "";
                }
            }
        });

        // Declara el evento de escucha del item countrie_add_Button countries_List
        countriesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                selectedCountryList = adapterView.getItemAtPosition(i).toString();
            }
        });

        // Declara el evento de escucha del item fileTravelItinerary_Button
        fileTravelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean fileCreate      = true;
                selectedCountryArray    = selectCountryAdapter.getCountriesArray();
                String formatDate       = getResources().getString(R.string.formatDate_label);

                if( String.valueOf(departureDate.getText()).equals(formatDate) ){
                    fileCreate = false;
                }else
                if( String.valueOf(returnDate.getText()).equals(formatDate)){
                    fileCreate = false;
                }else
                if( selectedCountryArray.isEmpty() ){
                    fileCreate = false;
                }

                if( fileCreate ){
                    // Mensaje de aviso al usuario
                    setupMessageToast(R.string.travel_itinerary_created);

                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if( messageToast.getView().isShown() ){
                                handler.postDelayed(this, 1000);
                            }else{
                                launchTransaction("B");
                            }
                        }
                    },1000);
                }else{
                    // Mensaje de aviso al usuario
                    setupMessageToast(R.string.data_required_Text);
                }
            }
        });

        // Declara el evento de escucha del item cancel_Button
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchTransaction("A");
            }
        });

    }// fin de listenerEventsSetup

    /* Metodo encargdo de lanzar la transaccion Welcome con su
     * correspondiente parametro.
     */
    private void launchTransaction( String dialog ){
        Bundle args = new Bundle();
        args.putString("callDialog",dialog);
        Fragment welcomeFragment  = ForeignExchange.getWelcomeFragment();
        welcomeFragment.setArguments(args);
        FragmentToTransaction.commit( ForeignExchange.getActivityMain(), welcomeFragment );
    }

    /* Configura los mensajes de aviso o alerta
     * para el usuario */
    private void setupMessageToast( int idString ){

        if( messageToast.getView().isShown() ){
            messageToast.cancel();
        }
        messageToast.setText(idString);
        messageToast.show();
    }

}
