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
import android.widget.CheckBox;
import android.widget.ListView;

import com.example.brotherj.uidesign.Data.SaveData;
import com.example.brotherj.uidesign.JsonClass.GetJson;
import com.example.brotherj.uidesign.bean.Food;
import com.example.brotherj.uidesign.bean.Order;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class RestaurantOrderFragment extends Fragment {
    ArrayList<Order> food = new ArrayList<Order>();

    public RestaurantOrderFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_restaurant_order, container, false);

        Button btnRestRefresh = (Button)view.findViewById(R.id.btnRestRefresh);
        CheckBox chkFoodQty = (CheckBox)view.findViewById(R.id.chkFoodQty);
        CheckBox chkOrderTime = (CheckBox)view.findViewById(R.id.chkOrderTime);

        food = GetJson.restaurantGetOrder(GetJson.restaurantGetOrderline());

        Order[] foodList = new Order[food.size()];
        for(int i = 0 ;i<food.size();i++)
            foodList[i] = food.get(i);

        ListView lstRestFood = (ListView)view.findViewById(R.id.lstRestOrder);
        ArrayList<String> foodMenu = new ArrayList<String>();
        ArrayAdapter<String> listAdapter =
                new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, foodMenu);
        for (int i = 0; i < foodList.length; i++) {
            listAdapter.add("Order number : " + foodList[i].getNumber() + "\n"
                    + "Order Time : " + foodList[i].getDate_time() + "\n"
                    + "Order Total : " + foodList[i].getOrder_total() + "\n"
                    + "Customer ID : " + foodList[i].getCustomerid() + "\n"
                    + "Driver ID : " + foodList[i].getDriverid() + "\n"
            );
        }

        chkOrderTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                food = GetJson.restaurantOrderTimeGetOrder(GetJson.restaurantGetOrderline());

                Order[] foodList = new Order[food.size()];
                for(int i = 0 ;i<food.size();i++)
                    foodList[i] = food.get(i);


                ArrayList<String> foodMenu = new ArrayList<String>();
                ArrayAdapter<String> listAdapter =
                        new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, foodMenu);
                for (int i = 0; i < foodList.length; i++) {
                    listAdapter.add("Order number : " + foodList[i].getNumber() + "\n"
                                    + "Order Time : " + foodList[i].getDate_time() + "\n"
                                    + "Order Total : " + foodList[i].getOrder_total() + "\n"
                                    + "Customer ID : " + foodList[i].getCustomerid() + "\n"
                                    + "Driver ID : " + foodList[i].getDriverid() + "\n"
                    );
                }
                ListView lstRestFood = (ListView)view.findViewById(R.id.lstRestOrder);
                lstRestFood.setAdapter(listAdapter);
            }
        });

        chkFoodQty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                food = GetJson.restaurantOrderTotalGetOrder(GetJson.restaurantGetOrderline());

                Order[] foodList = new Order[food.size()];
                for(int i = 0 ;i<food.size();i++)
                    foodList[i] = food.get(i);


                ArrayList<String> foodMenu = new ArrayList<String>();
                ArrayAdapter<String> listAdapter =
                        new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, foodMenu);
                for (int i = 0; i < foodList.length; i++) {
                    listAdapter.add("Order number : " + foodList[i].getNumber() + "\n"
                                    + "Order Time : " + foodList[i].getDate_time() + "\n"
                                    + "Order Total : " + foodList[i].getOrder_total() + "\n"
                                    + "Customer ID : " + foodList[i].getCustomerid() + "\n"
                                    + "Driver ID : " + foodList[i].getDriverid() + "\n"
                    );
                }
                ListView lstRestFood = (ListView)view.findViewById(R.id.lstRestOrder);
                lstRestFood.setAdapter(listAdapter);
            }
        });

        btnRestRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                food = GetJson.restaurantGetOrder(GetJson.restaurantGetOrderline());

                Order[] foodList = new Order[food.size()];
                for(int i = 0 ;i<food.size();i++)
                    foodList[i] = food.get(i);


                ArrayList<String> foodMenu = new ArrayList<String>();
                ArrayAdapter<String> listAdapter =
                        new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, foodMenu);
                for (int i = 0; i < foodList.length; i++) {
                    listAdapter.add("Order number : " + foodList[i].getNumber() + "\n"
                                    + "Order Time : " + foodList[i].getDate_time() + "\n"
                                    + "Order Total : " + foodList[i].getOrder_total() + "\n"
                                    + "Customer ID : " + foodList[i].getCustomerid() + "\n"
                                    + "Driver ID : " + foodList[i].getDriverid() + "\n"
                    );
                }
                ListView lstRestFood = (ListView)view.findViewById(R.id.lstRestOrder);
                lstRestFood.setAdapter(listAdapter);
            }
        });

        lstRestFood.setAdapter(listAdapter);
        lstRestFood.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                SaveData.resOrderline = GetJson.restaurantGetOrderlineForOrder(food.get(arg2));
                SaveData.resOrder = food.get(arg2);
                RestOrderDetailFragment fragment = new RestOrderDetailFragment();
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
