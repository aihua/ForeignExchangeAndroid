package com.settleitsoft.foreignexchange;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FragmentWelcome extends Fragment {

    private View welcomeLayout;

    // Metodo principal o constructor
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Obtiene items del diseño
        welcomeLayout  = inflater.inflate(R.layout.fragment_welcome, container, false);
        return welcomeLayout;
    }
}
