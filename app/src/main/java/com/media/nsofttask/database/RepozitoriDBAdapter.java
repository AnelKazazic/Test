package com.media.nsofttask.database;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.media.nsofttask.model.RepozitoriDBModel;
import com.media.nsofttask.R;
import java.util.List;

public class RepozitoriDBAdapter extends RecyclerView.Adapter<RepozitoriDBAdapter.DataViewHolder> {

    Context context;
    public List<RepozitoriDBModel> favoriteModels;

    public RepozitoriDBAdapter(Context context, List<RepozitoriDBModel> favoriteModels){

        this.context = context;
        this.favoriteModels = favoriteModels;
    }

    @NonNull
    @Override
    public RepozitoriDBAdapter.DataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_favorite_helper, parent, false);
        return new DataViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull RepozitoriDBAdapter.DataViewHolder holder, int position) {

        final RepozitoriDBModel favoriteModel = favoriteModels.get(position);

        Glide.with(context)
                .load(favoriteModel.getAvatarurl())
                .into(holder.avatarurl);
        holder.owner.setText(favoriteModel.getOwner());
        holder.name.setText(favoriteModel.getName());
        holder.description.setText(favoriteModel.getDescription());
        holder.star.setText(favoriteModel.getStar());
        holder.forks.setText(favoriteModel.getForks());
        holder.issues.setText(favoriteModel.getIssues());
        holder.watchers.setText(favoriteModel.getWatchers());
    }

    @Override
    public int getItemCount() {
        return favoriteModels.size();
    }

    public static class DataViewHolder extends RecyclerView.ViewHolder {

        ImageView avatarurl;
        ImageView favorite;
        TextView owner;
        TextView name;
        TextView description;
        TextView star;
        TextView forks;
        TextView issues;
        TextView watchers;

        public DataViewHolder(@NonNull View itemView) {
            super(itemView);

            avatarurl = itemView.findViewById(R.id.image1);
            owner = itemView.findViewById(R.id.txt_login);
            name = itemView.findViewById(R.id.txt_name);
            description = itemView.findViewById(R.id.txt_description);
            star = itemView.findViewById(R.id.txt_star);
            forks = itemView.findViewById(R.id.txt_forks);
            issues = itemView.findViewById(R.id.txt_issues);
            watchers = itemView.findViewById(R.id.txt_watchers);
            favorite = itemView.findViewById(R.id.image6);

        }
    }
}
