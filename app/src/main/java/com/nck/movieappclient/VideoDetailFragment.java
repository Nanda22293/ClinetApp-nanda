package com.nck.movieappclient;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.reward.RewardedVideoAd;

import es.dmoral.toasty.Toasty;


/**
 * A simple {@link Fragment} subclass.
 */
public class VideoDetailFragment extends Fragment {

    public VideoDetailFragment() {
        // Required empty public constructor
    }

    View myview;
    String movieIds;
    public static MovieModel movieModel;
    static ImageView Movieimage,coverImage;
    static TextView movieName,movieReview,viewCount,viewRating,movieCat;
    static Button btnsmall,btnbig;

private InterstitialAd interstitialAd;
    private RewardedVideoAd mRewardedVideoAd;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        myview = inflater.inflate(R.layout.movie_review_item, container, false);

        GoogleAds googleAds = new GoogleAds();
        interstitialAd = googleAds.loadInterstitialAds(getContext());
        mRewardedVideoAd = googleAds.loadRewardedVideoAds(getContext());


        coverImage = myview.findViewById(R.id.coverImg);
        movieCat = myview.findViewById(R.id.movieCat);
        Movieimage = myview.findViewById(R.id.imageM);
        movieName = myview.findViewById(R.id.movie_name);
        movieReview = myview.findViewById(R.id.movieReview);
        viewCount = myview.findViewById(R.id.txtviewcount);
        viewRating = myview.findViewById(R.id.rating);
        btnbig = myview.findViewById(R.id.btnbig);
        btnsmall = myview.findViewById(R.id.btnsmall);



        FirebaseConnect firebaseConnect = new FirebaseConnect(getContext(),getFragmentManager());
        firebaseConnect.updateMoviesCount(movieModel,movieIds);


        if (movieModel!=null)
        {
            Glide.with(getContext())
                    .load(movieModel.movieImage)
                    .into(Movieimage);
            movieName.setText(movieModel.movieName);
            viewCount.setText(movieModel.movieCount+"");
            viewRating.setText(movieModel.movieRating);
            movieCat.setText(movieModel.movieCategory);
            movieReview.setText(movieModel.movieVideo);
            Glide.with(getContext())
                 .load(movieModel.coverImage)
                 .into(coverImage);

            btnsmall.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PopupMenu popupMenu = new PopupMenu(getContext(),btnsmall);
                    popupMenu.getMenuInflater().inflate(R.menu.downloadmenu,popupMenu.getMenu());
                    popupMenu.show();
                    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            if (item.getItemId()==R.id.linkOne)
                            {
                               mRewardedVideoAd.show();
                                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                                builder.setTitle("Thank You,Enjoy !");
                                builder.setMessage("Are you sure to Download ?");
                                builder.setIcon(R.drawable.ic_cdown);
                                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        if (getContext()!= null)
                                        {
                                            Intent intent = new Intent();
                                            intent.setAction(Intent.ACTION_VIEW);
                                            intent.setData(Uri.parse(movieModel.smallLink));
                                            getContext().startActivity(intent);
                                        }
                                    }
                                });
                                builder.setNegativeButton("Cancle", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Toasty.warning(getContext(),"Thank !,Next Choose Movie !",Toasty.LENGTH_LONG).show();
                                    }
                                });
                                builder.show();
                            }
                            if (item.getItemId()==R.id.linkTwo)
                            {
                               mRewardedVideoAd.show();
                                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                                builder.setTitle("Thank You,Enjoy !");
                                builder.setMessage("Are you sure to Download ?");
                                builder.setIcon(R.drawable.ic_cdown);
                                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        if (getContext()!= null)
                                        {
                                            Intent intent = new Intent();
                                            intent.setAction(Intent.ACTION_VIEW);
                                            intent.setData(Uri.parse(movieModel.smallLinkTwo));
                                            getContext().startActivity(intent);
                                        }
                                    }
                                });
                                builder.setNegativeButton("Cancle", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Toasty.warning(getContext(),"Thank !,Next Choose Movie !",Toasty.LENGTH_LONG).show();
                                    }
                                });
                                builder.show();
                            }
                            return true;
                        }
                    });
                }
            });

            btnbig.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PopupMenu popupMenu = new PopupMenu(getContext(),btnbig);
                    popupMenu.getMenuInflater().inflate(R.menu.downloadmenu,popupMenu.getMenu());
                    popupMenu.show();
                    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            if (item.getItemId()==R.id.linkOne)
                            {
                                mRewardedVideoAd.show();
                                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                                builder.setTitle("Thank You,Enjoy !");
                                builder.setMessage("Are you sure to Download ?");
                                builder.setIcon(R.drawable.ic_cdown);
                                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        if (getContext()!= null)
                                        {
                                            Intent intent = new Intent();
                                            intent.setAction(Intent.ACTION_VIEW);
                                            intent.setData(Uri.parse(movieModel.bigLink));
                                            getContext().startActivity(intent);
                                        }
                                    }
                                });
                                builder.setNegativeButton("Cancle", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Toasty.warning(getContext(),"Thank !,Next Choose Movie !",Toasty.LENGTH_LONG).show();
                                    }
                                });
                                builder.show();
                            }
                            if (item.getItemId()==R.id.linkTwo)
                            {
                                mRewardedVideoAd.show();
                                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                                builder.setTitle("Thank You,Enjoy !");
                                builder.setMessage("Are you sure to Download ?");
                                builder.setIcon(R.drawable.ic_cdown);
                                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        if (getContext()!= null)
                                        {
                                            Intent intent = new Intent();
                                            intent.setAction(Intent.ACTION_VIEW);
                                            intent.setData(Uri.parse(movieModel.bigLinkTwo));
                                            getContext().startActivity(intent);
                                        }
                                    }
                                });
                                builder.setNegativeButton("Cancle", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Toasty.warning(getContext(),"Thank !,Next Choose Movie !",Toasty.LENGTH_LONG).show();
                                    }
                                });
                                builder.show();
                            }
                            return true;
                        }
                    });

                }
            });

        }


        FirebaseConnect fconnect = new FirebaseConnect(getContext(),getFragmentManager());
        fconnect.getMovieReview(movieModel.movieName);
        MainActivity.toolbar.setVisibility(View.VISIBLE);
        return myview;
    }

}