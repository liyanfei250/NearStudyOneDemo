package base;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lzq.near.R;

import app.AbsSuperApplication;
import butterknife.ButterKnife;

public abstract class BaseActivity extends NearBaseActivity {

    protected Toolbar mToolbar;

    protected TextView toolbar_title;

    protected ImageView right_buttom;

    protected Toast mToast;

    protected ProgressDialog bar;

    protected void init() {

        mToolbar = (Toolbar) findViewById(R.id.id_toolBar);
        setSupportActionBar(mToolbar);

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AbsSuperApplication.pushActivity(this);
    }

    @Override
    protected void onDestroy() {

        super.onDestroy();
        ButterKnife.reset(this);
        AbsSuperApplication.popActivity(this);

    }

    //封装Intent，但是需要携带数据还是需要重新写intent
    public void go(Class cls) {
        startActivity(new Intent(this, cls));

    }

    //String ----> text show
    public void showToast(CharSequence text) {
        if (mToast == null) {
            mToast = Toast.makeText(this, text, Toast.LENGTH_LONG);

        }
        mToast.setText(text);
        mToast.show();

    }

    //getResources -----> text show
    public void showToast(@StringRes int res) {
        showToast(getResources().getText(res));

    }

    //show ProgressDialog
    public void showProgress(String msg) {
        if (bar == null) {
            bar = new ProgressDialog(this);
            bar.setMessage(msg);
            bar.setIndeterminate(true);
            bar.setCancelable(false);
        }

        if (bar.isShowing()) {
            bar.setMessage(msg);
        }
        bar.show();

    }

    //hide ProgressDialog
    public void dismissDialog() {
        if (bar != null && bar.isShowing()) {
            bar.setCancelable(false);
            bar.dismiss();
        }

    }

    //click menu and event
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    protected FragmentManager fragmentManager;


    protected abstract int getLayoutId();

    /**
     * 1, 设置TAG 2, 避免重复创建
     *
     * @param containId 存放fragment布局的布局id
     * @param clz       要存放 fragment 的 类名
     */
    public void replaceFragment(int containId, Class<? extends Fragment> clz) {
        replaceFragmentWithArgs(containId, clz, null);
    }

    public void replaceFragmentWithArgs(int containId, Class<? extends Fragment> clz, Bundle bundle) {
        String TAG = clz.getSimpleName();
        Fragment fragment = fragmentManager.findFragmentByTag(TAG);
        if (fragment == null) {
            try {
                fragment = clz.newInstance();
                if (bundle != null) {
                    fragment.setArguments(bundle);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            if (bundle != null) {
                fragment.setArguments(bundle);
            }
        }

        fragmentManager.beginTransaction()
                .replace(containId, fragment, TAG)
                .addToBackStack(TAG)
                .commitAllowingStateLoss();
    }

    protected void addFragment(int containId, Class<?> clz, boolean isAddBackStack) {
        addFragment(containId, clz, isAddBackStack, null);
    }

    protected void addFragment(int containId, Class<?> clz, boolean isAddBackStack, Bundle bundle) {
        String TAG = clz.getSimpleName();
        Fragment fragment = fragmentManager.findFragmentByTag(TAG);
        if (fragment == null) {
            try {
                fragment = (Fragment) clz.newInstance();
                if (bundle != null) {
                    fragment.setArguments(bundle);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

//        FragmentTransaction transaction = fragmentManager.beginTransaction();
//        if (fragment != null && fragment.isAdded()){
//            transaction.add(containId, fragment, TAG);
//        }
//        if (isAddBackStack){
//            transaction.addToBackStack(TAG);
//        }
//        transaction.commit();
        if (fragmentManager.getFragments() != null && fragmentManager.getFragments().size() >= 1) {
            for (Fragment f : fragmentManager.getFragments()) {
                fragmentManager.beginTransaction()
                        .hide(f)
                        .commit();
            }
        }

        if (fragment != null && fragment.isAdded()) {
            fragmentManager.beginTransaction()
                    .show(fragment)
                    .commit();
        } else {
            fragmentManager.beginTransaction()
                    .add(containId, fragment, TAG)
                    .addToBackStack(isAddBackStack ? TAG : null)
                    .show(fragment)
                    .commitAllowingStateLoss();
        }
    }

   /* @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        System.out.println(fragmentManager.getBackStackEntryCount());
        if (fragmentManager.getBackStackEntryCount() > 1) {
            fragmentManager.popBackStack();
            return true;
        } else {
            //// TODO: 6/20/16  双击 退出 程序
            finish();
            return true;
        }
    }*/



}
