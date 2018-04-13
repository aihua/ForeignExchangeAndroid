package com.settleitsoft.foreignexchange;

import android.support.v4.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class FragDialogAmountCalculated extends DialogFragment {

    private View amountCalculatedLayout;
    private static final String TAG = "FragDialogAmountCalculated";
    private ArrayList<String> data;
    private Button calculateAmountButton;
    private TextView exchangeCountryTextView, conversionRateTextView, sourceCountryTextView;
    private String textValueContainer;
    private String[] countriesISO;

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
        amountCalculatedLayout  = inflater.inflate(R.layout.fragment_amount_calculated, container, false);

        // Obtiene items del diseño
        calculateAmountButton   = amountCalculatedLayout.findViewById(R.id.calculate_Button);
        exchangeCountryTextView = amountCalculatedLayout.findViewById(R.id.exchange_country_Text);
        conversionRateTextView  = amountCalculatedLayout.findViewById(R.id.conversion_rate_Text);
        sourceCountryTextView   = amountCalculatedLayout.findViewById(R.id.source_country_Text);
        countriesISO            = getResources().getStringArray(R.array.countriesISO);

        // Configura los textos con la informacion suministrada.
        textValueContainer = exchangeCountryTextView.getText().toString().replace("#country#", this.data.get(1));
        exchangeCountryTextView.setText(textValueContainer);

        textValueContainer = conversionRateTextView.getText().toString().replace("#code#", this.data.get(0));
        conversionRateTextView.setText(textValueContainer);

        textValueContainer = sourceCountryTextView.getText().toString().replace("#country#", this.data.get(1));
        textValueContainer = textValueContainer.replace("#code#", this.data.get(0));
        sourceCountryTextView.setText(textValueContainer);

        // Configura los eventos de escucha
        listenerEventsSetup();

        return amountCalculatedLayout;
    }

    // Metodo que ajusta el tamaño del diseño al contenedor tipo Dialogo.
    @Override
    public void onResume(){
        super.onResume();
         int widthLayout   = getResources().getDisplayMetrics().widthPixels;
         int heightLayout  = getResources().getDisplayMetrics().heightPixels;
         getDialog().getWindow().setLayout( widthLayout, heightLayout );
    }

    /* Metodo que configura los eventos de escucha de los
     * items del diseño.
     */
    private void listenerEventsSetup(){

//        // Declara el evento de escucha del item calculate_Button
//        calculateAmountButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });
    }
}
