package com.jiyoung.andstudy.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;

import com.jiyoung.andstudy.R;

public class ToolbarFragment extends Fragment {

    private static int seekvalue = 10;
    private static EditText edittext;

    ToolbarListener activityCallback;

    //activity와 통신하기 위한 Interface
    public interface ToolbarListener {
        public void onButtonClick(int position, String text);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //fragment의 레이아웃을 인플레이트 함
        View view = inflater.inflate(R.layout.fragment_toolbar, container, false);

        edittext = (EditText) view.findViewById(R.id.edittext);

        SeekBar seekBar = (SeekBar) view.findViewById(R.id.seekbar);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                seekvalue = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        Button button = (Button) view.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activityCallback.onButtonClick(seekvalue, edittext.getText().toString());
            }
        });

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        //hosting activity가 ToolbarListener를 구현하고 있는지 check
        try {
            activityCallback = (ToolbarListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement ToolbarListener");
        }
    }
}
