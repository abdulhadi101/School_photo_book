package com.example.school_photo_book;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;


import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class ViewPagerAdapterFront extends PagerAdapter {
    // Declare Variables
    Context context;

    int[] fullPhotos;

    private LayoutInflater inflater;



    public ViewPagerAdapterFront (Context context, int[] fullPhotos) {
        this.context = context;
        this.fullPhotos = fullPhotos;
    }

    @Override
    public int getCount() {
        return fullPhotos.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == ((RelativeLayout) object);
    }


    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        // Declare Variables

        ImageView photos;

        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.viewpager_item_front, container,
                false);

        // Locate the ImageView in viewpager_item.xml
        photos =  itemView.findViewById(R.id.full_photo);

        // Capture position and set to the ImageView
        photos.setImageResource(this.fullPhotos[position]);

        // Add viewpager_item.xml to ViewPager
        container.addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        // Remove viewpager_item1.xml from ViewPager
        container.removeView((RelativeLayout) object);

    }
}