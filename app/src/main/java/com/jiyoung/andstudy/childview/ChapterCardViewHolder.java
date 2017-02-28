package com.jiyoung.andstudy.childview;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jiyoung.andstudy.R;
import com.jiyoung.andstudy.data.ChapterInfo;

public class ChapterCardViewHolder extends RecyclerView.ViewHolder {

    ImageView imgThumb;
    TextView tvTitle;
    TextView tvDesc;

    ChapterInfo chapterInfo;

    public interface OnChapterClickListener {
        public void onChapterClick(View view, ChapterInfo chapterInfo, int position);
    }

    OnChapterClickListener clickListener;
    public void setOnChapterClickListener(OnChapterClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public ChapterCardViewHolder(View itemView) {
        super(itemView);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (clickListener != null) {
                    clickListener.onChapterClick(view, chapterInfo, getAdapterPosition());
                }
            }
        });

        imgThumb = (ImageView) itemView.findViewById(R.id.img_thumb);
        tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
        tvDesc = (TextView) itemView.findViewById(R.id.tv_desc);
    }

    public void setChapterInfo(ChapterInfo chapterInfo) {
        this.chapterInfo = chapterInfo;

        imgThumb.setImageResource(chapterInfo.getImageId());
        tvTitle.setText(chapterInfo.getTitle());
        tvDesc.setText(chapterInfo.getDescription());
    }
}
