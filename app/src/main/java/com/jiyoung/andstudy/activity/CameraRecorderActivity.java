package com.jiyoung.andstudy.activity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.jiyoung.andstudy.R;

import java.io.File;

public class CameraRecorderActivity extends AppCompatActivity {

    private static final int REQUEST_VIDEO_CAPTURE = 101;
    private Uri fileUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_recorder);

        Button btnRecord = (Button) findViewById(R.id.btn_record);

        btnRecord.setEnabled(hasCamera());
        btnRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startRecording();
            }
        });
    }

    public void startRecording() {
        File mediaFile = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/myvideo.mp4");
        fileUri = Uri.fromFile(mediaFile);

        Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
        startActivityForResult(intent, REQUEST_VIDEO_CAPTURE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_VIDEO_CAPTURE) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(CameraRecorderActivity.this, "Video has been saved to:\n" + fileUri, Toast.LENGTH_LONG).show();
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(CameraRecorderActivity.this, "Video recording cancelled.", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(CameraRecorderActivity.this, "Failed to record video.", Toast.LENGTH_LONG).show();
            }
        }
    }

    private boolean hasCamera() {
        return (getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY));
    }
}
