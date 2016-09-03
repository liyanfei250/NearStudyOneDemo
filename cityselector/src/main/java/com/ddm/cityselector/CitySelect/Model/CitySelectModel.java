package com.ddm.cityselector.CitySelect.Model;

import android.content.Context;

import com.ddm.cityselector.moudle.CityMoudle;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by DPro on 5/1/16.
 * at 19:25
 * at 7:25 PM
 */
public class CitySelectModel {

    private Context context;

    public CitySelectModel(Context context) {
        this.context = context;
    }

    public CityBean readCityList() {
        CityBean cityBeanList = new CityBean();
        String cityList = null;
        try {
            InputStreamReader inputStreamReader =
                    new InputStreamReader(context.getAssets().open("provinceCityArea.json"));
            String line;
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuilder stringBuilder = new StringBuilder();
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }

            cityList = stringBuilder.toString();

            bufferedReader.close();
            inputStreamReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            cityBeanList =  new Gson().fromJson(cityList, CityBean.class);
        }catch (Exception e){
            e.printStackTrace();
        }

        return cityBeanList;
    }
}
