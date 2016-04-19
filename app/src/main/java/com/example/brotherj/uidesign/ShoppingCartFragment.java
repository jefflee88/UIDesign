package com.example.brotherj.uidesign;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.brotherj.uidesign.Data.SaveData;

import org.w3c.dom.Text;


/**
 * A simple {@link Fragment} subclass.
 */
public class ShoppingCartFragment extends Fragment {
    TextView[] txtName = new TextView[10];
    TextView[] txtQty = new TextView[10];
    TextView[] txtPrice = new TextView[10];
    Button[] btnDelete = new Button[10];
    int i;


    public ShoppingCartFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_shopping_cart, container, false);
        txtName[0] = (TextView) view.findViewById(R.id.txtName0);
        txtName[1] = (TextView) view.findViewById(R.id.txtName1);
        txtName[2] = (TextView) view.findViewById(R.id.txtName2);
        txtName[3] = (TextView) view.findViewById(R.id.txtName3);
        txtName[4] = (TextView) view.findViewById(R.id.txtName4);
        txtName[5] = (TextView) view.findViewById(R.id.txtName5);
        txtName[6] = (TextView) view.findViewById(R.id.txtName6);
        txtName[7] = (TextView) view.findViewById(R.id.txtName7);
        txtName[8] = (TextView) view.findViewById(R.id.txtName8);
        txtName[9] = (TextView) view.findViewById(R.id.txtName9);
        txtQty[0] = (TextView) view.findViewById(R.id.txtQty0);
        txtQty[1] = (TextView) view.findViewById(R.id.txtQty1);
        txtQty[2] = (TextView) view.findViewById(R.id.txtQty2);
        txtQty[3] = (TextView) view.findViewById(R.id.txtQty3);
        txtQty[4] = (TextView) view.findViewById(R.id.txtQty4);
        txtQty[5] = (TextView) view.findViewById(R.id.txtQty5);
        txtQty[6] = (TextView) view.findViewById(R.id.txtQty6);
        txtQty[7] = (TextView) view.findViewById(R.id.txtQty7);
        txtQty[8] = (TextView) view.findViewById(R.id.txtQty8);
        txtQty[9] = (TextView) view.findViewById(R.id.txtQty9);
        txtPrice[0] = (TextView) view.findViewById(R.id.txtPrice0);
        txtPrice[1] = (TextView) view.findViewById(R.id.txtPrice1);
        txtPrice[2] = (TextView) view.findViewById(R.id.txtPrice2);
        txtPrice[3] = (TextView) view.findViewById(R.id.txtPrice3);
        txtPrice[4] = (TextView) view.findViewById(R.id.txtPrice4);
        txtPrice[5] = (TextView) view.findViewById(R.id.txtPrice5);
        txtPrice[6] = (TextView) view.findViewById(R.id.txtPrice6);
        txtPrice[7] = (TextView) view.findViewById(R.id.txtPrice7);
        txtPrice[8] = (TextView) view.findViewById(R.id.txtPrice8);
        txtPrice[9] = (TextView) view.findViewById(R.id.txtPrice9);
        btnDelete[0] = (Button) view.findViewById(R.id.btnDelete0);
        btnDelete[1] = (Button) view.findViewById(R.id.btnDelete1);
        btnDelete[2] = (Button) view.findViewById(R.id.btnDelete2);
        btnDelete[3] = (Button) view.findViewById(R.id.btnDelete3);
        btnDelete[4] = (Button) view.findViewById(R.id.btnDelete4);
        btnDelete[5] = (Button) view.findViewById(R.id.btnDelete5);
        btnDelete[6] = (Button) view.findViewById(R.id.btnDelete6);
        btnDelete[7] = (Button) view.findViewById(R.id.btnDelete7);
        btnDelete[8] = (Button) view.findViewById(R.id.btnDelete8);
        btnDelete[9] = (Button) view.findViewById(R.id.btnDelete9);
        btnDelete[0].setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                SaveData.cusChooseFoods.remove(0);
                reset();
                update();
            }
        });
        btnDelete[1].setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                SaveData.cusChooseFoods.remove(1);
                reset();
                update();
            }
        });
        btnDelete[2].setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                SaveData.cusChooseFoods.remove(2);
                reset();
                update();
            }
        });
        btnDelete[3].setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                SaveData.cusChooseFoods.remove(3);
                reset();
                update();
            }
        });
        btnDelete[4].setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                SaveData.cusChooseFoods.remove(4);
                reset();
                update();
            }
        });
        btnDelete[5].setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                SaveData.cusChooseFoods.remove(5);
                reset();
                update();
            }
        });
        btnDelete[6].setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                SaveData.cusChooseFoods.remove(6);
                reset();
                update();
            }
        });
        btnDelete[7].setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                SaveData.cusChooseFoods.remove(7);
                reset();
                update();
            }
        });
        btnDelete[8].setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                SaveData.cusChooseFoods.remove(8);
                reset();
                update();
            }
        });
        btnDelete[9].setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                SaveData.cusChooseFoods.remove(9);
                reset();
                update();
            }
        });
        update();


        return view;
    }
    public void update(){
        for(i = 0;i< SaveData.cusChooseFoods.size();i++) {
            txtName[i].setText(SaveData.cusChooseFoods.get(i).getFood().getName());
            txtName[i].setVisibility(View.VISIBLE);
            txtPrice[i].setText(SaveData.cusChooseFoods.get(i).getFood().getPrice());
            txtPrice[i].setVisibility(View.VISIBLE);
            txtQty[i].setText(String.valueOf(SaveData.cusChooseFoods.get(i).getQty()));
            txtQty[i].setVisibility(View.VISIBLE);
            btnDelete[i].setVisibility(View.VISIBLE);
        }
    }
    public void reset(){
        for(i = 0;i<10;i++) {
            txtName[i].setText("");
            txtName[i].setVisibility(View.GONE);
            txtPrice[i].setText("");
            txtPrice[i].setVisibility(View.GONE);
            txtQty[i].setText("");
            txtQty[i].setVisibility(View.GONE);
            btnDelete[i].setVisibility(View.GONE);
        }
    }

}
