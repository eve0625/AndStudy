package com.jiyoung.andstudy.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.jiyoung.andstudy.R;
import com.jiyoung.andstudy.adapter.RecyclerChapterAdapter;
import com.jiyoung.andstudy.data.ChapterInfo;

public class RecyclerViewActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerChapterAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new RecyclerChapterAdapter(this);
        adapter.setOnAdapterChapterClickListener(new RecyclerChapterAdapter.OnAdapterChapterClickListener() {
            @Override
            public void onChapterClick(View view, ChapterInfo chapterInfo, int position) {
                Toast.makeText(RecyclerViewActivity.this, chapterInfo.getTitle(), Toast.LENGTH_SHORT).show();
            }
        });
        recyclerView.setAdapter(adapter);

    }
}
