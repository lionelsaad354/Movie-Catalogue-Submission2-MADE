package xyz.webflutter.moviecatalogue.fragments;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;

import java.util.ArrayList;
import java.util.Objects;

import xyz.webflutter.moviecatalogue.ItemClickSupport;
import xyz.webflutter.moviecatalogue.activities.DetailTvShowActivity;
import xyz.webflutter.moviecatalogue.models.ModelTvShow;
import xyz.webflutter.moviecatalogue.R;
import xyz.webflutter.moviecatalogue.adapters.AdapterTvShow;

public class TvShowFragment extends Fragment {
    private RecyclerView rvTvShow;
    private ArrayList<ModelTvShow> listTvShow;
    private RecyclerView.LayoutManager layoutManager;
    private String[] dataTitle;
    private String[] dataOverview;
    private String[] dataStatus;
    private String[] dataNetwork;
    private String[] dataLanguages;
    private String[] dataRuntime;
    private String[] dataGenre;
    private String[] dataThumbnail;


    public TvShowFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tv_show, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvTvShow = view.findViewById(R.id.rv_tv_show);
        rvTvShow.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        initArrayTvShow();
        addTvShow();

        AdapterTvShow adapterTvShow = new AdapterTvShow(listTvShow, getActivity());
        rvTvShow.setLayoutManager(layoutManager);
        rvTvShow.setAdapter(adapterTvShow);

        clickTvShow();

        final SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(rvTvShow);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                RecyclerView.ViewHolder viewHolder = rvTvShow.findViewHolderForAdapterPosition(0);
                assert viewHolder != null;
                ConstraintLayout constraintLayout = viewHolder.itemView.findViewById(R.id.constraint_layout);
                constraintLayout.animate().scaleY(1).scaleX(1).setDuration(300).setInterpolator(new AccelerateInterpolator()).start();
            }
        }, 100);

        rvTvShow.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                View view = snapHelper.findSnapView(layoutManager);
                int position = layoutManager.getPosition(Objects.requireNonNull(view));

                RecyclerView.ViewHolder viewHolder = rvTvShow.findViewHolderForAdapterPosition(position);
                ConstraintLayout constraintLayout = Objects.requireNonNull(viewHolder).itemView.findViewById(R.id.constraint_layout);

                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    constraintLayout.animate().setDuration(350).scaleX(1).scaleY(1).setInterpolator(new AccelerateInterpolator()).start();
                } else {
                    constraintLayout.animate().setDuration(350).scaleX(0.75f).scaleY(0.75f).setInterpolator(new AccelerateInterpolator()).start();
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });

    }

    private void addTvShow() {
        listTvShow = new ArrayList<>();

        for (int i = 0; i < dataTitle.length; i++) {
            ModelTvShow modelTvShow = new ModelTvShow();
            modelTvShow.setTitle(dataTitle[i]);
            modelTvShow.setStatus(dataStatus[i]);
            modelTvShow.setOverview(dataOverview[i]);
            modelTvShow.setThumbnail(dataThumbnail[i]);
            listTvShow.add(modelTvShow);
        }
    }

    private void initArrayTvShow() {
        dataTitle = getResources().getStringArray(R.array.title_tv_show);
        dataOverview = getResources().getStringArray(R.array.overview_tv_show);
        dataStatus = getResources().getStringArray(R.array.status_tv_show);
        dataNetwork = getResources().getStringArray(R.array.network);
        dataLanguages = getResources().getStringArray(R.array.languages_tv_show);
        dataRuntime = getResources().getStringArray(R.array.runtime);
        dataGenre = getResources().getStringArray(R.array.genre_tv_show);
        dataThumbnail = getResources().getStringArray(R.array.thumb_tv_show);
    }

    private void clickTvShow() {
        ItemClickSupport.addTo(rvTvShow).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                Intent intent = new Intent(getActivity(), DetailTvShowActivity.class);
                try {
                    ModelTvShow modelTvShow = new ModelTvShow();
                    modelTvShow.setTitle(dataTitle[position]);
                    modelTvShow.setOverview(dataOverview[position]);
                    modelTvShow.setStatus(dataStatus[position]);
                    modelTvShow.setNetwork(dataNetwork[position]);
                    modelTvShow.setLanguages(dataLanguages[position]);
                    modelTvShow.setRuntime(dataRuntime[position]);
                    modelTvShow.setGenre(dataGenre[position]);
                    modelTvShow.setThumbnail(dataThumbnail[position]);
                    intent.putExtra(DetailTvShowActivity.EXTRA_TV_SHOW, modelTvShow);
                    startActivity(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
