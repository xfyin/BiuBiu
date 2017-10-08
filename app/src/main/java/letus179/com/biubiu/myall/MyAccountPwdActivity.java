package letus179.com.biubiu.myall;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import letus179.com.biubiu.R;
import letus179.com.biubiu.common.BasicActivity;
import letus179.com.biubiu.edit.ClearEditText;
import letus179.com.biubiu.edit.IEditTextChangeListener;
import letus179.com.biubiu.edit.TextChangeListener;

/**
 * 设置--账户与安全--修改密码
 */
public class MyAccountPwdActivity extends BasicActivity implements View.OnClickListener {

    // 原密码，新密码，再次新密码
    private ClearEditText my_account_pwd_old, my_account_pwd_new, my_account_pwd_new_again;

    // (原密码，新密码，再次新密码) 显示还是隐藏
    private TextView my_account_pwd_old_show_hide, my_account_pwd_new_show_hide, my_account_pwd_new_again_show_hide;

    // 发送按钮
    private Button my_account_pwd_send;

    private boolean isOldShow, isNewShow, isNewShowAgain = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account_pwd);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        String title = getIntent().getStringExtra("title");
        setupBackAsUp(title, true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyAccountPwdActivity.this, MyAccountActivity.class);
                intent.putExtra("title", "账户与安全");
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.left_slide_in, R.anim.right_slide_out);
            }
        });
        overridePendingTransition(R.anim.right_slide_in, R.anim.left_slide_out);

        initViewAndClick();

        //EditView监听
        TextChangeListener textChangeListener = new TextChangeListener(my_account_pwd_send, new IEditTextChangeListener() {
            @Override
            public void textChange(boolean isHasContent) {
                if (isHasContent) {
                    my_account_pwd_send.setEnabled(true);
                    my_account_pwd_send.setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.clr_pressed, null));
                } else {
                    my_account_pwd_send.setEnabled(false);
                    my_account_pwd_send.setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.clr_normal, null));
                }
            }
        });
        // 传入所有要监听的editText都添加进入
        textChangeListener.addAllEditText(my_account_pwd_old, my_account_pwd_new, my_account_pwd_new_again);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.my_account_pwd_old_show_hide:
                if (!isOldShow) {
                    my_account_pwd_old_show_hide.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.pwd_show, null));
                    my_account_pwd_old.setInputType(InputType.TYPE_CLASS_TEXT);
                    isOldShow = true;
                } else {
                    my_account_pwd_old_show_hide.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.pwd_hide, null));
                    my_account_pwd_old.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    isOldShow = false;
                }
                break;
            case R.id.my_account_pwd_new_show_hide:
                if (!isNewShow) {
                    my_account_pwd_new_show_hide.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.pwd_show, null));
                    my_account_pwd_new.setInputType(InputType.TYPE_CLASS_TEXT);
                    isNewShow = true;
                } else {
                    my_account_pwd_new_show_hide.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.pwd_hide, null));
                    my_account_pwd_new.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    isNewShow = false;
                }
                break;
            case R.id.my_account_pwd_new_again_show_hide:
                if (!isNewShowAgain) {
                    my_account_pwd_new_again_show_hide.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.pwd_show, null));
                    my_account_pwd_new_again.setInputType(InputType.TYPE_CLASS_TEXT);
                    isNewShowAgain = true;
                } else {
                    my_account_pwd_new_again_show_hide.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.pwd_hide, null));
                    my_account_pwd_new_again.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    isNewShowAgain = false;
                }
                break;
            case R.id.my_account_pwd_send:
                // TODO: 2017/10/8  
                // 1.校验原密码
                String oldPwd = my_account_pwd_old.getText().toString();
                //2.两次输入的新密码是否一致
                String newPwd = my_account_pwd_new.getText().toString();
                String newPwdAgain = my_account_pwd_new_again.getText().toString();
                if (!newPwd.equals(newPwdAgain)) {
                    Toast.makeText(this, "两次输入的新密码不一致", Toast.LENGTH_SHORT).show();
                    return;
                }

                // 修改 存储
                Toast.makeText(this, "原密码：" + oldPwd + "\n新密码：" + newPwd + "\n确认密码：" + newPwdAgain, Toast.LENGTH_SHORT).show();
                Toast.makeText(this, "修改密码成功", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, MyAccountActivity.class);
                intent.putExtra("title", "账户与安全");
                startActivity(intent);
                finish();
                break;
            default:
                break;
        }
    }


    private void initViewAndClick() {
        my_account_pwd_old = (ClearEditText) findViewById(R.id.my_account_pwd_old);
        my_account_pwd_new = (ClearEditText) findViewById(R.id.my_account_pwd_new);
        my_account_pwd_new_again = (ClearEditText) findViewById(R.id.my_account_pwd_new_again);
        my_account_pwd_old_show_hide = (TextView) findViewById(R.id.my_account_pwd_old_show_hide);
        my_account_pwd_new_show_hide = (TextView) findViewById(R.id.my_account_pwd_new_show_hide);
        my_account_pwd_new_again_show_hide = (TextView) findViewById(R.id.my_account_pwd_new_again_show_hide);
        my_account_pwd_send = (Button) findViewById(R.id.my_account_pwd_send);
        my_account_pwd_old_show_hide.setOnClickListener(this);
        my_account_pwd_new_show_hide.setOnClickListener(this);
        my_account_pwd_new_again_show_hide.setOnClickListener(this);
        my_account_pwd_send.setOnClickListener(this);

    }
}
