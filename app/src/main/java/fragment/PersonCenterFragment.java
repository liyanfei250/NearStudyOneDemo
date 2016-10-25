package fragment;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lzq.near.R;
import com.example.lzq.near.activity.LoginActivity;

import java.io.File;
import java.util.ArrayList;

import app.AbsSuperApplication;
import base.BaseActivity;
import base.NearBaseFragment;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.nereo.multi_image_selector.MultiImageSelectorActivity;
import thinkfreely.changemodelibrary.ChangeModeController;
import util.AppUtils;
import util.CacheCleanUtil;
import util.ImagePickUtil;
import util.ToastUtils;
import util.UpdateUtils;
import view.CircleImageView;

/**
 * Created by LYF on 2016/9/2.
 */
public class PersonCenterFragment extends NearBaseFragment {
    @Bind(R.id.user_image)
    CircleImageView userImage;

    @Bind(R.id.user_pan)
    RelativeLayout userPan;
    @Bind(R.id.rl_cache)
    RelativeLayout rlCache;
    @Bind(R.id.rl_update)
    RelativeLayout rlUpdate;
    @Bind(R.id.rl_night)
    RelativeLayout rlNight;
    @Bind(R.id.system_setting)
    RelativeLayout systemSetting;
    @Bind(R.id.tv_day_or_night)
    TextView tvDay;
    @Bind(R.id.tv_cache_size)
    TextView mTvCacheSize;
    @Bind(R.id.tv_update_size)
    TextView mTvUpdate;

    private TextView tv_login;
    private ArrayList<String> list;
    private static int N = 0;
    private Object version;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        注意这块如果是在fragment中则只需要在activity中声明就好了，还有destroy的方法
//        ChangeModeController.getInstance().init(this.getActivity(),R.attr.class).setTheme(this.getActivity(), R.style.DayTheme, R.style.NightTheme);
        View view = inflater.inflate(R.layout.fragment_person, null);
        ButterKnife.bind(this, view);

        initData();
        getVersion();
        if (AbsSuperApplication.N == 0) {
            tvDay.setText("夜间模式");
        } else {
            tvDay.setText("白天模式");
        }

        tv_login = (TextView) view.findViewById(R.id.fast_login);
        tv_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginActivity.actionStart(PersonCenterFragment.this.getActivity());
            }
        });

        rlNight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (AbsSuperApplication.N == 0) {
                    //2.设置切换夜间活日间模式
                    ChangeModeController.changeNight(PersonCenterFragment.this.getActivity(), R.style.NightTheme);
                    AbsSuperApplication.N = 1;
                    tvDay.setText("白天模式");
                } else {
                    ChangeModeController.changeDay(PersonCenterFragment.this.getActivity(), R.style.DayTheme);
                    AbsSuperApplication.N = 0;
                    tvDay.setText("夜间模式");
                }
            }
        });

        list = new ArrayList<>();
        return view;
    }

    private void initData() {
        try {
            mTvCacheSize.setText(CacheCleanUtil.getTotalCacheSize(getActivity()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void initFragment() {

    }


    public static PersonCenterFragment newInstance() {

        Bundle args = new Bundle();

        PersonCenterFragment fragment = new PersonCenterFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);

    }

    @OnClick(R.id.user_image)
    public void onClick() {
        ImagePickUtil.pickImage(this, list, 2);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == 2) {
                String string = data.getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT).get(0);
                File file = new File(string);
                AbsSuperApplication.kjBitmap.display(userImage, string);
                //具体的操作上传头像，修改的步骤
//                DDMApiHttpClient.updateAvater(file, handler);
            }
        }
    }

    @OnClick({R.id.user_pan, R.id.rl_cache, R.id.rl_update, R.id.rl_night, R.id.system_setting})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.user_pan:
                break;
            case R.id.rl_cache:
                if (mTvCacheSize.getText().toString().equalsIgnoreCase("0K")) {
                    Toast.makeText(this.getActivity(),"暂无缓存可供清理！",Toast.LENGTH_SHORT).show();
                    return;
                }
                new AlertDialog.Builder(getActivity())
                        .setMessage("是否清除缓存?")
                        .setTitle("提示")
                        .setPositiveButton("是", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                CacheCleanUtil.clearAllCache(getActivity());
                                initData();
                                Toast.makeText(PersonCenterFragment.this.getActivity(),"缓存清理成功！",Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("否", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .create()
                        .show();
                break;
            case R.id.rl_update:
//                DialogUtils.createProgressDialog(this.getActivity()).show();

                // 强制检查更新,并添加额外回调用于处理进度框
                UpdateUtils.checkUpdate((BaseActivity)this.getActivity(), true);
                break;
            case R.id.system_setting:
                break;
        }
    }

    public void  getVersion() {
        //获取版本号，这里只是模拟
        String versonSize = AppUtils.getAppVersionName(AbsSuperApplication.getContext());
        ToastUtils.showToast(this.getActivity(),"最新版本="+versonSize);
        if(versonSize.equals("0")){
            mTvUpdate.setText("最新版本");
        }else{
            mTvUpdate.setText(versonSize);
        }

    }
}
