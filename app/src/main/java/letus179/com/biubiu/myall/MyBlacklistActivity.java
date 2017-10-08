package letus179.com.biubiu.myall;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import letus179.com.biubiu.R;
import letus179.com.biubiu.common.BasicActivity;

/**
 * 设置--黑名单
 */
public class MyBlacklistActivity extends BasicActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_blacklist);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        String title = getIntent().getStringExtra("title");
        setupBackAsUp(title, true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyBlacklistActivity.this, MySettingActivity.class);
                intent.putExtra("title", "设置");
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.left_slide_in, R.anim.right_slide_out);
            }
        });
        overridePendingTransition(R.anim.right_slide_in, R.anim.left_slide_out);
    }
}
