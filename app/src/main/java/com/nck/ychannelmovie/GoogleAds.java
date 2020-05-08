package com.nck.ychannelmovie;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.widget.FrameLayout;

import com.google.android.ads.nativetemplates.NativeTemplateStyle;
import com.google.android.ads.nativetemplates.TemplateView;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.formats.UnifiedNativeAd;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;

public class GoogleAds {

    private InterstitialAd mInterstitialAd;
    public InterstitialAd loadInterstitialAds(Context context)
    {
        MobileAds.initialize(context,context.getString(R.string.app_id));
        mInterstitialAd = new InterstitialAd(context);
        mInterstitialAd.setAdUnitId(context.getString(R.string.interstitial_unit_id));
        mInterstitialAd.loadAd(new AdRequest.Builder().build());

        mInterstitialAd.setAdListener(new AdListener(){
            @Override
            public void onAdClosed() {
                mInterstitialAd.loadAd(new AdRequest.Builder().build());

            }
        });
        return mInterstitialAd;
    }

    private void loadRewardedVideoAd(Context context) {
        mRewardedVideoAd.loadAd(context.getString(R.string.rewarded_unit_id),
                new AdRequest.Builder().build());
    }

    private RewardedVideoAd mRewardedVideoAd;
    public RewardedVideoAd loadRewardedVideoAds(final Context context)
    {
        MobileAds.initialize(context,context.getString(R.string.app_id));


        mRewardedVideoAd = MobileAds.getRewardedVideoAdInstance(context);
        loadRewardedVideoAd(context);
        mRewardedVideoAd.setRewardedVideoAdListener(new RewardedVideoAdListener() {
            @Override
            public void onRewardedVideoAdLoaded() {

            }

            @Override
            public void onRewardedVideoAdOpened() {

            }

            @Override
            public void onRewardedVideoStarted() {

            }

            @Override
            public void onRewardedVideoAdClosed() {
                loadRewardedVideoAd(context);
            }

            @Override
            public void onRewarded(RewardItem rewardItem) {

            }

            @Override
            public void onRewardedVideoAdLeftApplication() {

            }

            @Override
            public void onRewardedVideoAdFailedToLoad(int i) {

            }

            @Override
            public void onRewardedVideoCompleted() {

            }
        });
            return mRewardedVideoAd;

    }

    FrameLayout container1;
    AdView adView1;

    public void loadAdVerticalAds(View myview,Context context,Activity activity)
    {
        MobileAds.initialize(context,context.getString(R.string.app_id));
        adView1 = new AdView(context);

        container1= myview.findViewById(R.id.container1);


        adView1.setAdUnitId(context.getString(R.string.banner_unit_id));


        adView1.setAdSize(getAdSize(activity,context));


        adView1.loadAd(new AdRequest.Builder().build());


        container1.addView(adView1);


    }


    FrameLayout adContainer1;
    AdView adView2;
    AdRequest request2;
    public void loadVerticalAdsForHome(Context context,View myview,Activity activity)
    {
        MobileAds.initialize(context,context.getString(R.string.app_id));



        adContainer1 = myview.findViewById(R.id.adContainer1);
        adView2 = new AdView(context);
        adView2.setAdUnitId(context.getString(R.string.banner_unit_id));
        adContainer1.addView(adView2);


        request2 = new AdRequest.Builder().build();
        adView2.setAdSize(getAdSize(activity,context));
        adView2.loadAd(request2);


    }

    private AdSize getAdSize(Activity activity,Context context) {
        // Step 2 - Determine the screen width (less decorations) to use for the ad width.
        Display display = activity.getWindowManager().getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics();
        display.getMetrics(outMetrics);

        float widthPixels = outMetrics.widthPixels;
        float density = outMetrics.density;

        int adWidth = (int) (widthPixels / density);

        // Step 3 - Get adaptive ad size and return for setting on the ad view.
        return AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(context, adWidth);
    }

    FrameLayout adContainer,adContainer2;
    AdView adView3,adView4;

    public void loadTwoVerticalAds(View myview,Context context,Activity activity)
    {
        MobileAds.initialize(context,context.getString(R.string.app_id));
        adView3 = new AdView(context);
        adView4 = new AdView(context);
        adContainer= myview.findViewById(R.id.adContainer);
        adContainer2 = myview.findViewById(R.id.adContainer2);

        adView3.setAdUnitId(context.getString(R.string.banner_unit_id));
        adView4.setAdUnitId(context.getString(R.string.banner_unit_id));

        adView3.setAdSize(getAdSize(activity,context));
        adView4.setAdSize(getAdSize(activity,context));

        adView3.loadAd(new AdRequest.Builder().build());
        adView4.loadAd(new AdRequest.Builder().build());

        adContainer.addView(adView3);
        adContainer2.addView(adView4);

    }


    public void loadAdTemplate(final View myview, Context context, Activity activity) {
        MobileAds.initialize(context,context.getString(R.string.app_id));

        AdLoader adLoader = new AdLoader.Builder(context,context.getString(R.string.native_unit_id))
                .forUnifiedNativeAd(new UnifiedNativeAd.OnUnifiedNativeAdLoadedListener() {
                    @Override
                    public void onUnifiedNativeAdLoaded(UnifiedNativeAd unifiedNativeAd) {
                        ColorDrawable background = new ColorDrawable(Color.DKGRAY);
                        NativeTemplateStyle styles = new
                                NativeTemplateStyle.Builder().withMainBackgroundColor(background).build();

                        TemplateView template = myview.findViewById(R.id.smalltemplate);
                        template.setStyles(styles);
                        template.setNativeAd(unifiedNativeAd);

                    }
                })
                .build();

        adLoader.loadAd(new AdRequest.Builder().build());

    }
    }
