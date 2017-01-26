package com.lego.pfsoft.adapters;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckedTextView;

import com.lego.pfsoft.R;
import com.lego.pfsoft.model.Item;

import java.util.ArrayList;
import java.util.List;

public class ColorAdapterRV extends RecyclerView.Adapter<ColorAdapterRV.ColorsViewHolder> {

    private List<Item> mItems;

    public ColorAdapterRV(List<Item> items) {
        mItems = new ArrayList<>(items);
    }

    @Override
    public ColorsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CheckedTextView view = (CheckedTextView) LayoutInflater.from(parent.getContext()).inflate(R.layout.color_rv_item, parent, false);
        return new ColorsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ColorsViewHolder holder, final int position) {
        holder.mTextView.setText(mItems.get(position).getName());
        holder.mTextView.setTextColor(Color.parseColor(mItems.get(position).getColor()));
        holder.mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) v.getLayoutParams();
                if (((CheckedTextView) v).isChecked()) {
                    holder.mTextView.setBackgroundResource(R.drawable.button);
                    holder.mTextView.setTextColor(Color.parseColor(mItems.get(position).getColor()));
                    params.height = RecyclerView.LayoutParams.WRAP_CONTENT;
                    v.setLayoutParams(params);
                }
                else {
                    holder.mTextView.setBackground(customView(v.getContext(), Color.parseColor(mItems.get(position).getColor())));
                    holder.mTextView.setTextColor(Color.GRAY);
                    params.height = 400;
                    v.setLayoutParams(params);
                }
                ((CheckedTextView) v).toggle();
            }

            private GradientDrawable customView(Context context, int backgroundColor) {
                GradientDrawable shape = new GradientDrawable();
                shape.setShape(GradientDrawable.RECTANGLE);
                int dimenSize = context.getResources().getDimensionPixelOffset(R.dimen.border_radius);
                shape.setCornerRadii(new float[]{dimenSize, dimenSize, dimenSize, dimenSize, dimenSize, dimenSize, dimenSize, dimenSize});
                shape.setColor(backgroundColor);
                shape.setStroke(3, backgroundColor);
                return shape;
            }
        });
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    class ColorsViewHolder extends RecyclerView.ViewHolder {
        CheckedTextView mTextView;

        ColorsViewHolder(CheckedTextView view) {
            super(view);
            mTextView = view;
            mTextView.setTextSize(20);
        }
    }

}
