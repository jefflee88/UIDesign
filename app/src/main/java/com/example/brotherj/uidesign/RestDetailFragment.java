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
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.brotherj.uidesign.Data.SaveData;
import com.example.brotherj.uidesign.JsonClass.GetJson;
import com.example.brotherj.uidesign.bean.Food;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class RestDetailFragment extends Fragment {
    TextView txtRestType,txtRestName,txtRestTel,txtRestAddress;
    ArrayList<Food> food = new ArrayList<Food>();


    public RestDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rest_detail, container, false);
        txtRestType = (TextView) view.findViewById(R.id.txtRestType);
        txtRestName = (TextView) view.findViewById(R.id.txtRestName);
        txtRestTel = (TextView) view.findViewById(R.id.txtRestTel);
        txtRestAddress = (TextView) view.findViewById(R.id.txtRestAddress);
        txtRestType.setText(SaveData.cusChooseRestaurant.getType());
        txtRestName.setText(SaveData.cusChooseRestaurant.getName());
        txtRestTel.setText(SaveData.cusChooseRestaurant.getTelNum());
        txtRestAddress.setText(SaveData.cusChooseRestaurant.getAddress());
        GetJson.changeVersion();
        food = GetJson.getRestFoodDetail(SaveData.cusChooseRestaurant.getUserid());

        Food[] foodList = new Food[food.size()];
        for(int i = 0 ;i<food.size();i++)
            foodList[i] = food.get(i);

        ListView lstRestFood = (ListView)view.findViewById(R.id.lstRestDetail);
        ArrayList<String> foodMenu = new ArrayList<String>();
        ArrayAdapter<String> listAdapter =
                new ArrayAdapter<String>(getActivity(), R.layout.single_row, R.id.txtDetail, foodMenu);
        for (int i = 0; i < foodList.length; i++) {
            listAdapter.add("Name : " + foodList[i].getName() + "\n"
                    + "Type : " + foodList[i].getType() + "\n"
                    + "Price : " + foodList[i].getPrice() + "\n");
        }
        lstRestFood.setAdapter(listAdapter);
        lstRestFood.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                SaveData.cusChooseFood = (Food) food.get(arg2);
                FoodDetailFragment fragment = new FoodDetailFragment();
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
