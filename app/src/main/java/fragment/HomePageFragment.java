package fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ddm.cityselector.UI.CitySelectorActivity;
import com.example.lzq.near.R;
import com.example.lzq.near.activity.SearchActivity;

import app.AbsSuperApplication;
import base.BaseFragment;
import butterknife.InjectView;

/**
 * Created by LYF on 2016/9/2.
 */
public class HomePageFragment extends BaseFragment implements View.OnClickListener {

    @InjectView(R.id.back_parent)
    LinearLayout backParent;
    @InjectView(R.id.title_info)
    TextView titleInfo;
    @InjectView(R.id.right_image)
    ImageView rightImage;
    @InjectView(R.id.title_bar)
    LinearLayout titleBar;
    @InjectView(R.id.viewPage_contain)
    FrameLayout viewPageContain;
    private ImageView iv_seach;
    private TextView city_name;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_layout, null);

        iv_seach = (ImageView)view.findViewById(R.id.iv_seach);
        iv_seach.setOnClickListener(this);
        city_name = (TextView)view.findViewById(R.id.city_name);
        city_name.setOnClickListener(this);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home_layout;
    }

    protected void initFragment() {
        if (AbsSuperApplication.location != null) {
            city_name.setText(AbsSuperApplication.location);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == 2) {
                city_name.setText(data.getStringExtra("city"));
                AbsSuperApplication.location = data.getStringExtra("city");
            }
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_parent:
                break;
            case R.id.title_info:
                break;
            case R.id.right_image:
                break;
            case R.id.iv_seach:
                startActivity(new Intent(getActivity(), SearchActivity.class));
                break;
            case R.id.city_name:
                this.startActivityForResult(new Intent(AbsSuperApplication.getContext(), CitySelectorActivity.class), 2);
                break;
        }
    }
}
