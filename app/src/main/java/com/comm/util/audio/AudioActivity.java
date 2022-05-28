package com.comm.util.audio;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.comm.util.R;
import timber.log.Timber;

public class AudioActivity extends AppCompatActivity {

    private AudioManager mAudioManager;
    private AudioMngHelper audioMngHelper;
    private MediaPlayer mMediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio);
//        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        initView();
        audioMngHelper = new AudioMngHelper(this);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

            }
        },3000);
    }


    private void initView() {
        TextView btIncrease = findViewById(R.id.bt_increase);
        findViewById(R.id.bt_big).setOnClickListener(v -> {
            //最大音量
//            int maxVolume = mAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
//            //当前音量
//            int currentVolume = mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
//            Timber.i("maxVolue  " + maxVolume   + "  currentVolume  " + currentVolume);
            Timber.i("autdio      " + audioMngHelper.get100CurrentVolume());

        });
        btIncrease.setOnClickListener(v -> {
            audioMngHelper.setVoice100(80);
        });

        findViewById(R.id.bt_play_start).setOnClickListener(v -> {
            play();

        });

        findViewById(R.id.bt_play_stop).setOnClickListener(v -> {
            mMediaPlayer.pause();
            mMediaPlayer.reset();
        });

    }


    private void play() {
        mMediaPlayer = MediaPlayer.create(this, R.raw.mem_call_answer);
        mMediaPlayer.setLooping(true);
        mMediaPlayer.start();
    }
}
