package com.example.brotherj.uidesign;


import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.brotherj.uidesign.Data.SaveData;
import com.example.brotherj.uidesign.JsonClass.GetJson;


/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment {
    RadioGroup rdoGroup;
    EditText edtSearch;
    Button btnSearch;
    RadioButton rdoFood,rdoRest;
    String type;

    public SearchFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        GetJson.changeVersion();
        rdoGroup = (RadioGroup) view.findViewById(R.id.rdoGroup);
        edtSearch = (EditText) view.findViewById(R.id.edtSearch);
        btnSearch = (Button) view.findViewById(R.id.btnSearch);
        rdoFood = (RadioButton) view.findViewById(R.id.rdoFood);
        rdoRest = (RadioButton) view.findViewById(R.id.rdoRest);
        rdoGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // TODO Auto-generated method stub
                if (checkedId == rdoFood.getId()) {
                    type = "food";
                }
                if (checkedId == rdoRest.getId()) {
                    type = "restaurant";
                }
            }
        });



        btnSearch.setOnClickListener(new View.OnClickListener() {

        @Override
            public void onClick(View v) {
                if(type.equals("food"))
                    SaveData.cusSearchFood = GetJson.searchFood(edtSearch.getText().toString(), type);
                if(type.equals("restaurant"))
                    SaveData.cusSearchFood = GetJson.searchFood(edtSearch.getText().toString(), type);

                SearchResultFragment fragment = new SearchResultFragment();
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
