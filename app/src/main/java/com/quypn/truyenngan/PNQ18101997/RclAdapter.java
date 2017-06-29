package com.quypn.truyenngan.PNQ18101997;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.AdapterStoryExpand.Profile;
import com.example.quypn.truyenngan2.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;


/**
 * Created by QuyPN on 3/23/2017.
 */

public class RclAdapter extends RecyclerView.Adapter<HeaderViewHoder> {

    private ArrayList<Profile> lsData;
    private Context context;

    public RclAdapter(ArrayList<Profile> lsData, Context context) {
        this.lsData = lsData;
        this.context = context;
    }

    @Override
    public HeaderViewHoder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View headerItem = inflater.inflate(R.layout.item_rclview, null);
        return new HeaderViewHoder(headerItem);
    }

    @Override
    public void onBindViewHolder(HeaderViewHoder holder, final int position) {
        final Profile profile = lsData.get(position);
        holder.txt_stt.setText(String.valueOf(position + 1));
        holder.txt_title.setText(profile.getTitle());
        Picasso.with(context).load(lsData.get(position).getUrlImg()).into(holder.imageView);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, ReaderActivity.class);

                intent.putExtra("profile", profile);

                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return lsData.size();
    }
}

class HeaderViewHoder extends RecyclerView.ViewHolder {

      TextView txt_stt;
      TextView txt_title;
      CircleImageView imageView;

    public HeaderViewHoder(View itemView) {

        super(itemView);
        txt_stt = (TextView) itemView.findViewById(R.id.txt_stt);
        txt_title = (TextView) itemView.findViewById(R.id.txt_title);
        imageView = (CircleImageView) itemView.findViewById(R.id.img_img);
        imageView.setBackgroundResource(R.drawable.wait_video);
    }
}
