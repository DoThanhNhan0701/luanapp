package com.example.music;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    MediaPlayer mediaPlayer;
    TextView status, name;
    ConstraintLayout constraintLayout;
    Button buttonPlay, buttonStop, buttonNext, buttonClose;
    ArrayList<AmNhac> arrayListAmnhac;
    int viTri = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mapping();
        mappingAmnhac();
        khoiTaoAmnhac();
        status();


    }

    private void animation() {
        ImageView image = (ImageView)findViewById(R.id.imageView2);
        Animation animFade = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate);
        image.startAnimation(animFade);
    }

    private void status() {
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viTri++;
                if(viTri > arrayListAmnhac.size() - 1){
                    viTri = 0;

                }
                if(mediaPlayer.isPlaying()){
                    mediaPlayer.stop();
                }
                khoiTaoAmnhac();
                mediaPlayer.start();
                status.setText("Play");
                buttonPlay.setText("PAUSE");

            }
        });
        buttonStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.stop();
                status.setText("STOP");
                buttonPlay.setText("PLAY");

            }
        });
        buttonPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animation();
                if(mediaPlayer.isPlaying()){
                    mediaPlayer.pause();
                    status.setText("PAUSE");
                    buttonPlay.setText("PLAY");
                }else{
                    mediaPlayer.start();
                    status.setText("PLAYING");
                    buttonPlay.setText("PAUSE");
                }
            }
        });
        buttonClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.exit(0);
            }
        });
    }

    private void khoiTaoAmnhac() {
        mediaPlayer = MediaPlayer.create(MainActivity.this, arrayListAmnhac.get(viTri).getResource());
        name.setText(arrayListAmnhac.get(viTri).getName());
    }

    private void mapping(){
        name = (TextView) findViewById(R.id.textView2);
        status = (TextView) findViewById(R.id.textView3);
        buttonPlay = (Button) findViewById(R.id.button2);
        buttonStop = (Button) findViewById(R.id.button1);
        buttonNext = (Button) findViewById(R.id.button3);
        buttonClose = (Button) findViewById(R.id.button4);
        constraintLayout = (ConstraintLayout) findViewById(R.id.contactLayout);
    }
    private void mappingAmnhac(){
        arrayListAmnhac = new ArrayList<>();
        arrayListAmnhac.add(new AmNhac("Coong chua", R.raw.congchua));
        arrayListAmnhac.add(new AmNhac("Anh Sáu", R.raw.anhsau));
        arrayListAmnhac.add(new AmNhac("Cãi lương anh ba bắt cá", R.raw.cailuong));
        arrayListAmnhac.add(new AmNhac("Nhạc vọng cỗ", R.raw.vonjgco));
    }


}