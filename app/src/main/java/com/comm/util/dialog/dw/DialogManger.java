package com.comm.util.dialog.dw;

import java.lang.ref.WeakReference;

import android.os.Handler;
import android.os.Message;
import androidx.fragment.app.FragmentManager;

public class DialogManger {
    public static final int TAG_LIKE = 0X01;
    public static final int TAG_COME = 0X02;
    private static final String TAG_SHOW = "tag_show";
    private static final String TAG_HIDDEN = "tag_hidden";
    private volatile  static DialogManger singleton;
    private  DialogHandler dialogHander;


    private DialogManger() {

    }

    public static DialogManger getInstance(){
        if(singleton==null){
            synchronized (DialogManger.class){
                if (singleton==null){
                    singleton = new DialogManger();
                }
            }
        }
        return  singleton;
    }

    public void likeButton(FragmentManager fm,int tagLike){
        ShowHideDialog ivDialog = ShowHideDialog.newIntance(tagLike);
        dialogHander = new DialogHandler(ivDialog);
        if (ivDialog!=null&&!ivDialog.isAdded()){
            try {
                ivDialog.show(fm,TAG_SHOW);
            }catch (Exception e){
                e.printStackTrace();
            }
            dialogHander.sendEmptyMessageDelayed(0,1500);
        }
    }

    public static class DialogHandler extends Handler {
        WeakReference<ShowHideDialog> dialogWeakReference;
        ShowHideDialog dialog;
        public DialogHandler(ShowHideDialog ivDialog) {
            dialogWeakReference = new WeakReference<>(ivDialog);
            dialog = dialogWeakReference.get();
        }


        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (dialog!=null&&dialog.isAdded()) {
                try {
                    dialog.dismiss();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }
}
