package com.media.nsofttask.Database;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.media.nsofttask.Model.ConModel;
import com.media.nsofttask.R;

import java.util.List;

public class ConAdapter extends RecyclerView.Adapter<ConAdapter.DataViewHolder> {

    Context context;
    public List<ConModel> conModels;


    public ConAdapter(Context context, List<ConModel> conModels) {
        this.context = context;
        this.conModels = conModels;
    }


    @NonNull
    @Override
    public ConAdapter.DataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_contributors_helper, parent, false);
        return new DataViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ConAdapter.DataViewHolder holder, int position) {

        final ConModel conModel = conModels.get(position);

        Glide.with(context)
                .load(conModel.getAvatarurl())
                .into(holder.contributor_url);

        holder.contributor_name.setText(conModel.getContributor_name());


    }

    @Override
    public int getItemCount() {
        return conModels.size();
    }

    public static class DataViewHolder extends RecyclerView.ViewHolder {

        ImageView contributor_url;
        TextView contributor_name;
        ImageView favorite;


        public DataViewHolder(@NonNull View itemView) {
            super(itemView);

            contributor_url= itemView.findViewById(R.id.contributor_url);
            contributor_name = itemView.findViewById(R.id.contributor_name);
            favorite = itemView.findViewById(R.id.favorite_image);
        }
    }
}
