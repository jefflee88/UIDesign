package com.example.brotherj.uidesign;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.brotherj.uidesign.Data.SaveData;


/**
 * A simple {@link Fragment} subclass.
 */
public class FoodDetailFragment extends Fragment {
    TextView txtFoodDetailName,txtFoodDetailType,txtFoodDetailPrice,txtFoodDetailRestaurant;

    public FoodDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_food_detail, container, false);
        txtFoodDetailName = (TextView) view.findViewById(R.id.txtFoodDetailName);
        txtFoodDetailType = (TextView) view.findViewById(R.id.txtFoodDetailType);
        txtFoodDetailPrice = (TextView) view.findViewById(R.id.txtFoodDetailPrice);
        txtFoodDetailRestaurant = (TextView) view.findViewById(R.id.txtFoodDetailRestaurant);
        txtFoodDetailName.setText(SaveData.cusChooseFood.getName());
        txtFoodDetailType.setText(SaveData.cusChooseFood.getType());
        txtFoodDetailPrice.setText(SaveData.cusChooseFood.getPrice());
        txtFoodDetailRestaurant.setText(SaveData.cusChooseFood.getRestaurantid());
        Button btnAddToCart = (Button) view.findViewById(R.id.btnAddToCart);
        btnAddToCart.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

            }
        });
        return view;
        // Inflate the layout for this fragment
    }

}
