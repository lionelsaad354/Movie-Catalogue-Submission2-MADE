package xyz.webflutter.moviecatalogue.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.Objects;

import xyz.webflutter.moviecatalogue.R;
import xyz.webflutter.moviecatalogue.models.ModelTvShow;

public class DetailTvShowActivity extends AppCompatActivity {
    public static final String EXTRA_TV_SHOW = "tv_show_catalogue";
    private TextView tvTitle;
    private TextView tvOverview;
    private TextView tvStatus;
    private TextView tvLanguages;
    private TextView tvRuntime;
    private TextView tvGenre;
    private TextView tvOverviewText;
    private TextView detailViewTvShow;
    private String title;
    private ImageView ivNetwork;
    private ImageView ivThumbnail;
    private CardView cvOverviewTvShow;
    private CardView cvDetailTvShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_tv_show);
        initView();
        getData();
        Objects.requireNonNull(getSupportActionBar()).setTitle("Detail");
        getSupportActionBar().setSubtitle(title);
        initializeAnimation();
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
    }

    private void initView() {
        tvTitle = findViewById(R.id.title_tv_show_detail);
        tvOverview = findViewById(R.id.overview_tv_show_detail);
        tvStatus = findViewById(R.id.status_tv_show_detail);
        tvLanguages = findViewById(R.id.languages_tv_show_detail);
        tvRuntime = findViewById(R.id.runtime_tv_show_detail);
        tvGenre = findViewById(R.id.genre_tv_show_detail);
        tvOverviewText = findViewById(R.id.overview_tv_show);
        detailViewTvShow = findViewById(R.id.detail_view_tv_show);
        ivNetwork = findViewById(R.id.iv_show_network);
        ivThumbnail = findViewById(R.id.thumbnail_tv_show_detail);
        cvOverviewTvShow = findViewById(R.id.cv_overview_tv_show);
        cvDetailTvShow = findViewById(R.id.cv_detail_tv_show);
    }

    private void getData() {
        ModelTvShow modelTvShow = getIntent().getParcelableExtra(EXTRA_TV_SHOW);
        title = modelTvShow.getTitle();
        String overview = modelTvShow.getOverview();
        String status = modelTvShow.getStatus();
        String languages = modelTvShow.getLanguages();
        String runtime = modelTvShow.getRuntime();
        String genre = modelTvShow.getGenre();
        String network = modelTvShow.getNetwork();
        String thumbnail = modelTvShow.getThumbnail();

        tvTitle.setText(title);
        tvOverview.setText(overview);
        tvStatus.setText(status);
        tvLanguages.setText(languages);
        tvRuntime.setText(runtime);
        tvGenre.setText(genre);
        Glide.with(getApplicationContext())
                .load(network)
                .into(ivNetwork);
        Glide.with(getApplicationContext())
                .load(thumbnail)
                .into(ivThumbnail);
    }

    private void initializeAnimation() {
        Animation zoomingAnimation = AnimationUtils.loadAnimation(this, R.anim.zoom_animation);
        Animation swipeUp1 = AnimationUtils.loadAnimation(this, R.anim.swipe_animation1);
        Animation swipeUp2 = AnimationUtils.loadAnimation(this, R.anim.swipe_animation2);

        ivThumbnail.startAnimation(zoomingAnimation);
        ivNetwork.startAnimation(zoomingAnimation);
        tvTitle.startAnimation(swipeUp1);
        tvOverviewText.startAnimation(swipeUp1);
        detailViewTvShow.startAnimation(swipeUp1);
        cvOverviewTvShow.startAnimation(swipeUp2);
        cvDetailTvShow.startAnimation(swipeUp2);
    }
}
