package com.jiyoung.andstudy.activity;

import android.content.Context;
import android.os.Build;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter;
import android.print.PrintManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.jiyoung.andstudy.R;

public class HtmlPrintActivity extends AppCompatActivity {

    private WebView myWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_html_print);

        WebView webView = new WebView(this);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                createWebPrintJob(view);
                myWebView = null;
            }
        });

        String htmlDocument = "<html><body><h1>Android Print Test</h1><p>"
                + "This is some sample content.</p></body></html>";
        webView.loadDataWithBaseURL(null, htmlDocument, "text/html", "UTF-8", null);

        myWebView = webView;
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
