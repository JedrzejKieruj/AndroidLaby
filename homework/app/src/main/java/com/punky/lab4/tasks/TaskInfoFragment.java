package com.punky.lab4.tasks;


import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.punky.lab4.MainActivity;
import com.punky.lab4.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TaskInfoFragment extends Fragment {


    public TaskInfoFragment() {
        // Required empty public constructor
    }
    public void displayTask(TaskListContent.Task task){
        FragmentActivity activity = getActivity();

        TextView taskInfoTitle = activity.findViewById(R.id.taskInfoTitle);
        TextView taskInfoDescription = activity.findViewById(R.id.taskInfoDescription);
        ImageView taskInfoImage = activity.findViewById(R.id.taskInfoImage);

        taskInfoTitle.setText(task.title);
        taskInfoDescription.setText(task.details);
        if(task.picPath != null && !task.picPath.isEmpty()){
            if(task.picPath.contains("drawable")) {
                Drawable taskDrawable;
                switch (task.picPath) {
                    case "drawable 1":
                        taskDrawable = activity.getResources().getDrawable(R.drawable.circle_drawable_green);
                        break;
                    case "drawable 2":
                        taskDrawable = activity.getResources().getDrawable(R.drawable.circle_drawable_orange);
                        break;
                    case "drawable 3":
                        taskDrawable = activity.getResources().getDrawable(R.drawable.circle_drawable_red);
                        break;
                    default:
                        taskDrawable = activity.getResources().getDrawable(R.drawable.circle_drawable_green);
                }
                taskInfoImage.setImageDrawable(taskDrawable);
            }else {
                taskInfoImage.setImageDrawable(activity.getResources().getDrawable(R.drawable.circle_drawable_green));
            }
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_task_info, container, false);
    }
    @Override
    public void onActivityCreated(@NonNull Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Intent intent = getActivity().getIntent();
        if(intent != null) {
            TaskListContent.Task receivedTask = intent.getParcelableExtra(MainActivity.taskExtra);
            if(receivedTask != null)
                displayTask(receivedTask);
        }
    }

}
