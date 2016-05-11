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
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.brotherj.uidesign.Data.SaveData;
import com.example.brotherj.uidesign.JsonClass.GetJson;
import com.example.brotherj.uidesign.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class GetJobDetailFragment extends Fragment {

    TextView txtGetJobOID,txtGetJobTotalQty,txtGetJobDate,txtGetJobDeliverTo,txtGetJobRestTotal;
    ImageButton imgBtnRest;
    ImageButton imgBtnCust;
    Button btnGetThisJob;

    public GetJobDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_get_job_detail, container, false);
        txtGetJobOID = (TextView)view.findViewById(R.id.txtGetJobOID);
        txtGetJobTotalQty = (TextView)view.findViewById(R.id.txtGetJobTotalQty);
        txtGetJobDate = (TextView)view.findViewById(R.id.txtGetJobDate);
        txtGetJobDeliverTo = (TextView)view.findViewById(R.id.txtGetJobDeliverTo);
        txtGetJobRestTotal = (TextView)view.findViewById(R.id.txtGetJobRestTotal);
        txtGetJobRestTotal.setText(GetJson.getResNum(SaveData.driveChooseMyOrder.getNumber()));
        txtGetJobOID.setText(Integer.toString(SaveData.driveChooseOrder.getNumber()));
        txtGetJobTotalQty.setText(GetJson.orderGetOrderlineTotal(SaveData.driveChooseOrder.getNumber()));
        txtGetJobDate.setText(SaveData.driveChooseOrder.getDate_time());
        txtGetJobDeliverTo.setText(GetJson.getCusLocation(SaveData.driveChooseOrder.getCustomerid()));
        btnGetThisJob = (Button)view.findViewById(R.id.btnGetThisJob);
        imgBtnCust = (ImageButton)view.findViewById(R.id.imgBtnCust);
        imgBtnCust.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                SaveData.location = GetJson.getCusLocation(SaveData.driveChooseOrder.getCustomerid());
                Intent Intent = new Intent(view.getContext(), LocationActivity.class);
                startActivityForResult(Intent, 0);
            }
        });
        btnGetThisJob.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                GetJson.handle();
                Toast.makeText(getActivity(), "You are handle this work!!", Toast.LENGTH_SHORT).show();
            }
        });



        return view;
    }

}
