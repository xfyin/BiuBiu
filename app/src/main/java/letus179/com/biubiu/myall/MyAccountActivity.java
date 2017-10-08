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

    // 绑定手机号
    private LinearLayout my_account_phone;

    // 绑定的手机号显示 “去绑定或者具体手机号”
    private TextView my_account_phone_bingding;

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
                intent = new Intent(MyAccountActivity.this, MyAccountPhoneActivity.class);
                intent.putExtra("title", "绑定手机号");
                startActivityForResult(intent, Constants.BINGDING_PHONE_NUM);
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
                    String phone = data.getStringExtra("phone");
                    my_account_phone_bingding.setText(phone.substring(0, 3) + "****" + phone.substring(7));
                    break;
                default:
                    break;
            }
        }
    }

    private void initViewAndClick() {
        my_account_phone = (LinearLayout) findViewById(R.id.my_account_phone);
        my_account_phone_bingding = (TextView) findViewById(R.id.my_account_phone_bingding);
        my_account_phone.setOnClickListener(this);
    }
}
