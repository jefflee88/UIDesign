package com.example.brotherj.uidesign;


import android.content.Intent;
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
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.brotherj.uidesign.Data.SaveData;
import com.example.brotherj.uidesign.Data.SingleRow;
import com.example.brotherj.uidesign.JsonClass.GetJson;
import com.example.brotherj.uidesign.bean.Food;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class RestDetailFragment extends Fragment {
    ImageButton imgBtnRestAddress;
    TextView txtRestType,txtRestName,txtRestTel,txtRestAddress;
    ArrayList<Food> food = new ArrayList<Food>();
    String [] myfood;
    String [] imgpath;


    public RestDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rest_detail, container, false);
        imgBtnRestAddress = (ImageButton)view.findViewById(R.id.imgBtnRestAddress);
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
        myfood = new String[foodList.length];

        for (int i = 0; i < foodList.length; i++) {
            myfood[i] = "Name : " + foodList[i].getName() + "\n"
                    + "Type : " + foodList[i].getType() + "\n"
                    + "Price : " + foodList[i].getPrice() + "\n";
        }

        imgpath = new String[foodList.length];

        for (int i = 0; i < foodList.length; i++) {
            imgpath[i] = "http://10.0.2.2/fyp_connect/image/"+foodList[i].getRestaurantid()+"/"+foodList[i].getImage();
        }

        ListView lstRestFood = (ListView)view.findViewById(R.id.lstRestDetail);
        SingleRow listAdapter=new SingleRow(getActivity(), myfood, imgpath);

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

        imgBtnRestAddress.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                SaveData.location = SaveData.cusChooseRestaurant.getAddress();
                Intent Intent = new Intent(view.getContext(), LocationActivity.class);
                startActivityForResult(Intent, 0);
            }
        });
        return view;
    }

}
