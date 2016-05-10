package com.example.brotherj.uidesign;


import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.brotherj.uidesign.Data.SaveData;
import com.example.brotherj.uidesign.JsonClass.GetJson;
import com.example.brotherj.uidesign.bean.Food;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddFoodFragment extends Fragment {
    EditText edtModifyFoodName,edtModifyFoodPrice;
    Spinner spinType;
    ImageButton imgBtnTakePhoto,imgBtnGallery;
    ImageView imgAddFood;
    private  static final  int CAM_REQUEST = 1313;


    public AddFoodFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_food, container, false);
        GetJson.changeVersion();
        edtModifyFoodName = (EditText) view.findViewById(R.id.edtAddFoodName);
        edtModifyFoodPrice = (EditText) view.findViewById(R.id.edtAddFoodPrice);
        spinType=(Spinner) view.findViewById(R.id.spinType);
        String[] items={ "Bread", "Drink", "Meat", "Noodles", "Pizza","Rice","Seafood","Snacks","Soup","Sushi"};
        ArrayAdapter<String> LTRadapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, items);
        LTRadapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinType.setAdapter(LTRadapter);

        Button btnModifySave = (Button) view.findViewById(R.id.btnAddAddFood);
        btnModifySave.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Food modifyFood = new Food("null","null","null","null","null","null");
                modifyFood.setName(edtModifyFoodName.getText().toString());
                modifyFood.setPrice(edtModifyFoodPrice.getText().toString());
                modifyFood.setType(spinType.getSelectedItem().toString());
                GetJson.addFood(modifyFood);
            }
        });
        imgAddFood = (ImageView) view.findViewById(R.id.imgAddFood);
        imgBtnTakePhoto =(ImageButton) view.findViewById(R.id.imgBtnTakePhoto);
        imgBtnGallery = (ImageButton) view.findViewById(R.id.imgBtnGallery);
        imgBtnTakePhoto.setOnClickListener(new imgBtnTakePhotoClicker());
        imgBtnGallery.setOnClickListener(new imgBtnGalleryClicker());

        return view;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CAM_REQUEST) {
            if (data != null) {
                Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
                imgAddFood.setImageBitmap(thumbnail);
            }
        }

        if(requestCode == 1){
            imgAddFood.setImageURI(data.getData());
        }
    }

    class imgBtnTakePhotoClicker implements Button.OnClickListener{
        public void onClick(View v){
            Intent cameraintent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(cameraintent, CAM_REQUEST);
        }
    }

    class imgBtnGalleryClicker implements Button.OnClickListener{
        public void  onClick(View v){
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "Select Image"),1);
        }
    }

}
