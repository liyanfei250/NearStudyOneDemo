package com.ddm.cityselector.CitySelect.View;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.AdapterView;

import com.ddm.cityselector.Adapter.CitySelectAdapter;
import com.ddm.cityselector.CitySelect.Model.CityBean;
import com.ddm.cityselector.CitySelect.Presenter.CitySelectPresenter;

/**
 * Created by DPro on 5/1/16.
 * at 19:56
 * at 7:56 PM
 */
public class ProvinceListFragment extends ListFragment implements BaseView, AdapterView.OnItemClickListener{

    private CitySelectAdapter selectAdapter;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        selectAdapter = new CitySelectAdapter(getActivity());
        new CitySelectPresenter(getActivity(), this);
        getListView().setOnItemClickListener(this);
    }

    @Override
    public void displayData(CityBean cityBean) {
        setListAdapter(selectAdapter);
        selectAdapter.addData(cityBean);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        try {
            ((CitySelectActivity)getActivity()).replaceCityListFragment(position);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
