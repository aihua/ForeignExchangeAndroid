package com.settleitsoft.foreignexchange;

import android.support.v4.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FragDialogAmountCalculated extends DialogFragment {

    private View amountCalculated;
    private static final String TAG = "FragDialogAmountCalculated";

    // Metodo principal o constructor
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Obtiene items del diseño
        amountCalculated  = inflater.inflate(R.layout.fragment_amount_calculated, container, false);
        return amountCalculated;
    }

    // Metodo que ajusta el tamaño del diseño al contenedor tipo Dialogo.
    @Override
    public void onResume(){
        super.onResume();
         int widthLayout   = getResources().getDisplayMetrics().widthPixels;
         int heightLayout  = getResources().getDisplayMetrics().heightPixels;
         getDialog().getWindow().setLayout( widthLayout, heightLayout );
    }
}
