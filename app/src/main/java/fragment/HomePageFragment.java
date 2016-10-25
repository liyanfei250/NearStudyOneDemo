package fragment;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ddm.cityselector.UI.CitySelectorActivity;
import com.example.lzq.near.R;
import com.example.lzq.near.activity.SearchActivity;
import com.youth.banner.Banner;

import net.Network;

import java.util.List;

import adapter.NearListAdapter;
import app.AbsSuperApplication;
import base.BaseFragment;
import bean.ZhuangbiImage;
import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by LYF on 2016/9/2.
 */
public class HomePageFragment extends BaseFragment implements View.OnClickListener {

    @Bind(R.id.city_name)
    TextView cityName;
    @Bind(R.id.title_info)
    TextView titleInfo;
    @Bind(R.id.iv_seach)
    ImageView ivSeach;
    @Bind(R.id.rv)
    RecyclerView rv;
    @Bind(R.id.srl)
    SwipeRefreshLayout srl;
    private ImageView iv_seach;
    private TextView city_name;
    private Banner banner;
    String[] images = new String[]{"http://pic34.nipic.com/20131031/11712251_135356579136_2.jpg", "http://pic2.ooopic.com/10/58/69/94b1OOOPIC24.jpg", "http://pic.90sjimg.com/back_pic/qk/back_origin_pic/00/03/22/5855ea9f7757ff6f46ae7722853621e9.jpg", "http://pic.90sjimg.com/back_pic/u/00/02/82/06/561b49e410883.jpg", "https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1473592945&di=bf96c815c263245a1a4882d9c40b8715&src=http://ly.scgttt.com/Templates/cn/images/banner.jpg"};
    String[] titles = new String[]{"标题1", "标题2", "标题3", "标题4", "标题5"};
    private LinearLayout titleView;

    NearListAdapter adapter = new NearListAdapter();
    Observer<List<ZhuangbiImage>> observer = new Observer<List<ZhuangbiImage>>() {
        @Override
        public void onCompleted() {
        }

        @Override
        public void onError(Throwable e) {
            srl.setRefreshing(false);
            Toast.makeText(getActivity(), R.string.loading_failed, Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onNext(List<ZhuangbiImage> images) {
            srl.setRefreshing(false);
            adapter.setImages(images);
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_layout, null);

        iv_seach = (ImageView) view.findViewById(R.id.iv_seach);
        iv_seach.setOnClickListener(this);
        city_name = (TextView) view.findViewById(R.id.city_name);
        city_name.setOnClickListener(this);
//        banner = (Banner) view.findViewById(R.id.banner);
        titleView = (LinearLayout) view.findViewById(R.id.titleView);
        //ButterKnfe使用
        ButterKnife.bind(this, view);
        //一步搞定，设置banner图片就行了
//        banner.setBannerStyle(BannerConfig.NUM_INDICATOR_TITLE);
//        banner.setImages(images);
//        banner.setBannerTitle(titles);

        rv.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        rv.setAdapter(adapter);
        srl.setColorSchemeColors(Color.BLUE, Color.GREEN, Color.RED, Color.YELLOW);
        srl.setEnabled(false);
        //网络请求
        netContro();

        return view;
    }


    private void netContro() {
//        unsubscribe();
//        adapter.setImages(null);
        srl.setRefreshing(true);
        search("在下");
    }

    private void search(String key) {
        subscription = Network.getZhuangbiApi()
                .search(key)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


}
