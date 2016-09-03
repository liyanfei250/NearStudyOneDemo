package com.example.lzq.near.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lzq.near.R;

import base.BaseActivity;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity {

    @InjectView(R.id.login_name)
    EditText loginName;
    @InjectView(R.id.login_password)
    EditText loginPassword;
    @InjectView(R.id.login_button)
    Button loginButton;
    @InjectView(R.id.forget_password)
    TextView forgetPassword;
    @InjectView(R.id.wx_login)
    ImageView wxLogin;
    @InjectView(R.id.qq_login)
    ImageView qqLogin;
    @InjectView(R.id.fast_register)
    TextView fastRegister;
    private TextView fast_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        fast_register = (TextView)findViewById(R.id.fast_register);
        fast_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RegisterActivity.actionStart(LoginActivity.this);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.reset(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }
    public static void actionStart(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }
    @OnClick({R.id.login_name, R.id.login_password, R.id.login_button, R.id.forget_password, R.id.wx_login, R.id.qq_login, R.id.fast_register})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login_name:
                break;
            case R.id.login_password:
                break;
            case R.id.login_button:
                break;
            case R.id.forget_password:
                break;
            case R.id.wx_login:
                break;
            case R.id.qq_login:
                break;
            case R.id.fast_register:
                break;
        }
    }
}
