package com.comm.util.tool.camera;

import android.content.Context;
import android.view.ViewGroup;

/**
 * Created by Administrator on 8/31/2017.
 */

public interface ICamera {

    void init(Context context, ViewGroup viewGroup);
    void init(Context context, ViewGroup viewGroup, ViewGroup viewGroup1);
    void init(Context context, ViewGroup layout, ViewGroup remoteLaout, int profile);
    void startPlay(int uid);
    void pause();
    void release();
    void checkShowRemote(boolean show);
    int setVideoProfile(int profile, boolean videoProfile);
}
