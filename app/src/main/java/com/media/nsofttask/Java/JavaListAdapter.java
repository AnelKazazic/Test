package com.media.nsofttask.Java;



import android.content.Context;
import android.content.Intent;
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
import com.bumptech.glide.Glide;
import com.media.nsofttask.ListActivity;
import com.media.nsofttask.Model.DetailsModel;
import com.media.nsofttask.Model.ListModel;
import com.media.nsofttask.R;
import com.media.nsofttask.UserActivity;

import java.util.List;


public class JavaListAdapter extends RecyclerView.Adapter<JavaListAdapter.ListViewHolder>{

    public static final String KEY_URL = "url";
    public static final String KEY_OWNER = "owner";
    public static final String KEY_NAME = "name";
    public static final String KEY_DESCRIPTION = "description";
    public static final String KEY_STAR = "star";
    public static final String KEY_FORKS = "forks";
    public static final String KEY_ISSUES = "issues";
    public static final String KEY_WATCHERS = "watchers";
    public static final String KEY_BRANCH= "branch";
    public static final String KEY_CREATED = "created";
    public static final String KEY_UPDATED= "updated";
    public static final String KEY_HTML= "html";


    private final List<ListModel> listModels;
    private final List<DetailsModel> detailsModels;
    public Context context;

    public JavaListAdapter(List<ListModel> listModels, List<DetailsModel> detailsModels, Context context) {
        this.listModels = listModels;
        this.detailsModels = detailsModels;
        this.context = context;

    }


    @NonNull
    @Override
    public JavaListAdapter.ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.data_main, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull JavaListAdapter.ListViewHolder holder, int position) {

        final ListModel listModel = listModels.get(position);
        ListModel model = listModels.get(position);
        DetailsModel details = detailsModels.get(position);


        Glide.with(context)
                .load(listModel.getAvatarurl())
                .into(holder.avatarurl);


        holder.owner.setText(listModel.getOwner());
        holder.name.setText(listModel.getName());
        holder.description.setText(listModel.getDescription());
        holder.star.setText(listModel.getStar());
        holder.forks.setText(listModel.getForks());
        holder.issues.setText(listModel.getIssues());
        holder.watchers.setText(listModel.getWatchers());



        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(view.getContext(), UserActivity.class);
                intent.putExtra(KEY_URL,model.getAvatarurl());
                intent.putExtra(KEY_OWNER,model.getOwner());
                intent.putExtra(KEY_NAME,model.getName());
                intent.putExtra(KEY_DESCRIPTION,model.getDescription());
                intent.putExtra(KEY_STAR,model.getStar());
                intent.putExtra(KEY_FORKS,model.getForks());
                intent.putExtra(KEY_ISSUES,model.getIssues());
                intent.putExtra(KEY_WATCHERS,model.getWatchers());
                intent.putExtra(KEY_BRANCH,details.getBranch());
                intent.putExtra(KEY_CREATED,details.getCreated());
                intent.putExtra(KEY_UPDATED,details.getUpdated());
                intent.putExtra(KEY_HTML,details.getHtml());
                view.getContext().startActivity(intent);

            }
        });

        if(listModel.getFavorite()) {

            holder.favorite.setImageResource(R.drawable.ic_baseline_favorite_red_24);

        }else {

            holder.favorite.setImageResource(R.drawable.ic_baseline_favorite_border_24);


        }


        holder.favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(!listModel.getFavorite()) {

                    holder.favorite.setImageResource(R.drawable.ic_baseline_favorite_red_24);

                }else {

                    holder.favorite.setImageResource(R.drawable.ic_baseline_favorite_border_24);


                }

                //Intent intent = new Intent(view.getContext(), ListActivity.class);
                //intent.putExtra(KEY_URL,model.getAvatarurl());
                //intent.putExtra(KEY_OWNER,model.getOwner());
                //intent.putExtra(KEY_NAME,model.getName());
                //intent.putExtra(KEY_DESCRIPTION,model.getDescription());
                //intent.putExtra(KEY_STAR,model.getStar());
                //intent.putExtra(KEY_FORKS,model.getForks());
                //intent.putExtra(KEY_ISSUES,model.getIssues());
                //intent.putExtra(KEY_WATCHERS,model.getWatchers());
                //view.getContext().startActivity(intent);


            }
        });


    }

    @Override
    public int getItemCount() {
        return listModels.size();
    }


    public static class ListViewHolder extends RecyclerView.ViewHolder {

        ImageView avatarurl;
        ImageView favorite;
        TextView owner;
        TextView name;
        TextView description;
        LinearLayout linearLayout;
        TextView star;
        TextView forks;
        TextView issues;
        TextView watchers;


        public ListViewHolder(@NonNull View itemView) {
            super(itemView);

            avatarurl= (ImageView) itemView.findViewById(R.id.image1);
            owner = (TextView) itemView.findViewById(R.id.txt_login);
            name = (TextView) itemView.findViewById(R.id.txt_name);
            description = (TextView) itemView.findViewById(R.id.txt_description);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.layout1);
            star = (TextView) itemView.findViewById(R.id.txt_star);
            forks = (TextView) itemView.findViewById(R.id.txt_forks);
            issues = (TextView) itemView.findViewById(R.id.txt_issues);
            watchers = (TextView) itemView.findViewById(R.id.txt_watchers);
            favorite =(ImageView) itemView.findViewById(R.id.image6);


        }
    }
}
