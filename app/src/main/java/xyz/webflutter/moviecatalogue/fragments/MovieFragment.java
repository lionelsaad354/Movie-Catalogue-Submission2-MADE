package xyz.webflutter.moviecatalogue.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import java.util.ArrayList;

import xyz.webflutter.moviecatalogue.ItemClickSupport;
import xyz.webflutter.moviecatalogue.models.ModelMovie;
import xyz.webflutter.moviecatalogue.R;
import xyz.webflutter.moviecatalogue.activities.DetailActivity;
import xyz.webflutter.moviecatalogue.adapters.AdapterMovie;

public class MovieFragment extends Fragment {
    private RecyclerView rvMovie;
    private ArrayList<ModelMovie> listMovie;
    private String[] dataJudul;
    private String[] dataDeskripsi;
    private String[] dataStatus;
    private String[] dataTanggalRilis;
    private String[] dataBudget;
    private String[] dataRevenue;
    private String[] dataBahasa;
    private String[] dataActor1;
    private String[] dataActor2;
    private String[] dataActor3;
    private String[] dataGenre;
    private String[] dataPoster;


    public MovieFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvMovie = view.findViewById(R.id.rv_movie);
        rvMovie.setHasFixedSize(true);
        Animation animation = AnimationUtils.loadAnimation(getActivity(), R.anim.recyclerview_animation);
        rvMovie.startAnimation(animation);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        initArray();
        addMovie();
        AdapterMovie adapter = new AdapterMovie(listMovie, getActivity());
        rvMovie.setLayoutManager(layoutManager);
        rvMovie.setAdapter(adapter);

        clickMovie();

    }

    private void addMovie() {
        listMovie = new ArrayList<>();

        for (int i = 0; i < dataJudul.length; i++) {
            ModelMovie modelMovie = new ModelMovie();
            modelMovie.setJudul(dataJudul[i]);
            modelMovie.setStatus(dataStatus[i]);
            modelMovie.setTanggalRilis(dataTanggalRilis[i]);
            modelMovie.setActor1(dataActor1[i]);
            modelMovie.setActor2(dataActor2[i]);
            modelMovie.setActor3(dataActor3[i]);
            modelMovie.setPoster(dataPoster[i]);
            listMovie.add(modelMovie);
        }
    }

    private void initArray() {
        dataJudul = getResources().getStringArray(R.array.title);
        dataDeskripsi = getResources().getStringArray(R.array.overview);
        dataBahasa = getResources().getStringArray(R.array.language);
        dataStatus = getResources().getStringArray(R.array.statue);
        dataBudget = getResources().getStringArray(R.array.budget);
        dataRevenue = getResources().getStringArray(R.array.revenue);
        dataTanggalRilis = getResources().getStringArray(R.array.release_date);
        dataActor1 = getResources().getStringArray(R.array.array_actor1);
        dataActor2 = getResources().getStringArray(R.array.array_actor2);
        dataActor3 = getResources().getStringArray(R.array.array_actor3);
        dataGenre = getResources().getStringArray(R.array.genre);
        dataPoster = getResources().getStringArray(R.array.array_poster);
    }

    private void clickMovie() {
        ItemClickSupport.addTo(rvMovie).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                Intent intent = new Intent(getActivity(), DetailActivity.class);
                try {
                    ModelMovie modelMovie = new ModelMovie();
                    modelMovie.setJudul(dataJudul[position]);
                    modelMovie.setDeskripsi(dataDeskripsi[position]);
                    modelMovie.setTanggalRilis(dataTanggalRilis[position]);
                    modelMovie.setBahasa(dataBahasa[position]);
                    modelMovie.setBudget(dataBudget[position]);
                    modelMovie.setRevenue(dataRevenue[position]);
                    modelMovie.setStatus(dataStatus[position]);
                    modelMovie.setActor1(dataActor1[position]);
                    modelMovie.setActor2(dataActor2[position]);
                    modelMovie.setActor3(dataActor3[position]);
                    modelMovie.setGenre(dataGenre[position]);
                    modelMovie.setPoster(dataPoster[position]);
                    intent.putExtra(DetailActivity.EXTRA_DATA, modelMovie);
                    startActivity(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
