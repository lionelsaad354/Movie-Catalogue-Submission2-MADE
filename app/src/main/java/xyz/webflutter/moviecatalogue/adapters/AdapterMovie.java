package xyz.webflutter.moviecatalogue.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import xyz.webflutter.moviecatalogue.models.ModelMovie;
import xyz.webflutter.moviecatalogue.R;

public class AdapterMovie extends RecyclerView.Adapter<AdapterMovie.AdapterViewHolder> {
    private final Context context;

    private final ArrayList<ModelMovie> list;

    private ArrayList<ModelMovie> getList() {
        return list;
    }

    public AdapterMovie(ArrayList<ModelMovie> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public AdapterMovie.AdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_show_movie, parent, false);
        return new AdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterMovie.AdapterViewHolder holder, int position) {
        ModelMovie modelMovie = getList().get(position);

        holder.tvTitle.setText(modelMovie.getJudul());
        holder.tvReleased.setText(modelMovie.getStatus());
        holder.tvReleaseDate.setText(modelMovie.getTanggalRilis());
        holder.tvActor1.setText(modelMovie.getActor1());
        holder.tvActor2.setText(modelMovie.getActor2());
        holder.tvActor3.setText(modelMovie.getActor3());

        Glide.with(context)
                .load(getList().get(position).getPoster())
                .into(holder.ivPoster);
    }

    @Override
    public int getItemCount() {
        return getList().size();
    }

    class AdapterViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvTitle;
        private final TextView tvReleased;
        private final TextView tvReleaseDate;
        private final TextView tvActor1;
        private final TextView tvActor2;
        private final TextView tvActor3;
        private final ImageView ivPoster;

        AdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.title_film_home);
            tvReleased = itemView.findViewById(R.id.statue_film_home);
            tvReleaseDate = itemView.findViewById(R.id.release_film_home);
            tvActor1 = itemView.findViewById(R.id.actor_1);
            tvActor2 = itemView.findViewById(R.id.actor_2);
            tvActor3 = itemView.findViewById(R.id.actor_3);
            ivPoster = itemView.findViewById(R.id.poster_home);
        }
    }
}
