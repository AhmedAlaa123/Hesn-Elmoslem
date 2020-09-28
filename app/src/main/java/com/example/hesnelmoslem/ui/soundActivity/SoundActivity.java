package com.example.hesnelmoslem.ui.soundActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.hesnelmoslem.R;
import com.example.hesnelmoslem.ui.ZikerActivity.ZikerActivity;

import java.io.IOException;

public class SoundActivity extends AppCompatActivity implements View.OnClickListener {


    private SeekBar seekBar;
    private ImageView btn_play_sound;
    private ImageView btn_fast_forward;
    private ImageView btn_fast_backword;
    private MediaPlayer player;
    private ProgressBar progressBar;
    private Runnable runnable;
    private Handler handler;
    private boolean isPause = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sound);
        seekBar = findViewById(R.id.seekBar);
        btn_play_sound = findViewById(R.id.imageView_play_sound);
        btn_fast_backword = findViewById(R.id.imageView_fast_back);
        btn_fast_forward = findViewById(R.id.imageView_fast_forword);
        progressBar = findViewById(R.id.sound_activity_progress_bar);
        btn_play_sound.setOnClickListener(this);
        btn_fast_backword.setOnClickListener(this);
        btn_fast_forward.setOnClickListener(this);
        handler = new Handler();
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24);
        loadSound();

    }

    private void loadSound() {
        btn_play_sound.setImageResource(R.drawable.ic_baseline_pause_circle_filled_24);
        if (player != null) player.release();
        player = new MediaPlayer();
        try {
            player.setDataSource(this, Uri.parse(getIntent().getStringExtra(ZikerActivity.AUDIO_URL_EXTRA)));
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        player.prepare();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
            player.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    seekBar.setMax(mp.getDuration());
                    progressBar.setVisibility(View.GONE);
                    player.start();
                    playRecycle();
                    seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                        @Override
                        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                            if (fromUser) {
                                player.seekTo(progress);
                            }
                        }

                        @Override
                        public void onStartTrackingTouch(SeekBar seekBar) {
                        }

                        @Override
                        public void onStopTrackingTouch(SeekBar seekBar) {
                        }
                    });
                }
            });
            player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    btn_play_sound.setImageResource(R.drawable.ic_play);
                    isPause = false;
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void playRecycle() {
        seekBar.setProgress(player.getCurrentPosition());
        if (player.isPlaying()) {
            runnable = new Runnable() {
                @Override
                public void run() {
                    playRecycle();
                }
            };
            handler.postDelayed(runnable, 1000);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imageView_play_sound: {
                if (player != null && !player.isPlaying()) {
                    btn_play_sound.setImageResource(R.drawable.ic_baseline_pause_circle_filled_24);
                    player.start();
                    playRecycle();

                } else {

                    if (player != null) {
                        player.pause();
                        btn_play_sound.setImageResource(R.drawable.ic_play);
                    }
                }
                break;
            }
            case R.id.imageView_fast_back: {
                if (player != null && player.getCurrentPosition() - 2000 > 0) {
                    player.seekTo(player.getCurrentPosition() - 2000);
                } else {
                    player.seekTo(0);
                }
                seekBar.setProgress(player.getCurrentPosition());
                break;
            }
            case R.id.imageView_fast_forword: {
                if (player != null && player.getCurrentPosition() + 2000 < player.getDuration()) {
                    player.seekTo(player.getCurrentPosition() + 2000);
                } else {
                    player.seekTo(player.getDuration());
                }
                seekBar.setProgress(player.getCurrentPosition());
                break;
            }
        }
    }

    @Override
    public void onBackPressed() {
        if (player != null) player.release();
        finish();
        super.onBackPressed();
    }
}