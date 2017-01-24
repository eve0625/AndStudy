package com.jiyoung.andstudy.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.jiyoung.andstudy.R;
import com.jiyoung.andstudy.database.Product;

import java.io.IOException;

public class AudioActivity extends AppCompatActivity {

    private static MediaRecorder mediaRecorder;
    private static MediaPlayer mediaPlayer;

    private static String audioFilePath;
    private static Button btnStop;
    private static Button btnPlay;
    private static Button btnRecord;

    private boolean isRecording = false;

    private static final int RECORD_REQUEST_CODE = 101;
    private static final int STORAGE_REQUEST_CODE = 102;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio);

        btnPlay = (Button) findViewById(R.id.btn_play);
        btnRecord = (Button) findViewById(R.id.btn_record);
        btnStop = (Button) findViewById(R.id.btn_stop);

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playAudio();
            }
        });

        btnRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recordAudio();
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopAudio();
            }
        });

        if (!hasMicrophone()) { //마이크가 없는 경우 모든 버튼 비활성화
            btnPlay.setEnabled(false);
            btnRecord.setEnabled(false);
            btnStop.setEnabled(false);
        } else { //마이크가 있는 경우, Record 버튼은 활성화
            btnPlay.setEnabled(false);
            btnStop.setEnabled(false);

            audioFilePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/myaudio.3gp";
            requestPermission(Manifest.permission.RECORD_AUDIO, STORAGE_REQUEST_CODE); //녹음권한 요청
        }
    }

    public void playAudio() {
        btnPlay.setEnabled(false);
        btnRecord.setEnabled(false);
        btnStop.setEnabled(true);

        try {
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setDataSource(audioFilePath);
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void recordAudio() {
        isRecording = true;
        btnStop.setEnabled(true);
        btnPlay.setEnabled(false);
        btnRecord.setEnabled(false);

        try {
            mediaRecorder = new MediaRecorder();
            mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
            mediaRecorder.setOutputFile(audioFilePath);
            mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
            mediaRecorder.prepare();
        } catch (Exception e) {
            e.printStackTrace();
        }
        mediaRecorder.start();
    }

    public void stopAudio() {
        btnStop.setEnabled(false);
        btnPlay.setEnabled(true);

        if (isRecording) { //녹음중인 경우, 녹음기를 중단
            btnRecord.setEnabled(false);
            mediaRecorder.stop();
            mediaRecorder.release();
            mediaRecorder = null;
            isRecording = false;
        } else { //재생중인 경우, 재생을 중단
            mediaPlayer.release();
            mediaPlayer = null;
            btnRecord.setEnabled(true);
        }
    }

    protected  void requestPermission(String permissionType, int requestCode) {
        int permission = ContextCompat.checkSelfPermission(this, permissionType);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{permissionType}, requestCode);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case RECORD_REQUEST_CODE : { //녹음 퍼미션
                if (grantResults.length == 0 || grantResults[0] != PackageManager.PERMISSION_GRANTED) { //퍼미션이 허용되지 않은 경우
                    btnRecord.setEnabled(false); //녹음버튼 사용불가
                    Toast.makeText(this, "녹음 권한이 없습니다.", Toast.LENGTH_LONG).show();
                } else { //퍼미션이 허용된 경우
                    requestPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE, STORAGE_REQUEST_CODE); //저장소 쓰기 권한 요청
                }
                return;
            }
            case STORAGE_REQUEST_CODE : {
                if (grantResults.length == 0 || grantResults[0] != PackageManager.PERMISSION_GRANTED) { //퍼미션이 허용되지 않은 경우
                    btnRecord.setEnabled(false); //녹음버튼 사용불가
                    Toast.makeText(this, "저장소 쓰기 권한이 없습니다.", Toast.LENGTH_LONG).show();
                }
                return;
            }
        }
    }

    protected  boolean hasMicrophone() {
        return getPackageManager().hasSystemFeature(PackageManager.FEATURE_MICROPHONE);
    }
}
