package com.ddm.cityselector.UI;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.ddm.cityselector.CustomView.SildBar;
import com.ddm.cityselector.Fragment.CityNameFragment;
import com.ddm.cityselector.R;

import static com.ddm.cityselector.R.id.list_fragment_contain;

/**
 * Created by lzq on 9/3/16.
 */
public class CitySelectorActivity extends FragmentActivity {

    private RelativeLayout listFragmentContain;
    private RelativeLayout indexFragmentContain;

    private FragmentManager fragmentManager;

    CityNameFragment cityNameFragment;

    LinearLayout back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actvity_city_selector_layout);
        cityNameFragment = new CityNameFragment();
        listFragmentContain = (RelativeLayout) findViewById(list_fragment_contain);
        indexFragmentContain = (RelativeLayout) findViewById(R.id.index_fragment_contain);
        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.list_fragment_contain,
                cityNameFragment).commit();

        back = (LinearLayout) findViewById(R.id.back_parent);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_CANCELED);
                CitySelectorActivity.this.finish();
            }
        });

        SildBar sildBar = (SildBar) findViewById(R.id.sild_bar);
        sildBar.setOnLetterChangedListener(new SildBar.OnLetterChangedListener() {
            @Override
            public void onLetterChanged(String letter) {
                if (letter.equals("热门"))
                    cityNameFragment.getListView().setSelection(0);
                if (letter.charAt(0) >= 65 && letter.charAt(0) < 91){
                    cityNameFragment.getListView().setSelection(CityNameFragment.map.get(letter.charAt(0)) + 1);

                }
            }
        });

    }
}
