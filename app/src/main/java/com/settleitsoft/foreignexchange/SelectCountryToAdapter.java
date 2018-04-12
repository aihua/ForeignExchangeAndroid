package com.settleitsoft.foreignexchange;

import android.app.Activity;
import android.widget.ArrayAdapter;
import java.util.ArrayList;

public class SelectCountryToAdapter {

    private Activity activity;
    private ArrayList<String> countriesArray;

    /* Metodo constructor de la clase */
    public SelectCountryToAdapter(Activity activity) {
        this.activity = activity;
        this.countriesArray = new ArrayList<>();
    }

    /* Metodo que se encarga de configurar el adapatador. */
    private ArrayAdapter<CharSequence> getSetupAdapter(){

        return new ArrayAdapter(this.activity,
                android.R.layout.simple_list_item_1,
                this.countriesArray);
    }

    /* Metodo que se encarga de obtener el arreglo
     * de los paises seleccionados del spinner.
     */
    private void selectedCountryArray( String country ){
        this.countriesArray.add( country );
    }

    /* Metodo que obtiene el adaptador de paises. */
    public ArrayAdapter<CharSequence> getAdapter( String country ){
        selectedCountryArray(country);
        return getSetupAdapter();
    }

    /* Metodo que se encarga de obtener la lista
     * actual de paises seleccionados.
     */
    public ArrayList<String> getCountriesArray() {
        return this.countriesArray;
    }

}// Fin de la clase
