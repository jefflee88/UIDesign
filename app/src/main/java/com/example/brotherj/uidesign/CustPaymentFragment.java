package com.example.brotherj.uidesign;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TabHost;


/**
 * A simple {@link Fragment} subclass.
 */
public class CustPaymentFragment extends Fragment {

    private FragmentTabHost tabHost;
    private FragmentActivity fa = new FragmentActivity();

    public CustPaymentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cust_payment, container, false);

        tabHost = (FragmentTabHost)view.findViewById(R.id.tabHost);
        tabHost.setup(this.fa, fa.getSupportFragmentManager(), android.R.id.tabcontent);

        tabHost.addTab(tabHost.newTabSpec("tab1").setIndicator("Cash"),
                PayByCashFragment.class, null);

        tabHost.addTab(tabHost.newTabSpec("tab2").setIndicator("Credit Card"),
                PayByCreditFragment.class, null);

        return view;
    }

}
