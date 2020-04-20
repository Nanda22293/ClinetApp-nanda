package com.nck.movieappclient;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Date;

import es.dmoral.toasty.Toasty;


/**
 * A simple {@link Fragment} subclass.
 */
public class RequestFragment extends Fragment {

    public RequestFragment() {
        // Required empty public constructor
    }

    RewardedVideoAd rewardedVideoAd;
    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (rewardedVideoAd.isLoaded())
            {
                rewardedVideoAd.show();
            }
        }
    };


    FirebaseFirestore db;
    CollectionReference ref;
View myview;
    Button btnsave,btncancel;
    EditText edtname,edtimage;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        myview =  inflater.inflate(R.layout.fragment_request, container, false);

        GoogleAds googleAds = new GoogleAds();
        rewardedVideoAd = googleAds.loadRewardedVideoAds(getContext());
        googleAds.loadTwoVerticalAds(myview,getContext(),getActivity());

        handler.postDelayed(runnable,20000);

        edtname = myview.findViewById(R.id.edtname);
        edtimage = myview.findViewById(R.id.edtimage);
        btnsave = myview.findViewById(R.id.btnsave);
        btncancel = myview.findViewById(R.id.btncancel);

        db = FirebaseFirestore.getInstance();
        ref = db.collection(getString(R.string.request_ref));

        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rewardedVideoAd.isLoaded())
                {
                    rewardedVideoAd.show();
                }
                if (!edtimage.getText().toString().equals("")
                || !edtname.getText().toString().equals(""))
                {
                    RequestModel model = new RequestModel();
                    model.name = edtname.getText().toString().trim();
                    model.imagelink = edtimage.getText().toString().trim();

                    SimpleDateFormat format = new SimpleDateFormat("ddMMyyyyhhMMss");
                    model.createdArt = format.format(new Date());
                    ref.add(model);

                    edtname.setText("");
                    edtimage.setText("");

                    Toasty.success(getContext(),"Request Send Successfully",Toasty.LENGTH_LONG).show();
                }
                else
                {
                    Toasty.error(getContext(),"Please Input Date",Toasty.LENGTH_LONG).show();
                }

            }
        });


        return myview;
    }

}
