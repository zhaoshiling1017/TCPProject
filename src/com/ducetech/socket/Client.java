package com.ducetech.socket;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.net.Socket;

import com.ducetech.utils.ByteUtil;

public class Client {
	
    public static void send() {
        Socket socket = null;
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            socket = new Socket("127.0.0.1", 8088);
            String path = "/data/image/0136617e-39e9-11e7-bddc-000c29af8f90.jpg";
            bis = new BufferedInputStream(new FileInputStream(path));
            bos = new BufferedOutputStream(socket.getOutputStream());
            String dataStr = "fileName=0136617e-39e9-11e7-bddc-000c29af8f90";
            byte[] dataBys = dataStr.getBytes();
            short dataLen = (short) dataBys.length;
            bos.write(ByteUtil.short2Byte(dataLen));
            bos.write(dataBys);
            byte[] buf = new byte[1024];
            int len = 0;
            while ((len = bis.read(buf)) != -1) {
                bos.write(buf, 0, len);
            }
            bos.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (bos != null) {
                    bos.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                if (bis != null) {
                    bis.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                if (socket != null) {
                    socket.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args) {
		send();
	}
}
