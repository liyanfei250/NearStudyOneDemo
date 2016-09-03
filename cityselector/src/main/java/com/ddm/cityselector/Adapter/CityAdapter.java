package com.ddm.cityselector.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ddm.cityselector.CustomView.Custom;
import com.ddm.cityselector.R;
import com.ddm.cityselector.moudle.CityFirstChar;
import com.ddm.cityselector.moudle.CityMoudle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by DPro on 3/10/16.
 */
public class CityAdapter extends BaseAdapter {

    private List<CityFirstChar> list;
    private Context context;

    /**
     * 记录字母的位置
     */

    private Map<Character, Integer> map = new HashMap<>();

    public CityAdapter(Context context, List<CityFirstChar> list) {
        this.context = context;
        this.list = list;
        /**
         * 记录 字母的位置
         */

        for (int i = 0; i < list.size(); i++){
            char currentChat = list.get(i).getFirstChar();
            char previewChar = (i - 1) >= 0 ? list.get(i - 1).getFirstChar() : ' ';

            if (currentChat != previewChar){
                map.put(currentChat, i);
            }
        }

    }

    public Map<Character, Integer> getMap() {
        return map;
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
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item_layout, null);

            viewHolder.index = (TextView) convertView.findViewById(R.id.index);
            viewHolder.textView = (TextView) convertView.findViewById(R.id.city_name);

            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        char currentChat = list.get(position).getFirstChar();
        char previewChar = (position - 1) >= 0 ? list.get(position - 1).getFirstChar() : ' ';

        if (currentChat != previewChar){
            viewHolder.index.setVisibility(View.VISIBLE);
            viewHolder.index.setText(String.format("%s", currentChat));
            viewHolder.textView.setText(list.get(position).getCityName());
        }else{
            viewHolder.index.setVisibility(View.GONE);
            viewHolder.textView.setText(list.get(position).getCityName());
        }

        return convertView;
    }

    class ViewHolder{
        TextView index;
        TextView textView;
    }
}
