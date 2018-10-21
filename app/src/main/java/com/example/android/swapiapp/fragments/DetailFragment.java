package com.example.android.swapiapp.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.android.swapiapp.R;

public class DetailFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_detail_fragment, container, false);

        Bundle bundle = getArguments();
        TextView jsonView = view.findViewById(R.id.textViewJson);

        String item = "";
        if (bundle != null) {
            item = getArguments().getString("item");
        }

        jsonView.setText("works");

        return view;
    }
}
