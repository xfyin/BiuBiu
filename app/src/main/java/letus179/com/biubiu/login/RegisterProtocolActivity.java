package letus179.com.biubiu.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import letus179.com.biubiu.R;
import letus179.com.biubiu.common.BasicActivity;

public class RegisterProtocolActivity extends BasicActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_protocol);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        String title = getIntent().getStringExtra("search_title");
        setupBackAsUp(title, true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterProtocolActivity.this, RegisterMainActivity.class);
                intent.putExtra("search_title", "注册");
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.left_slide_in, R.anim.right_slide_out);
            }
        });
        overridePendingTransition(R.anim.right_slide_in, R.anim.left_slide_out);
    }
}
