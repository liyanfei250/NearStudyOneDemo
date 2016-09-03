package com.ddm.cityselector.Fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ListFragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.Poi;
import com.ddm.cityselector.Adapter.CityAdapter;
import com.ddm.cityselector.Adapter.HotCityGridViewAdapter;
import com.ddm.cityselector.CustomView.Custom;
import com.ddm.cityselector.R;
import com.ddm.cityselector.moudle.CityFirstChar;
import com.ddm.cityselector.moudle.CityMoudle;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by DPro on 3/9/16.
 */
public class CityNameFragment extends ListFragment implements AdapterView.OnItemClickListener,LoaderManager.LoaderCallbacks {

    public Gson gson = new Gson();
    /**
     * json 城市的数组
     */

    JSONArray citys;

    public static Map<Character, Integer> map;

    private CityAdapter cityAdapter;

    private List<CityMoudle> cityMoudleList;
    private List<String> list = new ArrayList<>();

    private List<String> hotCity = new ArrayList<>();
    /**
     * 热门城市的数组
     */
    JSONArray hotCitys;

    /**
     * 城市名字 和首字母的排序
     */

    List<CityFirstChar> cityFirstCharList = new ArrayList<>();


    CityMoudle cityMoudle;

    List<String> cityNameAndPingyin = new ArrayList<>();

    public static Map<Character, List<Integer>> ci = new HashMap<>();

    private List<String> sortCityList = new ArrayList<>();


    public LocationClient mLocationClient = null;
    public BDLocationListener myListener = new MyLocationListener();
    private TextView mCityLocation;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        View view;
        view = LayoutInflater.from(getActivity()).inflate(R.layout.hot_city_layout, null);
        Custom gridView = (Custom) view.findViewById(R.id.grad_view);
        mCityLocation = (TextView) view.findViewById(R.id.city_locate);


