package com.jiyoung.andstudy.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jiyoung.andstudy.R;
import com.jiyoung.andstudy.fragment.TextFragment;
import com.jiyoung.andstudy.fragment.ToolbarFragment;

public class FragmentActivity extends AppCompatActivity implements ToolbarFragment.ToolbarListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
    }

    @Override
    public void onButtonClick(int fontsize, String text) {
        //첫번째 fragment에서 버튼 클릭시, 두번째 fragment를 찾아 public 메서드를 호출하여 통신함
        TextFragment textFragment = (TextFragment) getSupportFragmentManager().findFragmentById(R.id.text_fragment);
        textFragment.changeTextProperties(fontsize, text);
    }
}
