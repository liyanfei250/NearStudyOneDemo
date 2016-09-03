package com.ddm.cityselector.CitySelect.View;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.AdapterView;

import com.ddm.cityselector.Adapter.ChildCityAdapter;
import com.ddm.cityselector.CitySelect.Model.CityBean;
import com.ddm.cityselector.CitySelect.Presenter.CitySelectPresenter;

/**
 * Created by DPro on 5/1/16.
 * at 21:38
 * at 9:38 PM
 */
public class CityFragment extends ListFragment implements BaseView, AdapterView.OnItemClickListener{
    private int anInt;
    private ChildCityAdapter childCityAdapter;
    private CityBean city;
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        anInt = getArguments().getInt("bundle");
        childCityAdapter = new ChildCityAdapter(getActivity());
        new CitySelectPresenter(getActivity(), this);
        getListView().setOnItemClickListener(this);
    }

    @Override
    public void displayData(CityBean cityBean) {
        city = cityBean;
        setListAdapter(childCityAdapter);
        childCityAdapter.addData(cityBean, anInt);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent data = new Intent();
        data.putExtra("province", city.getCityCode().get(anInt).getProvince());
        data.putExtra("city", city.getCityCode().get(anInt).getCity().get(position).getCityName());
        getActivity().setResult(Activity.RESULT_OK, data);
        getActivity().finish();
    }
}
