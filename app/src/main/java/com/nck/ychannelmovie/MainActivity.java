package com.nck.ychannelmovie;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.Wave;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    static Toolbar toolbar;
    static String currentFrag = "";
    static String prefrag="";
    DrawerLayout drawer;
    TextView txtVersion;

    private InterstitialAd mInterstitialAd;


    ProgressBar progressBar;
    RelativeLayout splash_screen , datapanel;
    Handler mh = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            splash_screen.setVisibility(View.GONE);
            datapanel.setVisibility(View.VISIBLE);
            getSupportActionBar().show();;
        }
    };


Context context;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        splashScreen();


        drawer = findViewById(R.id.drawer);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Home");
        currentFrag = getString(R.string.home_frag);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,
                drawer,
                toolbar,
                R.string.openDrawer,
                R.string.closeDrawer);
        toggle.getDrawerArrowDrawable().setColor(Color.rgb(255,109,0));
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        // Drawer menu
        /*getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu);

        toggle.syncState();*/


        setFragment(new HomeFragment());
        NavigationView navigationView = findViewById(R.id.navView);
        View headerView = navigationView.getHeaderView(0);
        txtVersion = headerView.findViewById(R.id.versionName);
        txtVersion.setText("Version  :  "+BuildConfig.VERSION_NAME);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId()==R.id.home_menu)
                {
                    setFragment(new HomeFragment());
                    setTitle(getString(R.string.home_frag));
                    currentFrag = getString(R.string.home_frag);
                }
                else if (item.getItemId()==R.id.movie_menu)
                {
                    currentFrag = getString(R.string.movie_frag);
                    setFragment(new MovieFragment());
                    setTitle(getString(R.string.movie_frag));

                }
                else if (item.getItemId()==R.id.series_menu)
                {

                    currentFrag = getString(R.string.series_frag);
                    setFragment(new SeriesFragment());
                    setTitle(getString(R.string.series_frag));
                }
                else if (item.getItemId()==R.id.search_menu)
                {
                    setFragment(new SearchFragment());
                    setTitle(getString(R.string.search_frag));
                    currentFrag = getString(R.string.search_frag);
                }
                else if (item.getItemId()==R.id.share_menu)
                {
                    currentFrag = getString(R.string.share_frag);
                    Intent shareIntent = new Intent();
                    shareIntent.setAction(Intent.ACTION_SEND);
                   // shareIntent.putExtra(Intent.EXTRA_TEXT,"https://play.google.com/store/apps/details?id="+BuildConfig.APPLICATION_ID);
                    shareIntent.putExtra(Intent.EXTRA_TEXT,"https://www.facebook.com/109666614059425/posts/109740840718669/");
                    shareIntent.setType("text/plain");
                    startActivity(shareIntent);
                }
                else if (item.getItemId()==R.id.request_menu)
                {
                    currentFrag = getString(R.string.request_str);
                    setFragment(new RequestFragment());
                    setTitle(getString(R.string.request_str));
                }
                else if (item.getItemId()==R.id.about_menu)
                {
                    setFragment(new AboutFragment());
                    setTitle(getString(R.string.about_frag));
                    currentFrag = getString(R.string.about_frag);

                }



                drawer.closeDrawer(Gravity.LEFT);

                return false;
            }
        });

        splash_screen = findViewById(R.id.splash_screen);
        datapanel = findViewById(R.id.datapanel);
        mh.postDelayed(runnable,5000);

        ActionBar actionBar = getSupportActionBar();
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#102027"));
        actionBar.setBackgroundDrawable(colorDrawable);

        toolbar.setVisibility(View.VISIBLE);


    }

    public void setFragment(Fragment f)
    {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft= fm.beginTransaction();
        ft.replace(R.id.navContent,f);
        ft.commit();


    }

    @Override
    public void onBackPressed() {
        if (currentFrag.equals(getString(R.string.home_frag)))
        {
            if (drawer.isDrawerOpen(Gravity.LEFT))
            {
                drawer.closeDrawer(Gravity.LEFT);
            }
            else
            {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Thank For Using");
                builder.setMessage("Are You Sure To Exit ?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                        finishAffinity();
                    }
                });
                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();
            }

        }
        else if (currentFrag.equals(getString(R.string.movie_frag))||
            currentFrag.equals(getString(R.string.series_frag))||
            currentFrag.equals(getString(R.string.request_str))||
        currentFrag.equals(getString(R.string.search_frag))||
                currentFrag.equals(R.string.seeallmovie)||
        currentFrag.equals(getString(R.string.about_frag)))
            {
                setFragment(new HomeFragment());
                currentFrag = getString(R.string.home_frag);
                setTitle(currentFrag);
            }
            else if (currentFrag.equals(getString(R.string.series_det_frag)))
        {
                if (prefrag.equals(getString(R.string.home_frag)))
                {
                setFragment(new HomeFragment());
                currentFrag = getString(R.string.home_frag);
                setTitle(currentFrag);
            }
            else if (prefrag.equals(getString(R.string.series_frag)))
            {
                setFragment(new SeriesFragment());
                currentFrag = getString(R.string.series_frag);
                setTitle(currentFrag);
            }
            else if (prefrag.equals(getString(R.string.series_det_frag)))
            {
                setFragment(new SeriesFragment());
                currentFrag = getString(R.string.series_frag);
                setTitle(currentFrag);
            }
            else if (prefrag.equals(getString(R.string.search_frag)))
            {
                setFragment(new SearchFragment());
                currentFrag = getString(R.string.search_frag);
                setTitle(currentFrag);
            }

        }
            else if (currentFrag.equals(getString(R.string.movie_det_frag)))
        {
            if (prefrag.equals(getString(R.string.home_frag)))
            {
                setFragment(new HomeFragment());
                currentFrag = getString(R.string.home_frag);
                setTitle(currentFrag);
            }
            else if (prefrag.equals(getString(R.string.movie_frag)))
            {
                setFragment(new MovieFragment());
                currentFrag = getString(R.string.movie_frag);
                setTitle(currentFrag);
            }
            else if (prefrag.equals(getString(R.string.movie_det_frag)))
            {
                setFragment(new HomeFragment());
                currentFrag = getString(R.string.home_frag);
                setTitle(currentFrag);
            }
            else if (prefrag.equals(getString(R.string.search_frag)))
            {
                setFragment(new SearchFragment());
                currentFrag = getString(R.string.search_frag);
                setTitle(currentFrag);
            }
            else if (prefrag.equals(getString(R.string.seeallmovie)))
            {
                setFragment(new SeeAllFragment());
                currentFrag = getString(R.string.seeallmovie);
            }
            if (currentFrag.equals(getString(R.string.seeallmovie)))
            {
                if (prefrag.equals(getString(R.string.movie_det_frag)))
                {
                    setFragment(new SeeAllFragment());
                    currentFrag = getString(R.string.seeallmovie);
                }
                else if (prefrag.equals(getString(R.string.home_frag)))
                {
                    setFragment(new HomeFragment());
                    currentFrag = getString(R.string.home_frag);
                }
            }
        }


            else
            {
                super.onBackPressed();
            }

    }

    public void splashScreen()
    {
        ProgressBar progressBar = (ProgressBar)findViewById(R.id.spin_kit);
        Sprite doubleBounce = new Wave();
        progressBar.setIndeterminateDrawable(doubleBounce);
    }


}
