package com.nck.movieappclient;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.ads.InterstitialAd;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;

public class EpisodeAdapter extends RecyclerView.Adapter <EpisodeAdapter.EpisodeHolder> {
    ArrayList<EpisodeModel> models = new ArrayList<>();
    Context context;
    private InterstitialAd interstitialAd;

    public EpisodeAdapter(ArrayList<EpisodeModel> models, Context context) {
        this.models = models;
        this.context = context;

        GoogleAds googleAds = new GoogleAds();
        interstitialAd = googleAds.loadInterstitialAds(context);
    }

    @NonNull
    @Override
    public EpisodeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View myview = LayoutInflater.from(parent.getContext()).inflate(R.layout.episodeitem,parent,false);
        EpisodeHolder holder = new EpisodeHolder(myview);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull EpisodeHolder holder, final int position) {

        holder.epName.setText(models.get(position).episodeName);
        holder.download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                interstitialAd.show();
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Thank You,Enjoy !");
                builder.setMessage("Are you sure to Download ?");
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (context!= null)
                        {

                            EpisodeModel model = models.get(position);
                            Intent intent = new Intent();
                            intent.setAction(Intent.ACTION_VIEW);
                            intent.setData(Uri.parse(model.episodeVideo));
                            context.startActivity(intent);
                        }
                    }
                });
                builder.setNegativeButton("Cancle", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toasty.warning(context,"Thank !,Next Choose Movie !",Toasty.LENGTH_LONG).show();
                    }
                });
                builder.show();

            }
        });
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public class EpisodeHolder extends RecyclerView.ViewHolder{

        TextView epName;
        ImageView download;



        public EpisodeHolder(@NonNull View itemView) {
            super(itemView);

            epName = itemView.findViewById(R.id.epname);
            download = itemView.findViewById(R.id.download_ep);

        }
    }

}
