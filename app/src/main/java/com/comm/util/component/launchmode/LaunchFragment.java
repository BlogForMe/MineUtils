package com.comm.util.component.launchmode;

import java.util.concurrent.TimeUnit;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.comm.util.R;

/**
 * Created by Administrator on 2017/12/8 0008.
 */

public class LaunchFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_luanch, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        Button textView = view.findViewById(R.id.textView2);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    TimeUnit.MILLISECONDS.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
//                FirstActivity.FragmentStart(getActivity());
            }
        });
    }

}
