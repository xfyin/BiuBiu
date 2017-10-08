package letus179.com.biubiu.myall;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import letus179.com.biubiu.MainActivity;
import letus179.com.biubiu.R;
import letus179.com.biubiu.common.BasicActivity;

public class MySettingActivity extends BasicActivity implements View.OnClickListener {

    // 我的账户与安全
    private LinearLayout my_setting_account_safe;

    // 消息通知, 黑名单, 收货地址
    private TextView my_setting_notify, my_setting_blacklist, my_setting_address, my_setting_suggestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_setting);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        String title = getIntent().getStringExtra("title");
        final String slide_menu = getIntent().getStringExtra("slide_menu");
        setupBackAsUp(title, true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ("true".equals(slide_menu)) {
                    Intent intent = new Intent(MySettingActivity.this, MainActivity.class);
                    intent.putExtra("slide_menu", "true");
                    startActivity(intent);
                    finish();
                    overridePendingTransition(R.anim.left_slide_in, R.anim.right_slide_out);
                } else {
                    setResult(RESULT_OK, new Intent());
                    finish();
                    overridePendingTransition(R.anim.left_slide_in, R.anim.right_slide_out);
                }
            }
        });
        overridePendingTransition(R.anim.right_slide_in, R.anim.left_slide_out);

        initViewAndClick();
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.my_setting_account_safe:
                intent = new Intent(MySettingActivity.this, MyAccountActivity.class);
                intent.putExtra("title", "账户与安全");
                startActivity(intent);
                break;
            case R.id.my_setting_notify:
                intent = new Intent(MySettingActivity.this, MyNotifyActivity.class);
                intent.putExtra("title", "消息通知");
                startActivity(intent);
                break;
            case R.id.my_setting_blacklist:
                intent = new Intent(MySettingActivity.this, MyBlacklistActivity.class);
                intent.putExtra("title", "黑名单");
                startActivity(intent);
                break;
            case R.id.my_setting_address:
                intent = new Intent(MySettingActivity.this, MyAddressActivity.class);
                intent.putExtra("title", "管理收货地址");
                startActivity(intent);
                break;
            case R.id.my_setting_suggestion:
                intent = new Intent(MySettingActivity.this, MySuggestionActivity.class);
                intent.putExtra("title", "意见与建议");
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    /**
     * 初始化布局并设置点击事件
     */
    private void initViewAndClick() {
        my_setting_account_safe = (LinearLayout) findViewById(R.id.my_setting_account_safe);
        my_setting_notify = (TextView) findViewById(R.id.my_setting_notify);
        my_setting_blacklist = (TextView) findViewById(R.id.my_setting_blacklist);
        my_setting_address = (TextView) findViewById(R.id.my_setting_address);
        my_setting_suggestion = (TextView) findViewById(R.id.my_setting_suggestion);
        my_setting_account_safe.setOnClickListener(this);
        my_setting_notify.setOnClickListener(this);
        my_setting_blacklist.setOnClickListener(this);
        my_setting_address.setOnClickListener(this);
        my_setting_suggestion.setOnClickListener(this);
    }

}
