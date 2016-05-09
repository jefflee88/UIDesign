package com.example.brotherj.uidesign;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
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
public class PayByCashFragment extends Fragment {

    TextView txtPayTotal,txtPayQty,txtPayTel,txtPayAddress;
    Button btnPayCash;
    EditText edtCash;
    public PayByCashFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pay_by_cash, container, false);
        txtPayTotal = (TextView) view.findViewById(R.id.txtPayTotal);
        txtPayQty = (TextView) view.findViewById(R.id.txtPayQty);
        txtPayTel = (TextView) view.findViewById(R.id.txtPayTel);
        txtPayAddress = (TextView) view.findViewById(R.id.txtPayAddress);
        txtPayTotal.setText(SaveData.AllPrice);
        txtPayQty.setText(SaveData.AllQty);
        txtPayTel.setText(SaveData.customer.getTelNum());
        txtPayAddress.setText(SaveData.customer.getAddress());
        edtCash = (EditText)view.findViewById(R.id.edtCash);
        btnPayCash = (Button) view.findViewById(R.id.btnPayCash);
        btnPayCash.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                try {
                    if (Integer.parseInt(SaveData.AllPrice) < Integer.parseInt(edtCash.getText().toString())) {
                        GetJson.creatOrder(SaveData.cusChooseFoods, Integer.parseInt(SaveData.AllPrice));
                        Toast.makeText(getActivity(), "Order confirm", Toast.LENGTH_SHORT).show();
                        SaveData.cusChooseFoods.removeAll(SaveData.cusChooseFoods);
                        GetJson.setCustomerPayment("Cash", null, null);
                        SaveData.AllPrice = null;
                        SaveData.AllQty = null;
                        BlankFragment fragment = new BlankFragment();
                        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.fragment_container, fragment);
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();

                    } else {
                        Toast.makeText(getActivity(), "Not enough money", Toast.LENGTH_SHORT).show();

                    }
                }catch(Exception e){
                    Toast.makeText(getActivity(), "please insert the data", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }

}
