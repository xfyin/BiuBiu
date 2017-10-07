package letus179.com.biubiu;

import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.content.res.ResourcesCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;

import letus179.com.biubiu.common.BasicActivity;
import letus179.com.biubiu.bottomNav.TabDb;

public class MainActivity extends BasicActivity implements TabHost.OnTabChangeListener {

    // 底部导航
    private FragmentTabHost mTabHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //初始化FragmentTabHost
        initHost();
        //初始化底部导航栏
        initTab();
        //默认选中
        mTabHost.onTabChanged(TabDb.getTabsTxt()[0]);

    }

    @Override
    public void onTabChanged(String tabId) {
        //从分割线中获得多少个切换界面
        TabWidget tabWidget = mTabHost.getTabWidget();
        for (int i = 0; i < tabWidget.getChildCount(); i++) {
            View v = tabWidget.getChildAt(i);
            TextView tv = (TextView) v.findViewById(R.id.foot_tv);
            ImageView iv = (ImageView) v.findViewById(R.id.foot_iv);
            //修改当前的界面按钮颜色图片
            if (i == mTabHost.getCurrentTab()) {
                tv.setTextColor(ResourcesCompat.getColor(getResources(), R.color.tab_light_color, null));
                iv.setImageResource(TabDb.getTabsImgLight()[i]);
            } else {
                tv.setTextColor(ResourcesCompat.getColor(getResources(), R.color.tab_color, null));
                iv.setImageResource(TabDb.getTabsImg()[i]);
            }
        }
    }

    private void initTab() {
        String[] tabs = TabDb.getTabsTxt();
        for (int i = 0; i < tabs.length; i++) {
            //新建TabSpec
            TabHost.TabSpec tabSpec = mTabHost.newTabSpec(TabDb.getTabsTxt()[i]);
            //设置view
            View view = LayoutInflater.from(this).inflate(R.layout.nav_bootom_item, null);
            ((TextView) view.findViewById(R.id.foot_tv)).setText(TabDb.getTabsTxt()[i]);
            ((ImageView) view.findViewById(R.id.foot_iv)).setImageResource(TabDb.getTabsImg()[i]);
            tabSpec.setIndicator(view);
            //加入TabSpec
            mTabHost.addTab(tabSpec, TabDb.getFragment()[i], null);
        }
    }

    private void initHost() {
        mTabHost = (FragmentTabHost) findViewById(R.id.main_tab);
        //调用setup方法 设置view
        mTabHost.setup(this, getSupportFragmentManager(), R.id.main_view);
        //去除分割线
        mTabHost.getTabWidget().setDividerDrawable(null);
        //监听事件
        mTabHost.setOnTabChangedListener(this);
    }


}
