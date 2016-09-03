package com.ddm.cityselector.CitySelect.Presenter;

import android.content.Context;

import com.ddm.cityselector.CitySelect.Model.CitySelectModel;
import com.ddm.cityselector.CitySelect.View.BaseView;

/**
 * Created by DPro on 5/1/16.
 * at 19:58
 * at 7:58 PM
 */
public class CitySelectPresenter {
    private BaseView baseView;
    private CitySelectModel citySelectModel;

    public CitySelectPresenter(Context context, BaseView baseView) {
        this.baseView = baseView;
        citySelectModel = new CitySelectModel(context);
        loadData();
    }


    public void loadData(){
        baseView.displayData(citySelectModel.readCityList());
    }
}
