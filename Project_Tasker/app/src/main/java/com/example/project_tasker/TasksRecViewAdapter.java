package com.example.project_tasker;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;

public class TasksRecViewAdapter extends RecyclerView.Adapter<TasksRecViewAdapter.ViewHolder>
{
    private ArrayList<Task> tasks = new ArrayList<>();
    private Context context;
    private int parentProjectIndex;
    private int parentCategoryIndex;
    private int parentCardIndex;

    public void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
        notifyDataSetChanged();
    }

    public TasksRecViewAdapter(Context context, int parentProjectIndex, int parentCategoryIndexIndex, int parentCardIndex)
    {
        this.context = context;
        this.parentProjectIndex = parentProjectIndex;
        this.parentCategoryIndex = parentCategoryIndex;
        this.parentCardIndex = parentCardIndex;
    }

    @NonNull
    @Override
    public TasksRecViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from( parent.getContext() ).inflate( R.layout.tasks_list_item, parent, false );

        TasksRecViewAdapter.ViewHolder holder = new TasksRecViewAdapter.ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull TasksRecViewAdapter.ViewHolder holder, int position) {
        holder.txtTaskName.setText( tasks.get( position ).getName() );

        holder.tasksListItemParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO Tu chyba ma wejsc dialog z opisem, smietnikiem i kredka
                Intent intent = new Intent( context, TaskActivity.class);
                intent.putExtra( "parentProjectIndex", parentProjectIndex );
                intent.putExtra( "parentCategoryIndex", parentCategoryIndex );
                intent.putExtra( "parentCardIndex", parentCardIndex );
                intent.putExtra( "parentTaskIndex", tasks.indexOf( tasks.get( position ) ) );
                context.startActivity( intent );
            }
        });
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder
    {
        private TextView txtTaskName;
        private TextView txtTaskDescription;
        private MaterialCardView tasksListItemParent;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtTaskName = itemView.findViewById(R.id.txtTaskName);
            tasksListItemParent = itemView.findViewById(R.id.tasksListItemParent);
        }
    }
}
