//package com.comm.util.tool.camera;
//
//import android.content.Context;
//import android.os.Handler;
//import android.os.Message;
//import android.text.TextUtils;
//import android.util.Log;
//import android.view.SurfaceView;
//import android.view.ViewGroup;
//
//import io.agora.rtc.IRtcEngineEventHandler;
//import io.agora.rtc.RtcEngine;
//import io.agora.rtc.video.VideoCanvas;
//import io.agora.rtc.video.VideoEncoderConfiguration;
//import timber.log.Timber;
//
///**
// * Created by Administrator on 8/31/2017.
// */
//
//public class CameraAgroa implements ICamera {
//
//    private final static String APP_ID = "60b3cba44af84714b86ea4b3a9f28e8d";
////   private  String APP_ID;
//
//    private String mCameraOneToOneID = null;
//
//    private Context mContext;
//    private RtcEngine mRtcEngine = null;
//
//    private ViewGroup mLocalContainer;
//    private SurfaceView mLocalView;
//
////    private ViewGroup mRemoteLayout;
//
//    private boolean mCheckShowRemote = true;
//    // 连接是否超时
//    private boolean isConnTIMEOUT = false;
//
//    public CameraAgroa(String cameraOneToOneID, ICameraState mICameraState) {
//        mCameraOneToOneID = cameraOneToOneID;
//        this.mICameraState = mICameraState;
//    }
//
//    private ICameraState mICameraState;
//
//    private final IRtcEngineEventHandler mRtcEventHandler = new IRtcEngineEventHandler() {
//        @Override
//        public void onFirstRemoteVideoDecoded(final int uid, int width, int height, int elapsed) { //  远端视频接收解码回调
//            mHandler.sendEmptyMessage(uid);
//            if (mICameraState != null) {
//                mICameraState.onFist(uid, width, height, elapsed);
//            }
//        }
//
//        @Override
//        public void onUserOffline(int uid, int reason) { // 其他用户离开当前频道回调 (onUserOffline)
//            if (mICameraState != null) {
//                mICameraState.onOffLine(uid, 0);
//            }
//        }
//
//        @Override
//        public void onUserMuteVideo(final int uid, final boolean muted) { // 用户静音回调
//
//            if (mICameraState != null) {
//                mICameraState.onMute(uid, muted);
//            }
//        }
//
//
//        @Override
//        public void onUserJoined(int uid, int elapsed) {  // 其他用户加入当前频道回调 (onUserJoined)
//            super.onUserJoined(uid, elapsed);
//            isConnTIMEOUT = false;
//        }
//
//        @Override
//        public void onJoinChannelSuccess(String channel, int uid, int elapsed) { // 加入频道回调
//            super.onJoinChannelSuccess(channel, uid, elapsed);
//            isConnTIMEOUT = true;
//            mHandler.sendEmptyMessageDelayed(0x01, 2 * 60 * 1000);
//        }
//    };
//
//    private Handler mHandler = new Handler() {
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//            switch (msg.what) {
//                case 0x01:  // 主动断开
//                    if (mICameraState != null && isConnTIMEOUT) {
//                        mICameraState.onOffLine(0, 11);
//                        isConnTIMEOUT = false;
//                    }
//                    break;
//                default:
//                    startPlay(msg.what);
//                    break;
//            }
//        }
//    };
//
//    @Override
//    public void init(Context context, ViewGroup viewGroup) {
//
//    }
//
//    @Override
//    public void init(Context context, ViewGroup layout, ViewGroup remoteLaout, int profile) {
//        if (mCameraOneToOneID == null) {
//            new NullPointerException("一对一视频ID为空！");
//            return;
//        }
//        mContext = context;
////        View partialLayout = LayoutInflater.from(context).inflate(R.layout.partial_camera_agroa, null);
////        layout.addView(partialLayout);
////
////        mLocalLayout = (ViewGroup) partialLayout.findViewById(R.id.camera_self);
//        mLocalContainer = layout;
//
//        //创建 RtcEngine 对象
////        try {
////            mRtcEngine = RtcEngine.create(mContext, APP_ID, mRtcEventHandler);
////        } catch (Exception e) {
////            Timber.e("创建视频失败：" + e.getMessage());
////        }
////
////
////
////        //加入视频
////        mRtcEngine.joinChannel(null, mCameraOneToOneID, "", 0);
//        initializeEngine();
//        setupVideoConfig();
//        setupLocalVideo();
//        joinChannel();
//
//    }
//
//    private void initializeEngine() {
//        try {
//            mRtcEngine = RtcEngine.create(mContext, APP_ID, mRtcEventHandler);
//        } catch (Exception e) {
//            Log.e("CameraAgroa", Log.getStackTraceString(e));
//            throw new RuntimeException("NEED TO check rtc sdk init fatal error\n" + Log.getStackTraceString(e));
//        }
//    }
//
//    private void setupVideoConfig() {
//        // In simple use cases, we only need to enable video capturing
//        // and rendering once at the initialization step.
//        // Note: audio recording and playing is enabled by default.
//        mRtcEngine.enableVideo();
//
//        // Please go to this page for detailed explanation
//        // https://docs.agora.io/en/Video/API%20Reference/java/classio_1_1agora_1_1rtc_1_1_rtc_engine.html#af5f4de754e2c1f493096641c5c5c1d8f
//        mRtcEngine.setVideoEncoderConfiguration(new VideoEncoderConfiguration(
//                VideoEncoderConfiguration.VD_640x360,
//                VideoEncoderConfiguration.FRAME_RATE.FRAME_RATE_FPS_15,
//                VideoEncoderConfiguration.STANDARD_BITRATE,
//                VideoEncoderConfiguration.ORIENTATION_MODE.ORIENTATION_MODE_FIXED_PORTRAIT));
//    }
//
//    private void setupLocalVideo() {
//        // This is used to set a local preview.
//        // The steps setting local and remote view are very similar.
//        // But note that if the local user do not have a uid or do
//        // not care what the uid is, he can set his uid as ZERO.
//        // Our server will assign one and return the uid via the event
//        // handler callback function (onJoinChannelSuccess) after
//        // joining the channel successfully.
//        mLocalView = RtcEngine.CreateRendererView(mContext);
//        mLocalView.setZOrderMediaOverlay(true);
//        mLocalContainer.addView(mLocalView);
//        mRtcEngine.setupLocalVideo(new VideoCanvas(mLocalView, VideoCanvas.RENDER_MODE_HIDDEN, 0));
//    }
//
//    private void joinChannel() {
//        // 1. Users can only see each other after they join the
//        // same channel successfully using the same app id.
//        // 2. One token is only valid for the channel name that
//        // you use to generate this token.
//        String token = /*getString(R.string.agora_access_token);*/null;
//        if (TextUtils.isEmpty(token) || TextUtils.equals(token, "#YOUR ACCESS TOKEN#")) {
//            token = null; // default, no token
//        }
//        mRtcEngine.joinChannel(null, "demoChannel1", "Extra Optional Data", 0);
//
//        if (mICameraState != null) {
//            mICameraState.onFist(0, 0, 0, 0);
//        }
//    }
//
//    @Override
//    public void init(Context context, ViewGroup layout, ViewGroup remoteLaout) {
//
//
//    }
//
//    @Override
//    public void startPlay(int uid) {
////        if (mCheckShowRemote) {
//        //远程医生视频
//        SurfaceView remoteVideoView = RtcEngine.CreateRendererView(mContext);
//        if (mRtcEngine == null) {
//            return;
//        }
//        mRtcEngine.setupRemoteVideo(new VideoCanvas(remoteVideoView, VideoCanvas.RENDER_MODE_FIT, uid));
////            mRemoteLayout.addView(remoteVideoView);
//        mLocalContainer.addView(remoteVideoView);
//        Timber.d(remoteVideoView.getMeasuredHeight() + "-=" + remoteVideoView.getMeasuredWidth());
//
//        remoteVideoView.setTag(uid);
////        }
//    }
//
//    @Override
//    public void pause() {
//
//    }
//
//    @Override
//    public void release() {
//        mRtcEngine.leaveChannel();
//        RtcEngine.destroy();
//        mRtcEngine = null;
//    }
//
//    @Override
//    public void checkShowRemote(boolean show) {
//        this.mCheckShowRemote = show;
//    }
//
//    @Override
//    public int setVideoProfile(int profile, boolean videoProfile) {
//        return mRtcEngine.setVideoProfile(profile, videoProfile);
//    }
//
//    public interface ICameraState {
//        void onFist(final int uid, int width, int height, int elapsed);
//
//        void onOffLine(int uid, int reason);
//
//        void onMute(final int uid, final boolean muted);
//    }
//}