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
import com.google.android.gms.ads.InterstitialAd;

import java.util.ArrayList;


public class SeriesAdapter extends RecyclerView.Adapter<SeriesAdapter.MovieHolder> {
    ArrayList<SeriesModel> SeriesModels = new ArrayList<>();
    Context context;
    FragmentManager fm;

    private InterstitialAd interstitialAd;
    ArrayList<String> seriesIds = new ArrayList<>();
    public SeriesAdapter(ArrayList<SeriesModel> SeriesModels, final Context context, FragmentManager fm) {
        this.SeriesModels = SeriesModels;
        this.context = context;
        this.fm = fm;

        GoogleAds googleAds = new GoogleAds();
        interstitialAd = googleAds.loadInterstitialAds(context);

    }

    public SeriesAdapter(ArrayList<SeriesModel> seriesModels, final Context context, FragmentManager fm, ArrayList<String> seriesIds) {
        this.SeriesModels = seriesModels;
        this.context = context;
        this.fm = fm;
        this.seriesIds = seriesIds;

        GoogleAds googleAds = new GoogleAds();
        interstitialAd = googleAds.loadInterstitialAds(context);

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
                .load(SeriesModels.get(position).seriesImage)
                .into(holder.movieImage);
        holder.txtShow.setText(SeriesModels.get(position).seriesName);

        holder.movieImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (interstitialAd.isLoaded())
                {
                    interstitialAd.show();
                    gotoNext(position);
                }
                else
                {
                    gotoNext(position);
                }


            }
        });
    }

    public void gotoNext(int position)
    {
        MainActivity.prefrag = MainActivity.currentFrag;
        MainActivity.currentFrag = context.getString(R.string.series_det_frag);
        SeiresDetailFragment detfrag = new SeiresDetailFragment();
        detfrag.model = SeriesModels.get(position);
        detfrag.seriesIds = seriesIds.get(position);
        setFragment(detfrag);
        SeriesFragment.setHeader(SeriesModels.get(position).seriesName);
    }

    public void setFragment(Fragment detfrag)
    {
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.navContent,detfrag);
        ft.commit();
    }

    @Override
    public int getItemCount() {
        return SeriesModels.size();
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
}

