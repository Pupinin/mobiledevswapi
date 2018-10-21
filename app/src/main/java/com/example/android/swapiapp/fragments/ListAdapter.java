package com.example.android.swapiapp.fragments;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.swapiapp.R;

import java.io.Console;

//voor recyclerview
public class ListAdapter extends RecyclerView.Adapter {
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
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

    private class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView mItemTextView;
        private ImageView mItemImage;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);

            mItemTextView = (TextView) itemView.findViewById(R.id.itemText);
            mItemImage = (ImageView) itemView.findViewById(R.id.itemImage);
            itemView.setOnClickListener(this);
        }


        public void bindView(int position) {
            mItemTextView.setText(MovieData.EPISODE_LIST[position]);
            mItemImage.setImageResource(MovieData.PICTURE_PATH[position]);
        }


        @Override
        public void onClick(View v) {

        }
    }
}
