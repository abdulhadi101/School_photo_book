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

public class ViewPagerAdapterStaff extends PagerAdapter {
    // Declare Variables
    Context context;

    private String[] StaffName;
    private String[] Rank;
    private String[] Staffphoneno;
    private String[] StaffAddress;
    private String[] StaffComments;

    private int[] Staffphotos;
    private LayoutInflater inflater;



    public ViewPagerAdapterStaff(Context context, String[] StaffName, String[] Rank, String[] StaffPhoneno, String[] StaffAddress,
                            String[] StaffComments, int[] Staffphotos) {
        this.context = context;
        this.StaffName = StaffName;
        this.Rank = Rank;
        this.Staffphoneno = StaffPhoneno;
        this.StaffAddress = StaffAddress;
        this.StaffComments = StaffComments;
        this.Staffphotos = Staffphotos;
    }

    @Override
    public int getCount() {
        return StaffName.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == ((RelativeLayout) object);
    }


    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        // Declare Variables
        TextView staff_name;
        TextView staff_rank;
        TextView staff_phoneno;
        TextView staff_address;
        TextView staff_comments;
        ImageView staff_photos;

        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.viewpager_item_staff, container,
                false);

        // Locate the TextViews in viewpager_item.xml
        staff_name = itemView.findViewById(R.id.names_staff);
        staff_rank = itemView.findViewById(R.id.rank);
        staff_phoneno = itemView.findViewById(R.id.phoneno_staff);
        staff_address = itemView.findViewById(R.id.addresses_staff);
        staff_comments = itemView.findViewById(R.id.comments_staff);
            // Locate the ImageView in viewpager_item.xml
        staff_photos =  itemView.findViewById(R.id.photo_staff);

        // Capture position and set to the TextViews
        staff_name.setText(StaffName[position]);
        staff_rank.setText(Rank[position]);
        staff_phoneno.setText(Staffphoneno[position]);
        staff_address.setText(StaffAddress[position]);
        staff_comments.setText(StaffComments[position]);


        // Capture position and set to the ImageView
        staff_photos.setImageResource(this.Staffphotos[position]);

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