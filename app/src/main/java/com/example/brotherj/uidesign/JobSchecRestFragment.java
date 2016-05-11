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
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.brotherj.uidesign.Data.SaveData;
import com.example.brotherj.uidesign.JsonClass.GetJson;


/**
 * A simple {@link Fragment} subclass.
 */
public class JobSchecRestFragment extends Fragment {

    ImageButton imgBtnCustDist;

    public JobSchecRestFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_job_schec_rest, container, false);

        imgBtnCustDist = (ImageButton)view.findViewById(R.id.imgBtnCustDist);

        ListView lstGetJob = (ListView)view.findViewById(R.id.lstRestDist);
        String[] testList = new String[] {"test 1", "test 2", "test 3",  "test 4",  "test 5"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, testList);
        lstGetJob.setAdapter(adapter);
        lstGetJob.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {

            }
        });

        imgBtnCustDist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Intent = new Intent(view.getContext(), MapsActivity.class);
                startActivityForResult(Intent, 0);
            }
        });

        return view;
    }

}
