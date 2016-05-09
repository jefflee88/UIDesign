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

import com.example.brotherj.uidesign.Data.SaveData;
import com.example.brotherj.uidesign.Data.SingleRow;
import com.example.brotherj.uidesign.JsonClass.GetJson;
import com.example.brotherj.uidesign.bean.Food;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ManageFoodFragment extends Fragment {

    String [] myfood;
    String [] imgpath;
    ArrayList<Food> food = new ArrayList<Food>();

    public ManageFoodFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_manage_food, container, false);

        GetJson.changeVersion();
        food = GetJson.getRestFoodDetail(SaveData.restaurant.getUserid());

        Food [] foodList = new Food[food.size()];
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

        ListView lstRestFood = (ListView)view.findViewById(R.id.lstRestFood);
        SingleRow listAdapter=new SingleRow(getActivity(), myfood, imgpath);

        lstRestFood.setAdapter(listAdapter);
        lstRestFood.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                SaveData.resSearchFood = (Food)food.get(arg2);
                ModifyFoodFragment fragment = new ModifyFoodFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        Button btnAddNewFood = (Button)view.findViewById(R.id.btnAddNewFood);
        btnAddNewFood.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                AddFoodFragment fragment = new AddFoodFragment();
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
