package com.ddm.cityselector.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ddm.cityselector.CitySelect.Model.CityBean;
import com.ddm.cityselector.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DPro on 5/1/16.
 * at 21:49
 * at 9:49 PM
 */
public class ChildCityAdapter extends BaseAdapter {

    List<CityBean.CityCodeBean.City> cityList;
    private Context context;

    public ChildCityAdapter(Context context) {
        this.context = context;
        cityList = new ArrayList<>();
    }

    public void addData(CityBean cityBean, int location){
        if (cityBean != null){
            cityList.addAll(cityBean.getCityCode().get(location).getCity());
            notifyDataSetChanged();
        }
    }

    @Override
    public int getCount() {
        return cityList.size();
    }

    @Override
    public Object getItem(int position) {
        return cityList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null ){
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.province_item_layout, null);
            viewHolder.province = (TextView) convertView.findViewById(R.id.province);
            viewHolder.more = (TextView) convertView.findViewById(R.id.more);

            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.province.setText(cityList.get(position).getCityName());

        return convertView;
    }

    private class ViewHolder{
        private TextView province;
        private TextView more;
    }
}
