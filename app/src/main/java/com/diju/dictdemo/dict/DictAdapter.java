package com.diju.dictdemo.dict;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.diju.dictdemo.R;
import com.diju.dictdemo.dict.model.Dict;

import java.util.ArrayList;
import java.util.List;

public class DictAdapter extends RecyclerView.Adapter<DictAdapter.MyViewHolder> {
    List<Dict> dictList = new ArrayList<>();

    Context context;

    private CityAdapter.OnItemSelectedListener mOnItemClickListener;

    public void setOnItemClickListener(CityAdapter.OnItemSelectedListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    public interface OnItemSelectedListener {
        /**
         * item点击事件
         *
         * @param view
         * @param position
         */
        void onItemSelected(View view, int position);
    }

    public DictAdapter(Context context, List<Dict> dictList) {
        this.dictList = dictList;
        this.context = context;
    }

    @Override
    public DictAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        DictAdapter.MyViewHolder holder = new DictAdapter.MyViewHolder(
                LayoutInflater.from(context).inflate(R.layout.item_citylist, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(DictAdapter.MyViewHolder holder, final int position) {
        holder.tv.setText(dictList.get(position).getDictValue());
        holder.tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null && position < dictList.size()) {
                    mOnItemClickListener.onItemSelected(v, position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return dictList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv;

        public MyViewHolder(View view) {
            super(view);
            tv = (TextView) view.findViewById(R.id.default_item_city_name_tv);
        }
    }
}
