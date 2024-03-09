package com.example.log_in;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Fav_Adapter extends BaseAdapter {
    private List<byte[]> imageList;

    public Fav_Adapter(List<byte[]> imageList) {
        this.imageList = imageList;
    }

    @Override
    public int getCount() {
        return imageList.size();
    }

    @Override
    public Object getItem(int i) {
        return imageList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        // Inflate your item layout
        if (view == null) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fav_images, viewGroup, false);
        }

        ImageView imageView = view.findViewById(R.id.favimg);
        byte[] imageData = imageList.get(i);

        if (imageView != null) {
            // Convert the byte array to a Bitmap and set it to the ImageView
            Bitmap bitmap = BitmapFactory.decodeByteArray(imageData, 0, imageData.length);
            imageView.setImageBitmap(bitmap);
        }

        return view;
    }
}
