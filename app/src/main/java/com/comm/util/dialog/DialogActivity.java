package com.comm.util.dialog;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import com.android.util.circledialog.CircleDialog;
import com.comm.util.R;
import com.comm.util.bean.PictureTypeEntity;
import com.comm.util.dialog.dw.BottomDialog;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

public class DialogActivity extends AppCompatActivity {

    private BottomSheetBehavior<View> bottomSheetBehavior;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);

        findViewById(R.id.btConfirm).setOnClickListener(m->{
            new CircleDialog.Builder()
//                        .setTitle("标题")
                    .setWidth(0.3f)
                    .setText("移动认证简介\\n\" +\n" +
                            " \"\\n\" +\n" +
                            " \"1、什么是移动认证\\n\"")
                    .setPositive("确定",v->
                            Toast.makeText(DialogActivity.this, "取消", Toast.LENGTH_SHORT).show()
                    )
                    .setNegative("取消",v->
                        Toast.makeText(DialogActivity.this, "取消", Toast.LENGTH_SHORT).show())
                    .show(getSupportFragmentManager());
        });

        findViewById(R.id.btRecycleView).setOnClickListener(v->{
            ArrayList<PictureTypeEntity>  listData = new ArrayList<>();
            for (int i=0;i<4;i++){
                listData.add(new PictureTypeEntity(i,"周"+i));
            }
//            BaseQuickAdapter rvAdapter = new BaseQuickAdapter(R.layout.listData) {
//                @Override
//                protected void convert(BaseViewHolder helper, Object item) {
//
//                }
//
//                @Override
//                public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
//
//                }
//            }


        });


    }


    private void initListener() {

//        bottomSheetBehavior = BottomSheetBehavior.from(findViewById(R.id.ll_bottom_sheet));
//        bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
//            @Override
//            public void onStateChanged(@NonNull View bottomSheet, int newState) {
//
//            }
//
//            @Override
//            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
//
//            }
//        });
    }


    public void btDialog(View v) {
        InvestPwdDialog invest = new InvestPwdDialog();
        invest.show(getSupportFragmentManager(), "InvestPwdDialog");
    }


    //底部弹出Dialog
    public void btBottomDialog(View v) {
        BottomDialog.newInstance().show(getSupportFragmentManager(),BottomDialog.TAG);
//        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
    }


    @SuppressLint("ValidFragment")
    public class InvestPwdDialog extends DialogFragment {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the Builder class for convenient dialog construction
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setMessage("是否设置投资密码")
                    .setPositiveButton("去设置", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    })
                    .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
            // Create the AlertDialog object and return it
            return builder.create();
        }
    }

}
