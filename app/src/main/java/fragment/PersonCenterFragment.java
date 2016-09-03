package fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lzq.near.R;
import com.example.lzq.near.activity.LoginActivity;

import base.NearBaseFragment;
import butterknife.ButterKnife;

/**
 * Created by LYF on 2016/9/2.
 */
public class PersonCenterFragment extends NearBaseFragment {
    private TextView tv_login;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_person, null);
        tv_login = (TextView)view.findViewById(R.id.fast_login);
        tv_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginActivity.actionStart(PersonCenterFragment.this.getActivity());
            }
        });
        return view;
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
        ButterKnife.reset(this);
    }
}
