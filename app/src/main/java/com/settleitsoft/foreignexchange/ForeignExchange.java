package com.settleitsoft.foreignexchange;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

public class ForeignExchange extends AppCompatActivity {

    private Fragment welcomeFragment;
    private Toolbar toolbar;
    private TextView toolbarTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foreign_exchange);

        // Configura el Toolbar
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        // Configura titulo del toolbar
        toolbarTitle = findViewById(R.id.toolbar_Text);
        toolbarTitle.setText(R.string.welcome_title);

        // Obtiene el fragment de Welcome
        welcomeFragment = new FragmentWelcome();

        FragmentTransaction fragTransaction = getSupportFragmentManager().beginTransaction();
        fragTransaction.replace(R.id.fragm_main_container, welcomeFragment );
        fragTransaction.commit();
    }
}
