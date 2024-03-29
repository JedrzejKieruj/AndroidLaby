package com.punky.lab1app;

import android.graphics.Color;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        setButtonsClickListener();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void myClickHandler(View view) {
        TextView txtV = (TextView) findViewById(R.id.hello_txt_v);
        switch (view.getId()){
            case R.id.button:
                txtV.setTextColor(Color.RED);
                break;
            case R.id.button2:
                txtV.setTextColor(Color.GREEN);
                break;
            case R.id.button3:
                txtV.setTextColor(Color.BLUE);
                break;
            default:
        }
    }

    public void setButtonsClickListener() {
        Button buttonRed = (Button) findViewById(R.id.button);
        Button buttonGreen = (Button) findViewById(R.id.button2);
        Button buttonBlue = (Button) findViewById(R.id.button3);

        final View.OnClickListener myClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myClickHandler(view);
            }
        };
        buttonRed.setOnClickListener(myClickListener);
        buttonBlue.setOnClickListener(myClickListener);
        buttonGreen.setOnClickListener(myClickListener);
    }

}
