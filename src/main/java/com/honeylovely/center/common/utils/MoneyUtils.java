package com.honeylovely.center.common.utils;

/**
 * Created by Administrator on 9/30/2016.
 */
public class MoneyUtils {
    public static double convert2Yuan(double amount) {
        return  (double) ((int) (amount * 100)) / 100;
    }

//    public static void main(String[] args) {
//        System.out.println(191200 / 100.0);
//    }
}
