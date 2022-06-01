package com.example.project_tasker;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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

    public void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
        notifyDataSetChanged();
    }

    public TasksRecViewAdapter(Context context, int parentProjectIndex, int parentCategoryIndex, int parentCardIndex)
    {
        this.context = context;
        this.parentProjectIndex = parentProjectIndex;
        this.parentCategoryIndex = parentCategoryIndex;
        this.parentCardIndex = parentCardIndex;
        memoryManager = new MemoryManager();
    }

    @NonNull
    @Override
    public TasksRecViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType ) {
        View view = LayoutInflater.from( parent.getContext() ).inflate( R.layout.tasks_list_item, parent, false );

        TasksRecViewAdapter.ViewHolder holder = new TasksRecViewAdapter.ViewHolder(view);

        return holder;
    }

    public void showAlertDialogDelete(View view, Dialog prevDialog) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context );
        builder.setTitle("Delete task");
        builder.setMessage("Are you sure you want to delete this task?");

        builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                MainActivity.app.projects.get(parentProjectIndex).categories.get(parentCategoryIndex).cards.get(parentCardIndex).deleteTask( taskIndex );
                dialog.cancel();
                prevDialog.cancel();

                try {
                    memoryManager.saveDataToInternalStorage(context);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                TasksActivity.getRecViewTasks().getAdapter().notifyDataSetChanged();

            }
        });
        builder.setNegativeButton("Cancel", null);

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void showTaskDetails( View view ) {
        final Dialog dialog = new Dialog( context );
        dialog.setContentView(R.layout.dialog_task_details);
        dialog.getWindow().setLayout( ((Activity) context).getWindow().peekDecorView().getWidth(), ViewGroup.LayoutParams.WRAP_CONTENT );

        Spinner spinner = (Spinner) dialog.findViewById( R.id.spinnerPriority );
        Integer[] priorities = { 1, 2, 3, 4, 5};
        ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(dialog.getContext(), R.layout.custom_dropdown_list, priorities );
        spinner.setSelection( currTask.getPriority() - 1  );

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                currTask.setPriority((int) (spinner.getSelectedItem() ));
                setid();

                try {
                    memoryManager.saveDataToInternalStorage(context);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            private void setid() {
                spinner.setSelection( currTask.getPriority() - 1  );


            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                spinner.setSelection( currTask.getPriority() - 1 );
            }
        });

        spinner.setAdapter(adapter);
        spinner.setSelection( currTask.getPriority() - 1 );

        TextView textName = (TextView) dialog.findViewById(R.id.txtTitleName);
        textName.setText( currTask.getName() );
        TextView textDescription = (TextView) dialog.findViewById(R.id.txtDescription );
        textDescription.setText( currTask.getDescription() );

        ImageButton editButton = (ImageButton) dialog.findViewById(R.id.btnEditTask );

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( dialog.getContext(), EditTaskActivity.class );
                intent.putExtra( "parentProjectIndex", parentProjectIndex );
                intent.putExtra( "parentCategoryIndex", parentCategoryIndex );
                intent.putExtra( "parentCardIndex", parentCardIndex );
                intent.putExtra( "parentTaskIndex", taskIndex );
                dialog.getContext().startActivity( intent );
                dialog.cancel();
            }
        });

        ImageButton deleteButton = (ImageButton) dialog.findViewById(R.id.btnDeleteTask );

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlertDialogDelete( TasksActivity.getRecViewTasks(), dialog );
            }
        });

        dialog.show();
    }

    @Override
    public void onBindViewHolder(@NonNull TasksRecViewAdapter.ViewHolder holder, int position)
    {
        holder.txtTaskName.setText(tasks.get( position ).getName());
        holder.checkBoxTaskDone.setChecked(tasks.get( position ).getStatus());

        taskIndex = tasks.indexOf( tasks.get(position) );

        holder.tasksListItemParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currTask = tasks.get(tasks.indexOf(tasks.get(position)));
                showTaskDetails( TasksActivity.getRecViewTasks() );
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
