package com.StoryClass;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.quypn.truyenngan2.R;
import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder;

/**
 * Created by QuyPN on 5/13/2017.
 */

public class ClassifyStoryViewHolder extends GroupViewHolder {

    private TextView txt_tile;
    private Boolean expand = false;
    private ImageView imageView;
    private ImageView img_tmp;

    public ClassifyStoryViewHolder(View itemView) {
        super(itemView);
        txt_tile = (TextView) itemView.findViewById(R.id.txt_title);
        imageView = (ImageView) itemView.findViewById(R.id.img_expand);
        img_tmp = (ImageView) itemView.findViewById(R.id.img_tmp);
    }

    public void setTxt_tile(String txt_tile) {
        this.txt_tile.setText(txt_tile);
    }

    public void setExpand(Boolean aBoolean) {
        this.expand = aBoolean;
    }

    public boolean isExpand() {
        return this.expand;
    }

    public void setImgExpand(int resId) {
        imageView.setImageResource(resId);
    }

    public void setImg(int resId) {
        img_tmp.setImageResource(resId);
    }


}
