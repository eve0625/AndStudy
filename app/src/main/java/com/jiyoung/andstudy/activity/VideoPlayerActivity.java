package com.jiyoung.andstudy.activity;

import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.MediaController;
import android.widget.VideoView;

import com.jiyoung.andstudy.R;

public class VideoPlayerActivity extends AppCompatActivity {

    String TAG = "VideoPlayer";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player);

        final VideoView videoView = (VideoView) findViewById(R.id.videoview);

        /* ViewView 사용하기 */
        //1.인터넷 주소 사용하기 (manifest에 인터넷 퍼미션 필요)
        //videoView.setVideoPath("http://www.ebookfrenzy.com/android_book/movie.mp4");

        //2. resource 상의 비디오파일 사용하기 (raw 폴더 생성 후, 그 하위에 대상 동영상 파일 추가)
        //아래 두가지 다 실행가능하나, R.raw.movie를 사용하는 방법이 더 좋을듯.
        videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.movie));
        //videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/raw/movie")); //확장자를 포함하지 않는 파일명

        /* MediaController 추가하기 */
        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);

        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                Log.i(TAG, "Duration=" + videoView.getDuration());
            }
        });
        videoView.start();
    }
}
