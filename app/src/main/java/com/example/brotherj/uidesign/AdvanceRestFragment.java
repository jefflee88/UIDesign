package com.example.brotherj.uidesign;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.brotherj.uidesign.Data.SaveData;
import com.example.brotherj.uidesign.JsonClass.GetJson;


/**
 * A simple {@link Fragment} subclass.
 */
public class AdvanceRestFragment extends Fragment {
    EditText edtSearch;
    Spinner spinner,spinner2;
    Button button3;
    String keyword,type,dis;


    public AdvanceRestFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_advance_rest, container, false);
        edtSearch = (EditText) view.findViewById(R.id.edtSearch);
        spinner = (Spinner)view.findViewById(R.id.spinner);
        spinner2 = (Spinner)view.findViewById(R.id.spinner2);
        button3 = (Button)view.findViewById(R.id.button3);
        String[] items={ "American", "Chinese", "Freanch", "Itailan", "Japanese","Korean"};
        ArrayAdapter<String> LTRadapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, items);
        LTRadapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(LTRadapter);

        String[] items2={ "Central and Western", "Wan Chai", "Eastern", "Southern", "Yau Tsim Mong","Sham Shui Po","Kowloon City","Wong Tai Sin","Kwun Tong","Kwai Tsing","Tsuen Wan","Tuen Mun","Yuen Long","North","Tai Po","Sha Tin","Sai Kung","Islands"};
        ArrayAdapter<String> LTRadapter2 = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, items2);
        LTRadapter2.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner2.setAdapter(LTRadapter2);

        button3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                keyword = edtSearch.getText().toString();
                if (keyword == null && keyword.isEmpty()) {
                    keyword = "null";
                }

                type = spinner.getSelectedItem().toString();
                if (type == null && type.isEmpty()) {
                    type = "null";
                }

                dis = spinner2.getSelectedItem().toString();
                if (dis == null && dis.isEmpty()) {
                    dis = "null";
                }

                SaveData.cusSearchRestaurant = GetJson.cusSearchRestaurant(keyword, type, dis);
                SaveData.isFood = false;
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
