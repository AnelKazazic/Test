package com.media.nsofttask.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.media.nsofttask.model.DetailsModel;
import com.media.nsofttask.R;
import com.media.nsofttask.DetailsActivity;

import java.util.List;

public class DetailsAdapter extends RecyclerView.Adapter<DetailsAdapter.DetailsViewHolder> {

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
    public static final String KEY_UPDATED = "updated";
    public static final String KEY_HTML = "html";

    private final List<DetailsModel> detailsModels;
    public Context context;

    public DetailsAdapter(List<DetailsModel> detailsModels, Context context) {
        this.detailsModels = detailsModels;
        this.context = context;
    }


    @NonNull
    @Override
    public DetailsAdapter.DetailsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.data_main, parent, false);
        return new DetailsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DetailsAdapter.DetailsViewHolder holder, int position) {

        final DetailsModel model = detailsModels.get(position);

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(view.getContext(), DetailsActivity.class);
                intent.putExtra(KEY_URL,model.getAvatarurl());
                intent.putExtra(KEY_OWNER,model.getOwner());
                intent.putExtra(KEY_NAME,model.getName());
                intent.putExtra(KEY_DESCRIPTION,model.getDescription());
                intent.putExtra(KEY_STAR,model.getStar());
                intent.putExtra(KEY_FORKS,model.getForks());
                intent.putExtra(KEY_ISSUES,model.getIssues());
                intent.putExtra(KEY_WATCHERS,model.getWatchers());
                intent.putExtra(KEY_BRANCH,model.getBranch());
                intent.putExtra(KEY_CREATED,model.getCreated());
                intent.putExtra(KEY_UPDATED,model.getUpdated());
                intent.putExtra(KEY_HTML,model.getHtml());

                view.getContext().startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return detailsModels.size();
    }

    public static class DetailsViewHolder extends RecyclerView.ViewHolder {

        ImageView avatarurl;
        TextView owner;
        TextView name;
        TextView description;
        LinearLayout linearLayout;
        TextView star;
        TextView forks;
        TextView issues;
        TextView watchers;
        TextView branch;
        TextView created;
        TextView updated;
        ImageView image_html;

        public DetailsViewHolder(@NonNull View itemView) {
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
            branch = itemView.findViewById(R.id.txt_branch);
            created = itemView.findViewById(R.id.txt_created);
            updated = itemView.findViewById(R.id.txt_updated);
            image_html = itemView.findViewById(R.id.image_html);
        }
    }
}
