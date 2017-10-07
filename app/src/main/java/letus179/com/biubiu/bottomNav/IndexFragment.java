package letus179.com.biubiu.bottomNav;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.ViewDragHelper;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.lang.reflect.Field;

import de.hdodenhof.circleimageview.CircleImageView;
import letus179.com.biubiu.R;
import letus179.com.biubiu.common.SlidingMenu;

public class IndexFragment extends Fragment implements View.OnClickListener {

    private SlidingMenu slidingMenu;

    // 左滑动
    private DrawerLayout mDrawerLayout;

    private NavigationView navView;

    // 图像
    private CircleImageView iconImage;

    // 用户名
    private TextView username;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.index_fragment, container, false);
//        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
//        toolbar.setNavigationIcon(R.drawable.home1);
//        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mTabHost.setVisibility(View.GONE);
//                mDrawerLayout.openDrawer(GravityCompat.START);
//            }
//        });

//        mDrawerLayout = (DrawerLayout) view.findViewById(R.id.drawer_layout);
//        setDrawerLeftEdgeSize(getActivity(), mDrawerLayout, 0.8f);

//        navView = (NavigationView) view.findViewById(R.id.nav_view);
//        RelativeLayout relativeLayout = (RelativeLayout) navView.inflateHeaderView(R.layout.nav_slide_header);
//        iconImage = (CircleImageView) relativeLayout.findViewById(R.id.icon_image);
//        username = (TextView) relativeLayout.findViewById(R.id.username);
//        iconImage.setOnClickListener(this);


//        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.bottom_setting);
//        mMenu = (SlidingMenu) view.findViewById(R.id.id_menu);

        slidingMenu = (SlidingMenu) getActivity().findViewById(R.id.id_menu);


        TextView view1 = (TextView) view.findViewById(R.id.index_toggle_menu);
        view1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                slidingMenu.toggle();
            }
        });

        return view;
    }

    /**
     * 抽屉滑动范围控制
     *
     * @param activity
     * @param drawerLayout
     * @param displayWidthPercentage 占全屏的份额0~1
     */
    private void setDrawerLeftEdgeSize(Activity activity, DrawerLayout drawerLayout, float displayWidthPercentage) {
        if (activity == null || drawerLayout == null)
            return;
        try {
            // find ViewDragHelper and set it accessible
            Field leftDraggerField = drawerLayout.getClass().getDeclaredField("mLeftDragger");
            leftDraggerField.setAccessible(true);
            ViewDragHelper leftDragger = (ViewDragHelper) leftDraggerField.get(drawerLayout);
            // find edgesize and set is accessible
            Field edgeSizeField = leftDragger.getClass().getDeclaredField("mEdgeSize");
            edgeSizeField.setAccessible(true);
            int edgeSize = edgeSizeField.getInt(leftDragger);
            // set new edgesize
            // Point displaySize = new Point();
            DisplayMetrics dm = new DisplayMetrics();
            activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
            edgeSizeField.setInt(leftDragger, Math.max(edgeSize, (int) (dm.widthPixels * displayWidthPercentage)));
        } catch (NoSuchFieldException e) {
            Log.e("NoSuchFieldException", e.getMessage().toString());
        } catch (IllegalArgumentException e) {
            Log.e("IllegalArException", e.getMessage().toString());
        } catch (IllegalAccessException e) {
            Log.e("IllegalAccessException", e.getMessage().toString());
        }
    }

    @Override
    public void onClick(View v) {

    }
}