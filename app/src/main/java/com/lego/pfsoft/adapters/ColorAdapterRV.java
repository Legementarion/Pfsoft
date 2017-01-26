package com.lego.pfsoft.adapters;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lego.pfsoft.R;
import com.lego.pfsoft.model.Item;

import java.util.ArrayList;
import java.util.List;

public class ColorAdapterRV extends RecyclerView.Adapter<ColorAdapterRV.ColorsViewHolder> {

    private final Callback mListener;
    private final List<Item> mItems;
    private final Context mContext;
    private final int mExpandedHeight;

    public ColorAdapterRV(Callback mListener, List<Item> items, Context context) {
        this.mListener = mListener;
        mItems = new ArrayList<>(items);
        mContext = context;
        mExpandedHeight = mContext.getResources().getDimensionPixelSize(R.dimen.expanded_height);
    }

    @Override
    public ColorsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        TextView view = (TextView) LayoutInflater.from(mContext).inflate(R.layout.color_rv_item, parent, false);
        return new ColorsViewHolder(view, mListener);
    }

    @Override
    public void onBindViewHolder(final ColorsViewHolder holder, final int position) {
        final Item item = mItems.get(position);
        RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) holder.mTextView.getLayoutParams();
        holder.mTextView.setText(item.getName());

        if (item.isChecked()) {
            holder.mTextView.setTextColor(Color.GRAY);
            holder.mTextView.getBackground().setColorFilter(item.getColor(), PorterDuff.Mode.SRC_ATOP);
            params.height = mExpandedHeight;
            holder.mTextView.setLayoutParams(params);
        } else {
            holder.mTextView.setTextColor(item.getColor());
            holder.mTextView.getBackground().clearColorFilter();
            params.height = RecyclerView.LayoutParams.WRAP_CONTENT;
            holder.mTextView.setLayoutParams(params);
        }
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    static class ColorsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView mTextView;
        private final Callback mListener;

        ColorsViewHolder(TextView view, Callback mListener) {
            super(view);
            mTextView = view;
            this.mListener = mListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mListener.onItemClick(getAdapterPosition());
        }
    }

    public interface Callback {
        void onItemClick(int position);
    }

}
