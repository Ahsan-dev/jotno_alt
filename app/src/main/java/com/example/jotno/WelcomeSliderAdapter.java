package com.example.jotno;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.jotno.Models.BannerDatum;
import com.example.jotno.Models.SliderItem;
import com.smarteist.autoimageslider.SliderViewAdapter;
import com.squareup.picasso.Picasso;

import java.util.List;

public class WelcomeSliderAdapter extends
        SliderViewAdapter<WelcomeSliderAdapter.SliderAdapterVH> {

    private Context context;
    private List<BannerDatum> mSliderItems ;

    public WelcomeSliderAdapter(Context context, List<BannerDatum> mSliderItems) {
        this.context = context;
        this.mSliderItems = mSliderItems;
    }

    @Override
    public SliderAdapterVH onCreateViewHolder(ViewGroup parent) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.slider_item_layout, null);
        return new SliderAdapterVH(inflate);
    }



    @Override
    public void onBindViewHolder(SliderAdapterVH viewHolder, final int position) {

        BannerDatum sliderItem = mSliderItems.get(position);

        Picasso.get().load(sliderItem.getImage()).placeholder(R.drawable.jotno_logo).into(viewHolder.sliderItemImage);



        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "This is item in position " + position, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getCount() {
        //slider view count could be dynamic size
        return mSliderItems.size();
    }

    class SliderAdapterVH extends SliderViewAdapter.ViewHolder {

        View itemView;
        ImageView sliderItemImage;


        public SliderAdapterVH(View itemView) {
            super(itemView);
            sliderItemImage = itemView.findViewById(R.id.slider_item_image_id);
            this.itemView = itemView;
        }
    }


}
