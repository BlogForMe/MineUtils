//package com.comm.util.tool.camera;
//
//import android.Manifest;
//import android.content.pm.PackageManager;
//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.FrameLayout;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.core.app.ActivityCompat;
//import androidx.core.content.ContextCompat;
//import androidx.fragment.app.Fragment;
//
//import com.comm.util.R;
//
//import timber.log.Timber;
//
//
//
//public class CameraFragment extends Fragment implements CameraAgroa.ICameraState {
//    private String TAG = getClass().getSimpleName();
//
//    private static final int PERMISSION_REQ_ID = 22;
//
//    private static final String[] REQUESTED_PERMISSIONS = {
//            Manifest.permission.RECORD_AUDIO,
//            Manifest.permission.CAMERA,
//            Manifest.permission.WRITE_EXTERNAL_STORAGE
//    };
//    //private CameraAgroa mCamera;
//
//
//    private TextView mState;
//    private ImageView mMageView;
//
//    private int mCameraTime = 0;
//
////    private FrameLayout remoteLayout;
//
////    private BoxManager mBoxManager;
//
//    private int mProfile = 40;
//
//    private int WAIT_VIDEO_INIT = 0X02;
////    private AppContext mAppContext;
//
//    private final static int[] SUBSCRIBE_IMPORTANT_MESSAGE = new int[]{-1};
//
//    private boolean isNeedToDetail = false;
//    private FrameLayout layoutCamera;
//
//
//    //    Handler handler;
//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        View view = inflater.inflate(R.layout.activity_video_chat_view, container, false);
//
//        layoutCamera = (FrameLayout) view.findViewById(R.id.local_video_view_container);
////        remoteLayout = (FrameLayout) view.findViewById(R.id.camera_doctor);
//
//        // Ask for permissions at runtime.
//        // This is just an example set of permissions. Other permissions
//        mCamera = new CameraAgroa("888", this);
//
//        // may be needed, and please refer to our online documents.
//        if (checkSelfPermission(REQUESTED_PERMISSIONS[0], PERMISSION_REQ_ID) &&
//                checkSelfPermission(REQUESTED_PERMISSIONS[1], PERMISSION_REQ_ID) &&
//                checkSelfPermission(REQUESTED_PERMISSIONS[2], PERMISSION_REQ_ID)) {
//            mCamera.init(getActivity().getApplicationContext(), layoutCamera, null, mProfile);
//        }
//
//        return view;
//    }
//
//
//
//    @Override
//    public void onFist(int uid, int width, int height, int elapsed) {
//        Timber.i("  onFist()");
//    }
//
//    @Override
//    public void onOffLine(int uid, int reason) {
//    }
//
//    @Override
//    public void onMute(int uid, boolean muted) {
//
//    }
//
//
////    @Override
////    public void onGlobalLayout() {
////        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(mMageView.getWidth(), mMageView.getWidth() - 85);
////        layoutParams.bottomMargin = 15;
////        remoteLayout.setLayoutParams(layoutParams);
////    }
//
//
//
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        Timber.tag(TAG).i("onDestroy()");
//        try {
//            mCamera.release();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//        Timber.tag(TAG).i("onDestroyView()");
//    }
//
//
//
//    private boolean checkSelfPermission(String permission, int requestCode) {
//        if (ContextCompat.checkSelfPermission(getActivity(), permission) !=
//                PackageManager.PERMISSION_GRANTED) {
//            ActivityCompat.requestPermissions(getActivity(), REQUESTED_PERMISSIONS, requestCode);
//            return false;
//        }
//
//        return true;
//    }
//
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode,
//                                           @NonNull String[] permissions, @NonNull int[] grantResults) {
//
//        if (requestCode == PERMISSION_REQ_ID) {
//            if (grantResults[0] != PackageManager.PERMISSION_GRANTED ||
//                    grantResults[1] != PackageManager.PERMISSION_GRANTED ||
//                    grantResults[2] != PackageManager.PERMISSION_GRANTED) {
////                showLongToast("Need permissions " + Manifest.permission.RECORD_AUDIO +
////                        "/" + Manifest.permission.CAMERA + "/" + Manifest.permission.WRITE_EXTERNAL_STORAGE);
//                return;
//            }
//            mCamera.init(getActivity().getApplicationContext(), layoutCamera, null, mProfile);
//
//            // Here we continue only if all permissions are granted.
//            // The permissions can also be granted in the system settings manually.
//
//        }
//    }
//
//
//
//}
