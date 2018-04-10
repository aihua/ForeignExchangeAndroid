package com.settleitsoft.foreignexchange;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class ForeignExchange extends AppCompatActivity {

    private Fragment welcomeFragment;
    private Toolbar toolbar;
    private TextView toolbarTitle;
    private ProgressBar circularProgBar;
    private AlertDialog.Builder travelItineraryDialog;
    private Handler checkProgBarHandler;
    private String callDialog = "A";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foreign_exchange);

        // Obtiene los items del diseño
        toolbar         = findViewById(R.id.toolbar);
        toolbarTitle    = findViewById(R.id.toolbar_Text);
        circularProgBar = findViewById(R.id.welcome_progBar);

        // Configura el Toolbar
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        setupToolbarText(R.string.welcome_title);

        runProgBar();          // Ejecuta progress Bar
        setupVerifyProgBar();  // Ejecuta la verificacion del progress Bar

        // Obtiene el fragment de Welcome
        welcomeFragment = new FragmentWelcome();
        FragmentTransaction fragTransaction = getSupportFragmentManager().beginTransaction();
        fragTransaction.replace(R.id.fragm_main_container, welcomeFragment );
        fragTransaction.commit();

    }// Fin de onCreate

    /* Funcion que se encarga de configurar el
     * texto toolbar general de la applicacion
     * Param:
     *      id: id del string a adjuntar
     */
    public void setupToolbarText( int id ){

        toolbarTitle.setText(id);

    }// fin de setupToolbar

    /* Funcion que se encarga de ejecutar el
     * progress bar por un tiempo de 5 segundos
     */
    public void runProgBar(){

        circularProgBar.setVisibility( View.VISIBLE );

        // Duerme la aplicacion durante 5 segundos
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                circularProgBar.setVisibility( View.GONE );
            }
        }, 5000);

    }// fin de runProgBar

    /* Funcion que se encarga de configurar la verificacion
     * del estado del progress bar.
     */
    private void setupVerifyProgBar(){

        checkProgBarHandler = new Handler();
        checkProgBarHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if( circularProgBar.getVisibility() == View.GONE ){
                    createTravelItineraryDialog();
                }else{
                    checkProgBarHandler.postDelayed(this, 1000);
                }
            }
        },1000);

    }// Fin funcion setupVerifyProgBarxd

    /* Funcion que se encarga de crear los dialogos
     * para la creacion o actualizacion del itinerario
     * de viaje del usuario.
     */
    private void createTravelItineraryDialog(){

        int titleId = 0;
        int textId  = 0;

        // Identifica el dialogo a crear.
        switch(callDialog){
            case "A":
                titleId = R.string.noTravelItinerary_title;
                textId  = R.string.noTravelItinerary_text;
                break;
            case "B":
                titleId = R.string.countryNoTravelItinerary_title;
                textId  = R.string.countryNoTravelItinerary_text;
                break;
            case "F":
                break;
        }// Fin del switch

        // Instancia el objeto dialogo
        travelItineraryDialog = new AlertDialog.Builder(this);

        // Configura el objeto dialogo
        travelItineraryDialog
                .setTitle(titleId)
                .setMessage(textId)
                .setCancelable(false)
                .setPositiveButton(R.string.yes_button_text, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setNegativeButton(R.string.no_button_text, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        // Identifica el dialogo a crear.
                        switch(callDialog){
                            case "A":
                                callDialog = "B";
                                createTravelItineraryDialog();
                                break;
                            case "B":
                                callDialog = "F";
                                break;
                        }// Fin del switch

                    }
                });

        // Muestra el Dialogo
        travelItineraryDialog.show();

    }// Fin de createTravelItineraryDialog

}// Fin clase ForeignExchange
