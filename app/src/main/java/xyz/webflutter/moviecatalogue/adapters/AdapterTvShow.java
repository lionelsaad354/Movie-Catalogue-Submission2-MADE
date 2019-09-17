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

import xyz.webflutter.moviecatalogue.models.ModelTvShow;
import xyz.webflutter.moviecatalogue.R;

public class AdapterTvShow extends RecyclerView.Adapter<AdapterTvShow.AdapterTvHolder> {
    private final Context context;
    private final ArrayList<ModelTvShow> list;

    private ArrayList<ModelTvShow> getList() {
        return list;
    }

    public AdapterTvShow(ArrayList<ModelTvShow> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public AdapterTvHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_show_tv, parent, false);
        return new AdapterTvHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterTvHolder holder, int position) {
        ModelTvShow modelTvShow = getList().get(position);

        holder.tvTitle.setText(modelTvShow.getTitle());
        holder.tvStatus.setText(modelTvShow.getStatus());
        holder.tvOverview.setText(modelTvShow.getOverview());
        Glide.with(context)
                .load(getList().get(position).getThumbnail())
                .into(holder.thumbnail);

    }

    @Override
    public int getItemCount() {
        return getList().size();
    }

    class AdapterTvHolder extends RecyclerView.ViewHolder {
        private final TextView tvTitle;
        private final TextView tvStatus;
        private final TextView tvOverview;
        private final ImageView thumbnail;

        AdapterTvHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.title_tv_show);
            tvStatus = itemView.findViewById(R.id.status_tv_show);
            tvOverview = itemView.findViewById(R.id.overview_tv_show);
            thumbnail = itemView.findViewById(R.id.thumbnail_tv_show);
        }
    }
}
