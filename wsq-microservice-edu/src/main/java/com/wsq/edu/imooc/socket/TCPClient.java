package com.wsq.edu.imooc.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * @author xyzzg
 * @version 1.0
 * @date 2019-10-28 19:29
 */
public class TCPClient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("172.17.7.106",8081);
        OutputStream os = socket.getOutputStream();
        InputStream is = socket.getInputStream();

        os.write(new String("a88130303030303036383332353037313201001c3a0125252531303030363905010000000a00020000022600030000012a0004000007e500050000000100c41d3a").getBytes());
        int ch = 0;
        byte[] buff = new byte[1024];
        ch = is.read(buff);


        String content = new String(buff,0,ch);
        System.out.println(content);

        is.close();
        os.close();
        socket.close();
    }
}
