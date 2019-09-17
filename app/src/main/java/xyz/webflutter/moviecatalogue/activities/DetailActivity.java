package xyz.webflutter.moviecatalogue.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.Objects;

import xyz.webflutter.moviecatalogue.models.ModelMovie;
import xyz.webflutter.moviecatalogue.R;

public class DetailActivity extends AppCompatActivity {
    public static final String EXTRA_DATA = "movie_catalogue";
    private TextView title;
    private TextView overview;
    private TextView releasedDate;
    private TextView statue;
    private TextView budget;
    private TextView revenue;
    private TextView language;
    private TextView actor1;
    private TextView actor2;
    private TextView actor3;
    private TextView genre;
    private TextView overviewText;
    private TextView detailText;
    private ImageView poster;
    private View detailLayout;
    private CardView overviewLayout;
    private TableLayout tableLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        getItem();
        getView();
        initAnimation();
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
    }

    private void getItem() {
        title = findViewById(R.id.title_film);
        overview = findViewById(R.id.overview_film);
        releasedDate = findViewById(R.id.date_film);
        statue = findViewById(R.id.release_statue);
        budget = findViewById(R.id.budget_film);
        revenue = findViewById(R.id.revenue_film);
        language = findViewById(R.id.language);
        actor1 = findViewById(R.id.actor_1_detail);
        actor2 = findViewById(R.id.actor_2_detail);
        actor3 = findViewById(R.id.actor_3_detail);
        genre = findViewById(R.id.genre);
        poster = findViewById(R.id.poster);
        overviewLayout = findViewById(R.id.overviewLayout);
        detailLayout = findViewById(R.id.detailLayout);
        overviewText = findViewById(R.id.overview);
        detailText = findViewById(R.id.detail_view);
        tableLayout = findViewById(R.id.table_view);
    }

    private void getView() {
        ModelMovie modelMovie = getIntent().getParcelableExtra(EXTRA_DATA);
        String tvTitle = modelMovie.getJudul();
        String tvOverview = modelMovie.getDeskripsi();
        String tvReleased = modelMovie.getTanggalRilis();
        String tvStatue = modelMovie.getStatus();
        String tvBudget = modelMovie.getBudget();
        String tvRevenue = modelMovie.getRevenue();
        String tvLanguage = modelMovie.getBahasa();
        String tvActor1 = modelMovie.getActor1();
        String tvActor2 = modelMovie.getActor2();
        String tvActor3 = modelMovie.getActor3();
        String tvGenre = modelMovie.getGenre();
        String ivPoster = modelMovie.getPoster();

        title.setText(tvTitle);
        overview.setText(tvOverview);
        releasedDate.setText(tvReleased);
        statue.setText(tvStatue);
        budget.setText(tvBudget);
        revenue.setText(tvRevenue);
        language.setText(tvLanguage);
        actor1.setText(tvActor1);
        actor2.setText(tvActor2);
        actor3.setText(tvActor3);
        genre.setText(tvGenre);
        Glide.with(getApplicationContext())
                .load(ivPoster)
                .into(poster);
        Objects.requireNonNull(getSupportActionBar()).setSubtitle(tvTitle);
    }

    private void initAnimation() {
        Animation zoomingAnimation = AnimationUtils.loadAnimation(this, R.anim.zoom_animation);
        Animation swipeUp1 = AnimationUtils.loadAnimation(this, R.anim.swipe_animation1);
        Animation swipeUp2 = AnimationUtils.loadAnimation(this, R.anim.swipe_animation2);

        poster.startAnimation(zoomingAnimation);
        title.startAnimation(swipeUp1);
        overviewText.startAnimation(swipeUp1);
        detailText.startAnimation(swipeUp1);
        overviewLayout.startAnimation(swipeUp2);
        detailLayout.startAnimation(swipeUp2);
        overview.startAnimation(swipeUp2);
        tableLayout.startAnimation(swipeUp2);
    }
}