        mLocationClient = new LocationClient(getActivity().getBaseContext());     //声明LocationClient类
        mLocationClient.registerLocationListener(myListener);    //注册监听函数
        initLocation();
        mLocationClient.start();
        mCityLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("city", mCityLocation.getText().toString());
                getActivity().setResult(Activity.RESULT_OK, intent);
                getActivity().finish();
            }
        });
        /**
         * 读取 城市列表
         */
        readCityList();

        /**
         * 根据首字母排序城市
         */
        getCitySortByPinyin();

        gridView.setAdapter(new HotCityGridViewAdapter(hotCity, getActivity()));
        getListView().addHeaderView(view);
        cityAdapter = new CityAdapter(getActivity(), cityFirstCharList);
        setListAdapter(cityAdapter);
        map = cityAdapter.getMap();

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.putExtra("city", hotCity.get(position));
                getActivity().setResult(Activity.RESULT_OK, intent);
                getActivity().finish();
            }
        });

        getListView().setOnItemClickListener(this);

        //setListAdapter(new CityAdapter(getActivity(), ci));
        getLoaderManager().initLoader(0, null, this);
    }

    private void initLocation() {
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy
        );//可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        option.setCoorType("bd09ll");//可选，默认gcj02，设置返回的定位结果坐标系
        int span = 1000;
        option.setScanSpan(span);//可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
        option.setIsNeedAddress(true);//可选，设置是否需要地址信息，默认不需要
        option.setOpenGps(true);//可选，默认false,设置是否使用gps
        option.setLocationNotify(true);//可选，默认false，设置是否当gps有效时按照1S1次频率输出GPS结果
        option.setIsNeedLocationDescribe(true);//可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
        option.setIsNeedLocationPoiList(true);//可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
        option.setIgnoreKillProcess(false);//可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
        option.SetIgnoreCacheException(false);//可选，默认false，设置是否收集CRASH信息，默认收集
        option.setEnableSimulateGps(false);//可选，默认false，设置是否需要过滤gps仿真结果，默认需要
        mLocationClient.setLocOption(option);
    }

    @Override
    public Loader onCreateLoader(int id, Bundle args) {
        copyGloab2Databases(getActivity(),"cityList.json");
        Uri uri = Uri.parse(getSDPath()+ "cityList.json");
        CursorLoader loader = new CursorLoader(getActivity(),uri,null,null,null,null);

        return loader;
    }

    @Override
    public void onLoadFinished(Loader loader, Object data) {

    }

    @Override
    public void onLoaderReset(Loader loader) {

    }

    private class MyLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            if (location != null) {

                if (location.getCity() != null
                        && !location.getCity().equals("")) {
                    mLocationClient.stop();
                    mCityLocation.setText(location.getCity());

                }
            }
            //Receive Location
            StringBuffer sb = new StringBuffer(256);
            sb.append("time : ");
            sb.append(location.getTime());
            sb.append("\nerror code : ");
            sb.append(location.getLocType());
            sb.append("\nlatitude : ");
            sb.append(location.getLatitude());
            sb.append("\nlontitude : ");
            sb.append(location.getLongitude());
            sb.append("\nradius : ");
            sb.append(location.getRadius());
            if (location.getLocType() == BDLocation.TypeGpsLocation) {// GPS定位结果
                sb.append("\nspeed : ");
                sb.append(location.getSpeed());// 单位：公里每小时
                sb.append("\nsatellite : ");
                sb.append(location.getSatelliteNumber());
                sb.append("\nheight : ");
                sb.append(location.getAltitude());// 单位：米
                sb.append("\ndirection : ");
                sb.append(location.getDirection());// 单位度
                sb.append("\naddr : ");
                sb.append(location.getAddrStr());
                sb.append("\ndescribe : ");
                sb.append("gps定位成功");

            } else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {// 网络定位结果
                sb.append("\naddr : ");
                sb.append(location.getAddrStr());
                //运营商信息
                sb.append("\noperationers : ");
                sb.append(location.getOperators());
                sb.append("\ndescribe : ");
                sb.append("网络定位成功");
            } else if (location.getLocType() == BDLocation.TypeOffLineLocation) {// 离线定位结果
                sb.append("\ndescribe : ");
                sb.append("离线定位成功，离线定位结果也是有效的");
            } else if (location.getLocType() == BDLocation.TypeServerError) {
                sb.append("\ndescribe : ");
                sb.append("服务端网络定位失败，可以反馈IMEI号和大体定位时间到loc-bugs@baidu.com，会有人追查原因");
            } else if (location.getLocType() == BDLocation.TypeNetWorkException) {
                sb.append("\ndescribe : ");
                sb.append("网络不同导致定位失败，请检查网络是否通畅");
            } else if (location.getLocType() == BDLocation.TypeCriteriaException) {
                sb.append("\ndescribe : ");
                sb.append("无法获取有效定位依据导致定位失败，一般是由于手机的原因，处于飞行模式下一般会造成这种结果，可以试着重启手机");
            }
            sb.append("\nlocationdescribe : ");
            sb.append(location.getLocationDescribe());// 位置语义化信息
            List<Poi> list = location.getPoiList();// POI数据
            if (list != null) {
                sb.append("\npoilist size = : ");
                sb.append(list.size());
                for (Poi p : list) {
                    sb.append("\npoi= : ");
                    sb.append(p.getId() + " " + p.getName() + " " + p.getRank());
                }
            }
            Log.i("BaiduLocationApiDem", sb.toString());
        }
    }

    private void getCitySortByPinyin() {
        char cha = 97;
        for (int i = 0; i < 26; i++) {
            List<CityMoudle> list = new ArrayList<>();
            cityNameAndPingyin.add(String.valueOf(cha).toUpperCase());
            for (int j = 0; j < cityMoudleList.size(); j++) {
                //list.clear();
                char[] ch = cityMoudleList.get(j).getPinyin().substring(0, 1).toCharArray();
                if (ch[0] == cha) {
                    CityFirstChar cityFirstChar = new CityFirstChar();
                    sortCityList.add(cityMoudleList.get(j).getCity());
                    list.add(cityMoudleList.get(j));
                    cityNameAndPingyin.add(cityMoudleList.get(j).getCity());
                    cityFirstChar.setFirstChar((char) (cha - 32));
                    cityFirstChar.setCityName(cityMoudleList.get(j).getCity());
                    cityFirstCharList.add(cityFirstChar);
                }
            }
            cha++;
        }
    }

    private void readCityList() {
        try {
            InputStreamReader inputStreamReader =
                    new InputStreamReader(getActivity().getAssets().open("cityList.json"));
            String line;
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuilder stringBuilder = new StringBuilder();
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }

            JSONObject jsonObject = new JSONObject(stringBuilder.toString());
            citys = jsonObject.getJSONArray("citys");
            hotCitys = jsonObject.getJSONArray("topCitys");
            bufferedReader.close();
            inputStreamReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        cityMoudleList = new ArrayList<>();
        for (int i = 0; i < citys.length(); i++) {
            try {
                cityMoudle = gson.fromJson(citys.getString(i), CityMoudle.class);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            cityMoudleList.add(cityMoudle);
        }

        for (int i = 0; i < hotCitys.length(); i++) {
            try {
                hotCity.add(gson.fromJson(hotCitys.getString(i),
                        CityMoudle.class).getCity());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (position > 0) {
            Intent intent = new Intent();
            intent.putExtra("city", cityFirstCharList.get(position - 1).getCityName());
            getActivity().setResult(Activity.RESULT_OK, intent);
            getActivity().finish();
        }
    }


    public static void copyGloab2Databases(Context context, String fileName) {
        File file = new File(getSDPath());
        // 不存在则创建，存在就返回
        if (!file.exists())
            file.mkdirs();
        File copyFile = new File( getSDPath()+ fileName);
        InputStream in = null;
        OutputStream out = null;
        try {
            // 获取图片，将图片copy到sdcard
            in = context.getAssets().open(fileName);
            out = new FileOutputStream(copyFile);
            byte[] buff = new byte[1024];
            int len;
            while ((len = in.read(buff)) > 0) {
                out.write(buff, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null)
                    out.close();
                if (in != null)
                    in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static String getSDPath(){
        File sdDir = null;
        boolean sdCardExist = Environment.getExternalStorageState()
                .equals(android.os.Environment.MEDIA_MOUNTED);//判断sd卡是否存在
        if(sdCardExist)
        {
            sdDir = Environment.getExternalStorageDirectory();//获取跟目录
        }
        return sdDir.toString();
    }
}
