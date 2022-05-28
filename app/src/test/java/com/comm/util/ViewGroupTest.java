package com.comm.util;

import org.junit.Test;

public class ViewGroupTest {
    protected static final int FLAG_DISALLOW_INTERCEPT = 0x80000;


    @Test
    public void caculateTest() {
        int mGroupFlags = ~FLAG_DISALLOW_INTERCEPT;
        mGroupFlags &= ~FLAG_DISALLOW_INTERCEPT;
        int ff = (mGroupFlags & FLAG_DISALLOW_INTERCEPT);
//        System.out.println(BleUtil.bytes2hex());
        System.out.println(ff);
//        int ff = 0x1;
    }
}
