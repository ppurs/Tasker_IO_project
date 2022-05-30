package com.example.project_tasker;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.CheckBox;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;

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

    public void showTaskDetails( View view, TextView name, TextView description) {
        final Dialog dialog = new Dialog( context );
        dialog.setContentView(R.layout.dialog_task_details);
        dialog.getWindow().setLayout( ((Activity) context).getWindow().peekDecorView().getWidth(), ViewGroup.LayoutParams.WRAP_CONTENT );

        /*Spinner spinner = (Spinner) ((Activity) context).findViewById( R.id.spinnerPriority );
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(dialog.getContext(),
                R.array.priority_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);*/

        TextView textName = (TextView) dialog.findViewById(R.id.txtTitleName);
        textName.setText( name.getText() );
        TextView textDescription = (TextView) dialog.findViewById(R.id.txtDescription );
        textDescription.setText( description.getText() );

        ImageButton editButton = (ImageButton) dialog.findViewById(R.id.btnEditTask );

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* Intent intent = new Intent( dialog.getContext(), EditTaskctivity.class );
                intent.putExtra( "parentProjectIndex", parentProjectIndex );
                intent.putExtra( "parentCategoryIndex", parentCategoryIndex );
                intent.putExtra( "parentCardIndex", parentCardIndex );
                //intent.putExtra( "parentTaskIndex", ) //PRZEKAZAC TASKA
                startActivity( intent );*/
            }
        });

        ImageButton deleteButton = (ImageButton) dialog.findViewById(R.id.btnDeleteTask );

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //usuwanie taska
            }
        });

        dialog.show();
    }

    @Override
    public void onBindViewHolder(@NonNull TasksRecViewAdapter.ViewHolder holder, int position) {
        Task currTask = MainActivity.app.projects.get(parentProjectIndex).categories.get(parentCategoryIndex).cards.get(parentCardIndex).tasks.get(tasks.indexOf(tasks.get(position)));

        holder.txtTaskName.setText( tasks.get( position ).getName() );
        holder.checkBoxTaskDone.setChecked(currTask.getStatus());

        holder.tasksListItemParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showTaskDetails( TasksActivity.getRecViewTasks(), holder.txtTaskName, holder.txtTaskDescription );
            }
        });

        holder.checkBoxTaskDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currTask.changeStatus();
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
