package com.example.brotherj.uidesign;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TabHost;


/**
 * A simple {@link Fragment} subclass.
 */
public class CustPaymentFragment extends Fragment {

    public CustPaymentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cust_payment, container, false);

        TabHost tabHost = (TabHost)view.findViewById(R.id.tabHost);
        tabHost.setup();

        TabHost.TabSpec tab1 = tabHost.newTabSpec("Cash");
        tab1.setIndicator("Cash");
        tab1.setContent(R.id.tabCash);
        tabHost.addTab(tab1);

        TabHost.TabSpec tab2 = tabHost.newTabSpec("Credit Card");
        tab1.setIndicator("Credit Card");
        tab1.setContent(R.id.tabCredit);
        tabHost.addTab(tab2);

        return view;
    }

}
