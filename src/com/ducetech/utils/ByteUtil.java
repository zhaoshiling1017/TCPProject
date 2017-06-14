package com.ducetech.utils;

public class ByteUtil {
	
	 /**
     * 短整型转换为2位字节数组
     * @param shortVal
     * @return
     */
    public static byte[] short2Byte(int shortVal) {
        byte[] b = new byte[2];
        for (int i = 0; i < 2; i++) {
            b[i] = (byte) (shortVal >> 8 * (1 - i) & 0xFF);
        }
        return b;
    }

    /**
     * 2位字节数组转换为短整型
     * @param b
     * @return
     */
    public static short byte2Short(byte[] b) {
        short shortVal = 0;
        for (int i = 0; i < b.length; i++) {
        	shortVal += (b[i] & 0xFF) << (8 * (1 - i));
        }
        return shortVal;
    }
}
