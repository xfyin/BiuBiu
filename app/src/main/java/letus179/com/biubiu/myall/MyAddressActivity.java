package letus179.com.biubiu.myall;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.necer.ndialog.NDialog;

import java.util.ArrayList;
import java.util.List;

import letus179.com.biubiu.R;
import letus179.com.biubiu.adapter.AddressAdapter;
import letus179.com.biubiu.common.BasicActivity;
import letus179.com.biubiu.common.Constants;
import letus179.com.biubiu.entity.MyAddress;

/**
 * 设置--管理收货地址
 */
public class MyAddressActivity extends BasicActivity {

    // 收货地址列表
    private ListView my_address_list;

    // 添加新的地址
    private Button my_address_add;

    // 地址列表
    private List<MyAddress> myAddressList;

    // listView适配器
    private AddressAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_address);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        String title = getIntent().getStringExtra("title");
        setupBackAsUp(title, true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyAddressActivity.this, MySettingActivity.class);
                intent.putExtra("title", "设置");
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.left_slide_in, R.anim.right_slide_out);
            }
        });
        overridePendingTransition(R.anim.right_slide_in, R.anim.left_slide_out);

        my_address_add = (Button) findViewById(R.id.my_address_add);
        my_address_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyAddressActivity.this, MyAddressNewActivity.class);
                intent.putExtra("title", "新增地址");
                startActivityForResult(intent, Constants.ADD_MY_ADDRESS);
                finish();
            }
        });

        my_address_list = (ListView) findViewById(R.id.my_address_list);
        initAddresses();
        adapter = new AddressAdapter(MyAddressActivity.this, R.layout.address_item, myAddressList);
        my_address_list.setAdapter(adapter);
        my_address_list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                //return的值决定是否在长按后再加一个短按动作 true为不加短按,false为加入短按
                deleteDialog(position);
                return true;
            }
        });
    }

    /**
     * 长按后，弹出删除收货地址条目的对话框
     */
    private void deleteDialog(final int position) {
        new NDialog(this)
                .setMessageCenter(true)
                .setMessageSize(16)
                .setMessage("确认删除该收货地址？")
                .setNegativeButtonText("取消")
                .setPositiveButtonText("确定")
                .setNegativeTextColor(Color.parseColor("#D2691E"))
                .setPositiveTextColor(Color.parseColor("#D2691E"))
                .setButtonCenter(true)
                .setButtonSize(14)
                .setCancleable(true)
                .setHasDivider(true)
                .setOnConfirmListener(new NDialog.OnConfirmListener() {
                    @Override
                    public void onClick(int which) {
                        //which,0代表NegativeButton，1代表PositiveButton
                        if (which == 1) {
                            // TODO: 2017/10/4  从DB中删除收货地址
                            myAddressList.remove(position);
                            adapter.notifyDataSetChanged();
                        }
                    }
                }).create(NDialog.CONFIRM).show();
    }

    // TODO: 2017/10/4
    private void initAddresses() {
        myAddressList = new ArrayList<>();
        MyAddress ad = new MyAddress();
        ad.setName("殷小飞");
        ad.setPhone("15201552704");
        ad.setLocation("北京市 北京市 海淀区");
        ad.setDetail("西二旗大街2号楼111111111111111111111401");
        myAddressList.add(ad);
        for (int i = 0; i < 18; i++) {
            ad = new MyAddress();
            ad.setName(i + "殷学飞");
            ad.setPhone("15201552704");
            ad.setLocation("北京市 北京市 海淀区");
            ad.setDetail("西二旗大街2号楼1401");
            myAddressList.add(ad);
        }
    }

}
