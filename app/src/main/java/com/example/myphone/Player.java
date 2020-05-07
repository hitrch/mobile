package com.example.myphone;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

public class Player extends AppCompatActivity {
    private VideoView videoPlayer;
    private static final int READ_REQUEST_CODE = 42;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        Toast toast = Toast.makeText(getApplicationContext(), "Please open file", 400);
        toast.show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode,
                                 Intent resultData) {
        if (requestCode == READ_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            Uri uri = null;
            if (resultData != null) {
                uri = resultData.getData();
                videoPlayer =  findViewById(R.id.videoPlayer);
                videoPlayer.setVideoURI(uri);
                MediaController mediaController = new MediaController(this);
                videoPlayer.setMediaController(mediaController);
                mediaController.setMediaPlayer(videoPlayer);
            }
        }
    }

    public void findAudio(View view) {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("audio/*");
        startActivityForResult(intent, READ_REQUEST_CODE);
    }

    public void findVideo(View view) {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("video/*");
        startActivityForResult(intent, READ_REQUEST_CODE);
    }

    public void stop(View view){
        if(videoPlayer != null) {
            videoPlayer.stopPlayback();
            videoPlayer.resume();
        }
        else {
            Toast toast = Toast.makeText(getApplicationContext(), "Please open file", 400);
            toast.show();
        }
    }
}
