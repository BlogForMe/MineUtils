package com.comm.util;

public class DevicePort {

    static {
        System.loadLibrary("Tdeviceport");
    }

    public static native int Init();

    public static native int Destroy();

    public static native int OpenChannel(int channel, int bdr, int type);

    public static native int CloseChannel(int channel);

    public static native int WriteString(String input);

    public static native String ReadString(int channel);
}
