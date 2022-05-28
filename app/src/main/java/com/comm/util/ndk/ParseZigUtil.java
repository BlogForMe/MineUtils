package com.comm.util.ndk;


public class ParseZigUtil {

    public static DeviceValueBean modifyDeviceState(String data) {
        //Log.i("Alan", "data:" + data);
        String[] split = data.split(" ");
        if (split.length >= 23) {
            DeviceValueBean device = new DeviceValueBean();
            //判断两次是否同一个设备
            boolean checkOldSameDevice = false;

            int userCode = Integer.parseInt(split[1], 16);
            int machineCode = Integer.parseInt(split[2] + split[3], 16);
            int mJumpValue = Integer.parseInt(split[10] + split[11] + split[12], 16);

            if (machineCode == 1) {
                int mPressureHighValue = Integer.parseInt(split[4] + split[5] + split[6], 16);
                int mPressureLowValue = Integer.parseInt(split[7] + split[8] + split[9], 16);

                device.setPressureHighValue(mPressureHighValue);
                device.setPressureLowValue(mPressureLowValue);
                device.setJumpValue(mJumpValue);

                checkOldSameDevice = !device.isCheckGls();

                device.setCheckGls(false);
            } else {
                int before = Integer.parseInt(split[4] + split[5] + split[6], 16);
                device.setDataValue(mJumpValue);
                device.setBeforeMeal(before);
                checkOldSameDevice = device.isCheckGls();
                device.setCheckGls(true);
                //Log.i("Alan", before == 0 ? "空腹" : "餐后");
            }
            String date = getDate(split);
            device.setDate(date);
            return device;
        }
        return null;
    }


    /**
     * 16进制转10进制
     *
     * @param tx
     * @return
     */
    public static int hexToInt(String tx) {
        return Integer.parseInt(tx, 16);
    }


    private static String getDate(String[] split) {
        StringBuilder stringBuilder = new StringBuilder();

        int year = Integer.parseInt(split[13] + split[14], 16);
        int month = Integer.parseInt(split[15] + split[16], 16);
        int day = Integer.parseInt(split[17] + split[18], 16);

        int tt = Integer.parseInt(split[19] + split[20], 16);
        int ss = Integer.parseInt(split[21] + split[22], 16);

        stringBuilder.append(year);
        stringBuilder.append(String.format("%02d", month));
        stringBuilder.append(String.format("%02d", day));
        stringBuilder.append(String.format("%02d", tt));
        stringBuilder.append(String.format("%02d", ss));
        return stringBuilder.toString();
    }

    public static String ByteArrayTo16StrInDisplay(int[] src) {
        StringBuilder stringBuilder = new StringBuilder();
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv + " ");
        }
        return stringBuilder.toString();
    }

    public static String hexToByteArray(String inHex) {
        int hexlen = inHex.length();
//        byte[] result;
//        if (hexlen % 2 == 1) {
//            //奇数
//            hexlen++;
//            result = new byte[(hexlen / 2)];
//            inHex = "0" + inHex;
//        } else {
//            //偶数
//            result = new byte[(hexlen / 2)];
//        }
//        int j = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < hexlen; i += 2) {
//            result[j] = hexToByte(inHex.substring(i, i + 2));
//            j++;
            sb.append(inHex.substring(i, i + 2))
                    .append(" ");
        }
        return sb.toString();
    }


    /**
     * 获取data低4位
     *
     * @param data
     * @return
     */
    public static int getLow4(byte data) {
        return (data & 0x0f);
    }

    public static DeviceValueBean getPCpress(String pcData) {
        String data = hexToByteArray(pcData);
        String[] split = data.split(" ");
        DeviceValueBean dvBean = new DeviceValueBean();
        if (split.length > 5) {
            dvBean.setPressureLowValue(hexToInt(split[4]));
            dvBean.setJumpValue(hexToInt(split[5]));
            int highTxt = hexToInt(split[1] + split[2]) & 0X7FFF;
            dvBean.setPressureHighValue(highTxt);
        }
        return dvBean;
    }

    public static int getCurrentPress(String pcData) {

        String data = hexToByteArray(pcData);
        String[] split = data.split(" ");
        if (split.length > 2) {
            return ((hexToInt(split[1]) & 0x0F) << 8) | hexToInt(split[2]);
        }
        return 0;
    }


    /**
     * 取一个字节高几位
     *
     * @param b
     * @param length
     * @return
     */
    public static int getLeftNum(byte b, int length) {
        return b >> (8 - length);
    }


    /**
     * 取一个字节低几位bit
     *
     * @param b
     * @param length
     * @return
     */
    public static int getRightNum(byte b, int length) {
        byte mv = (byte) (0xff >> (8 - length));
        return b & mv;
    }

    /**
     * https://blog.csdn.net/bluestarjava/article/details/83446129
     *
     * @param b
     * @param startIndex 高位从0开始
     * @param endIndex
     * @return
     */
    public static int getMidNum(byte b, int startIndex, int endIndex) {
        byte i = (byte) getLeftNum(b, endIndex + 1);//先取高几位
        return getRightNum(i, endIndex - startIndex + 1);//再取低几位
    }
}
