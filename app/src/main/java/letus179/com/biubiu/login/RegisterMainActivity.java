package letus179.com.biubiu.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import letus179.com.biubiu.R;
import letus179.com.biubiu.common.BasicActivity;
import letus179.com.biubiu.edit.ClearEditText;
import letus179.com.biubiu.edit.IEditTextChangeListener;
import letus179.com.biubiu.edit.TextChangeListener;
import letus179.com.biubiu.utils.TimeCountUtils;

import static letus179.com.biubiu.R.id.agree_protocol;
import static letus179.com.biubiu.R.id.obtain_code;


public class RegisterMainActivity extends BasicActivity implements View.OnClickListener {

    private EditText registerCodeText;
    private Button obtainCodeButton;
    private ClearEditText pwdText;
    private ClearEditText pwdTextAgain;
    private Button registerCompleteButton;
    private CheckBox agreeProtocol;
    private TextView registerProtocol;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        String title = getIntent().getStringExtra("search_title");
        setupBackAsUp(title, true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterMainActivity.this, RegisterActivity.class);
                intent.putExtra("search_title", "注册一下");
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.left_slide_in, R.anim.right_slide_out);
            }
        });
        overridePendingTransition(R.anim.right_slide_in, R.anim.left_slide_out);


        registerCodeText = (EditText) findViewById(R.id.code_text);
        obtainCodeButton = (Button) findViewById(obtain_code);
        pwdText = (ClearEditText) findViewById(R.id.pwd_text);
        pwdTextAgain = (ClearEditText) findViewById(R.id.pwd_text_again);
        registerCompleteButton = (Button) findViewById(R.id.register_complete);
        agreeProtocol = (CheckBox) findViewById(agree_protocol);
        registerProtocol = (TextView) findViewById(R.id.register_protocol);

        obtainCodeButton.setOnClickListener(this);
        registerCompleteButton.setOnClickListener(this);
        registerProtocol.setOnClickListener(this);

        TextChangeListener textChangeListener = new TextChangeListener(registerCompleteButton, new IEditTextChangeListener() {
            @Override
            public void textChange(boolean isHasContent) {
                if (isHasContent) {
                    registerCompleteButton.setEnabled(true);
                    registerCompleteButton.setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.clr_pressed, null));
                } else {
                    registerCompleteButton.setEnabled(false);
                    registerCompleteButton.setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.clr_normal, null));
                }
            }
        });
        // 传入所有要监听的editText都添加进入
        textChangeListener.addAllEditText(registerCodeText, pwdText, pwdTextAgain);

        TimeCountUtils timeCountUtils = new TimeCountUtils(this, obtainCodeButton, 60000, 1000);
        timeCountUtils.start();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case obtain_code:
                // 发送验证码
                TimeCountUtils timeCountUtils = new TimeCountUtils(this, obtainCodeButton, 60000, 1000);
                timeCountUtils.start();
                break;
            case R.id.register_complete:
                if (!agreeProtocol.isChecked()) {
                    Toast.makeText(RegisterMainActivity.this, "请先同意用户《注册协议》", Toast.LENGTH_SHORT).show();
                }
                //注册
                break;
            case R.id.register_protocol:
                // 注册协议
                Intent intent = new Intent(RegisterMainActivity.this, RegisterProtocolActivity.class);
                intent.putExtra("search_title", "注册协议");
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
