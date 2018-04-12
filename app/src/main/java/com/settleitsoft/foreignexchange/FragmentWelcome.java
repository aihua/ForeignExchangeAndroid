package com.settleitsoft.foreignexchange;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FragmentWelcome extends Fragment {

    private View welcomeLayout;
    private String callDialog;

    @Override
    public void onCreate( Bundle bundle ) {
        super.onCreate(bundle);
        if( getArguments() == null ){
            this.callDialog = "A";
        }else{
            this.callDialog = getArguments().getString("callDialog");
        }
    }

    // Metodo principal o constructor
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Obtiene items del diseño
        welcomeLayout  = inflater.inflate(R.layout.fragment_welcome, container, false);

        // Configura titulo del toolbar
        ForeignExchange.setupToolbarText(R.string.welcome_title);

        // Ejecuta y verifica el progress Bar
        ForeignExchange.runProgBar();
        ForeignExchange.setupVerifyProgBar( this.callDialog );

        return welcomeLayout;
    }
}
