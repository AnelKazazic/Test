package com.media.nsofttask.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.media.nsofttask.database.RepozitoriDBHelper;
import com.media.nsofttask.model.DetailsModel;
import com.media.nsofttask.model.RepozitoriModel;
import com.media.nsofttask.R;
import com.media.nsofttask.DetailsActivity;
import java.util.ArrayList;
import java.util.List;


public class RepozitoriAdapter extends RecyclerView.Adapter<RepozitoriAdapter.ListViewHolder> implements Filterable {

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
    private final List<RepozitoriModel> repozitoriModels;
    private final List<DetailsModel> detailsModels;
    private List<RepozitoriModel> listListFiltered;
    public Context context;
    RepozitoriDBHelper repozitoriDBHelper;


    public RepozitoriAdapter(List<RepozitoriModel> repozitoriModels, List<DetailsModel> detailsModels, Context context,
                             List<RepozitoriModel> listListFiltered)
    {
        this.repozitoriModels = repozitoriModels;
        this.detailsModels = detailsModels;
        this.context = context;
        this.listListFiltered=listListFiltered;

    }



    @NonNull
    @Override
    public RepozitoriAdapter.ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.data_main, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RepozitoriAdapter.ListViewHolder holder, int position) {

        final RepozitoriModel repozitoriModel = repozitoriModels.get(position);
        RepozitoriModel model = repozitoriModels.get(position);
        DetailsModel details = detailsModels.get(position);
        repozitoriDBHelper = new RepozitoriDBHelper(context.getApplicationContext());


        Glide.with(context)
                .load(repozitoriModel.getAvatarurl())
                .into(holder.avatarurl);


        holder.owner.setText(repozitoriModel.getOwner());
        holder.name.setText(repozitoriModel.getName());
        holder.description.setText(repozitoriModel.getDescription());
        holder.star.setText(repozitoriModel.getStar());
        holder.forks.setText(repozitoriModel.getForks());
        holder.issues.setText(repozitoriModel.getIssues());
        holder.watchers.setText(repozitoriModel.getWatchers());

        if(repozitoriModel.getFavorite()){

            holder.favorite.setImageResource(R.drawable.ic_baseline_favorite_border_24);


        }


        holder.constraintLayout.setOnClickListener(view -> {

            Intent intent = new Intent(view.getContext(), DetailsActivity.class);
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

        });

        if(repozitoriModel.getFavorite()) {

            holder.favorite.setImageResource(R.drawable.ic_baseline_favorite_red_24);

        }else {

            holder.favorite.setImageResource(R.drawable.ic_baseline_favorite_border_24);


        }




        holder.favorite.setOnClickListener(view -> {

            if(repozitoriModel.getFavorite()) {

                holder.favorite.setImageResource(R.drawable.ic_baseline_favorite_red_24);


            }else {


                holder.favorite.setImageResource(R.drawable.ic_baseline_favorite_border_24);

            }

            repozitoriDBHelper = new RepozitoriDBHelper(context.getApplicationContext());

            final String avatar_url = repozitoriModel.getAvatarurl();
            final String owner = repozitoriModel.getOwner();
            final String name = repozitoriModel.getName();
            final String description = repozitoriModel.getDescription();
            final String star = repozitoriModel.getStar();
            final String forks = repozitoriModel.getForks();
            final String issues = repozitoriModel.getIssues();
            final String watchers = repozitoriModel.getWatchers();


            repozitoriDBHelper.InsertData(avatar_url, owner, name, description, star,
                    forks, issues, watchers);


        });


    }

    @Override
    public int getItemCount() {
        return repozitoriModels.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {

            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();

                if (charString.isEmpty()) {
                    listListFiltered = repozitoriModels;
                } else {
                    List<RepozitoriModel> filteredList = new ArrayList<>();
                    for (RepozitoriModel list : repozitoriModels) {
                        if (list.getName().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(list);
                        }
                    }
                    listListFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = listListFiltered;


                return filterResults;


            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {

                listListFiltered = (ArrayList<RepozitoriModel>) filterResults.values;


            }
        };


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
        ConstraintLayout constraintLayout;


        public ListViewHolder(@NonNull View itemView) {
            super(itemView);

            avatarurl= itemView.findViewById(R.id.image1);
            owner = itemView.findViewById(R.id.txt_login);
            name = itemView.findViewById(R.id.txt_name);
            description = itemView.findViewById(R.id.txt_description);
            linearLayout = itemView.findViewById(R.id.layout1);
            star = itemView.findViewById(R.id.txt_star);
            forks = itemView.findViewById(R.id.txt_forks);
            issues = itemView.findViewById(R.id.txt_issues);
            watchers = itemView.findViewById(R.id.txt_watchers);
            favorite = itemView.findViewById(R.id.image6);
            constraintLayout =itemView.findViewById(R.id.constraintLayout);


        }


    }


}
