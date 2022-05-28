package com.comm.util.bean;

import java.math.BigDecimal;

public class test {

    public static void main(String[] args) {
//        long da = 20190613114700l;
//        System.out.println(da+60);
        String m = "0";
        double bd = new BigDecimal(m).doubleValue();
        System.out.println(bd);

        System.out.println(0.0==bd);
    }
}
