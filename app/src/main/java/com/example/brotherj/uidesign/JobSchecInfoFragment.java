package com.example.brotherj.uidesign;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.brotherj.uidesign.Data.SaveData;
import com.example.brotherj.uidesign.JsonClass.GetJson;


/**
 * A simple {@link Fragment} subclass.
 */
public class JobSchecInfoFragment extends Fragment {
    ImageButton imgBtnCust;
    TextView txtGetJobOID,txtGetJobTotalQty,txtGetJobDate,txtGetJobDeliverTo,txtGetJobRestTotal;
    public JobSchecInfoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_job_schec_info, container, false);
        txtGetJobOID = (TextView)view.findViewById(R.id.txtGetJobOID);
        txtGetJobTotalQty = (TextView)view.findViewById(R.id.txtGetJobTotalQty);
        txtGetJobDate = (TextView)view.findViewById(R.id.txtGetJobDate);
        txtGetJobDeliverTo = (TextView)view.findViewById(R.id.txtGetJobDeliverTo);
        txtGetJobRestTotal = (TextView)view.findViewById(R.id.txtGetJobRestTotal);
        txtGetJobRestTotal.setText(GetJson.getResNum(SaveData.driveChooseMyOrder.getNumber()));
        txtGetJobOID.setText(Integer.toString(SaveData.driveChooseMyOrder.getNumber()));
        txtGetJobTotalQty.setText(GetJson.orderGetOrderlineTotal(SaveData.driveChooseMyOrder.getNumber()));
        txtGetJobDate.setText(SaveData.driveChooseMyOrder.getDate_time());
        txtGetJobDeliverTo.setText(GetJson.getCusLocation(SaveData.driveChooseMyOrder.getCustomerid()));

        imgBtnCust = (ImageButton)view.findViewById(R.id.imgBtnCust);
        imgBtnCust.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                SaveData.location = GetJson.getCusLocation(SaveData.driveChooseMyOrder.getCustomerid());
                Intent Intent = new Intent(view.getContext(), LocationActivity.class);
                startActivityForResult(Intent, 0);
            }
        });
        return view;
    }

}
