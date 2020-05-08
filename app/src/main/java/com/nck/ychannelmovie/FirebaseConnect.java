package com.nck.ychannelmovie;

import android.content.Context;
import android.graphics.Color;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class FirebaseConnect {

    FirebaseFirestore db;
    CollectionReference movieRef,seriesRef,categoryRef,episodeRef,settingRef;

    Context context;
    FragmentManager fm;

    public FirebaseConnect(Context context) {
        this.context= context;

        db = FirebaseFirestore.getInstance();
        movieRef = db.collection("movies");
        seriesRef = db.collection("series");
        categoryRef = db.collection("categories");
        episodeRef = db.collection("episode");
        settingRef = db.collection("setting");
    }

    public FirebaseConnect(Context context,FragmentManager fm) {
        this.context= context;

        db = FirebaseFirestore.getInstance();
        movieRef = db.collection("movies");
        seriesRef = db.collection("series");
        categoryRef = db.collection("categories");
        episodeRef = db.collection("episode");
        settingRef = db.collection("setting");
        this.fm = fm;
    }
    public void showSlide()
    {

        settingRef.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {

                SettingModel settingModel = new SettingModel();
                HomeFragment.sampleImages.clear();
                for (DocumentSnapshot snapshot : queryDocumentSnapshots)
                {
                    settingModel = snapshot.toObject(SettingModel.class);
                }
                if (settingModel.useSlideShow.equals("Yes"))
                {

                    HomeFragment.sampleImages.add(settingModel.slide1);
                    HomeFragment.sampleImages.add(settingModel.slide2);
                    HomeFragment.sampleImages.add(settingModel.slide3);
                    HomeFragment.sampleImages.add(settingModel.slide4);
                    HomeFragment.sampleImages.add(settingModel.slide5);

                    HomeFragment.carouselView.setPageCount(HomeFragment.sampleImages.size());

                    HomeFragment.carouselView.setImageListener(HomeFragment.imageListener);
                }
                else
                {
                    HomeFragment.carouselView.setVisibility(View.GONE);
                }
            }
        });
    }

  public void getAllMovies()
  {
      movieRef.orderBy("createdArt", Query.Direction.DESCENDING).addSnapshotListener(new EventListener<QuerySnapshot>() {
          @Override
          public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
              ArrayList<MovieModel> movieModels = new ArrayList<>();
              ArrayList<String > movieIds = new ArrayList<>();
              for (DocumentSnapshot snapshot: queryDocumentSnapshots)
              {
                  movieModels.add(snapshot.toObject(MovieModel.class));
                  movieIds.add(snapshot.getId());
              }
              MovieAdapter adapter = new MovieAdapter(movieModels,context,fm,movieIds);
              HomeFragment.allMovie.setAdapter(adapter);
              LinearLayoutManager lm = new LinearLayoutManager(context,RecyclerView.HORIZONTAL,false);
              HomeFragment.allMovie.setLayoutManager(lm);
              HomeFragment.txtallmovie.setText("All Movies ("+ movieModels.size()+")");

          }
      });
  }

    public void getAllMoviesByCategory(final String category)
    {
        movieRef.whereEqualTo("movieCategory",category).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                ArrayList<MovieModel> movieModels = new ArrayList<>();
                ArrayList<String > movieIds = new ArrayList<>();
                for (DocumentSnapshot snapshot: queryDocumentSnapshots)
                {
                    movieModels.add(snapshot.toObject(MovieModel.class));
                    movieIds.add(snapshot.getId());
                }
                MovieAdapter adapter = new MovieAdapter(movieModels,context,fm,movieIds);
                HomeFragment.allMovie.setAdapter(adapter);
                LinearLayoutManager lm = new LinearLayoutManager(context,RecyclerView.HORIZONTAL,false);
                HomeFragment.allMovie.setLayoutManager(lm);
                HomeFragment.txtallmovie.setText(category+"Movies ("+ movieModels.size()+")");

            }
        });
    }
    public void getAllSeriesByCategory(final String category)
    {
        seriesRef.whereEqualTo("seriesCategory",category).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                ArrayList<SeriesModel> seriesModels = new ArrayList<>();
                ArrayList<String> seriesIds = new ArrayList<>();
                for (DocumentSnapshot snapshot: queryDocumentSnapshots)
                {
                    seriesIds.add(snapshot.getId());
                    seriesModels.add(snapshot.toObject(SeriesModel.class));
                }
                SeriesAdapter adapter = new SeriesAdapter(seriesModels,context,fm,seriesIds);
                HomeFragment.allSeries.setAdapter(adapter);
                LinearLayoutManager lm = new LinearLayoutManager(context,RecyclerView.HORIZONTAL,false);
                HomeFragment.allSeries.setLayoutManager(lm);
                HomeFragment.txtallseries.setText(category+"Series ("+ seriesModels.size()+")");
            }
        });
    }

    public void getAllMoviesByMovieFragment()
    {
        movieRef.orderBy("createdArt", Query.Direction.DESCENDING).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                ArrayList<MovieModel> movieModels = new ArrayList<>();
                ArrayList<String > movieIds = new ArrayList<>();
                for (DocumentSnapshot snapshot: queryDocumentSnapshots)
                {
                    movieModels.add(snapshot.toObject(MovieModel.class));
                    movieIds.add(snapshot.getId());
                }
                MovieAdapter adapter = new MovieAdapter(movieModels,context,fm,movieIds);
                MovieFragment.allmovie.setAdapter(adapter);

                GridLayoutManager lm = new GridLayoutManager(context,3,RecyclerView.HORIZONTAL,false);
                MovieFragment.allmovie.setLayoutManager(lm);

                MovieFragment.txtallmovie.setText("All Movies ("+ movieModels.size()+")");

            }
        });
    }

    public void seeAllMovie()
    {
        movieRef.orderBy("createdArt", Query.Direction.DESCENDING).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                ArrayList<MovieModel> movieModels = new ArrayList<>();
                ArrayList<String > movieIds = new ArrayList<>();
                for (DocumentSnapshot snapshot: queryDocumentSnapshots)
                {
                    movieModels.add(snapshot.toObject(MovieModel.class));
                    movieIds.add(snapshot.getId());
                }
                MovieAdapter adapter = new MovieAdapter(movieModels,context,fm,movieIds);
                SeeAllFragment.allmovie.setAdapter(adapter);

                GridLayoutManager lm = new GridLayoutManager(context,3,RecyclerView.VERTICAL,false);
                SeeAllFragment.allmovie.setLayoutManager(lm);

                SeeAllFragment.txtallmovie.setText("All Movies ("+ movieModels.size()+")");
            }
        });
    }


    public void getAllSeries()
    {
        seriesRef.orderBy("createdArt", Query.Direction.DESCENDING).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                ArrayList<SeriesModel> seriesModels = new ArrayList<>();
                ArrayList<String> seriesIds = new ArrayList<>();
                for (DocumentSnapshot snapshot: queryDocumentSnapshots)
                {
                    seriesIds.add(snapshot.getId());
                    seriesModels.add(snapshot.toObject(SeriesModel.class));
                }
                SeriesAdapter adapter = new SeriesAdapter(seriesModels,context,fm,seriesIds);
                HomeFragment.allSeries.setAdapter(adapter);
                LinearLayoutManager lm = new LinearLayoutManager(context,RecyclerView.HORIZONTAL,false);
                HomeFragment.allSeries.setLayoutManager(lm);
                HomeFragment.txtallseries.setText("All Series ("+ seriesModels.size()+")");

            }
        });
    }

    public void getAllSeriesBySeriesFragment()
    {
        seriesRef.orderBy("createdArt", Query.Direction.DESCENDING).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                ArrayList<SeriesModel> seriesModels = new ArrayList<>();
                ArrayList<String> seriesIds = new ArrayList<>();
                for (DocumentSnapshot snapshot: queryDocumentSnapshots)
                {
                    seriesIds.add(snapshot.getId());
                    seriesModels.add(snapshot.toObject(SeriesModel.class));
                }
                SeriesAdapter adapter = new SeriesAdapter(seriesModels,context,fm,seriesIds);
                SeriesFragment.allsereis.setAdapter(adapter);
                GridLayoutManager lm = new GridLayoutManager(context,3,RecyclerView.HORIZONTAL,false);
                SeriesFragment.allsereis.setLayoutManager(lm);
                SeriesFragment.txtallseries.setText("All Series ("+ seriesModels.size()+")");

            }
        });
    }

    public void getAllCategory() {
        categoryRef.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                ArrayList <CategoryModel> categoryModels = new ArrayList<>();
                CategoryModel model = new CategoryModel();
                model.categoryName = context.getString(R.string.all_str);
                categoryModels.add(model);
                for (DocumentSnapshot snapshot : queryDocumentSnapshots)
                {
                    categoryModels.add(snapshot.toObject(CategoryModel.class));
                }


                LinearLayoutManager lm = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
                CategoryAdapter adapter = new CategoryAdapter(categoryModels,context,fm);
                HomeFragment.allCategory.setAdapter(adapter);
                HomeFragment.allCategory.setLayoutManager(lm);
                HomeFragment.txtallcategory.setText("All Category ("+categoryModels.size()+")");

            }
        });
    }
    public void getTopTenMovies()
    {
        movieRef.orderBy("movieCount", Query.Direction.DESCENDING).limit(7)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                        ArrayList<MovieModel> movieModels = new ArrayList<>();
                        ArrayList<String> movieIds = new ArrayList<>();
                        for (DocumentSnapshot snapshot: queryDocumentSnapshots)
                        {
                            movieModels.add(snapshot.toObject(MovieModel.class));
                            movieIds.add(snapshot.getId());
                        }
                        MovieAdapter adapter = new MovieAdapter(movieModels,context,fm,movieIds);
                        MovieFragment.list.setAdapter(adapter);
                        MovieFragment.list.scrollToPosition(movieModels.size()/2);
                    }
                });
    }

    public void getTopTenSeries()
    {
        seriesRef.orderBy("seriesCount", Query.Direction.DESCENDING).limit(10)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                        ArrayList<SeriesModel> seriesModels = new ArrayList<>();
                        ArrayList<String> seriesIds = new ArrayList<>();
                        for (DocumentSnapshot snapshot: queryDocumentSnapshots)
                        {
                            seriesIds.add(snapshot.getId());
                            seriesModels.add(snapshot.toObject(SeriesModel.class));
                        }
                        SeriesAdapter adapter = new SeriesAdapter(seriesModels,context,fm,seriesIds);
                        SeriesFragment.list.setAdapter(adapter);
                        SeriesFragment.list.scrollToPosition(seriesModels.size()/2);
                    }
                });
    }

    public void updateSeriesCount (SeriesModel model,String id)
    {
        model.seriesCount+=1;
        seriesRef.document(id).set(model);
    }
    public void updateMoviesCount (MovieModel model,String id)
    {
        model.movieCount+=1;
        movieRef.document(id).set(model);
    }


    public void getEpBySeriesName (String seriesName)
    {
        episodeRef.whereEqualTo("episodeSeries",seriesName)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                        ArrayList<EpisodeModel> models = new ArrayList<>();
                        for (DocumentSnapshot snapshot : queryDocumentSnapshots)
                        {
                            models.add(snapshot.toObject(EpisodeModel.class));
                        }
                        EpisodeAdapter adapter = new EpisodeAdapter(models,context);
                        SeiresDetailFragment.epCount.setImageBitmap(SeiresDetailFragment.textAsBitmap(models.size()+"", 35, Color.WHITE));

                        LinearLayoutManager lm = new LinearLayoutManager(context,RecyclerView.VERTICAL,false);
                        SeiresDetailFragment.list.setAdapter(adapter);
                        SeiresDetailFragment.list.setLayoutManager(lm);
                    }
                });

    }

    public void getMovieReview (String movieName)
    {
        movieRef.whereEqualTo("movieName",movieName)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                        ArrayList<MovieModel> movieModels = new ArrayList<>();
                        for (DocumentSnapshot snapshot: queryDocumentSnapshots)
                        {
                            movieModels.add(snapshot.toObject(MovieModel.class));
                        }
                    }
                });
    }


    public void getAllMoviewsForSearchFragment() {
        movieRef.orderBy("createdArt", Query.Direction.DESCENDING).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                ArrayList<MovieModel> movieModels = new ArrayList<>();
                ArrayList<String > movieIds = new ArrayList<>();
                for (DocumentSnapshot snapshot: queryDocumentSnapshots)
                {
                    movieModels.add(snapshot.toObject(MovieModel.class));
                    movieIds.add(snapshot.getId());
                }
                MovieAdapter adapter = new MovieAdapter(movieModels,context,fm,movieIds);
                SearchFragment.allMovie.setAdapter(adapter);
                LinearLayoutManager lm = new LinearLayoutManager(context,RecyclerView.HORIZONTAL,false);
                SearchFragment.allMovie.setLayoutManager(lm);
                SearchFragment.txtallmovie.setText("All Movies : ("+ movieModels.size()+")");

            }
        });
        seriesRef.orderBy("createdArt", Query.Direction.DESCENDING).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                ArrayList<SeriesModel> seriesModels = new ArrayList<>();
                ArrayList<String> seriesIds = new ArrayList<>();
                for (DocumentSnapshot snapshot: queryDocumentSnapshots)
                {
                    seriesIds.add(snapshot.getId());
                    seriesModels.add(snapshot.toObject(SeriesModel.class));
                }
                SeriesAdapter adapter = new SeriesAdapter(seriesModels,context,fm,seriesIds);
                SearchFragment.allSeries.setAdapter(adapter);
                LinearLayoutManager lm = new LinearLayoutManager(context,RecyclerView.HORIZONTAL,false);
                SearchFragment.allSeries.setLayoutManager(lm);
                SearchFragment.txtallseries.setText("All Series ("+ seriesModels.size()+")");

            }
        });
    }

    public void getAllMoviewsForSearchFragmentByQuery(final String query) {
        movieRef.orderBy("movieName")
                .startAt(query)
                .endAt(query+"\uf8ff").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                ArrayList<MovieModel> movieModels = new ArrayList<>();
                ArrayList<String > movieIds = new ArrayList<>();
                for (DocumentSnapshot snapshot: queryDocumentSnapshots)
                {
                    movieModels.add(snapshot.toObject(MovieModel.class));
                    movieIds.add(snapshot.getId());
                }
                MovieAdapter adapter = new MovieAdapter(movieModels,context,fm,movieIds);
                SearchFragment.allMovie.setAdapter(adapter);
                LinearLayoutManager lm = new LinearLayoutManager(context,RecyclerView.HORIZONTAL,false);
                SearchFragment.allMovie.setLayoutManager(lm);
                SearchFragment.txtallmovie.setText("Search Movies : "+query+" ("+ movieModels.size()+")");

            }
        });
        seriesRef.orderBy("seriesName")
                .startAt(query)
                .endAt(query+"\uf8ff").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                ArrayList<SeriesModel> seriesModels = new ArrayList<>();
                ArrayList<String> seriesIds = new ArrayList<>();
                for (DocumentSnapshot snapshot: queryDocumentSnapshots)
                {
                    seriesIds.add(snapshot.getId());
                    seriesModels.add(snapshot.toObject(SeriesModel.class));
                }
                SeriesAdapter adapter = new SeriesAdapter(seriesModels,context,fm,seriesIds);
                SearchFragment.allSeries.setAdapter(adapter);
                LinearLayoutManager lm = new LinearLayoutManager(context,RecyclerView.HORIZONTAL,false);
                SearchFragment.allSeries.setLayoutManager(lm);
                SearchFragment.txtallseries.setText("Search Series :"+query+" ("+ seriesModels.size()+")");

            }
        });
    }
}

