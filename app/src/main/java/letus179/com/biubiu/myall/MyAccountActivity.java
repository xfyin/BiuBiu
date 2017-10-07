package letus179.com.biubiu.myall;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;

import letus179.com.biubiu.MainActivity;
import letus179.com.biubiu.R;
import letus179.com.biubiu.common.BasicActivity;

public class MyAccountActivity extends BasicActivity implements View.OnClickListener{

    // 绑定手机号
    private LinearLayout my_account_phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        String title = getIntent().getStringExtra("search_title");
        setupBackAsUp(title, true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyAccountActivity.this, MainActivity.class);
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
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    private void initViewAndClick() {
        my_account_phone = (LinearLayout) findViewById(R.id.my_account_phone);

        my_account_phone.setOnClickListener(this);
    }
}
