package com.settleitsoft.foreignexchange;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

public class TravelItineraryDialog {

    public static void createDialog( final Activity activity , final String callDialog ){

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
        AlertDialog.Builder travelItineraryDialog = new AlertDialog.Builder(activity);

        // Configura el objeto dialogo
        travelItineraryDialog
                .setTitle(titleId)
                .setMessage(textId)
                .setCancelable(false)
                .setPositiveButton(R.string.yes_button_text, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // Identifica el dialogo a crear.
                        switch(callDialog){
                            case "A":
                                FragmentToTransaction.commit(activity, ForeignExchange.getTravelItineraryFragment());
                                break;
                            case "B":
                                break;
                        }// Fin del switch
                    }
                })
                .setNegativeButton(R.string.no_button_text, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        // Identifica el dialogo a crear.
                        switch(callDialog){
                            case "A":
                                createDialog( activity,"B");
                                break;
                            case "B":
                                break;
                        }// Fin del switch
                    }
                });

        // Muestra el Dialogo
        travelItineraryDialog.show();

    }// Fin funcion createDialog

}// fin de la clase
