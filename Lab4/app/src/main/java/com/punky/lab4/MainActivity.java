package com.punky.lab4;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
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
    private FloatingActionButton floatbut;
    private int reqCode = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        floatbut = (FloatingActionButton) findViewById(R.id.floatingActionButtonEntryScreen);
        floatbut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startEntryScreen();
            }
        });
    }

    private void startSecondActivity(TaskListContent.Task task, int position) {
        Intent intent = new Intent(this, TaskInfoActivity.class);
        intent.putExtra(taskExtra, task);
        startActivity(intent);
    }
    private void startEntryScreen() {
        Intent intent = new Intent(this, entryScreen.class);
        startActivityForResult(intent,reqCode);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == reqCode){
            if(resultCode == RESULT_OK){
                ((TaskFragment) getSupportFragmentManager().findFragmentById(R.id.taskFragment)).notifyDataChange();
            }
        }
    }

    private void displayTaskInFragment(TaskListContent.Task task){
        TaskInfoFragment taskInfoFragment = ((TaskInfoFragment) getSupportFragmentManager().findFragmentById(R.id.displayFragment));
        if(taskInfoFragment != null) {
            taskInfoFragment.displayTask(task);
        }
    }

    @Override
    public void onListFragmentClickInteraction(TaskListContent.Task task, int position) {
        //Toast.makeText(this, getString(R.string.item_selected_msg) + position, Toast.LENGTH_SHORT).show();
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            displayTaskInFragment(task);

        }else {
            startSecondActivity(task, position);
        }
    }

    @Override
    public void onListFragmentLongClickInteraction(TaskListContent.Task task) {
        // MR: operator == dla stringów nie zadziała. Musi Pan skorzystać z funkcji equals: task.contactRingtone.equals("ringtone 1")
        if(task.contactRingtone.equals("ringtone 1")){
            MediaPlayer ring = MediaPlayer.create(MainActivity.this,R.raw.ringtone1);
            ring.start();
        }else{
            MediaPlayer ring = MediaPlayer.create(MainActivity.this,R.raw.ringtone2);
            ring.start();
        }

    }

    @Override
    public void onListDeleteButton(int position) {
        showDeleteDialog();
        currentItemPosition = position;
    }

    private void showDeleteDialog() {
        DeleteDialog.newInstance().show(getSupportFragmentManager(), getString(R.string.delete_dialog_tag));
    }


    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {
        View v = findViewById(R.id.floatingActionButtonEntryScreen);
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
        if(currentItemPosition != -1 && currentItemPosition < TaskListContent.ITEMS.size()){
            TaskListContent.removeItem(currentItemPosition);
            ((TaskFragment) getSupportFragmentManager().findFragmentById(R.id.taskFragment)).notifyDataChange();
        }
    }

}