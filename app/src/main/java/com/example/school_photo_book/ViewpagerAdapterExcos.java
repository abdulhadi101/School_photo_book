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

public class ViewpagerAdapterExcos extends PagerAdapter {
    // Declare Variables
    Context context;

    String[] ExcosName;
    String[] Post;
    int[] Excosphotos;

    private LayoutInflater inflater;



    public ViewpagerAdapterExcos (Context context, String[] ExcosName, String[] Post, int[] Excosphotos) {
        this.context = context;
        this.ExcosName = ExcosName;
        this.Post = Post;
        this.Excosphotos = Excosphotos;
    }

    @Override
    public int getCount() {
        return ExcosName.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == ((RelativeLayout) object);
    }


    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        // Declare Variables
        TextView excosName;
        TextView excosPost;
        ImageView ecxosphotos;

        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.viewpager_item1, container,
                false);

        // Locate the TextViews in viewpager_item1.xml
        excosName = itemView.findViewById(R.id.name_excos);
        excosPost = itemView.findViewById(R.id.excos_post);
             // Locate the ImageView in viewpager_item.xml
        ecxosphotos =  itemView.findViewById(R.id.excosphoto);

        // Capture position and set to the TextViews
        excosName.setText(ExcosName[position]);
        excosPost.setText(Post[position]);
           // Capture position and set to the ImageView
        ecxosphotos.setImageResource(this.Excosphotos[position]);

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