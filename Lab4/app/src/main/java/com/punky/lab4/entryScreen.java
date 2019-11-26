package com.punky.lab4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.punky.lab4.tasks.TaskListContent;

import static java.lang.Math.random;

public class entryScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry_screen);
        Button addconbutt = findViewById(R.id.addContactButton);
        final EditText limitedPhone =  findViewById(R.id.contactPhone);
        final EditText limitedDate =  findViewById(R.id.contactBirthday);
        addconbutt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(limitedDate.getText().toString().charAt(2) != '.' || limitedDate.getText().toString().charAt(5) != '.' || Integer.parseInt(limitedPhone.getText().toString())  < 9 ){
                    if(limitedDate.getText().toString().charAt(2) != '.' || limitedDate.getText().toString().charAt(5) != '.'){
                        Toast.makeText(getApplicationContext(), "Enter valid date in format XX.YY.ZZZZ", Toast.LENGTH_LONG);
                    }
                    if(Integer.parseInt(limitedPhone.getText().toString())  < 9){
                        Toast.makeText(getApplicationContext(), "Phone number must be 9 digits long", Toast.LENGTH_LONG);
                    }
                }
                addClick(view);
            }
        });
        /*                if(limitedDate.toString().charAt(2) != '.' || limitedDate.toString().charAt(5) != '.' || Integer.parseInt(limitedPhone.toString())  < 9 ){
                    if(limitedDate.toString().charAt(2) != '.' || limitedDate.toString().charAt(5) != '.'){
                        Toast.makeText(getApplicationContext(), "Enter valid date in format XX.YY.ZZZZ", Toast.LENGTH_LONG);
                    }
                    if(Integer.parseInt(limitedPhone.toString())  < 9){
                        Toast.makeText(getApplicationContext(), "Phone number must be 9 digits long", Toast.LENGTH_LONG);
                    }
                }else {*/

    }

    public void addClick(View view) {
        EditText taskContactName = findViewById(R.id.contactName);
        String typedContactName = taskContactName.getText().toString();
        EditText taskContactSurname = findViewById(R.id.contactSurname);
        String typedContactSurname = taskContactSurname.getText().toString();
        EditText taskContactBirthday = findViewById(R.id.contactBirthday);
        String typedContactBirthday = taskContactBirthday.getText().toString();
        EditText taskContactPhone = findViewById(R.id.contactPhone);
        String typedContactPhone = taskContactPhone.getText().toString();

        Spinner drawableSpinner = findViewById(R.id.spinner);
        String selectedRingtone = drawableSpinner.getSelectedItem().toString();

        String picPath = "drawable" + (int)(random() * 5 );

        TaskListContent.addItem(new TaskListContent.Task(typedContactName,
                    typedContactSurname,
                    typedContactBirthday,
                    typedContactPhone,
                    selectedRingtone,
                    picPath));

      /*  ((TaskFragment) getSupportFragmentManager().findFragmentById(R.id.taskFragment)).notifyDataChange();

        taskContactName.setText("");
        taskContactSurname.setText("");
        taskContactBirthday.setText("");
        taskContactPhone.setText("");

        InputMethodManager imn = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imn.hideSoftInputFromWindow(view.getWindowToken(),0);*/

      setResult(RESULT_OK);
      finish();
    }
}


        /*
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
        */


