package com.jiyoung.andstudy.activity;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter;
import android.print.PrintManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.jiyoung.andstudy.R;

public class WebPrintActivity extends AppCompatActivity {

    private WebView myWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_print);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        myWebView = (WebView) findViewById(R.id.myWebView);
        myWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return super.shouldOverrideUrlLoading(view, url);
            }
        });
        myWebView.loadUrl("http://developer.android.com/google/index.html");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_web_print, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_print) {
            createWebPrintJob(myWebView);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void createWebPrintJob(WebView webView) {
        if (Build.VERSION.SDK_INT >= 19) {

            PrintManager printManager = (PrintManager) this.getSystemService(Context.PRINT_SERVICE);
            PrintDocumentAdapter printAdapter = null;

            if (Build.VERSION.SDK_INT >= 21) {
                printAdapter = webView.createPrintDocumentAdapter("MyDocument");
            } else if (Build.VERSION.SDK_INT >= 19) {
                printAdapter = webView.createPrintDocumentAdapter();
            }

            String jobName = getString(R.string.app_name) + " Print Test";
            printManager.print(jobName, printAdapter, new PrintAttributes.Builder().build());
        }

    }

}
