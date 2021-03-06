package com.example.brotherj.uidesign;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.brotherj.uidesign.Data.DownloadImageTask;
import com.example.brotherj.uidesign.Data.SaveData;
import com.example.brotherj.uidesign.JsonClass.GetJson;
import com.example.brotherj.uidesign.bean.SelectFood;


/**
 * A simple {@link Fragment} subclass.
 */
public class FoodDetailFragment extends Fragment {
    TextView txtFoodDetailName,txtFoodDetailType,txtFoodDetailPrice,txtFoodDetailRestaurant;
    EditText edtQty;
    ImageView imgFoodDetail;
    int qty =1 ;

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
        edtQty = (EditText) view.findViewById(R.id.edtQty);
        imgFoodDetail = (ImageView)view.findViewById(R.id.imgFoodDetail);
        txtFoodDetailName.setText(SaveData.cusChooseFood.getName());
        txtFoodDetailType.setText(SaveData.cusChooseFood.getType());
        txtFoodDetailPrice.setText(SaveData.cusChooseFood.getPrice());
        txtFoodDetailRestaurant.setText(SaveData.cusChooseFood.getRestaurantid());
        new DownloadImageTask(imgFoodDetail).execute("http://10.0.2.2/fyp_connect/image/"+SaveData.cusChooseFood.getRestaurantid()+"/"+SaveData.cusChooseFood.getImage());

        Button btnPlus = (Button) view.findViewById(R.id.btnPlus);
        btnPlus.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                qty = Integer.parseInt(edtQty.getText().toString());
                if (qty <= 0){

                }else if ( qty>=1){
                    qty++;
                    edtQty.setText(Integer.toString(qty));
                }
            }
        });

        Button btnMinus = (Button) view.findViewById(R.id.btnMinus);
        btnMinus.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                qty = Integer.parseInt(edtQty.getText().toString());
                if (qty <= 0){

                }else if ( qty>1){
                    qty--;
                    edtQty.setText(Integer.toString(qty));
                }
            }
        });

        Button btnAddToCart = (Button) view.findViewById(R.id.btnAddToCart);
        btnAddToCart.setOnClickListener(new View.OnClickListener() {
        public void onClick(View view) {
            if(SaveData.cusChooseFoods.isEmpty()) {
                SaveData.cusChooseFoods.add(new SelectFood(SaveData.cusChooseFood, qty));
                Toast.makeText(getActivity(), "Ok", Toast.LENGTH_SHORT).show();
                SearchResultFragment fragment = new SearchResultFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }else if(SaveData.cusChooseFoods.size()<10) {
                SaveData.cusChooseFoods.add(new SelectFood(SaveData.cusChooseFood, qty));
                Toast.makeText(getActivity(), "Ok", Toast.LENGTH_SHORT).show();
                SearchResultFragment fragment = new SearchResultFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            }else{
                Toast.makeText(getActivity(), "one order can take only 10 foods", Toast.LENGTH_SHORT).show();
            }

            //GetJson.creatOrder(qty,SaveData.cusChooseFood);
            }
        });
        return view;
        // Inflate the layout for this fragment
    }

}
