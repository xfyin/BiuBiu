package letus179.com.biubiu.myall;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import letus179.com.biubiu.R;
import letus179.com.biubiu.common.BasicActivity;
import letus179.com.biubiu.common.Constants;

public class MyAccountActivity extends BasicActivity implements View.OnClickListener{

    // 绑定手机号, 修改密码, 社交账号绑定（QQ）
    private LinearLayout my_account_phone, my_account_pwd, my_account_social_qq;

    // 绑定的手机号显示 “去绑定或者更滑号码”， 展示绑定的手机号
    private TextView my_account_phone_bingding, my_account_phone_show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        String title = getIntent().getStringExtra("title");
        setupBackAsUp(title, true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyAccountActivity.this, MySettingActivity.class);
                intent.putExtra("title", "设置");
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.left_slide_in, R.anim.right_slide_out);
            }
        });
        overridePendingTransition(R.anim.right_slide_in, R.anim.left_slide_out);

        initViewAndClick();
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.my_account_phone:
                String bingding = my_account_phone_bingding.getText().toString();
                if ("去绑定".equals(bingding)) {
                    intent = new Intent(MyAccountActivity.this, MyAccountPhoneActivity.class);
                    intent.putExtra("title", "绑定手机号");
                    startActivityForResult(intent, Constants.BINGDING_PHONE_NUM);
                } else if ("更换号码".equals(bingding)) {
                    intent = new Intent(MyAccountActivity.this, MyAccountPhoneChangeActivity.class);
                    intent.putExtra("title", "更换绑定手机");
                    intent.putExtra("old_phone", my_account_phone_show.getText().toString());
                    startActivityForResult(intent, Constants.CHANGE_BINGDING_PHONE_NUM);
                }
                break;
            case R.id.my_account_pwd:
                intent = new Intent(MyAccountActivity.this, MyAccountPwdActivity.class);
                intent.putExtra("title", "修改密码");
                startActivity(intent);
                break;
            case R.id.my_account_social_qq:
                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case Constants.BINGDING_PHONE_NUM:
                case Constants.CHANGE_BINGDING_PHONE_NUM:
                    my_account_phone_bingding.setText("更换号码");
                    my_account_phone_show.setVisibility(View.VISIBLE);
                    my_account_phone_show.setText(data.getStringExtra("phone"));
                    break;
                default:
                    break;
            }
        }
    }

    private void initViewAndClick() {
        //手机号
        my_account_phone = (LinearLayout) findViewById(R.id.my_account_phone);
        my_account_phone_bingding = (TextView) findViewById(R.id.my_account_phone_bingding);
        my_account_phone_show = (TextView) findViewById(R.id.my_account_phone_show);
        my_account_phone.setOnClickListener(this);

        // 密码
        my_account_pwd = (LinearLayout) findViewById(R.id.my_account_pwd);
        my_account_pwd.setOnClickListener(this);

        my_account_social_qq = (LinearLayout) findViewById(R.id.my_account_social_qq);
        my_account_social_qq.setOnClickListener(this);

    }
}
