package com.settleitsoft.foreignexchange;

import android.app.Activity;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;
import java.util.ArrayList;

public class FragmentAmountEntry extends Fragment {

    private View amountEntryLayout;
    private ArrayList<String> data;
    private ArrayList<Integer> selectedAmountsArray;
    private Spinner countriesSpinner;
    private CountriesToAdapter countriesAdapter;
    private ListView amountsListView;
    private AmountEntryToAdapter amountsEntryAdapter;
    private FloatingActionButton amountAddButton, amountRemoveButton;
    private EditText amountEdit, totalAmountEdit;
    private Integer entryAmount, selectedAmountList;
    private Toast messageToast;
    private Button calculateButton;
    private String selectedCountry, defaultValueSpinner;

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

        // Obtiene items del diseño
        amountEdit            = amountEntryLayout.findViewById(R.id.amount_Edit);
        countriesSpinner      = amountEntryLayout.findViewById(R.id.countries_spinner);
        amountsListView       = amountEntryLayout.findViewById(R.id.amounts_List);
        amountAddButton       = amountEntryLayout.findViewById(R.id.amount_add_Button);
        amountRemoveButton    = amountEntryLayout.findViewById(R.id.amount_remove_Button);
        totalAmountEdit       = amountEntryLayout.findViewById(R.id.total_amount_Edit);
        calculateButton       = amountEntryLayout.findViewById(R.id.calculate_Button);
        defaultValueSpinner   = getResources().getString(R.string.country_select_prompt);

        // Instancia los objetos necesarios para la correcta interaccion con el usuario
        countriesAdapter      = new CountriesToAdapter(getActivity());
        amountsEntryAdapter   = new AmountEntryToAdapter(getActivity());
        messageToast          = Toast.makeText(getActivity(),"", Toast.LENGTH_SHORT );

        // Configura Pais
        countriesSpinner.setAdapter(countriesAdapter.getAdapter());

        // Configura los eventos de escucha
        listenerEventsSetup();

        return amountEntryLayout;
    }

    /* Metodo que configura los eventos de escucha de los
     * items del diseño.
     */
    private void listenerEventsSetup(){

        // Declara el evento de escucha del item countries_spinner
        countriesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedCountry = adapterView.getItemAtPosition(i).toString();
            }
        });

        // Declara el evento de escucha del item amounts_List
        amountsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                selectedAmountList = Integer.parseInt(adapterView.getItemAtPosition(i).toString());
            }
        });

        // Declara el evento de escucha del item amount_add_Button
        amountAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String amountEmpty = amountEdit.getText().toString();

                if( amountEmpty.equals("") ){
                    // Mensaje de aviso al usuario
                    setupMessageToast(R.string.amount_empty_Text);
                }else{
                    amountEdit.setText("");
                    entryAmount = Integer.parseInt(amountEmpty);
                    amountsListView.setAdapter(amountsEntryAdapter.getAdapter(entryAmount,true));

                    // Calcula o recalcula el monto total
                    calculateTotalAmount();
                }
            }
        });

        // Declara el evento de escucha del item amount_remove_Button
        amountRemoveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if( selectedAmountList == null ){
                    // Mensaje de aviso al usuario
                    setupMessageToast(R.string.amount_no_selected_list_Text);
                }else{
                    amountsListView.setAdapter(amountsEntryAdapter.getAdapter(selectedAmountList,false));
                    selectedAmountList = null;

                    // Calcula o recalcula el monto total
                    calculateTotalAmount();
                }
            }
        });

        // Declara el evento de escucha del item Calculate_Button
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean  calculateForeign  = true;
                selectedAmountsArray = amountsEntryAdapter.getAmountsArray();

                if( selectedCountry.equals(defaultValueSpinner) ){
                    calculateForeign = false;
                }else
                if( selectedAmountsArray.isEmpty() ){
                    calculateForeign = false;
                }

                if( calculateForeign ){
                    FragDialogAmountCalculated fragDialogAmountCalculated = new FragDialogAmountCalculated();
                    fragDialogAmountCalculated.show(getFragmentManager(),"FragDialogAmountCalculated");
                }else{
                    // Mensaje de aviso al usuario
                    setupMessageToast(R.string.data_required_Text);
                }
            }
        });

    }

    /* Metodo que se encarga de calcular el monto
     * total para la conversion.
     */
    private void calculateTotalAmount(){

        selectedAmountsArray = amountsEntryAdapter.getAmountsArray();
        Integer totalAmount  = 0;

        for( Integer amount: selectedAmountsArray ){
            totalAmount += amount;
        }

        totalAmountEdit.setText(totalAmount.toString());
    }

    /* Configura los mensajes de aviso o alerta
     * para el usuario */
    private void setupMessageToast( int idString ){

        if( messageToast.getView().isShown() ){
            messageToast.cancel();
        }
        messageToast.setText(idString);
        messageToast.show();
    }

}// Fin de la clase
