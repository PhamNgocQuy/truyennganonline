package com.AdapterStoryExpand;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.quypn.truyenngan2.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by QuyPN on 5/13/2017.
 */

public class GirdViewAdapter extends BaseAdapter {

    private ArrayList<Profile> lsData;
    private Context context;
    private LayoutInflater inflater;

    public GirdViewAdapter(ArrayList<Profile> lsData, Context context, LayoutInflater inflater) {
        this.lsData = lsData;
        this.context = context;
        this.inflater = inflater;

    }

    @Override
    public int getCount() {
        return lsData.size();
    }

    @Override
    public Profile getItem(int position) {
        return lsData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Profile profile = lsData.get(position);
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_story_layout, null);
            viewHolder = new ViewHolder();
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.img_story);
            viewHolder.textView = (TextView) convertView.findViewById(R.id.txt_title);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Picasso.with(context).load(profile.getUrlImg()).into(viewHolder.imageView);
        viewHolder.textView.setText(profile.getTitle());


        return convertView;
    }

    class ViewHolder {
        ImageView imageView;
        TextView textView;
    }
}
