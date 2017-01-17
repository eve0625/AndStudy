package com.jiyoung.andstudy.activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
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

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle("My ToolBar");
        collapsingToolbarLayout.setContentScrimColor(Color.GREEN);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new RecyclerChapterAdapter(this);
        adapter.setOnAdapterChapterClickListener(new RecyclerChapterAdapter.OnAdapterChapterClickListener() {
            @Override
            public void onChapterClick(View view, ChapterInfo chapterInfo, int position) {
                if (chapterInfo.getmClass() != null) {
                    Intent intent = new Intent(RecyclerViewActivity.this, chapterInfo.getmClass());
                    startActivity(intent);
                } else {
                    Toast.makeText(RecyclerViewActivity.this, chapterInfo.getTitle(), Toast.LENGTH_SHORT).show();
                }
            }
        });
        recyclerView.setAdapter(adapter);

    }
}
