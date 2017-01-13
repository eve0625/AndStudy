package com.jiyoung.andstudy.activity;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jiyoung.andstudy.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class FloatingButtonActivityFragment extends Fragment {

    public FloatingButtonActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_floating_button, container, false);
    }
}
