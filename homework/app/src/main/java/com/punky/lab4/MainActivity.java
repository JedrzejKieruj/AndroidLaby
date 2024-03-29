package com.punky.lab4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.punky.lab4.tasks.TaskInfoFragment;
import com.punky.lab4.tasks.TaskListContent;

public class MainActivity extends AppCompatActivity
        implements
        TaskFragment.OnListFragmentInteractionListener,
        DeleteDialog.OnDeleteDialogInteractionListener
{
    public static final String taskExtra = "taskExtra";
    private  int currentItemPosition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void addClick(View view) {
        EditText taskTitleEditTxt = findViewById(R.id.taskTitle);
        EditText taskDescriptionEditTxt = findViewById(R.id.taskDescription);
        Spinner drawableSpinner = findViewById(R.id.drawableSpinner);
        String taskTitle = taskTitleEditTxt.getText().toString();
        String taskDescription = taskDescriptionEditTxt.getText().toString();
        String selectedImage = drawableSpinner.getSelectedItem().toString();

        if(taskTitle.isEmpty() && taskDescription.isEmpty()){
            TaskListContent.addItem(new TaskListContent.Task("Task." + TaskListContent.ITEMS.size() + 1,
                    getString(R.string.default_title),
                    getString(R.string.default_description),
                    selectedImage));
        }else{
            if(taskTitle.isEmpty())
                taskTitle = getString(R.string.default_title);
            if(taskDescription.isEmpty())
                taskDescription = getString(R.string.default_description);
            TaskListContent.addItem(new TaskListContent.Task("Task." + TaskListContent.ITEMS.size() + 1,
                    taskTitle,
                    taskDescription,
                    selectedImage));
        }
        ((TaskFragment) getSupportFragmentManager().findFragmentById(R.id.taskFragment)).notifyDataChange();

        taskTitleEditTxt.setText("");
        taskDescriptionEditTxt.setText("");

        InputMethodManager imn = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imn.hideSoftInputFromWindow(view.getWindowToken(),0);
    }
    private void startSecondActivity(TaskListContent.Task task, int position) {
        Intent intent = new Intent(this, TaskInfoActivity.class);
        intent.putExtra(taskExtra, task);
        startActivity(intent);
    }
    private void displayTaskInFragment(TaskListContent.Task task){
        TaskInfoFragment taskInfoFragment = ((TaskInfoFragment) getSupportFragmentManager().findFragmentById(R.id.displayFragment));
        if(taskInfoFragment != null) {
            taskInfoFragment.displayTask(task);
        }
    }

    @Override
    public void onListFragmentClickInteraction(TaskListContent.Task task, int position) {
        Toast.makeText(this, getString(R.string.item_selected_msg) + position, Toast.LENGTH_SHORT).show();
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            displayTaskInFragment(task);
        }else {
            startSecondActivity(task, position);
        }
    }

    @Override
    public void onListFragmentLongClickInteraction(int position) {
        Toast.makeText(this, getString(R.string.long_click_msg) + position, Toast.LENGTH_SHORT).show();
        showDeleteDialog();
        currentItemPosition = position;
    }
    private void showDeleteDialog() {
        DeleteDialog.newInstance().show(getSupportFragmentManager(), getString(R.string.delete_dialog_tag));
    }


    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {
        View v = findViewById(R.id.addButton);
        if(v != null) {
            Snackbar.make(v,getString(R.string.delete_cancel_msg), Snackbar.LENGTH_LONG).setAction(getString(R.string.retry_msg), new View.OnClickListener() {
                @Override
                public  void onClick(View v) {
                    showDeleteDialog();
                }
            }).show();
        }
    }


    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        if(currentItemPosition != 1 && currentItemPosition < TaskListContent.ITEMS.size()){
            TaskListContent.removeItem(currentItemPosition);
            ((TaskFragment) getSupportFragmentManager().findFragmentById(R.id.taskFragment)).notifyDataChange();
        }
    }

}
