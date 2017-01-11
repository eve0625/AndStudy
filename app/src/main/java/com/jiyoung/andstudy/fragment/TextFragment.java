package com.jiyoung.andstudy.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jiyoung.andstudy.R;

public class TextFragment extends Fragment {

    private TextView textview;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_text, container, false);

        textview = (TextView) view.findViewById(R.id.textview);

        return view;
    }

    public void changeTextProperties(int fontsize, String text) {
        textview.setTextSize(fontsize);
        textview.setText(text);
    }
}
