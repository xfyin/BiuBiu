package letus179.com.biubiu.myall;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import letus179.com.biubiu.R;
import letus179.com.biubiu.common.BasicActivity;
import letus179.com.biubiu.edit.ClearEditText;
import letus179.com.biubiu.edit.IEditTextChangeListener;
import letus179.com.biubiu.edit.TextChangeListener;
import letus179.com.biubiu.utils.TimeCountUtils;
import letus179.com.biubiu.utils.ValidatorUtils;

/**
 * 设置--账户与安全--绑定手机
 */
public class MyAccountPhoneActivity extends BasicActivity implements View.OnClickListener {

    // 手机号， 验证码， 密码；
    private ClearEditText my_account_phone_num, my_account_phone_code, my_account_phone_pwd;

    // 获取验证码, 提交
    private Button my_account_phone_get_code, my_account_phone_send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account_phone);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        String title = getIntent().getStringExtra("title");
        setupBackAsUp(title, true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyAccountPhoneActivity.this, MyAccountActivity.class);
                intent.putExtra("title", "账户与安全");
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.left_slide_in, R.anim.right_slide_out);
            }
        });
        overridePendingTransition(R.anim.right_slide_in, R.anim.left_slide_out);

        initViewAndClick();

        TextChangeListener textChangeListener = new TextChangeListener(my_account_phone_send, new IEditTextChangeListener() {
            @Override
            public void textChange(boolean isHasContent) {
                if (isHasContent) {
                    my_account_phone_send.setEnabled(true);
                    my_account_phone_send.setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.clr_pressed, null));
                } else {
                    my_account_phone_send.setEnabled(false);
                    my_account_phone_send.setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.clr_normal, null));
                }
            }
        });
        // 传入所有要监听的editText都添加进入
        textChangeListener.addAllEditText(my_account_phone_num, my_account_phone_code, my_account_phone_pwd);

    }

    @Override
    public void onClick(View v) {

        String phone = my_account_phone_num.getText().toString();

        switch (v.getId()) {
            case R.id.my_account_phone_get_code:
                // 发送验证码之前先校验手机号
                if (!ValidatorUtils.isMobile(phone)) {
                    Toast.makeText(MyAccountPhoneActivity.this, "手机号不合法", Toast.LENGTH_SHORT).show();
                    return;
                }

                // TODO: 2017/10/8  
                TimeCountUtils timeCountUtils = new TimeCountUtils(this, my_account_phone_get_code, 60000, 1000);
                timeCountUtils.start();
                break;
            case R.id.my_account_phone_send:

                String pwd = my_account_phone_pwd.getText().toString();
                if (!ValidatorUtils.isPassword(pwd)) {
                    Toast.makeText(this, "密码不符合要求， 请输入6~12位，由数字、字母及下划线组合的密码", Toast.LENGTH_SHORT).show();
                    return;
                }

                // TODO: 2017/10/8
                Toast.makeText(this, "手机号：" + phone + "\n验证码：" + my_account_phone_code.getText().toString() + "\n密码：" + pwd, Toast.LENGTH_SHORT).show();

                Intent intent = new Intent();
                intent.putExtra("phone", phone);
                setResult(RESULT_OK, intent);
                finish();
                break;
            default:
                break;
        }
    }

    /**
     * 初始化View并设置点击事件
     */
    private void initViewAndClick() {
        my_account_phone_num = (ClearEditText) findViewById(R.id.my_account_phone_num);
        my_account_phone_code = (ClearEditText) findViewById(R.id.my_account_phone_code);
        my_account_phone_pwd = (ClearEditText) findViewById(R.id.my_account_phone_pwd);
        my_account_phone_get_code = (Button) findViewById(R.id.my_account_phone_get_code);
        my_account_phone_send = (Button) findViewById(R.id.my_account_phone_send);
        my_account_phone_num.setOnClickListener(this);
        my_account_phone_code.setOnClickListener(this);
        my_account_phone_pwd.setOnClickListener(this);
        my_account_phone_get_code.setOnClickListener(this);
        my_account_phone_send.setOnClickListener(this);
    }
}
