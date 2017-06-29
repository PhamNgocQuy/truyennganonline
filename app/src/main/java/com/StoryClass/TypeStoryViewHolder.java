package com.StoryClass;

import android.view.View;
import android.widget.TextView;

import com.example.quypn.truyenngan2.R;
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;

import de.hdodenhof.circleimageview.CircleImageView;


/**
 * Created by QuyPN on 5/13/2017.
 */

public class TypeStoryViewHolder extends ChildViewHolder {


    private TextView txt_stt;
    private TextView txt_tile;
    private CircleImageView imageView;


    public TypeStoryViewHolder(View itemView) {
        super(itemView);

        txt_stt = (TextView) itemView.findViewById(R.id.txt_stt);
        txt_tile = (TextView) itemView.findViewById(R.id.txt_title);
        imageView = (CircleImageView) itemView.findViewById(R.id.img_img);

    }

    public void setTxt_stt(String txt_stt) {
        this.txt_stt.setText(txt_stt);
    }


    public void setTxt_tile(String txt_tile) {
        this.txt_tile.setText(txt_tile);
    }

    public void setImageView(int resID) {
        imageView.setImageResource(resID);
    }

}
