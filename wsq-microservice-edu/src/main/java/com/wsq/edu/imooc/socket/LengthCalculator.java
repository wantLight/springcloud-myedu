package com.wsq.edu.imooc.socket;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * @author xyzzg
 * @version 1.0
 * @date 2019-10-28 19:28
 */
public class LengthCalculator extends Thread {

    //socket成员变量
    private Socket socket;

    public LengthCalculator(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            OutputStream os  = socket.getOutputStream();
            InputStream is = socket.getInputStream();
            int ch = 0;
            byte[] buff = new byte[1024];
            ch = is.read(buff);
            //byte数组转字符串
            String content = new String(buff,0,ch);
            System.out.println(content);

            os.write(String.valueOf(content.length()).getBytes());
            is.close();
            os.close();
            socket.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
