package com.example.brotherj.uidesign;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.example.brotherj.uidesign.Data.SaveData;
import com.example.brotherj.uidesign.JsonClass.GetJson;


/**
 * A simple {@link Fragment} subclass.
 */
public class RestOrderDetailFragment extends Fragment {
    TextView[] txtName = new TextView[10];
    TextView[] txtQty = new TextView[10];
    TextView[] txtType = new TextView[10];
    TextView txtRestTotalQty,txtRestOrderDeliver;
    CheckBox chkComplete;
    Button btnSendToDriver;
    int i;


    public RestOrderDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rest_order_detail, container, false);
        txtRestTotalQty = (TextView) view.findViewById(R.id.txtRestTotalQty);
        txtRestOrderDeliver = (TextView) view.findViewById(R.id.txtRestOrderDeliver);
        txtName[0] = (TextView) view.findViewById(R.id.txtName);
        txtName[1] = (TextView) view.findViewById(R.id.txtName1);
        txtName[2] = (TextView) view.findViewById(R.id.txtName2);
        txtName[3] = (TextView) view.findViewById(R.id.txtName3);
        txtName[4] = (TextView) view.findViewById(R.id.txtName4);
        txtName[5] = (TextView) view.findViewById(R.id.txtName5);
        txtName[6] = (TextView) view.findViewById(R.id.txtName6);
        txtName[7] = (TextView) view.findViewById(R.id.txtName7);
        txtName[8] = (TextView) view.findViewById(R.id.txtName8);
        txtName[9] = (TextView) view.findViewById(R.id.txtName9);
        txtQty[0] = (TextView) view.findViewById(R.id.txtQty);
        txtQty[1] = (TextView) view.findViewById(R.id.txtQty1);
        txtQty[2] = (TextView) view.findViewById(R.id.txtQty2);
        txtQty[3] = (TextView) view.findViewById(R.id.txtQty3);
        txtQty[4] = (TextView) view.findViewById(R.id.txtQty4);
        txtQty[5] = (TextView) view.findViewById(R.id.txtQty5);
        txtQty[6] = (TextView) view.findViewById(R.id.txtQty6);
        txtQty[7] = (TextView) view.findViewById(R.id.txtQty7);
        txtQty[8] = (TextView) view.findViewById(R.id.txtQty8);
        txtQty[9] = (TextView) view.findViewById(R.id.txtQty9);
        txtType[0] = (TextView) view.findViewById(R.id.txtType);
        txtType[1] = (TextView) view.findViewById(R.id.txtType1);
        txtType[2] = (TextView) view.findViewById(R.id.txtType2);
        txtType[3] = (TextView) view.findViewById(R.id.txtType3);
        txtType[4] = (TextView) view.findViewById(R.id.txtType4);
        txtType[5] = (TextView) view.findViewById(R.id.txtType5);
        txtType[6] = (TextView) view.findViewById(R.id.txtType6);
        txtType[7] = (TextView) view.findViewById(R.id.txtType7);
        txtType[8] = (TextView) view.findViewById(R.id.txtType8);
        txtType[9] = (TextView) view.findViewById(R.id.txtType9);
        txtRestTotalQty.setText(update());
        txtRestOrderDeliver.setText(GetJson.getCusLocation(SaveData.resOrder.getCustomerid()));
        chkComplete = (CheckBox)view.findViewById(R.id.chkComplete);
        btnSendToDriver = (Button)view.findViewById(R.id.btnSendToDriver);

        btnSendToDriver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (chkComplete.isChecked()) {
                    GetJson.setCompleted(SaveData.resOrder.getNumber());
                    Toast.makeText(getActivity(), "Food completed !", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getActivity(), "Please complete the food order !", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }
    public String update(){
        int qty = 0;
        for(i = 0;i< SaveData.resOrderline.size();i++) {
            qty += SaveData.resOrderline.get(i).getQuanitity();
            txtName[i].setText(GetJson.getFoodName(SaveData.resOrderline.get(i).getFoodId()));
            txtName[i].setVisibility(View.VISIBLE);
            txtType[i].setText(GetJson.getFoodType(SaveData.resOrderline.get(i).getFoodId()));
            txtType[i].setVisibility(View.VISIBLE);
            txtQty[i].setText(String.valueOf(SaveData.resOrderline.get(i).getQuanitity()));
            txtQty[i].setVisibility(View.VISIBLE);
        }
        return String.valueOf(qty);
    }

}
