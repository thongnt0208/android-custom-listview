package com.example.lab3customlistview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class FruitListAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private ArrayList<Fruit> fruitsList;

    public FruitListAdapter(Context context, int layout, ArrayList<Fruit> fruitsList) {
        this.context = context;
        this.layout = layout;
        this.fruitsList = fruitsList;
    }

    @Override
    public int getCount() {
        return fruitsList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(layout, null);

        TextView tvTitle = convertView.findViewById(R.id.tvTitle);
        TextView tvDescription = convertView.findViewById(R.id.tvDescription);
        ImageView imgvThumbnail = convertView.findViewById(R.id.imgvThumbnail);

        Fruit fruit = fruitsList.get(position);
        tvTitle.setText(fruit.getName());
        tvDescription.setText(fruit.getDescription());
        if (fruit.getImageLink() == null) {
            imgvThumbnail.setImageResource(fruit.getImage());
        } else {
            Picasso.get()
                    .load(fruit.getImageLink())
                    .placeholder(R.drawable.no_image_icon)
                    .error(R.drawable.no_image_icon)
                    .into(imgvThumbnail);
        }
        return convertView;
    }
}
