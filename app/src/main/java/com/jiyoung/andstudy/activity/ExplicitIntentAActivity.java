package com.jiyoung.andstudy.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.jiyoung.andstudy.R;

public class ExplicitIntentAActivity extends AppCompatActivity {

    private static final int REQUEST_CODE = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_a);

        ((Button) findViewById(R.id.button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String myString = ((EditText) findViewById(R.id.edittext)).getText().toString();

                Intent intent = new Intent(ExplicitIntentAActivity.this, ExplicitIntentBActivity.class);
                intent.putExtra("qString", myString);
                startActivityForResult(intent, REQUEST_CODE);

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            String returnString = data.getExtras().getString("returnData");
            ((TextView) findViewById(R.id.textview)).setText(returnString);
        }
    }
}
