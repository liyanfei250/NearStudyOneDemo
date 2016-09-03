package fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lzq.near.R;

import base.NearBaseFragment;
import butterknife.ButterKnife;

/**
 * Created by LYF on 2016/9/2.
 */
public class MyCarFragment extends NearBaseFragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home_layout, null);

        return view;
    }

    @Override
    protected void initFragment() {

    }


    public static MyCarFragment newInstance() {

        Bundle args = new Bundle();

        MyCarFragment fragment = new MyCarFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
