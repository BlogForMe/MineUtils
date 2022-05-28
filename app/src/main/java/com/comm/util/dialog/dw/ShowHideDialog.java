package com.comm.util.dialog.dw;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import com.comm.util.R;

import static com.comm.util.dialog.dw.DialogManger.TAG_COME;
import static com.comm.util.dialog.dw.DialogManger.TAG_LIKE;

public class ShowHideDialog extends DialogFragment {
    private static final String TAG_EXPRESS = "tag_expression";

    public static ShowHideDialog newIntance(int like) {
        Bundle bundle = new Bundle();
        bundle.putInt(TAG_EXPRESS, like);
        ShowHideDialog showHide = new ShowHideDialog();
        showHide.setArguments(bundle);
        return showHide;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setStyle(STYLE_NO_FRAME, R.style.PauseDialog);
    }

    @Override
    public void onStart() {
        super.onStart();
        Window window = getDialog().getWindow();
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        layoutParams.gravity = Gravity.CENTER; //居中显示
        window.setWindowAnimations(R.style.DialogAnimation);
        window.setAttributes(layoutParams);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_show, container);
        if (getArguments() != null) {
            int tagLike = getArguments().getInt(TAG_EXPRESS, TAG_LIKE);
            if (tagLike == TAG_COME) {
                ((ImageView) view).setImageResource(R.mipmap.ble_come_on);
            }
        }
        return view;
    }

}
