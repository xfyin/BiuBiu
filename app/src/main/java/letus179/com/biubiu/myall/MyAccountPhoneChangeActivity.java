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
 * 设置--账户与安全--绑定手机--更换
 */
public class MyAccountPhoneChangeActivity extends BasicActivity implements View.OnClickListener {

    // 原密码，更换后的手机号，验证码
    private ClearEditText my_account_phone_change_pwd, my_account_phone_change_num, my_account_phone_change_code;

    // 获取验证码按钮， 发送按钮
    private Button my_account_phone_change_get_code, my_account_phone_change_send;

    // 原手机号
    private String oldPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account_phone_change);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        String title = getIntent().getStringExtra("title");
        oldPhone = getIntent().getStringExtra("old_phone");
        setupBackAsUp(title, true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyAccountPhoneChangeActivity.this, MyAccountPhoneActivity.class);
                intent.putExtra("title", "账户与安全");
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.left_slide_in, R.anim.right_slide_out);
            }
        });
        overridePendingTransition(R.anim.right_slide_in, R.anim.left_slide_out);

        //初始化View并设置Click事件
        initViewAndClick();

        // 对EditView监听
        TextChangeListener textChangeListener = new TextChangeListener(my_account_phone_change_send, new IEditTextChangeListener() {
            @Override
            public void textChange(boolean isHasContent) {
                if (isHasContent) {
                    my_account_phone_change_send.setEnabled(true);
                    my_account_phone_change_send.setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.clr_pressed, null));
                } else {
                    my_account_phone_change_send.setEnabled(false);
                    my_account_phone_change_send.setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.clr_normal, null));
                }
            }
        });
        // 传入所有要监听的editText都添加进入
        textChangeListener.addAllEditText(my_account_phone_change_pwd, my_account_phone_change_num, my_account_phone_change_code);
    }

    @Override
    public void onClick(View v) {
        String newPhone = my_account_phone_change_num.getText().toString();
        switch (v.getId()) {
            case R.id.my_account_phone_change_get_code:
                // 发送验证码之前先校验手机号
                if (!ValidatorUtils.isMobile(newPhone)) {
                    Toast.makeText(this, "手机号不合法", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (newPhone.equals(oldPhone)) {
                    Toast.makeText(this, "该手机号已经绑定", Toast.LENGTH_SHORT).show();
                    return;
                }

                // TODO: 2017/10/8
                TimeCountUtils timeCountUtils = new TimeCountUtils(this, my_account_phone_change_get_code, 60000, 1000);
                timeCountUtils.start();
                break;
            case R.id.my_account_phone_change_send:
                // TODO: 2017/10/8
                // 1 校验密码是否正确
                String pwd = my_account_phone_change_pwd.getText().toString();
                // 2，校验验证码是否正确
                String code = my_account_phone_change_code.getText().toString();
                // 3存储并设置
                Toast.makeText(this, "新手机号：" + newPhone + "\n验证码：" +code + "\n密码：" + pwd, Toast.LENGTH_SHORT).show();

                Intent intent = new Intent();
                intent.putExtra("phone", newPhone);
                setResult(RESULT_OK, intent);
                finish();
                break;
            default:
                break;
        }
    }

    /**
     * 初始化View并设置Click事件
     */
    private void initViewAndClick() {
        my_account_phone_change_pwd = (ClearEditText) findViewById(R.id.my_account_phone_change_pwd);
        my_account_phone_change_num = (ClearEditText) findViewById(R.id.my_account_phone_change_num);
        my_account_phone_change_code = (ClearEditText) findViewById(R.id.my_account_phone_change_code);
        my_account_phone_change_get_code = (Button) findViewById(R.id.my_account_phone_change_get_code);
        my_account_phone_change_send = (Button) findViewById(R.id.my_account_phone_change_send);
        my_account_phone_change_get_code.setOnClickListener(this);
        my_account_phone_change_send.setOnClickListener(this);

    }
}
