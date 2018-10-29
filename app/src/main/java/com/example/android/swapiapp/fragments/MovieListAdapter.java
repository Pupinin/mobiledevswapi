package com.example.android.swapiapp.fragments;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.swapiapp.R;

public class ListAdapter extends RecyclerView.Adapter {

    //memberVar
    public final ListItemClickListener mOnClickListener;

    //Constructor
    public ListAdapter(ListItemClickListener listener) {
        mOnClickListener = listener;
    }

    //interface
    public interface ListItemClickListener {
        void onListItemClick(int clickedItemIndex);
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        Context context = parent.getContext();
         LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.list_item, parent, false);
        //  view.setOnClickListener(mOnClickListener);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ((ListViewHolder) viewHolder).bindView(i);
    }


    @Override
    public int getItemCount() {
        return MovieData.EPISODE_LIST.length;
    }


    //ViewHolder class - start
    private class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView mItemTextView;
        private ImageView mItemImage;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);

            mItemTextView = (TextView) itemView.findViewById(R.id.itemText);
            mItemImage = (ImageView) itemView.findViewById(R.id.itemImage);
            itemView.setOnClickListener(this);
        }


//        public void bindView(int position) {
//            mItemTextView.setText(MovieData.EPISODE_LIST[position]);
//            mItemImage.setImageResource(MovieData.PICTURE_PATH[position]);
//        }


        @Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            mOnClickListener.onListItemClick(clickedPosition);
        }
    }
    //ViewHolder end
}
