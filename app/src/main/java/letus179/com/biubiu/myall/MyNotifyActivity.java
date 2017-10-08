package letus179.com.biubiu.myall;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.ToggleButton;

import letus179.com.biubiu.R;
import letus179.com.biubiu.common.BasicActivity;

public class MyNotifyActivity extends BasicActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    // 官方通知，新粉丝，评价&回复，点赞，私信，打卡
    private ToggleButton my_account_notify_official, my_account_notify_new_fansi, my_account_notify_evaluate, my_account_notify_agree, my_account_notify_personal_letter, my_account_notify_clock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_notify);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        String title = getIntent().getStringExtra("title");
        setupBackAsUp(title, true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyNotifyActivity.this, MySettingActivity.class);
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
        switch (v.getId()) {

            default:
                break;
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        int id = buttonView.getId();
        if (R.id.my_account_notify_official == id) {
            if (isChecked) {
                Toast.makeText(this, "官方通知——开启", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "官方通知——关闭", Toast.LENGTH_SHORT).show();
            }
        } else if (R.id.my_account_notify_new_fansi == id) {
            if (isChecked) {
                Toast.makeText(this, "新粉丝通知——开启", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "新粉丝通知——关闭", Toast.LENGTH_SHORT).show();
            }
        } else if (R.id.my_account_notify_evaluate == id) {
            if (isChecked) {
                Toast.makeText(this, "评价&回复通知——开启", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "评价&回复通知——关闭", Toast.LENGTH_SHORT).show();
            }
        } else if (R.id.my_account_notify_agree == id) {
            if (isChecked) {
                Toast.makeText(this, "点赞通知——开启", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "点赞通知——关闭", Toast.LENGTH_SHORT).show();
            }
        } else if (R.id.my_account_notify_personal_letter == id) {
            if (isChecked) {
                Toast.makeText(this, "私信通知——开启", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "私信通知——关闭", Toast.LENGTH_SHORT).show();
            }
        } else if (R.id.my_account_notify_clock == id) {
            if (isChecked) {
                Toast.makeText(this, "打卡通知——开启", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "打卡通知——关闭", Toast.LENGTH_SHORT).show();
            }
        }
    }

    // 初始化View并设置点击事件
    private void initViewAndClick() {
        my_account_notify_official = (ToggleButton) findViewById(R.id.my_account_notify_official);
        my_account_notify_new_fansi = (ToggleButton) findViewById(R.id.my_account_notify_new_fansi);
        my_account_notify_evaluate = (ToggleButton) findViewById(R.id.my_account_notify_evaluate);
        my_account_notify_agree = (ToggleButton) findViewById(R.id.my_account_notify_agree);
        my_account_notify_personal_letter = (ToggleButton) findViewById(R.id.my_account_notify_personal_letter);
        my_account_notify_clock = (ToggleButton) findViewById(R.id.my_account_notify_clock);
        my_account_notify_official.setOnCheckedChangeListener(this);
        my_account_notify_new_fansi.setOnCheckedChangeListener(this);
        my_account_notify_evaluate.setOnCheckedChangeListener(this);
        my_account_notify_agree.setOnCheckedChangeListener(this);
        my_account_notify_personal_letter.setOnCheckedChangeListener(this);
        my_account_notify_clock.setOnCheckedChangeListener(this);
    }
}