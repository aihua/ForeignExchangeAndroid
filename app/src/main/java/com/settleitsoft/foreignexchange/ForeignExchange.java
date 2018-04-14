package com.settleitsoft.foreignexchange;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

public class ForeignExchange extends AppCompatActivity {

    private static Fragment welcomeFragment, travelItineraryFragment, amountEntryFragment;
    private static ProgressBar circularProgBar;
    private static Handler checkProgBarHandler;
    private static Activity foreignExchangeActivity;
    private static TextView toolbarTitle;
    private static ConstraintLayout layoutProgBar;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foreign_exchange);

        // Obtiene la actividad principal
        foreignExchangeActivity = this;

        // Obtiene los items del diseño
        toolbar         = findViewById(R.id.toolbar);
        toolbarTitle    = findViewById(R.id.toolbar_Text);
        layoutProgBar   = findViewById(R.id.layout_progBar);
        circularProgBar = findViewById(R.id.welcome_progBar);

        // Configura el Toolbar
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        // Obitiene Fragmentos
        welcomeFragment         = new FragmentWelcome();            // Welcome
        travelItineraryFragment = new FragmentTravelItinerary();    // Travel Itinerary
        amountEntryFragment     = new FragmentAmountEntry();        // Amount Entry

        // Incrustar Fragmento
        FragmentToTransaction.commit( foreignExchangeActivity, welcomeFragment );

    }// Fin de onCreate

    /* Funcion que se encarga de configurar el
     * texto toolbar general de la applicacion.
     */
    public static void setupToolbarText( int id ){
        toolbarTitle.setText(id);
    }

    /* Obtiene la actividad principal de la App. */
    public static Activity getActivityMain(){
        return foreignExchangeActivity;
    }

    /* Obtiene el fragmento del diseño Welcome */
    public static Fragment getWelcomeFragment(){
        return welcomeFragment;
    }

    /* Obtiene el fragmento del diseño Amount Entry */
    public static Fragment getAmountEntryFragment(){
        return amountEntryFragment;
    }

    /* Obtiene el fragmento del diseño Travel Itinerary */
    public static Fragment getTravelItineraryFragment(){
        return travelItineraryFragment;
    }

    /* Obtiene el objeto Progress Bar de la actividad principal. */
    public static ProgressBar getCircularProgBar(){
        return circularProgBar;
    }

    /* Se encarga de bloquear y desbloquear el diseño mientra esta en carga. */
    public static void setClikeableProgBar( Boolean clickear ){
        layoutProgBar.setClickable(clickear);
    }

    /* Funcion que se encarga de ejecutar el progress
     * bar por un tiempo de 5 segundos.
     *
     * NOTA:
     * Este progreso es no esta ligado a ninguna
     * carga o proceso que este haciendo la aplicacion.
     */
    public static void runProgBar(){

        setClikeableProgBar(true);
        circularProgBar.setVisibility( View.VISIBLE );
        // Duerme la aplicacion durante 5 segundos
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                setClikeableProgBar(false);
                circularProgBar.setVisibility( View.GONE );
            }
        }, 3000);
    }

    /* Funcion que se encarga de configurar la verificacion
     * del estado del progress bar.
     */
    public static void setupVerifyProgBar( final ArrayList<String> data  ){

        checkProgBarHandler = new Handler();
        checkProgBarHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if( circularProgBar.getVisibility() == View.GONE ){
                    TravelItineraryDialog.createDialog( foreignExchangeActivity, data );
                }else{
                    checkProgBarHandler.postDelayed(this, 1000);
                }
            }
        },1000);
    }

}// Fin clase ForeignExchange
