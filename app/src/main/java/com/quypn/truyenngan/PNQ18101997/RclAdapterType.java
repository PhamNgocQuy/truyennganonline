package com.quypn.truyenngan.PNQ18101997;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.AdapterStoryExpand.Profile_Type;
import com.example.quypn.truyenngan2.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by QuyPN on 4/9/2017.
 */

public class RclAdapterType extends RecyclerView.Adapter<HeaderViewHoder_> {

    private ArrayList<Profile_Type> lsData;
    private Context context;

    public RclAdapterType(ArrayList<Profile_Type> lsData, Context context) {
        this.lsData = lsData;
        this.context = context;
    }

    @Override
    public HeaderViewHoder_ onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.item_rclview, null);
        return new HeaderViewHoder_(view);
    }

    @Override
    public void onBindViewHolder(HeaderViewHoder_ holder, int position) {
        final Profile_Type profile_type = lsData.get(position);
        holder.stt.setText(profile_type.getStt());
        holder.title.setText(profile_type.getTitle());
        holder.img_title.setImageResource(R.mipmap.ic_launcher);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,TypeActivity.class);
                intent.putExtra("type", profile_type);
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return lsData.size();
    }
}

class HeaderViewHoder_ extends RecyclerView.ViewHolder {
    TextView title;
    CircleImageView img_title;
    TextView stt;

    public HeaderViewHoder_(View itemView) {
        super(itemView);
        stt = (TextView) itemView.findViewById(R.id.txt_stt);
        title = (TextView) itemView.findViewById(R.id.txt_title);
        img_title = (CircleImageView) itemView.findViewById(R.id.img_img);
    }
}
