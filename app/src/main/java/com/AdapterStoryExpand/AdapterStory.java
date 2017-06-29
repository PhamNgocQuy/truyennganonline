package com.AdapterStoryExpand;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.StoryClass.ClassifyStoryViewHolder;
import com.StoryClass.TypeStoryViewHolder;
import com.example.quypn.truyenngan2.R;
import com.quypn.truyenngan.PNQ18101997.TypeActivity;
import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter;
import com.thoughtbot.expandablerecyclerview.listeners.OnGroupClickListener;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

/**
 * Created by QuyPN on 5/13/2017.
 */

public class AdapterStory extends ExpandableRecyclerViewAdapter<ClassifyStoryViewHolder, TypeStoryViewHolder> {

    Context mContext;

    public AdapterStory(List<? extends ExpandableGroup> groups, Context context) {
        super(groups);
        mContext = context;


    }

    @Override
    public ClassifyStoryViewHolder onCreateGroupViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.group_story_item, null);
        return new ClassifyStoryViewHolder(view);
    }

    @Override
    public TypeStoryViewHolder onCreateChildViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rclview, null);
        return new TypeStoryViewHolder(view);
    }

    @Override
    public void onBindChildViewHolder(TypeStoryViewHolder holder, int flatPosition, ExpandableGroup group, int childIndex) {
        final Profile_Type type = (Profile_Type) group.getItems().get(childIndex);

        holder.setTxt_stt(type.getStt());
        holder.setTxt_tile(type.getTitle());
        holder.setImageView(R.drawable.ic_type);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, TypeActivity.class);
                intent.putExtra("type", type);
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public void onBindGroupViewHolder(final ClassifyStoryViewHolder holder, int flatPosition, final ExpandableGroup group) {
        holder.setTxt_tile(group.getTitle());

        switch (flatPosition) {
            case 0:
                holder.setImg(R.drawable.favorite_group);
                break;
            case 3:
                holder.setImg(R.drawable.ic_thinking);
                break;
            case 1:
                holder.setImg(R.drawable.ic_shortstory);
                break;
            case 2:
                holder.setImg(R.drawable.ic_blogstory);
                break;
        }

        holder.setOnGroupClickListener(new OnGroupClickListener() {
            @Override
            public boolean onGroupClick(int flatPos) {
                AdapterStory.super.onGroupClick(flatPos);
                holder.setExpand(!holder.isExpand());
                if (holder.isExpand()) {
                    holder.setImgExpand(R.drawable.ic_down);
                } else {
                    holder.setImgExpand(R.drawable.ic_right);
                }
                return false;
            }
        });
    }

}
