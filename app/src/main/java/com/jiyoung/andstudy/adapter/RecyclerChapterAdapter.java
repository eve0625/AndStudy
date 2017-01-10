package com.jiyoung.andstudy.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jiyoung.andstudy.R;
import com.jiyoung.andstudy.activity.BoundServiceActivity;
import com.jiyoung.andstudy.activity.CommonGesturesActivity;
import com.jiyoung.andstudy.activity.MotionEventActivity;
import com.jiyoung.andstudy.activity.RemoteBoundServiceActivity;
import com.jiyoung.andstudy.activity.StateChangeActivity;
import com.jiyoung.andstudy.childview.ChapterCardViewHolder;
import com.jiyoung.andstudy.data.ChapterInfo;

import java.util.ArrayList;
import java.util.List;

public class RecyclerChapterAdapter extends RecyclerView.Adapter<ChapterCardViewHolder>
    implements  ChapterCardViewHolder.OnChapterClickListener {

    List<ChapterInfo> items = new ArrayList<ChapterInfo>();
    Context mContext;

    public interface OnAdapterChapterClickListener {
        public void onChapterClick(View view, ChapterInfo chapterInfo, int position);
    }

    OnAdapterChapterClickListener listener;
    public void setOnAdapterChapterClickListener(OnAdapterChapterClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onChapterClick(View view, ChapterInfo chapterInfo, int position) {
        if (this.listener != null) {
            listener.onChapterClick(view, chapterInfo, position);
        }
    }

    public RecyclerChapterAdapter(Context context) {
        this.mContext = context;
        items.add(new ChapterInfo(context.getResources().getDrawable(R.drawable.sample_0), "Activity State",
                "Activity의 상태변화를 로그를 찍어 확인해보고, 동적 상태 데이터를 저장,복구해본다.", StateChangeActivity.class));
        items.add(new ChapterInfo(context.getResources().getDrawable(R.drawable.sample_1), "Bound Service",
                "Bound Service를 로컬,전용으로 생성하여 바인딩해본다.", BoundServiceActivity.class));
        items.add(new ChapterInfo(context.getResources().getDrawable(R.drawable.sample_2), "Remote Bound Service",
                "Bound Service를 원격으로 생성하여 바인딩하고, 데이터를 보내본다.", RemoteBoundServiceActivity.class));
        items.add(new ChapterInfo(context.getResources().getDrawable(R.drawable.sample_3), "Touch Event",
                "Touch Event를 감지하여 관련 정보를 처리해본다.", MotionEventActivity.class));
        items.add(new ChapterInfo(context.getResources().getDrawable(R.drawable.sample_4), "Gesture Detect",
                "Gesture를 감지하여 관련 정보를 처리해본다.", CommonGesturesActivity.class));
        items.add(new ChapterInfo(context.getResources().getDrawable(R.drawable.sample_5), "TEST5", "this is test1. this is test1. this is test1. this is test1."));
        items.add(new ChapterInfo(context.getResources().getDrawable(R.drawable.sample_6), "TEST6", "this is test1. this is test1. this is test1. this is test1."));
        items.add(new ChapterInfo(context.getResources().getDrawable(R.drawable.sample_7), "TEST7", "this is test1. this is test1. this is test1. this is test1."));
    }

    @Override
    public ChapterCardViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        //Child View를 가지는 ViewHolder 객체를 생성하고 반환함. RecyclerView에 의해 호출됨.
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_card_view, viewGroup, false);
        ChapterCardViewHolder holder = new ChapterCardViewHolder(view);
        holder.setOnChapterClickListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(ChapterCardViewHolder holder, int position) {
        //onCreateViewHolder로 생성된 ViewHolder 객체에 보여줄 데이터를 설정함.
        holder.setChapterInfo(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
