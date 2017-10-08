package letus179.com.biubiu.myall;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.necer.ndialog.NDialog;

import letus179.com.biubiu.R;
import letus179.com.biubiu.common.BasicActivity;
import letus179.com.biubiu.edit.ClearEditText;
import letus179.com.biubiu.edit.IEditTextChangeListener;
import letus179.com.biubiu.edit.TextChangeListener;
import letus179.com.biubiu.utils.ValidatorUtils;

/**
 * 设置--意见反馈
 */
public class MySuggestionActivity extends BasicActivity implements View.OnClickListener {

    // 输入的建议以及留下的手机号
    private ClearEditText my_sug_input, my_sug_phone;

    // 输入字数限制
    private TextView my_sug_input_num_count;

    // 发送反馈
    private Button my_sug_send;

    private int maxMun = 350;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_suggestion);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        String title = getIntent().getStringExtra("title");
        setupBackAsUp(title, true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MySuggestionActivity.this, MySettingActivity.class);
                intent.putExtra("title", "设置");
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.left_slide_in, R.anim.right_slide_out);
            }
        });
        overridePendingTransition(R.anim.right_slide_in, R.anim.left_slide_out);

        initViewAndClick();

        //EditView监听
        TextChangeListener textChangeListener = new TextChangeListener(my_sug_send, new IEditTextChangeListener() {
            @Override
            public void textChange(boolean isHasContent) {
                if (isHasContent) {
                    my_sug_send.setEnabled(true);
                    my_sug_send.setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.clr_pressed, null));
                } else {
                    my_sug_send.setEnabled(false);
                    my_sug_send.setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.clr_normal, null));
                }
            }
        });
        // 传入所有要监听的editText都添加进入
        textChangeListener.addAllEditText(my_sug_input);

        // 输入框字数限制
        my_sug_input_num_count.setText("0/350");

        // 输入框监听
        my_sug_input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                my_sug_input_num_count.setText((maxMun - s.length()) + "/" + maxMun);
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.my_sug_send:
                // 0.手机号
                String phone = my_sug_phone.getText().toString();
                if (!TextUtils.isEmpty(phone) && !ValidatorUtils.isMobile(phone)) {
                    Toast.makeText(MySuggestionActivity.this, "手机号不合法", Toast.LENGTH_SHORT).show();
                    return;
                }
                // TODO: 2017/10/8
                String suggestion = my_sug_input.getText().toString();
                Toast.makeText(MySuggestionActivity.this, "手机号:" + phone +",\n具体建议：" + suggestion, Toast.LENGTH_SHORT).show();

                // 对话框
                feedbackDialog();
                break;
            default:
                break;
        }
    }

    /**
     * 初始化View并且设置Click
     */
    private void initViewAndClick() {
        my_sug_input = (ClearEditText) findViewById(R.id.my_sug_input);
        my_sug_phone = (ClearEditText) findViewById(R.id.my_sug_phone);
        my_sug_send = (Button) findViewById(R.id.my_sug_send);
        my_sug_input_num_count = (TextView) findViewById(R.id.my_sug_input_num_count);
        my_sug_send.setOnClickListener(this);
    }

    /**
     * NDialog有三种形式：确认框(此处)、选择框、输入框
     */
    private void feedbackDialog() {
        final NDialog dialog = new NDialog(MySuggestionActivity.this);
        dialog.setMessage("反馈以送达，我们将尽快处理您的反馈，谢谢。").
                setMessageSize(12).
                setMessageCenter(true).
                setNegativeButtonText("").
                setButtonCenter(true).
                setOnConfirmListener(new NDialog.OnConfirmListener() {
                    @Override
                    public void onClick(int which) {
                        //which,0代表NegativeButton，1代表PositiveButton
                        Intent intent = new Intent(MySuggestionActivity.this, MySettingActivity.class);
                        startActivity(intent);
                        finish();
                        overridePendingTransition(R.anim.left_slide_in, R.anim.right_slide_out);
                    }
                }).create(NDialog.CONFIRM).show();
    }


}


