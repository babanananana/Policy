package com.mobile.PolicyApp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FavoriteAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    // adapter에 들어갈 list 입니다.
    private ArrayList<com.mobile.PolicyApp.FavoriteData> listData = new ArrayList<com.mobile.PolicyApp.FavoriteData>();
    private final int TYPE_HEADER = 0;
    private final int TYPE_ITEM = 1;
    private final int TYPE_FOOTER = 2;
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        RecyclerView.ViewHolder holder;
        View view;
        if (viewType == TYPE_HEADER) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.favorite_header, parent, false);
            holder = new com.mobile.PolicyApp.HeaderViewHolder(view);
        } else if (viewType == TYPE_FOOTER) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.favorite_footer, parent, false);
            holder = new com.mobile.PolicyApp.FooterViewHolder(view);
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.favorite_item, parent, false);
            holder = new ItemViewHolder(view);
        }
        return holder;
    }
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof com.mobile.PolicyApp.HeaderViewHolder) {
            com.mobile.PolicyApp.HeaderViewHolder headerViewHolder = (com.mobile.PolicyApp.HeaderViewHolder) holder;
        } else if (holder instanceof com.mobile.PolicyApp.FooterViewHolder) {
            com.mobile.PolicyApp.FooterViewHolder footerViewHolder = (com.mobile.PolicyApp.FooterViewHolder) holder;
        } else {
            // Item을 하나, 하나 보여주는(bind 되는) 함수입니다.
            ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
            itemViewHolder.onBind(listData.get(position - 1), position);
        }
    }
    @Override
    public int getItemCount() {
        // RecyclerView의 총 개수 입니다.
        return listData.size() + 2;
    }
    void addItem(com.mobile.PolicyApp.FavoriteData favoriteData) {
        // 외부에서 item을 추가시킬 함수입니다.
        listData.add(favoriteData);
    }
    // RecyclerView의 핵심인 ViewHolder 입니다.
    // 여기서 subView를 setting 해줍니다.
    class ItemViewHolder extends RecyclerView.ViewHolder {
        private TextView textView1;
        private TextView textView2;
        private ImageView imageView;
        //private Button button;
        ItemViewHolder(View itemView) {
            super(itemView);
            textView1 = itemView.findViewById(R.id.favorite_list_1);
            textView2 = itemView.findViewById(R.id.favorite_list_text);
        }
        void onBind(com.mobile.PolicyApp.FavoriteData detailData, int position) {
            textView1.setText(detailData.getTitle());
            textView2.setText(detailData.getContent());
        }
    }
    @Override
    public int getItemViewType(int position) {
        if (position == 0)
            return TYPE_HEADER;
        else if (position == listData.size() + 1)
            return TYPE_FOOTER;
        else
            return TYPE_ITEM;
    }
}