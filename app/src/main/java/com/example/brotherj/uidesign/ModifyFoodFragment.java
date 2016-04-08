package com.example.brotherj.uidesign;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.brotherj.uidesign.Data.SaveData;
import com.example.brotherj.uidesign.JsonClass.GetJson;
import com.example.brotherj.uidesign.bean.Food;


/**
 * A simple {@link Fragment} subclass.
 */
public class ModifyFoodFragment extends Fragment {
    TextView txtModifyFoodId;
    EditText edtModifyFoodName,edtModifyFoodPrice;
    Spinner spinType;


    public ModifyFoodFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_modify_food, container, false);
        GetJson.changeVersion();
        txtModifyFoodId = (TextView) view.findViewById(R.id.txtModifyFoodId);
        edtModifyFoodName = (EditText) view.findViewById(R.id.edtModifyFoodName);
        edtModifyFoodPrice = (EditText) view.findViewById(R.id.edtModifyFoodPrice);
        spinType=(Spinner) view.findViewById(R.id.spinType);
        String [] types = new String[5];
        String[] items={ "french fries", "chicken", "drink", "food", "dessert"};
        for(int i = 0; i<4; i++)
        if (SaveData.resSearchFood.getType().equals(items[i])){}
        else{types[i]=items[i];}
        ArrayAdapter<String> aa=new ArrayAdapter<String>(this.getActivity(), R.layout.fragment_modify_food, types);
        aa.setDropDownViewResource(R.layout.fragment_modify_food);
        spinType.setAdapter(aa);


        txtModifyFoodId.setText(SaveData.resSearchFood.getId());
        edtModifyFoodName.setText(SaveData.resSearchFood.getName());
        edtModifyFoodPrice.setText(SaveData.resSearchFood.getPrice());
        Button btnModifySave = (Button) view.findViewById(R.id.btnModifySave);
        btnModifySave.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Food modifyFood;
                modifyFood = SaveData.resSearchFood;
                modifyFood.setName(edtModifyFoodName.getText().toString());
                modifyFood.setPrice(edtModifyFoodPrice.getText().toString());
                modifyFood.setType(spinType.getSelectedItem().toString());
                GetJson.modifyFood(modifyFood);
            }
        });
        return view;


    }

}


