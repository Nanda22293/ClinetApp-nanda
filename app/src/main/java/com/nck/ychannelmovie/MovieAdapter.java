package com.nck.ychannelmovie;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.reward.RewardedVideoAd;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieHolder> {
    ArrayList<MovieModel> movieModels = new ArrayList<>();
    Context context;
    FragmentManager fm;

    private InterstitialAd interstitialAd;

    private RewardedVideoAd mRewardedVideoAd;
    ArrayList<String > movieIds = new ArrayList<>();

    public MovieAdapter(ArrayList<MovieModel> movieModels, Context context , FragmentManager fm) {
        this.movieModels = movieModels;
        this.context = context;
        this.fm = fm;

        GoogleAds googleAds = new GoogleAds();
        interstitialAd =  googleAds.loadInterstitialAds(context);
        mRewardedVideoAd = googleAds.loadRewardedVideoAds(context);

    }

    public MovieAdapter(ArrayList<MovieModel> movieModels, Context context, FragmentManager fm, ArrayList<String> movieIds) {
        this.movieModels = movieModels;
        this.context = context;
        this.fm = fm;
        this.movieIds = movieIds;

        GoogleAds googleAds = new GoogleAds();
       interstitialAd =  googleAds.loadInterstitialAds(context);
       mRewardedVideoAd = googleAds.loadRewardedVideoAds(context);

    }

    @NonNull
    @Override
    public MovieHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View myview = LayoutInflater.from(parent.getContext()).inflate(R.layout.movieitem,parent,false);
        MovieHolder holder = new MovieHolder(myview);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MovieHolder holder, final int position) {

        Glide.with(context)
                .load(movieModels.get(position).movieImage)
                .into(holder.movieImage);
        holder.txtShow.setText(movieModels.get(position).movieName);
        holder.movieImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (interstitialAd.isLoaded())
                {
                    interstitialAd.show();
                    goToNext(position);
                }
                else
                {
                    goToNext(position);
                }
            }
        });
    }

    public void goToNext(int position)
    {
        MainActivity.prefrag = MainActivity.currentFrag;
        MainActivity.currentFrag = context.getString(R.string.movie_det_frag);
        VideoDetailFragment detfrag = new VideoDetailFragment();
        detfrag.movieModel = movieModels.get(position);
        detfrag.movieIds = movieIds.get(position);
        setFragment(detfrag);
        MovieFragment.setHeader(movieModels.get(position).movieName);
    }

    private void loadRewardedVideoAd() {
        mRewardedVideoAd.loadAd(context.getString(R.string.rewarded_unit_id),
                new AdRequest.Builder().build());
    }

    @Override
    public int getItemCount() {
        return movieModels.size();
    }

    public class MovieHolder extends RecyclerView.ViewHolder{

        ImageView movieImage;
        TextView txtShow;

        public MovieHolder(@NonNull View itemView) {
            super(itemView);
            movieImage = itemView.findViewById(R.id.image);
            txtShow = itemView.findViewById(R.id.txtshow);
        }
    }
    public void setFragment (Fragment f)
    {
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.navContent,f);
        ft.commit();
    }
}
