package com.example.project_tasker;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;

import org.parceler.Parcels;

import java.io.Serializable;
import java.util.ArrayList;

public class ProjectsRecViewAdapter extends RecyclerView.Adapter<ProjectsRecViewAdapter.ViewHolder>
{
    private ArrayList<Project> projects = new ArrayList<>();
    private Context context;

    public void setProjects(ArrayList<Project> projects) {
        this.projects = projects;
        notifyDataSetChanged();
    }

    public ProjectsRecViewAdapter(Context context)
    {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from( parent.getContext() ).inflate( R.layout.projects_list_item, parent, false );

        ProjectsRecViewAdapter.ViewHolder holder = new ProjectsRecViewAdapter.ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtProjectName.setText( projects.get( position ).getName() );
        holder.txtProjectDescription.setText( projects.get( position ).getDescription() );

        holder.projectsListItemParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( context, CategoriesActivity.class);
                intent.putExtra( "parentProjectIndex", projects.indexOf( projects.get( position ) ) );
                context.startActivity( intent );
            }
        });
    }

    @Override
    public int getItemCount() {
        return projects.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder
    {
        private TextView txtProjectName;
        private TextView txtProjectDescription;
        private MaterialCardView projectsListItemParent;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtProjectName = itemView.findViewById(R.id.txtProjectName);
            txtProjectDescription = itemView.findViewById(R.id.txtProjectDescription);
            projectsListItemParent = itemView.findViewById(R.id.projectsListItemParent);
        }
    }
}
