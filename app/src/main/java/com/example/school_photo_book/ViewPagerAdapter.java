package com.example.school_photo_book;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

public class ViewPagerAdapter extends PagerAdapter {
    // Declare Variables
    Context context;

    String[] Name;
    String[] Nickname;
    String[] Phonenumber;
    String[] Address;
    String[] Hobby;
    String[] Ambition;
    String[] comments;
    int[] photos;
    private LayoutInflater inflater;


    public ViewPagerAdapter(Context context, String[] Name, String[] Nickname, String[] Phonenumber, String[] Address,
                            String[] Hobby, String[] Ambition, String[] comments, int[] photos) {
        this.context = context;
        this.Name = Name;
        this.Nickname = Nickname;
        this.Phonenumber = Phonenumber;
        this.Address = Address;
        this.Hobby = Hobby;
        this.Ambition = Ambition;
        this.comments = comments;
        this.photos = photos;
    }

    @Override
    public int getCount() {
       return Name.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == ((RelativeLayout) object);
    }


    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        // Declare Variables
        TextView textName;
        TextView textNickname;
        TextView textPhonenumber;
        TextView textAddress;
        TextView textHobby;
        TextView textAmbition;
        TextView textComments;
        ImageView photos;

        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.viewpager_item, container,
                false);

        // Locate the TextViews in viewpager_item.xml
        textName = itemView.findViewById(R.id.names);
        textNickname = itemView.findViewById(R.id.nicknames);
        textPhonenumber = itemView.findViewById(R.id.phoneno);
        textAddress = itemView.findViewById(R.id.addresses);
        textHobby = itemView.findViewById(R.id.hobbies);
        textAmbition = itemView.findViewById(R.id.ambitions);
        textComments = itemView.findViewById(R.id.comments);

        // Locate the ImageView in viewpager_item.xml
        photos =  itemView.findViewById(R.id.photo);

        // Capture position and set to the TextViews
        textName.setText(Name[position]);
        textNickname.setText(Nickname[position]);
        textPhonenumber.setText(Phonenumber[position]);
        textAddress.setText(Address[position]);
        textHobby.setText(Hobby[position]);
        textAmbition.setText(Ambition[position]);
        textComments.setText(comments[position]);

         // Capture position and set to the ImageView
        photos.setImageResource(this.photos[position]);

        // Add viewpager_item.xml to ViewPager
        container.addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        // Remove viewpager_item.xml from ViewPager
        container.removeView((RelativeLayout) object);

    }
}