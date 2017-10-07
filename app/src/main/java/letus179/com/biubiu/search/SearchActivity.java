package letus179.com.biubiu.search;

import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import letus179.com.biubiu.R;
import letus179.com.biubiu.common.BasicActivity;

public class SearchActivity extends BasicActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        SearchView searchView = (SearchView) findViewById(R.id.all_search_content);
        searchView.setIconifiedByDefault(false);
        searchView.setQueryHint("搜索用户、物品、话题、资讯");

        TextView textView = (TextView) findViewById(R.id.all_search_text);
        textView.setVisibility(View.VISIBLE);
        textView.setText("取消");
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        // 设置搜索文本监听
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            //当点击搜索按钮时触发该方法
            @Override
            public boolean onQueryTextSubmit(String query) {
                Toast.makeText(SearchActivity.this, "onQueryTextSubmit", Toast.LENGTH_SHORT).show();
                return false;
            }

            // 实时监听，当搜索内容改变时触发该方法
            @Override
            public boolean onQueryTextChange(String newText) {
                Toast.makeText(SearchActivity.this, "onQueryTextChange", Toast.LENGTH_SHORT).show();
                return false;
            }
        });

    }
}
