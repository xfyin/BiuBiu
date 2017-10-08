package letus179.com.biubiu.myall;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.lljjcoder.citypickerview.widget.CityPicker;

import letus179.com.biubiu.R;
import letus179.com.biubiu.common.BasicActivity;
import letus179.com.biubiu.edit.ClearEditText;
import letus179.com.biubiu.edit.IEditTextChangeListener;
import letus179.com.biubiu.edit.TextChangeListener;
import letus179.com.biubiu.entity.MyAddress;
import letus179.com.biubiu.utils.ValidatorUtils;

/**
 * 设置--管理收货地址--新增地址
 */
public class MyAddressNewActivity extends BasicActivity implements View.OnClickListener {

    // 收货人, 联系方式, 详细地址
    private ClearEditText my_address_receiver, my_address_phone, my_address_detail;

    // 所在地址,
    private EditText my_address_location;

    // 新增地址按钮
    private Button my_address_save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_address_new);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        String title = getIntent().getStringExtra("title");
        setupBackAsUp(title, true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyAddressNewActivity.this, MyAddressActivity.class);
                intent.putExtra("title", "管理收货地址");
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.left_slide_in, R.anim.right_slide_out);
            }
        });
        overridePendingTransition(R.anim.right_slide_in, R.anim.left_slide_out);

        initViewAndClick();

        // 编辑收货地址
        MyAddress address = (MyAddress) getIntent().getSerializableExtra("edit_address");
        if (address != null) {
            my_address_receiver.setText(address.getName());
            my_address_phone.setText(address.getPhone());
            my_address_location.setText(address.getLocation());
            my_address_detail.setText(address.getDetail());
        }

        // EditText实现不可编辑解决办法
        my_address_location.setKeyListener(null);

        // 监听按钮
        TextChangeListener textChangeListener = new TextChangeListener(my_address_save, new IEditTextChangeListener() {
            @Override
            public void textChange(boolean isHasContent) {
                if (isHasContent) {
                    my_address_save.setEnabled(true);
                    my_address_save.setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.clr_pressed, null));
                } else {
                    my_address_save.setEnabled(false);
                    my_address_save.setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.clr_normal, null));
                }
            }
        });
        // 传入所有要监听的editText都添加进入
        textChangeListener.addAllEditText(my_address_receiver, my_address_phone, my_address_location, my_address_detail);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.my_address_location:
                chooseArea(v);
                break;
            case R.id.my_address_save:
                String phone = my_address_phone.getText().toString();
                String receiver = my_address_receiver.getText().toString();
                String detail = my_address_detail.getText().toString();
                String location = my_address_location.getText().toString();

                if (!ValidatorUtils.isMobile(phone)) {
                    Toast.makeText(MyAddressNewActivity.this, "手机号不合法", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (receiver.length() <= 1) {
                    Toast.makeText(MyAddressNewActivity.this, "姓名长度为2~12个字", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (detail.length() < 5) {
                    Toast.makeText(MyAddressNewActivity.this, "详细地址长度不能小于5", Toast.LENGTH_SHORT).show();
                    return;
                }

                Toast.makeText(MyAddressNewActivity.this, "收货人：" + receiver + ", \n手机号：" + phone +
                        ", \n所在地区：" + location + ", \n详细地址：" + detail, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MyAddressNewActivity.this, MyAddressActivity.class);
                intent.putExtra("title", "管理收货地址");
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.left_slide_in, R.anim.right_slide_out);
                break;
            default:
                break;
        }
    }

    // 初始化View并Click
    private void initViewAndClick() {
        my_address_receiver = (ClearEditText) findViewById(R.id.my_address_receiver);
        my_address_phone = (ClearEditText) findViewById(R.id.my_address_phone);
        my_address_location = (EditText) findViewById(R.id.my_address_location);
        my_address_detail = (ClearEditText) findViewById(R.id.my_address_detail);
        my_address_save = (Button) findViewById(R.id.my_address_save);
        my_address_location.setOnClickListener(this);
        my_address_save.setOnClickListener(this);
    }

    // 选择所在区域
    private void chooseArea(View view) {
        //判断输入法的隐藏状态
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm.isActive()) {
            imm.hideSoftInputFromWindow(view.getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
            selectAddress();//调用CityPicker选取区域

        }
    }

    // 选择区域
    private void selectAddress() {
        CityPicker cityPicker = new CityPicker.Builder(MyAddressNewActivity.this)
                .textSize(14)
                .title("地址选择")
                .titleBackgroundColor("#FFFFFF")
                .confirTextColor("#696969")
                .cancelTextColor("#696969")
                .province("北京市")
                .city("北京市")
                .district("海淀区")
                .textColor(Color.parseColor("#000000"))
                .provinceCyclic(true)
                .cityCyclic(false)
                .districtCyclic(false)
                .visibleItemsCount(7)
                .itemPadding(10)
                .onlyShowProvinceAndCity(false)
                .build();
        cityPicker.show();
        //监听方法，获取选择结果
        cityPicker.setOnCityItemClickListener(new CityPicker.OnCityItemClickListener() {
            @Override
            public void onSelected(String... citySelected) {
                //省份
                String province = citySelected[0];
                //城市
                String city = citySelected[1];
                //区县（如果设定了两级联动，那么该项返回空）
                String district = citySelected[2];
                //邮编
                String code = citySelected[3];
                //为TextView赋值
                my_address_location.setText(province.trim() + "-" + city.trim() + "-" + district.trim());
            }
        });
    }

}
