package com.settleitsoft.foreignexchange;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

public class FragmentToTransaction extends FragmentActivity{

    public static void commit ( Activity activity, Fragment layout ){

        FragmentTransaction fragTransaction = ((FragmentActivity)activity).getSupportFragmentManager().beginTransaction();
        fragTransaction.replace(R.id.fragm_main_container, layout);
        fragTransaction.commit();
    }
}// fin de la clase
