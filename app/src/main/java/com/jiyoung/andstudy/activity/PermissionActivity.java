package com.jiyoung.andstudy.activity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.jiyoung.andstudy.R;

public class PermissionActivity extends AppCompatActivity {

    private static String TAG = "PermissionDemo";
    private static final int RECORD_REQUEST_CODE = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission);

        //안드로이드 6.0 이상에서는, 위험퍼미션의 경우 매니페스트에 명시해도 런타임시 최초 한번 따로 승인을 받아야 함!!
        //RECORD_AUDIO 권한을 매니페스트에 명시했지만, 6.0 이상에서는 최초에 권한없음으로 나올것임! (한번 런타임 승인 후에는, 권한있음으로 나옴)
        int permission = ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO);

        if (permission != PackageManager.PERMISSION_GRANTED) { //안드로이드 6.0 이상만 해당!

            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.RECORD_AUDIO)) { //이전에 퍼미션 요청을 거절한 적이 있는 경우 관련 설명을 보여줌

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("이 권한은 오디오 녹음을 위해 필요하답니다.");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        makeRequest();
                    }
                });
                builder.create().show();

            } else { //최초인 경우
                makeRequest();
            }

        }
    }

    private void makeRequest() {
        //퍼미션 승인을 요청!
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO}, RECORD_REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case RECORD_REQUEST_CODE : {
                if (grantResults.length == 0 || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    Log.i(TAG, "Permissioin has been denied by user");
                } else {
                    Log.i(TAG, "Permission has been granted by user");
                }
                return;
            }
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
}
