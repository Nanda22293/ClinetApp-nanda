package com.nck.ychannelmovie;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


/**
 * A simple {@link Fragment} subclass.
 */
public class SeiresDetailFragment extends Fragment {

    public SeiresDetailFragment() {
        // Required empty public constructor
    }

    SeriesModel model;
    String seriesIds;
    static RecyclerView list;
    static ImageView image;
    static TextView sereisname,viewCount,seriesCat,viewRating,seriesReview;
    static FloatingActionButton epCount;
    View myview;

    private InterstitialAd mInterstitialAd;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       myview = inflater.inflate(R.layout.fragment_seires_detail, container, false);

       GoogleAds googleAds = new GoogleAds();
       googleAds.loadAdVerticalAds(myview,getContext(),getActivity());
      /*googleAds.loadAdTemplate(myview,getContext(),getActivity());*/
       mInterstitialAd = googleAds.loadInterstitialAds(getContext());
       seriesCat = myview.findViewById(R.id.seriesCat);
       viewRating = myview.findViewById(R.id.rating);
       seriesReview = myview.findViewById(R.id.seriesReview);

        list = myview.findViewById(R.id.eplist);
        image = myview.findViewById(R.id.image);
        sereisname = myview.findViewById(R.id.series_name);
        viewCount = myview.findViewById(R.id.txtviewcount);
        epCount = myview.findViewById(R.id.txtepcount);
        FirebaseConnect firebaseConnect = new FirebaseConnect(getContext(),getFragmentManager());
        firebaseConnect.updateSeriesCount(model,seriesIds);

        if (model!=null)
        {
            Glide.with(getContext())
                    .load(model.seriesImage)
                    .into(image);
            sereisname.setText(model.seriesName);
            viewCount.setText(model.seriesCount+"");
            seriesCat.setText(model.seriesCategory);
            viewRating.setText(model.seriesRating);
            seriesReview.setText(model.seriesReview);
        }
       FirebaseConnect fconnect = new FirebaseConnect(getContext(),getFragmentManager());
       fconnect.getEpBySeriesName(model.seriesName);

        return myview;
    }

    public static Bitmap textAsBitmap(String text, float textSize, int textColor) {
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setTextSize(textSize);
        paint.setColor(textColor);
        paint.setTextAlign(Paint.Align.LEFT);
        float baseline = -paint.ascent(); // ascent() is negative
        int width = (int) (paint.measureText(text) + 0.0f); // round
        int height = (int) (baseline + paint.descent() + 0.0f);
        Bitmap image = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(image);
        canvas.drawText(text, 0, baseline, paint);
        return image;
    }


}
