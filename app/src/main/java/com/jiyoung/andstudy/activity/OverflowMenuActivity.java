package com.jiyoung.andstudy.activity;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RelativeLayout;

import com.jiyoung.andstudy.R;

public class OverflowMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overflow_menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.overflow_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        RelativeLayout mainLayout = (RelativeLayout) findViewById(R.id.relative_layout);

        switch (item.getItemId()) {
            case R.id.menu_red :
                item.setChecked(!item.isChecked());
                mainLayout.setBackgroundColor(Color.RED);
                return true;
            case R.id.menu_green :
                item.setChecked(!item.isChecked());
                mainLayout.setBackgroundColor(Color.GREEN);
                return true;
            case R.id.menu_yellow :
                item.setChecked(!item.isChecked());
                mainLayout.setBackgroundColor(Color.YELLOW);
                return true;
            case R.id.menu_blue :
                item.setChecked(!item.isChecked());
                mainLayout.setBackgroundColor(Color.BLUE);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
