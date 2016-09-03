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
 * at 20:49
 * at 8:49 PM
 */
public class CitySelectAdapter  extends BaseAdapter{
    private Context context;
    private List<CityBean.CityCodeBean> cityCodeBeen;

    public CitySelectAdapter(Context context) {
        this.context = context;
        cityCodeBeen = new ArrayList<>();
    }


    public void addData(CityBean cityBean){
        if (cityBean != null){
            cityCodeBeen.addAll(cityBean.getCityCode());
            notifyDataSetChanged();
        }
    }

    @Override
    public int getCount() {
        return cityCodeBeen.size();
    }

    @Override
    public Object getItem(int position) {
        return cityCodeBeen.get(position);
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

        viewHolder.province.setText(cityCodeBeen.get(position).getProvince());
        if (cityCodeBeen.get(position).getCity().size() > 0){
            viewHolder.more.setVisibility(View.VISIBLE);
        }


        return convertView;
    }

    private class ViewHolder{
        private TextView province;
        private TextView more;
    }
}
