package com.jiyoung.andstudy.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.jiyoung.andstudy.R;

public class ExplicitIntentBActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_b);

        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            return;
        }

        String qString = extras.getString("qString");
        ((TextView) findViewById(R.id.textview)).setText(qString);

        ((Button) findViewById(R.id.button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    public void finish() {

        String returnString = ((EditText) findViewById(R.id.edittext)).getText().toString();

        Intent data = new Intent();
        data.putExtra("returnData", returnString);

        setResult(RESULT_OK, data);

        super.finish();
    }
}
