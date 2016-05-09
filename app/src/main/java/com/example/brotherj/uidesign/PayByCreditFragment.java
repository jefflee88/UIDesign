package com.example.brotherj.uidesign;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.brotherj.uidesign.Data.SaveData;
import com.example.brotherj.uidesign.JsonClass.GetJson;


/**
 * A simple {@link Fragment} subclass.
 */
public class PayByCreditFragment extends Fragment {
    TextView txtPayTotal,txtPayQty,txtPayTel,txtPayAddress;
    Button btnPayCredit;
    EditText edtCardNum,edtSC;

    public PayByCreditFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pay_by_credit, container, false);
        txtPayTotal = (TextView) view.findViewById(R.id.txtPayTotal);
        txtPayQty = (TextView) view.findViewById(R.id.txtPayQty);
        txtPayTel = (TextView) view.findViewById(R.id.txtPayTel);
        txtPayAddress = (TextView) view.findViewById(R.id.txtPayAddress);
        txtPayTotal.setText(SaveData.AllPrice);
        txtPayQty.setText(SaveData.AllQty);
        txtPayTel.setText(SaveData.customer.getTelNum());
        txtPayAddress.setText(SaveData.customer.getAddress());
        edtCardNum = (EditText)view.findViewById(R.id.edtCardNum);
        edtSC = (EditText)view.findViewById(R.id.edtSC);
        btnPayCredit = (Button) view.findViewById(R.id.btnPayCredit);
        btnPayCredit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                try {
                if((16==(long)Math.log10(Long.parseLong(edtCardNum.getText().toString()))+1)&&((3==(int)Math.log10(Integer.parseInt(edtSC.getText().toString()))+1)||(4==(int)Math.log10(Integer.parseInt(edtSC.getText().toString()))+1))) {
                    Toast.makeText(getActivity(), "Ok", Toast.LENGTH_SHORT).show();
                    GetJson.setCustomerPayment("Credit Card", edtCardNum.getText().toString(), edtSC.getText().toString());
                }else{
                    Toast.makeText(getActivity(), "Credit Card error", Toast.LENGTH_SHORT).show();

                }
                }catch(Exception e){
                    Toast.makeText(getActivity(), "please insert the data", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }

}
