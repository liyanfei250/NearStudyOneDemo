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

public class RegisterActivity extends BaseActivity {

    @InjectView(R.id.register_phone_number)
    EditText registerPhoneNumber;
    @InjectView(R.id.register_captcha)
    EditText registerCaptcha;
    @InjectView(R.id.register_password)
    EditText registerPassword;
    @InjectView(R.id.get_captcha)
    ImageView getCaptcha;
    @InjectView(R.id.count_down)
    TextView countDown;
    @InjectView(R.id.register_eye)
    ImageView registerEye;
    @InjectView(R.id.register_button)
    Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.reset(this);
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
