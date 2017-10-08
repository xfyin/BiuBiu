package letus179.com.biubiu.bottomNav;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import letus179.com.biubiu.R;
import letus179.com.biubiu.common.SlidingMenu;

public class IndexFragment extends Fragment implements View.OnClickListener {

    private SlidingMenu slidingMenu;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.index_fragment, container, false);
        slidingMenu = (SlidingMenu) getActivity().findViewById(R.id.id_menu);
        TextView index_toggle_menu = (TextView) view.findViewById(R.id.index_toggle_menu);
        index_toggle_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                slidingMenu.toggle();
            }
        });

        return view;
    }


    @Override
    public void onClick(View v) {

    }
}