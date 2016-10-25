package com.example.lzq.near.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.lzq.near.R;

import base.BaseActivity;
import butterknife.OnClick;

public class RegisterActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
    public static void actionStart(Context context) {
        Intent intent = new Intent(context, RegisterActivity.class);
        context.startActivity(intent);
    }
    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }

    @OnClick({R.id.register_phone_number, R.id.register_captcha, R.id.register_password, R.id.get_captcha, R.id.count_down, R.id.register_eye, R.id.register_button})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.register_phone_number:
                break;
            case R.id.register_captcha:
                break;
            case R.id.register_password:
                break;
            case R.id.get_captcha:
                break;
            case R.id.count_down:
                break;
            case R.id.register_eye:
                break;
            case R.id.register_button:
                break;
        }
    }
}
