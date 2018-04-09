package com.settleitsoft.foreignexchange;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import android.os.Handler;

public class FragmentWelcome extends Fragment {

    private View welcomeLayout;
    private ProgressBar circularProgBar;
    private final int time_display = 4000;

    // Metodo principal o constructor
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Obtiene items del diseño
        welcomeLayout   = inflater.inflate(R.layout.fragment_welcome, container, false);
        circularProgBar = welcomeLayout.findViewById(R.id.welcome_progBar);

        return welcomeLayout;
    }

    // Evento que se dispara cuando la vista ya esta cargada(Visible).
    // onCreateView ya ha sido ejecutado y ha retornado la vista.
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Duerme la aplicacion durante 5 segundos
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                circularProgBar.setVisibility( View.GONE );
            }
        }, time_display);
    }
}
