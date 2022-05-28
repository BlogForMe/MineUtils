package com.comm.util.ui.customview.view;

import java.util.ArrayList;
import java.util.List;

import android.os.Handler;

public class StaticReceive {
	/**
	 *  数据类型key——心电文件
	 */
	public static final String DATATYPEKEY_ECG_FILE = "ecgFile";
	/**
	 * 数据类型key——心电波形
	 */
	public static final String DATATYPEKEY_ECG_WAVE = "ecgwave";
//	protected static Context mContext;
	/**
	 * 设备数据消息——设备ID
	 */
	public static final int MSG_DATA_DEVICE_ID = 0x201;
	/**
	 *  设备数据消息——设备版本信息
	 *  device version
	 */
	public static final int MSG_DATA_DEVICE_VS = 0x202;
	/**
	 *  设备数据消息——心电波形数据
	 *  ECG WAVE
	 */
	public static final int MSG_DATA_ECG_WAVE = 0x20d;
	/**
	 *  设备数据消息——电池电量
	 *  Battery Power
	 */
	public static final int MSG_DATA_BATTERY = 0x20e;
	/**
	 * 设备数据消息——心电测量状态改变
	 * ecg measure status change
	 */
	public static final int MSG_DATA_ECG_STATUS_CH = 0x209;
	/**
	 * 设备数据消息——搏动标记
	 * pulse flag
	 */
	public static final int MSG_DATA_PULSE = 0x20f;
	/**
	 * 超时
	 * time out
	 */
	public static final int MSG_DATA_TIMEOUT = 0x210;
	/**
	 * 终端离线
	 * termimal offline
	 */
	public static final int MSG_TERMINAL_OFFLINE = 0x212;
	private final static String TAG = "StaticReceive";
	/** buffer of saving wave ,write to file */
	public static List<Integer> mWaveBuffer = new ArrayList<>();
	public static boolean pause = false;
	public static boolean start = false;
	/**
	 * 保存ECG波形数据,用于绘制波形
	 * save for drawing wave of ECG
	 */
	public static List<Integer> DRAWDATA = new ArrayList<>();
	public static List<Float> mECGReplayBuffer = new ArrayList<>();
	/**
	 * 通知上层各种数据消息
	 * updata UI handler
	 */
	private static Handler mHandler;

	public static void setmHandler(Handler mHandler) {
		StaticReceive.mHandler = mHandler;
	}

}
