package com.ddm.cityselector.CitySelect.View;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.ddm.cityselector.R;



/**
 * Created by DPro on 5/1/16.
 * at 18:52
 * at 6:52 PM
 */
public class CitySelectActivity extends FragmentActivity{

    public static final int REQUEST_CODE_NORMAL = 1;
    public static final int REQUEST_CODE_REGISTER = 2;
    public static final int REQUEST_CODE_LIVING= 3;

    private FrameLayout frameLayout;
    private ProvinceListFragment provinceListFragment;
    private CityFragment cityFragment;
    private LinearLayout back;
    private TextView titleInfo;

    private LinearLayout backParent;

    FragmentManager fragmentManager;
    FragmentTransaction transaction;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actvity_city_select_layout);
        backParent = (LinearLayout) findViewById(R.id.back_parent);
        backParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CitySelectActivity.this.finish();
            }
        });
        titleInfo = (TextView) findViewById(R.id.title_info);
        titleInfo.setText("城市选择");
        frameLayout = (FrameLayout) findViewById(R.id.contain);
        provinceListFragment = (ProvinceListFragment) getSupportFragmentManager().findFragmentById(R.id.contain);

        if (provinceListFragment == null){
            provinceListFragment = new ProvinceListFragment();
        }

        fragmentManager = getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();
        cityFragment = (CityFragment) fragmentManager.findFragmentById(R.id.contain);
        if (cityFragment == null){
            cityFragment = new CityFragment();
        }
        transaction.replace(R.id.contain, provinceListFragment).commitAllowingStateLoss();
    }


    void replaceCityListFragment(int position){
        try {
            System.out.println();
        }catch (Exception e){
            e.printStackTrace();
        }
        Bundle bundle = new Bundle();
        bundle.putInt("bundle", position);
        cityFragment.setArguments(bundle);
        fragmentManager.beginTransaction().replace(R.id.contain, cityFragment).commit();
    }
}
