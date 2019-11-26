package com.punky.lab4.tasks;


import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.punky.lab4.MainActivity;
import com.punky.lab4.R;
import com.punky.lab4.TaskInfoActivity;

import static java.lang.Math.random;

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


        taskInfoTitle.setText(task.contactSurname);
        taskInfoDescription.setText(task.contactBirthday);

/*
        if(task.contactRingtone != null && !task.contactRingtone.isEmpty()){
            if(task.contactRingtone.contains("ringtone")) {
                switch (task.contactRingtone) {
                    case "ringtone 1":
                        ringtone = MediaPlayer.create(TaskInfoFragment.this, R.raw.dance_monkey);
                        break;
                    case "ringtone 2":
                        ringtone = MediaPlayer.create(TaskInfoFragment.this, R.raw.ona_by_tak_chciala_);
                        break;
                    default:
                        ringtone = MediaPlayer.create(TaskInfoFragment.this, R.raw.dance_monkey);
                }
            }else {
                ringtone.
                taskInfoImage.setImageDrawable(activity.getResources().getDrawable(R.drawable.circle_drawable_green));
            }
        }

        String picPath = "drawable" + (int)(  1 + random() * 5 );
        int PersonPicID = getResources().getIdentifier(picPath, "drawable", getPackageName());
        taskInfoImage.setImageDrawable(taskDrawable);*/

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
