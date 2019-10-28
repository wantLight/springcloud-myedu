package com.wsq.edu.imooc.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author xyzzg
 * @version 1.0
 * @date 2019-10-28 19:26
 */
public class TCPServer {
    public static void main(String[] args) throws IOException {
        //创建socket
        ServerSocket serverSocket = new ServerSocket(65000);
        while (true){
            //客户端返回链接信息后返回
            Socket socket = serverSocket.accept();
            //获取客户端请求信息后，执行
            new LengthCalculator(socket).run();
        }
    }
}
