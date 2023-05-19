package com.media.nsofttask.Database;

import android.content.Context;
import android.graphics.drawable.Icon;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.media.nsofttask.R;

import java.util.ArrayList;

public class DatabaseAdapter extends RecyclerView.Adapter<DatabaseAdapter.DataViewHolder> {

    Context context;
    ArrayList avatar_url, owner, name, description, star, forks, issues, watchers;

    public DatabaseAdapter (Context context,ArrayList avatar_url, ArrayList owner,
                            ArrayList name, ArrayList description,   ArrayList star,
                            ArrayList forks,  ArrayList issues, ArrayList watchers){

        this.context=context;
        this.avatar_url=avatar_url;
        this.owner=owner;
        this.name = name;
        this.description=description;
        this.star=star;
        this.forks=forks;
        this.issues=issues;
        this.watchers=watchers;
    }

    @NonNull
    @Override
    public DatabaseAdapter.DataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_user_data, parent, false);
        return new DataViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull DatabaseAdapter.DataViewHolder holder, int position) {



        holder.avatarurl.setImageIcon(Icon.createWithContentUri(String.valueOf(avatar_url.get(position))));
        holder.owner.setText(String.valueOf(owner.get(position)));
        holder.name.setText(String.valueOf(name.get(position)));
        holder.description.setText(String.valueOf(description.get(position)));
        holder.star.setText(String.valueOf(star.get(position)));
        holder.forks.setText(String.valueOf(forks.get(position)));
        holder.issues.setText(String.valueOf(issues.get(position)));
        holder.watchers.setText(String.valueOf(watchers.get(position)));

    }

    @Override
    public int getItemCount() {
        return avatar_url.size();
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

            avatarurl= (ImageView) itemView.findViewById(R.id.image1);
            owner = (TextView) itemView.findViewById(R.id.txt_login);
            name = (TextView) itemView.findViewById(R.id.txt_name);
            description = (TextView) itemView.findViewById(R.id.txt_description);
            star = (TextView) itemView.findViewById(R.id.txt_star);
            forks = (TextView) itemView.findViewById(R.id.txt_forks);
            issues = (TextView) itemView.findViewById(R.id.txt_issues);
            watchers = (TextView) itemView.findViewById(R.id.txt_watchers);
            favorite =(ImageView) itemView.findViewById(R.id.image6);

        }
    }
}
