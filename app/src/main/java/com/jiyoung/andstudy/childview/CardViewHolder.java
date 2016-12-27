package com.jiyoung.andstudy.childview;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jiyoung.andstudy.R;
import com.jiyoung.andstudy.data.ChapterInfo;

public class CardViewHolder extends RecyclerView.ViewHolder {

    ImageView imgThumb;
    TextView tvTitle;
    TextView tvDesc;

    ChapterInfo chapterInfo;

    public CardViewHolder(View itemView) {
        super(itemView);

        imgThumb = (ImageView) itemView.findViewById(R.id.img_thumb);
        tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
        tvDesc = (TextView) itemView.findViewById(R.id.tv_desc);
    }

    public void setChapterInfo(ChapterInfo chapterInfo) {
        this.chapterInfo = chapterInfo;

        imgThumb.setImageDrawable(chapterInfo.getImage());
        tvTitle.setText(chapterInfo.getTitle());
        tvDesc.setText(chapterInfo.getDescription());
    }
}
