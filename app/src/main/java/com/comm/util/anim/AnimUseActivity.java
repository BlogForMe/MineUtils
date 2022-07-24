package com.comm.util.anim;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
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
        DialogManger.getInstance().likeButton(getSupportFragmentManager(), TAG_LIKE);
    }

    public void btCome(View view) {
        instance.likeButton(getSupportFragmentManager(), TAG_COME);
    }

    public void btHide(View view) {
        //        Animation animShow = AnimationUtils.loadAnimation(this, R.anim.anim_hide);
        //        ivBtn.startAnimation(animShow);
    }

    private void generics() {
        //List<? extends TextView> textViews =new ArrayList<Button>(); //解除了赋值限制
        //TextView textView = new TextView(this);
        //textViews.add(textView);
        //List<Button> buttons=new ArrayList<>();
        //textView = buttons;

        ArrayList<? extends TextView> textViews = new ArrayList<Button>();
        ArrayList<? super Button> textViews1 = new ArrayList<TextView>();
        //Button button = textViews1.get(0); //报错

        ArrayList<View> views = new ArrayList<>();
        addTextView(views);

        ArrayList<? super Button> textViews2 = new ArrayList<TextView>();

        List<? super TextView> tt = new ArrayList<View>();

        TextView textView = textViews.get(0);

        textViews1.add(new Button(this));

    }

    private void addTextView(List<? super TextView> textViews) {
        TextView textView = new TextView(this);
        textViews.add(textView);
    }

}
