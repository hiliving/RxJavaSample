package com.practice.dev.rxjavasample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.practice.dev.rxjavasample.model.SampleBean;

import java.util.List;

/**
 * Created by HY on 2017/4/13.
 */

public class ListAdapter extends BaseAdapter {
    List<SampleBean> list;
    Context context;

    public ListAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return list==null? 0 : list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder=null;
        if (convertView==null){
            convertView  = LayoutInflater.from(context).inflate(R.layout.list_item,null);
            holder = new ViewHolder(convertView);
            Glide.with(context).load(list.get(position).image_url).into(holder.imageView);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
            Glide.with(context).load(list.get(position).image_url).into(holder.imageView);
        }
        return convertView;
    }

    public void setImage(List<SampleBean> sampleBean) {
        this.list = sampleBean;
        notifyDataSetChanged();
    }

    class ViewHolder{
        ImageView imageView;
        public ViewHolder(View itemView) {
            this.imageView = (ImageView) itemView.findViewById(R.id.pic);
        }
    }
}
