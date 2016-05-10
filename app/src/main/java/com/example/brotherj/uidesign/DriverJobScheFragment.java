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
import android.widget.CheckBox;
import android.widget.ListView;

import com.example.brotherj.uidesign.Data.SaveData;
import com.example.brotherj.uidesign.JsonClass.GetJson;
import com.example.brotherj.uidesign.bean.Order;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class DriverJobScheFragment extends Fragment {
    ArrayList<Order> order;


    public DriverJobScheFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_driver_job_sche, container, false);
        CheckBox chkFoodQty = (CheckBox)view.findViewById(R.id.chkFoodQty);
        CheckBox chkOrderTime = (CheckBox)view.findViewById(R.id.chkOrderTime);

        ListView lstGetJob = (ListView)view.findViewById(R.id.lstJobSchec);
        order= GetJson.driveGetMyOrder();
        String[] orderlist = new String[order.size()];
        for(int i =0;i<order.size();i++) {
            orderlist[i] = "Order number : " + order.get(i).getNumber() + "\n"
                    + "Order Time : " + order.get(i).getDate_time() + "\n"
                    + "Order Total : " + order.get(i).getOrder_total() + "\n"
                    + "Customer ID : " + order.get(i).getCustomerid() + "\n"
                    + "Driver ID : " + order.get(i).getDriverid() + "\n";
        }

        chkFoodQty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ListView lstGetJob = (ListView)view.findViewById(R.id.lstJobSchec);
                order= GetJson.driveGetTotalMyOrder();
                String[] orderlist = new String[order.size()];
                for(int i =0;i<order.size();i++) {
                    orderlist[i] = "Order number : " + order.get(i).getNumber() + "\n"
                            + "Order Time : " + order.get(i).getDate_time() + "\n"
                            + "Order Total : " + order.get(i).getOrder_total() + "\n"
                            + "Customer ID : " + order.get(i).getCustomerid() + "\n"
                            + "Driver ID : " + order.get(i).getDriverid() + "\n";
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, orderlist);
                lstGetJob.setAdapter(adapter);
            }
        });

        chkOrderTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ListView lstGetJob = (ListView)view.findViewById(R.id.lstJobSchec);
                order= GetJson.driveGetTimeMyOrder();
                String[] orderlist = new String[order.size()];
                for(int i =0;i<order.size();i++) {
                    orderlist[i] = "Order number : " + order.get(i).getNumber() + "\n"
                            + "Order Time : " + order.get(i).getDate_time() + "\n"
                            + "Order Total : " + order.get(i).getOrder_total() + "\n"
                            + "Customer ID : " + order.get(i).getCustomerid() + "\n"
                            + "Driver ID : " + order.get(i).getDriverid() + "\n";
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, orderlist);
                lstGetJob.setAdapter(adapter);
            }
        });

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, orderlist);
        lstGetJob.setAdapter(adapter);
        lstGetJob.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                SaveData.driveChooseMyOrder = order.get(arg2);
                DriverJobSchecDetailFragment fragment = new DriverJobSchecDetailFragment();
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
