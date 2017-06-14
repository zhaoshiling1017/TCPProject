package com.ducetech.socket;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import com.ducetech.utils.ByteUtil;

public class Server {
	
	@SuppressWarnings("resource")
	public static void accept() throws Exception{
        ServerSocket ss = null;
        Socket socket = null;
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        ss = new ServerSocket(8088);
        while (true) {
            try {
                socket = ss.accept();
                bis = new BufferedInputStream(socket.getInputStream());
                String path = "/data/image/1.jpg";
                bos = new BufferedOutputStream(new FileOutputStream(path));
                byte[] dataLen = new byte[2];
                bis.read(dataLen);
                byte[] dataBytes = new byte[ByteUtil.byte2Short(dataLen)];
                int resultLen = bis.read(dataBytes);
                String result = new String(dataBytes, 0, resultLen);
                System.out.println(result);
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
                    if (null != bos) {
                        bos.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                try {
                    if (null != bis) {
                        bis.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                try {
                    if (null != socket) {
                        socket.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
	public static void main(String[] args) throws Exception {
		accept();
	}
}
