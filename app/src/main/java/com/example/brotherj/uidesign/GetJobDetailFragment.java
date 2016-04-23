package com.example.brotherj.uidesign;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;

import com.example.brotherj.uidesign.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class GetJobDetailFragment extends Fragment {

    ImageButton imgBtnRest;
    ImageButton imgBtnCust;

    public GetJobDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_get_job_detail, container, false);
        imgBtnRest = (ImageButton)view.findViewById(R.id.imgBtnRest);
        imgBtnCust = (ImageButton)view.findViewById(R.id.imgBtnCust);

        imgBtnRest.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent Intent = new Intent(view.getContext(), MapsActivity.class);
                startActivityForResult(Intent, 0);
            }
        });


        return view;
    }

}
