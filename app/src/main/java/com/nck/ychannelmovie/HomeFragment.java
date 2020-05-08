package com.nck.ychannelmovie;

import android.content.Context;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 *
 */
public class HomeFragment extends Fragment {

    public HomeFragment() {
        // Required empty public constructor
    }

    RewardedVideoAd rewardedVideoAd;
    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (rewardedVideoAd.isLoaded()) {
                rewardedVideoAd.show();
            }
        }
    };

    public static CarouselView carouselView;

    public static ArrayList<String> sampleImages = new ArrayList<>();
    View myview;
    static Context context;
    static RecyclerView allMovie, allSeries, allCategory;
    static TextView txtallmovie, txtallseries, txtallcategory;
    static Button btnsee, btnshow;
    static String currentFrag = "";


    static ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            Glide.with(context)
                    .load(sampleImages.get(position))
                    .into(imageView);
        }
    };


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        myview = inflater.inflate(R.layout.fragment_home, container, false);

        carouselView = (CarouselView) myview.findViewById(R.id.carouselView);
        allMovie = myview.findViewById(R.id.allmovie);
        txtallmovie = myview.findViewById(R.id.txtmovie);
        allSeries = myview.findViewById(R.id.allseires);
        txtallseries = myview.findViewById(R.id.txtseries);
        txtallcategory = myview.findViewById(R.id.txtcategory);
        allCategory = myview.findViewById(R.id.allcategory);

       /* btnsee = myview.findViewById(R.id.seeallmovie);
        btnsee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                currentFrag = getString(R.string.seeallmovie);
                setFragment(new SeeAllFragment());


            }
        });*/
       /* btnshow = myview.findViewById(R.id.seeallseries);
        btnshow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                currentFrag =getString(R.string.seeseries);
                setFragment(new SeriesFragment());

            }
        });*/


        SeriesFragment.activity = getActivity();
        MovieFragment.activity = getActivity();
        context = getContext();

        HomeFragment.carouselView.setPageCount(sampleImages.size());

        HomeFragment.carouselView.setImageListener(imageListener);

        GoogleAds googleAds = new GoogleAds();
        googleAds.loadVerticalAdsForHome(getContext(), myview, getActivity());
        rewardedVideoAd = googleAds.loadRewardedVideoAds(getContext());
        /*handler.postDelayed(runnable,0000);*/

        FirebaseConnect fConnect = new FirebaseConnect(getContext(), getFragmentManager());
        fConnect.showSlide();
        fConnect.getAllCategory();
        fConnect.getAllMovies();
        fConnect.getAllSeries();


        MainActivity.toolbar.setVisibility(View.VISIBLE);
        return myview;
    }

    public void setFragment(Fragment f) {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.navContent, f);
        ft.commit();


    }


}



