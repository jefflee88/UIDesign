package com.example.brotherj.uidesign;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;


/**
 * A simple {@link Fragment} subclass.
 */
public class DriverJobScheFragment extends Fragment {


    public DriverJobScheFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_driver_job_sche, container, false);
        CheckBox chkFoodQty = (CheckBox)view.findViewById(R.id.chkFoodQty);
        CheckBox chkOrderTime = (CheckBox)view.findViewById(R.id.chkOrderTime);

        ListView lstGetJob = (ListView)view.findViewById(R.id.lstJobSchec);
        String[] testList = new String[] {"test 1", "test 2", "test 3",  "test 4",  "test 5"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, testList);
        lstGetJob.setAdapter(adapter);
        lstGetJob.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                DriverJobSchecDetailFragment fragment = new DriverJobSchecDetailFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        return view;
    }

}
