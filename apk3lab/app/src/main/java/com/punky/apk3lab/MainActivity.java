package com.punky.apk3lab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.IOException;


public class MainActivity extends AppCompatActivity {

    public static final String SOUND_ID = "sound id";
    public static final int BUTTON_REQUEST = 1;
    private int current_sound = 0;
    private MediaPlayer backgroundPlayer;
    private MediaPlayer buttonPlayer;
    static public Uri[] sounds;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ImageButton imButton =
                (ImageButton) findViewById(R.id.face_button);

        sounds = new Uri[4];
        //Use parse method of the Uri class to obtain the Uri of a resource
        //specified by a string
        sounds[0] = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.ringd);
        sounds[1] = Uri.parse("android.resource://" + getPackageName() + "/" +
                R.raw.ring01);
        sounds[2] = Uri.parse("android.resource://" + getPackageName() + "/" +
                R.raw.ring02);
        sounds[3] = Uri.parse("android.resource://" + getPackageName() + "/" +
                R.raw.ring03);
        buttonPlayer = new MediaPlayer();
        buttonPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

        buttonPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
        //Pause the backgroundPlayer
                backgroundPlayer.pause();
        //Start the buttonPlayer
                mp.start();
            }
        });

        buttonPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
             @Override
             public void onCompletion(MediaPlayer mp) {
                 backgroundPlayer.start();
             }
         });


        imButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                        //Set the initial value of the color.
        //Alpha always set to 255.
                int random_color = 0xff000000;
        //For each color component draw a random value
                for (int i = 0; i < 3; i++) {
                    int component_color = (int) (255 * Math.random());
        //Shift the chosen color value to the appropriate color //component
                    component_color <<= (8 * i);
        //Apply the color
                    random_color |= component_color;
                }
                imButton.setColorFilter(random_color);

                //Reset the player
                buttonPlayer.reset();
                try {
        //Set Data source according to the current_sound value
                    buttonPlayer.setDataSource(getApplicationContext(),sounds[current_sound]);
                } catch (IOException e) {
                    e.printStackTrace();
                }
        //Prepare the player asynchronously
                buttonPlayer.prepareAsync();
        //No need to call start() since we call with onPreparedListener


            }
        });
        imButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
        //Create an Intent that will be used to launch the SecondActivity
                    Intent soundPick = new
                            Intent(getApplicationContext(),SecondActivity.class);
        //Attach the current_sound value to the Intent. This value can be
        //retrieved with the SOUND_ID key.
                    soundPick.putExtra(SOUND_ID,current_sound);
        //Start the SecondActivity indicating that it will give a result
        //back for a BUTTON_REQUEST request code
                    startActivityForResult(soundPick, BUTTON_REQUEST);
                    return true;
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent
            data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
    // Make sure the request was successful
            if (requestCode == BUTTON_REQUEST) {
                current_sound = data.getIntExtra(SOUND_ID, 0);
            }
        } else if (resultCode == RESULT_CANCELED) {
            Toast.makeText(getApplicationContext(), getText(R.string.back_message), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        //Send the background player to the paused state
        backgroundPlayer.pause();
        buttonPlayer.pause();
    }
    @Override
    protected void onResume() {
        super.onResume();
        //Create and prepare MediaPlayer with R.raw.mario as the data stream
        //source
        backgroundPlayer = MediaPlayer.create(this, R.raw.mario);
        //Define a procedure that will be executed when the MediaPlayer goes to
        //the prepared state
        backgroundPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
           @Override
           public void onPrepared(MediaPlayer mp) {
        //Set the looping parameter to true
               mp.setLooping(true);
        //Start the playback.
        //By placing the start method in the onPrepared event
        //we are always certain that the audio stream is prepared.
               mp.start();
           }
       });
    }
    @Override
    protected void onStop(){
        super.onStop();
        //Release the background player when we don’t need it.
        backgroundPlayer.release();
    }

}

