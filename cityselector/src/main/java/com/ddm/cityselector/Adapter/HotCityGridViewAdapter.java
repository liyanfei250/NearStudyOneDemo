package com.ddm.cityselector.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ddm.cityselector.R;

import java.util.List;


/**
 * Created by DPro on 3/10/16.
 */
public class HotCityGridViewAdapter extends BaseAdapter {

    private List<String> list;
    private Context c;

    public HotCityGridViewAdapter(List<String> list, Context c) {
        this.list = list;
        this.c = c;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null){
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(c).inflate(R.layout.grid_view_layout, null);
            viewHolder.textView = (TextView) convertView.findViewById(R.id.text);

            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.textView.setText(list.get(position));

        return convertView;
    }


    class ViewHolder{
        TextView textView;
    }
}
