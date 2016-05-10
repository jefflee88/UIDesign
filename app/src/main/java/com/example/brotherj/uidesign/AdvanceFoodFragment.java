package com.example.brotherj.uidesign;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.example.brotherj.uidesign.Data.SaveData;
import com.example.brotherj.uidesign.JsonClass.GetJson;
import com.example.brotherj.uidesign.bean.Food;


/**
 * A simple {@link Fragment} subclass.
 */
public class AdvanceFoodFragment extends Fragment {
    EditText edtSearch,edtPrice1,edtPrice2;
    Spinner spinner;
    Button btnSearch;
    String Keyword,type,min,max;

    public AdvanceFoodFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_advance_food, container, false);

        edtSearch = (EditText) view.findViewById(R.id.edtSearch);
        edtPrice1 = (EditText) view.findViewById(R.id.edtPrice1);
        edtPrice2 = (EditText) view.findViewById(R.id.edtPrice2);
        spinner = (Spinner)view.findViewById(R.id.spinner);
        btnSearch = (Button)view.findViewById(R.id.btnSearch);

        String[] items={ "Bread", "Drink", "Meat", "Noodles", "Pizza","Rice","Seafood","Snacks","Soup","Sushi"};
        ArrayAdapter<String> LTRadapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, items);
        LTRadapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(LTRadapter);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Keyword = edtSearch.getText().toString();
                if (Keyword == null && Keyword.isEmpty()) {
                    Keyword = "null";
                }
                type = spinner.getSelectedItem().toString();
                if (type == null && type.isEmpty()) {
                    type = "null";
                }
                min = edtPrice1.getText().toString();
                if (min == null && min.isEmpty()) {
                    min = "null";
                }
                max =edtPrice2.getText().toString();
                if (max == null && max.isEmpty()) {
                    max = "null";
                }
                SaveData.cusSearchFood = GetJson.cusSearchFood(Keyword,type,min,max);
                SaveData.isFood = true;
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
