package com.example.android.swapiapp.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.android.swapiapp.R;

public class ListFragment extends Fragment {

    ListView listView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returnt View
        View view = inflater.inflate(R.layout.activity_list_fragment, container, false);

        //recyclerview-start
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.listRecyclerView);

        ListAdapter listAdapter = new ListAdapter();
        recyclerView.setAdapter(listAdapter);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        //stop


        return view;
    }

    //when clicked


    //even uncomment voor recyclerView demo

//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//
//        //backup met listview:
//        //  listView = (ListView) getView().findViewById(R.id.listView);
//
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                String item = (String) listView.getItemAtPosition(position);
//                Toast.makeText(getActivity(), item, Toast.LENGTH_SHORT).show();
//
//                DetailFragment detailFragment = (DetailFragment) getFragmentManager().findFragmentById(R.id.detail);
//                if (detailFragment != null && detailFragment.isVisible()) {
//
//                    //Visible: in Landscape mode
//                    DetailFragment newFragment = new DetailFragment();
//                    Bundle bundle = new Bundle();
//                    bundle.putString("item", item);     //key item
//                    newFragment.setArguments(bundle);
//
//                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
//                    transaction.replace(detailFragment.getId(), newFragment);
//                    transaction.addToBackStack(null);
//
//                    transaction.commit();
//                } else {
//
//                    //Not visible: is not in Landscape mode
//                    Intent intent = new Intent(getActivity().getBaseContext(), DetailActivity.class);
//                    intent.putExtra("item", item);
//                    getActivity().startActivity(intent);
//                }
//
//            }
//        });
//    }
}
