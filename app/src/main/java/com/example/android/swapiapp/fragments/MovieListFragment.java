package com.example.android.swapiapp.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.recyclerview.extensions.ListAdapter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.swapiapp.R;
import com.example.android.swapiapp.movies.MovieManager;
import com.example.android.swapiapp.movies.MoviesApiAsyncTaskLoader;
import com.google.gson.Gson;

public class MovieListFragment extends Fragment implements LoaderManager.LoaderCallbacks<String> {

    private MovieManager movieManager;
    private static final int MOVIE_LOADER_ID = 20;
    private static final String MOVIEMANAGER_RAWJSON_TEXT_KEY = "movieManager";
    private boolean flag = false;
    RecyclerView mRecyclerView;
    ListAdapter mListAdapter;
    private TextView mItemTextView;
    private ImageView mItemImage;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        movieManager = new MovieManager();
        //returnt View
        View view = inflater.inflate(R.layout.activity_list_fragment, container, false);

        //recyclerview-start
        mRecyclerView = (RecyclerView) view.findViewById(R.id.listRecyclerView);
        mListAdapter = new ListAdapter();

        //LayoutManager
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);
        //stop

        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey(MOVIEMANAGER_RAWJSON_TEXT_KEY)) {
                movieManager.setRawJsonString(savedInstanceState.getString(MOVIEMANAGER_RAWJSON_TEXT_KEY));
                if(movieManager.getRawJsonString() != null)
                    flag = true;
            } else
                getMoviesFromLoader();
        } else {
            getMoviesFromLoader();
        }

        return view;
    }

    //when clicked

    //recycler:


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mRecyclerView.setAdapter(mListAdapter);
    }

    //VIEWHOLDER-start
    private class ListViewHolder extends RecyclerView.ViewHolder {

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);

            mItemTextView = (TextView) itemView.findViewById(R.id.itemText);
            mItemImage = (ImageView) itemView.findViewById(R.id.itemImage);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    //als data nog niet geladen is doe niks
                    if(!flag)
                        return;

                    Toast.makeText(getActivity(), "Film: " + (getAdapterPosition() + 1), Toast.LENGTH_LONG).show();
                    Gson gson = new Gson();
                    String item = gson.toJson(movieManager.ParseMoviesToArrayListMovies(movieManager.getRawJsonString()).get(getAdapterPosition()));

                    DetailFragment detailFragment = (DetailFragment) getFragmentManager().findFragmentById(R.id.detail);
                    if (detailFragment != null && detailFragment.isVisible()) {

                        //Visible: in Landscape mode
                        DetailFragment newFragment = new DetailFragment();
                        Bundle bundle = new Bundle();
                        bundle.putString("item", item);     //key item
                        newFragment.setArguments(bundle);

                        FragmentTransaction transaction = getFragmentManager().beginTransaction();
                        transaction.replace(detailFragment.getId(), newFragment);
                        transaction.addToBackStack(null);

                        transaction.commit();
                    } else {

                        //Not visible: is not in Landscape mode
                        Intent intent = new Intent(getActivity().getBaseContext(), DetailActivity.class);
                        intent.putExtra("item", item);
                        getActivity().startActivity(intent);
                    }
                }
            });
        }


        public void bindView(int position) {
            mItemTextView.setText(MovieData.EPISODE_LIST[position]);
            mItemImage.setImageResource(MovieData.PICTURE_PATH[position]);
        }
    }
    //stop

    //ADAPTER-start
    private class ListAdapter extends RecyclerView.Adapter<ListViewHolder> {

        @NonNull
        @Override
        public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            View view = inflater.inflate(R.layout.list_item, parent, false);
            return new ListViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ListViewHolder listViewHolder, int i) {
            listViewHolder.bindView(i);
        }

        @Override
        public int getItemCount() {
            return MovieData.EPISODE_LIST.length;
        }
    }


    //Async call to populate movieManager
    @NonNull
    @Override
    public Loader<String> onCreateLoader(int i, @Nullable Bundle bundle) {
        return new MoviesApiAsyncTaskLoader(this.getContext());
    }

    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String s) {
        movieManager.setRawJsonString(s);
        if(s != null)
            flag = true;
    }

    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {

    }

    private void getMoviesFromLoader() {
        LoaderManager loaderManager = getLoaderManager();
        Loader<String> githubSearchLoader = loaderManager.getLoader(MOVIE_LOADER_ID);
        if (githubSearchLoader == null) {
            loaderManager.initLoader(MOVIE_LOADER_ID, null, this);
        } else {
            loaderManager.restartLoader(MOVIE_LOADER_ID, null, this);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        String rawJson = movieManager.getRawJsonString();
        if (rawJson == "")
            return;
        outState.putString(MOVIEMANAGER_RAWJSON_TEXT_KEY, rawJson);
    }
}
