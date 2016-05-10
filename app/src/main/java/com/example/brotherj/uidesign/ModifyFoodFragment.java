package com.example.brotherj.uidesign;


import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.brotherj.uidesign.Data.DownloadImageTask;
import com.example.brotherj.uidesign.Data.SaveData;
import com.example.brotherj.uidesign.JsonClass.GetJson;
import com.example.brotherj.uidesign.bean.Food;


/**
 * A simple {@link Fragment} subclass.
 */
public class ModifyFoodFragment extends Fragment {
    TextView txtModifyFoodId;
    EditText edtModifyFoodName,edtModifyFoodPrice;
    Spinner spinType;
    ImageButton imgBtnTakePhoto,imgBtnGallery;
    ImageView imgModifyFood;
    private  static final  int CAM_REQUEST = 1313;


    public ModifyFoodFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_modify_food, container, false);
        GetJson.changeVersion();
        imgModifyFood = (ImageView)view.findViewById(R.id.imgModifyFood);
        txtModifyFoodId = (TextView) view.findViewById(R.id.txtModifyFoodId);
        edtModifyFoodName = (EditText) view.findViewById(R.id.edtModifyFoodName);
        edtModifyFoodPrice = (EditText) view.findViewById(R.id.edtModifyFoodPrice);
        spinType=(Spinner) view.findViewById(R.id.spinType);
        String [] types = new String[5];
        for(int i = 0; i<5 ; i++)
        types[i] = SaveData.resSearchFood.getType();
        String[] items={ "Bread", "Drink", "Meat", "Noodles", "Pizza","Rice","Seafood","Snacks","Soup","Sushi"};
        int y =0;
        for(int i = 0; i<5; i++) {
            if (SaveData.resSearchFood.getType().equals(items[i])==false) {
                types[y+1] =items[i];
                y++;
            } else {
            }
        }
        ArrayAdapter<String> LTRadapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, types);
        LTRadapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinType.setAdapter(LTRadapter);


        txtModifyFoodId.setText(SaveData.resSearchFood.getId());
        edtModifyFoodName.setText(SaveData.resSearchFood.getName());
        edtModifyFoodPrice.setText(SaveData.resSearchFood.getPrice());
        new DownloadImageTask(imgModifyFood).execute("http://10.0.2.2/fyp_connect/image/" + SaveData.resSearchFood.getRestaurantid() + "/" + SaveData.resSearchFood.getImage());
        Button btnModifySave = (Button) view.findViewById(R.id.btnModifySave);
        btnModifySave.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Food modifyFood;
                modifyFood = SaveData.resSearchFood;
                modifyFood.setName(edtModifyFoodName.getText().toString());
                modifyFood.setPrice(edtModifyFoodPrice.getText().toString());
                modifyFood.setType(spinType.getSelectedItem().toString());
                GetJson.modifyFood(modifyFood);
                SaveData.resSearchFood = modifyFood;
            }
        });

        imgModifyFood = (ImageView) view.findViewById(R.id.imgModifyFood);
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
                imgModifyFood.setImageBitmap(thumbnail);
            }
        }
        if(requestCode == 1){
            imgModifyFood.setImageURI(data.getData());
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


