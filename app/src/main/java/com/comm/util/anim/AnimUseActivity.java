package com.comm.util.anim;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import com.comm.util.R;
import com.comm.util.dialog.dw.DialogManger;
import com.comm.util.dialog.dw.ShowHideDialog;

import static com.comm.util.dialog.dw.DialogManger.TAG_COME;
import static com.comm.util.dialog.dw.DialogManger.TAG_LIKE;

/**
 * Android开发艺术探索 3.2.2 使用动画
 */
public class AnimUseActivity extends AppCompatActivity {

    private ImageView ivBtn;
//    private AlertDialog ivDialog;
    private ShowHideDialog ivDialog;
    private DialogManger instance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anim_use);

        ivBtn = findViewById(R.id.iv_btn);
//        View view = getLayoutInflater().inflate(R.layout.dialog_show, null);
//        ivDialog = new AlertDialog.Builder(this)
//                .setView(view)
//                .create();
         instance = DialogManger.getInstance();
    }

    public void btShow(View v) {
//        Animation animShow = AnimationUtils.loadAnimation(this, R.anim.anim_show);
//        ivBtn.startAnimation(animShow);
        DialogManger.getInstance().likeButton(getSupportFragmentManager(),TAG_LIKE);
    }

    public void btCome(View view){
        instance.likeButton(getSupportFragmentManager(),TAG_COME);
    }

    public void btHide(View view) {
//        Animation animShow = AnimationUtils.loadAnimation(this, R.anim.anim_hide);
//        ivBtn.startAnimation(animShow);
    }


}
