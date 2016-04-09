package com.example.brotherj.uidesign;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.brotherj.uidesign.Data.SaveData;
import com.example.brotherj.uidesign.JsonClass.GetJson;
import com.example.brotherj.uidesign.bean.Food;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddFoodFragment extends Fragment {
    EditText edtModifyFoodName,edtModifyFoodPrice;
    Spinner spinType;


    public AddFoodFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_food, container, false);
        GetJson.changeVersion();
        edtModifyFoodName = (EditText) view.findViewById(R.id.edtAddFoodName);
        edtModifyFoodPrice = (EditText) view.findViewById(R.id.edtAddFoodPrice);
        spinType=(Spinner) view.findViewById(R.id.spinType);
        String[] items={ "french fries", "chicken", "drink", "food", "dessert"};
        ArrayAdapter<String> LTRadapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, items);
        LTRadapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinType.setAdapter(LTRadapter);

        Button btnModifySave = (Button) view.findViewById(R.id.btnAddAddFood);
        btnModifySave.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Food modifyFood = new Food("null","null","null","null","null","null");
                modifyFood.setName(edtModifyFoodName.getText().toString());
                modifyFood.setPrice(edtModifyFoodPrice.getText().toString());
                modifyFood.setType(spinType.getSelectedItem().toString());
                GetJson.addFood(modifyFood);
            }
        });
        return view;
    }

}
