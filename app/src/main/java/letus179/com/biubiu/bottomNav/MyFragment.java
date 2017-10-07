package letus179.com.biubiu.bottomNav;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;
import letus179.com.biubiu.R;
import letus179.com.biubiu.myall.MySettingActivity;

/**
 * 底部导航栏--我的
 */
public class MyFragment extends Fragment implements View.OnClickListener {

    // biu币， 性别, 消息， 设置
    private ImageView my_all_biu_bi, my_all_info_sex, my_all_message, my_all_setting;

    // 我的信息
    private LinearLayout my_all_info;

    // 图像
    private CircleImageView my_all_info_image;

    // 昵称，签名, 兴趣， 粉丝 ，喜欢
    private TextView my_all_info_nick, my_all_info_signature, my_all_interest, my_all_fansi, my_all_like;

    // 动态，问答，话题，打卡，鉴定，出售，购买
    private View my_all_dynamic, my_all_answer_question, my_all_topic, my_all_clock, my_all_appraise, my_all_sale, my_all_purchase;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        ActionBar actionBar = getActivity().getActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setDisplayShowHomeEnabled(false);
        }

        View view = inflater.inflate(R.layout.my_fragment, container, false);
        initViews(view);
        return view;
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.my_all_biu_bi:
                break;
            case R.id.my_all_message:
                break;
            case R.id.my_all_setting:
                // 设置
                intent = new Intent(getActivity(), MySettingActivity.class);
                intent.putExtra("search_title", "设置");
                startActivity(intent);
                getActivity().finish();
                break;
            case R.id.my_all_info:
                break;
            case R.id.my_all_interest:
                break;
            case R.id.my_all_fan_si:
                break;
            case R.id.my_all_like:
                break;
            case R.id.my_all_dynamic:
                break;
            case R.id.my_all_answer_question:
                break;
            case R.id.my_all_topic:
                break;
            case R.id.my_all_clock:
                break;
            case R.id.my_all_appraise:
                break;
            case R.id.my_all_sale:
                break;
            case R.id.my_all_purchase:
                break;
            default:
                break;

        }

    }

    /**
     * 初始化View 并设置点击事件
     *
     * @param view
     */
    private void initViews(View view) {
        my_all_biu_bi = (ImageView) view.findViewById(R.id.my_all_biu_bi);
        my_all_info_sex = (ImageView) view.findViewById(R.id.my_all_info_sex);
        my_all_message = (ImageView) view.findViewById(R.id.my_all_message);
        my_all_setting = (ImageView) view.findViewById(R.id.my_all_setting);
        my_all_info = (LinearLayout) view.findViewById(R.id.my_all_info);
        my_all_info_image = (CircleImageView) view.findViewById(R.id.my_all_info_image);
        my_all_info_nick = (TextView) view.findViewById(R.id.my_all_info_nick);
        my_all_info_signature = (TextView) view.findViewById(R.id.my_all_info_signature);
        my_all_interest = (TextView) view.findViewById(R.id.my_all_interest);
        my_all_fansi = (TextView) view.findViewById(R.id.my_all_fan_si);
        my_all_like = (TextView) view.findViewById(R.id.my_all_like);
        my_all_dynamic = view.findViewById(R.id.my_all_dynamic);
        my_all_answer_question = view.findViewById(R.id.my_all_answer_question);
        my_all_topic = view.findViewById(R.id.my_all_topic);
        my_all_clock = view.findViewById(R.id.my_all_clock);
        my_all_appraise = view.findViewById(R.id.my_all_appraise);
        my_all_sale = view.findViewById(R.id.my_all_sale);
        my_all_purchase = view.findViewById(R.id.my_all_purchase);

        my_all_biu_bi.setOnClickListener(this);
        my_all_message.setOnClickListener(this);
        my_all_setting.setOnClickListener(this);
        my_all_info.setOnClickListener(this);
        my_all_interest.setOnClickListener(this);
        my_all_fansi.setOnClickListener(this);
        my_all_like.setOnClickListener(this);
        my_all_dynamic.setOnClickListener(this);
        my_all_answer_question.setOnClickListener(this);
        my_all_topic.setOnClickListener(this);
        my_all_clock.setOnClickListener(this);
        my_all_appraise.setOnClickListener(this);
        my_all_sale.setOnClickListener(this);
        my_all_purchase.setOnClickListener(this);
    }

}
