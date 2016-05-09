package com.example.brotherj.uidesign;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.AdapterView;

import com.example.brotherj.uidesign.Data.DownloadImageTask;
import com.example.brotherj.uidesign.Data.SaveData;
import com.example.brotherj.uidesign.Data.SingleRow;
import com.example.brotherj.uidesign.JsonClass.GetJson;
import com.example.brotherj.uidesign.bean.Food;
import com.example.brotherj.uidesign.bean.Restaurant;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class SearchResultFragment extends Fragment {

    String [] food;
    String [] imgpath;

    public SearchResultFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search_result, container, false);

        GetJson.changeVersion();
        if(SaveData.isFood) {
            Food[] foodList = new Food[SaveData.cusSearchFood.size()];
            for (int i = 0; i < SaveData.cusSearchFood.size(); i++)
                foodList[i] = SaveData.cusSearchFood.get(i);

            food = new String[foodList.length];

            for (int i = 0; i < foodList.length; i++) {
                               food[i] = "Name : " + foodList[i].getName() + "\n"
                                           + "Type : " + foodList[i].getType() + "\n"
                                            + "Price : " + foodList[i].getPrice() + "\n";
            }

            imgpath = new String[foodList.length];

            for (int i = 0; i < foodList.length; i++) {
                imgpath[i] = "http://10.0.2.2/fyp_connect/image/"+foodList[i].getRestaurantid()+"/"+foodList[i].getImage();
            }

            ListView lstSearchResult = (ListView) view.findViewById(R.id.lstSearchResult);
            SingleRow listAdapter=new SingleRow(getActivity(), food, imgpath);
           // ArrayList<String> foodMenu = new ArrayList<String>();
           // ArrayAdapter<String> listAdapter =
                  //  new ArrayAdapter<String>(getActivity(), R.layout.single_row, R.id.txtDetail, foodMenu);
            lstSearchResult.setAdapter(listAdapter);
            lstSearchResult.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                    SaveData.cusChooseFood = (Food) SaveData.cusSearchFood.get(arg2);
                    FoodDetailFragment fragment = new FoodDetailFragment();
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.fragment_container, fragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                }
            });
        }
        if(SaveData.isFood==false){
            Restaurant[] foodList = new Restaurant[SaveData.cusSearchRestaurant.size()];
            for (int i = 0; i < SaveData.cusSearchRestaurant.size(); i++)
                foodList[i] = SaveData.cusSearchRestaurant.get(i);

            ListView lstSearchResult = (ListView) view.findViewById(R.id.lstSearchResult);
            ArrayList<String> foodMenu = new ArrayList<String>();
            ArrayAdapter<String> listAdapter =
                    new ArrayAdapter<String>(getActivity(), R.layout.single_row, R.id.txtDetail, foodMenu);

            for (int i = 0; i < foodList.length; i++) {
                listAdapter.add("Name : " + foodList[i].getName() + "\n"
                                + "Address : " + foodList[i].getAddress() + "\n"
                                + "Type : " + foodList[i].getType() + "\n"
                                + "TelNum : " + foodList[i].getTelNum() + "\n"
                );
            }
            lstSearchResult.setAdapter(listAdapter);
            lstSearchResult.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                    SaveData.cusChooseRestaurant = new Restaurant("null","null","null","null","null","null");
                    SaveData.cusChooseRestaurant = (Restaurant) SaveData.cusSearchRestaurant.get(arg2);
                    RestDetailFragment fragment = new RestDetailFragment();
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.fragment_container, fragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                }
            });
        }

        return view;

    }


}
