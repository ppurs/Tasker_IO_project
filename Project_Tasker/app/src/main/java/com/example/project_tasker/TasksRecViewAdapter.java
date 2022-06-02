package com.example.project_tasker;

import static androidx.appcompat.content.res.AppCompatResources.getColorStateList;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;

import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;

import java.io.IOException;
import java.util.ArrayList;

public class TasksRecViewAdapter extends RecyclerView.Adapter<TasksRecViewAdapter.ViewHolder>
{
    private ArrayList<Task> tasks = new ArrayList<>();
    private Context context;
    private int parentProjectIndex;
    private int parentCategoryIndex;
    private int parentCardIndex;
    private Task currTask;
    private int taskIndex;
    private MemoryManager memoryManager;
    private TasksActivity activity;

    public void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
        notifyDataSetChanged();
    }

    public TasksRecViewAdapter(Context context, int parentProjectIndex, int parentCategoryIndex, int parentCardIndex, TasksActivity activity )
    {
        this.context = context;
        this.parentProjectIndex = parentProjectIndex;
        this.parentCategoryIndex = parentCategoryIndex;
        this.parentCardIndex = parentCardIndex;
        this.activity = activity;
        memoryManager = new MemoryManager();
    }

    @NonNull
    @Override
    public TasksRecViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType ) {
        View view = LayoutInflater.from( parent.getContext() ).inflate( R.layout.tasks_list_item, parent, false );

        TasksRecViewAdapter.ViewHolder holder = new TasksRecViewAdapter.ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull TasksRecViewAdapter.ViewHolder holder, int position)
    {
        holder.txtTaskName.setText(tasks.get( position ).getName());
        holder.checkBoxTaskDone.setChecked(tasks.get( position ).getStatus());
        holder.checkBoxTaskDone.setButtonTintList(ColorStateList.valueOf(MainActivity.app.projects.get(parentProjectIndex).categories.get(parentCategoryIndex).getColor()));

        taskIndex = tasks.indexOf( tasks.get(position) );

        holder.tasksListItemParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currTask = tasks.get(tasks.indexOf(tasks.get(position)));
                activity.showTaskDetails( TasksActivity.getRecViewTasks(), position );
            }
        });

        holder.checkBoxTaskDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tasks.get( position ).changeStatus();

                try {
                    memoryManager.saveDataToInternalStorage(context);
                } catch (IOException e) {
                    e.printStackTrace();
                }
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
        private CheckBox checkBoxTaskDone;
        private MaterialCardView tasksListItemParent;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtTaskName = itemView.findViewById(R.id.txtTaskName);
            checkBoxTaskDone = itemView.findViewById(R.id.checkBoxTaskDone);
            tasksListItemParent = itemView.findViewById(R.id.tasksListItemParent);
        }
    }
}
