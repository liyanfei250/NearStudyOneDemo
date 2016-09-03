package base;

import android.support.v4.app.Fragment;

/**
 * Created by LYF on 2016/9/2.
 */
public abstract class NearBaseFragment extends Fragment{
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            initFragment();
        }
    }


    protected abstract void initFragment();
}
