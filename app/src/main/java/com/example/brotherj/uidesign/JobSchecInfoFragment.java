package com.example.brotherj.uidesign;


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

    TextView txtGetJobOID,txtGetJobTotalQty,txtGetJobDate,txtGetJobDeliverTo;
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
        txtGetJobOID.setText(Integer.toString(SaveData.driveChooseMyOrder.getNumber()));
        txtGetJobTotalQty.setText(GetJson.orderGetOrderlineTotal(SaveData.driveChooseMyOrder.getNumber()));
        txtGetJobDate.setText(SaveData.driveChooseMyOrder.getDate_time());
        txtGetJobDeliverTo.setText(GetJson.getCusLocation(SaveData.driveChooseMyOrder.getCustomerid()));
        return view;
    }

}
