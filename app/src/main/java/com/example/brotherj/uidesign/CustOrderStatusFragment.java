package com.example.brotherj.uidesign;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.brotherj.uidesign.JsonClass.GetJson;
import com.example.brotherj.uidesign.bean.Order;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class CustOrderStatusFragment extends Fragment {
    ArrayList<Order> order;

    public CustOrderStatusFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cust_order_status, container, false);

        ListView lstOrderStat = (ListView)view.findViewById(R.id.lstOrderStat);

        order= GetJson.cusGetOrder();
        String[] orderlist = new String[order.size()];
        for(int i =0;i<order.size();i++) {
            orderlist[i] = "Order number : " + order.get(i).getNumber() + "\n"
                    + "Order Time : " + order.get(i).getDate_time() + "\n"
                    + "Order Total : " + order.get(i).getOrder_total() + "\n"
                    + "Customer ID : " + order.get(i).getCustomerid() + "\n"
                    + "Driver ID : " + order.get(i).getDriverid() + "\n";
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, orderlist);
        lstOrderStat.setAdapter(adapter);
        return view;
    }

}
