package com.example.brotherj.uidesign;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.brotherj.uidesign.Data.SaveData;
import com.example.brotherj.uidesign.JsonClass.GetJson;


/**
 * A simple {@link Fragment} subclass.
 */
public class ModifyFoodFragment extends Fragment {
    TextView txtModifyFoodId;
    EditText edtModifyFoodName;
    EditText edtModifyFoodPrice;


    public ModifyFoodFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_manage_food, container, false);
        txtModifyFoodId = (TextView) view.findViewById(R.id.txtModifyFoodId);
        edtModifyFoodName = (EditText) view.findViewById(R.id.edtModifyFoodName);
        edtModifyFoodPrice = (EditText) view.findViewById(R.id.edtModifyFoodPrice);
        //Button btnModifySave = (Button) view.findViewById(R.id.btnModifySave);
        //btnModifySave.setOnClickListener(new View.OnClickListener() {
            //public void onClick(View view) {

            //}
        //});
        return view;


    }
    @Override
    public void onStart(){
        super.onStart();
        txtModifyFoodId.setText(SaveData.searchFood.getId());
        edtModifyFoodName.setText(SaveData.searchFood.getName());
        edtModifyFoodPrice.setText(SaveData.searchFood.getPrice());

    }

    @Override
    public void onResume(){
        super.onResume();
    }
}


