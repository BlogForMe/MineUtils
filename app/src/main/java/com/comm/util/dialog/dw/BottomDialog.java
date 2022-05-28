package com.comm.util.dialog.dw;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.comm.util.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

/**
 * Created by Administrator on 2017/12/19 0019.
 */

public class BottomDialog extends BottomSheetDialogFragment {
    public static final String TAG = "BottomDialog";


    public static BottomDialog newInstance() {
        Bundle args = new Bundle();
        BottomDialog fragment = new BottomDialog();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_calculator, container, false);

        return view;
    }

    /*  @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        BottomSheetDialog dialog = (BottomSheetDialog) super.onCreateDialog(savedInstanceState);
        View view = getLayoutInflater().inflate(R.layout.dialog_calculator, null);
        dialog.setContentView(view);
        mBehavior = BottomSheetBehavior.from((View) view.getParent());
        return super.onCreateDialog(savedInstanceState);

    }*/
}
