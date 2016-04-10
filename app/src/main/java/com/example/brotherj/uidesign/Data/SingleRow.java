package com.example.brotherj.uidesign.Data;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.brotherj.uidesign.R;

/**
 * Created by user on 10/4/2016.
 */
public class SingleRow extends ArrayAdapter<String>{

    private final Activity context;
    private final String[] web;
    private final Integer[] imageId;

    public SingleRow(Activity context, String[] web, Integer[] imageId) {
        super(context, R.layout.single_row, web);
        this.context = context;
        this.web = web;
        this.imageId = imageId;
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.single_row, null, true);
        TextView txtTitle = (TextView) rowView.findViewById(R.id.txtDetail);

        ImageView imageView = (ImageView) rowView.findViewById(R.id.imgDetail);
        txtTitle.setText(web[position]);

        imageView.setImageResource(imageId[position]);
        return rowView;
    }
}
