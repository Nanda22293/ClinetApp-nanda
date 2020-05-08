package com.nck.ychannelmovie;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import recycler.coverflow.RecyclerCoverFlow;


/**
 * A simple {@link Fragment} subclass.
 */
public class MovieFragment extends Fragment {

    static TextView txtallmovie;
    static RecyclerView allmovie;
    static RecyclerCoverFlow list;

    View myview;

    public MovieFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        myview = inflater.inflate(R.layout.fragment_movie, container, false);
        txtallmovie = myview.findViewById(R.id.txtallmovie);
        allmovie = myview.findViewById(R.id.allmovie);
        list = myview.findViewById(R.id.list);


        activity = getActivity();
        FirebaseConnect fConnect = new FirebaseConnect(getContext(),getFragmentManager());
        fConnect.getAllMoviesByMovieFragment();
        fConnect.getTopTenMovies();

        GoogleAds googleAds = new GoogleAds();
        googleAds.loadAdVerticalAds(myview,getContext(),getActivity());
        MainActivity.toolbar.setVisibility(View.VISIBLE);
        return myview;
    }



    public static Activity activity;
    public static void setHeader (String header)
    {
       activity.setTitle(header);
    }
}
