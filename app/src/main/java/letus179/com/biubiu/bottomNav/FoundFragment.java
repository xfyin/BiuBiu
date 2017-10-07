package letus179.com.biubiu.bottomNav;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import letus179.com.biubiu.R;
import letus179.com.biubiu.search.SearchActivity;

public class FoundFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.found_fragment, container,false);

		TextView text = (TextView) view.findViewById(R.id.index_search_text);
		text.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getActivity(), SearchActivity.class);
				startActivity(intent);
			}
		});
		return view;
	}
}
