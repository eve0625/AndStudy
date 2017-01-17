package com.jiyoung.andstudy.activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

import com.jiyoung.andstudy.R;

import java.net.MalformedURLException;
import java.net.URL;

public class MyWebViewActivity extends AppCompatActivity {

    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_web_view);

        webView = (WebView) findViewById(R.id.webview);

        Intent intent = getIntent();
        Uri data = intent.getData();
        URL url = null;

        try {
            url = new URL(data.getScheme(), data.getHost(), data.getPath());
            webView.loadUrl(url.toString());

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
