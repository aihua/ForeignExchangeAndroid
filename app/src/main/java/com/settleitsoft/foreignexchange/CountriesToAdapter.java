package com.settleitsoft.foreignexchange;

import android.app.Activity;
import android.widget.ArrayAdapter;
import java.util.ArrayList;

public class CountriesToAdapter {

    private Activity activity;
    private ArrayAdapter<CharSequence> countriesAdapter;


    public CountriesToAdapter(Activity activity) {
        this.activity = activity;
        this.countriesAdapter = setupAdapter();
    }

    /* Metodo que se encarga de configurar el adapatador. */
    private ArrayAdapter<CharSequence> setupAdapter(){

        return new ArrayAdapter(this.activity,
                                android.R.layout.select_dialog_item,
                                getValueOfArray());
    }

    /* Metodo que se encarga de obtener la lista de opciones
     * de paises para el combo countries.
     */
    private ArrayList<String> getValueOfArray(){

        String[] optionsArray  = this.activity.getResources().getStringArray(R.array.countries);
        ArrayList<String> countriesArray  = new ArrayList<>();

        // Itera los valores de la array
        for( String obj: optionsArray ){
            String[] keyValue = obj.split(",");
            if( keyValue.length == 1 ){
                countriesArray.add(keyValue[0]);
            }else{
                countriesArray.add(keyValue[1]);
            }
        }

        return countriesArray;
    }

    /* Obtiene el adaptador de paises  */
    public ArrayAdapter<CharSequence> getAdapter(){
        return countriesAdapter;
    }

}// Fin de la clase
