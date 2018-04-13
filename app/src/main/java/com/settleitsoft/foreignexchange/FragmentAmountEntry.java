package com.settleitsoft.foreignexchange;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;

public class FragmentAmountEntry extends Fragment {

    private View amountEntryLayout;
    private ArrayList<String> data;

    // Obtengo parametros pasados al fragment.
    @Override
    public void onCreate( Bundle bundle ) {
        super.onCreate(bundle);
        if( getArguments() != null ){
            this.data = getArguments().getStringArrayList("data");
        }
    }

    // Metodo principal o constructor
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Obtiene items del diseño
        amountEntryLayout  = inflater.inflate(R.layout.fragment_amount_entry, container, false);

        // Configura titulo del toolbar
        ForeignExchange.setupToolbarText(R.string.foreingExchange_title);

        return amountEntryLayout;
    }

}
