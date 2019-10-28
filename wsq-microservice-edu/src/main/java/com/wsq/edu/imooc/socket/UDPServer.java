package com.wsq.edu.imooc.socket;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * @author xyzzg
 * @version 1.0
 * @date 2019-10-28 19:39
 */
public class UDPServer {

    public static void main(String[] args) throws Exception{
        DatagramSocket datagramSocket = new DatagramSocket(65001);
        byte[] bytes = new byte[100];
        DatagramPacket packet = new DatagramPacket(bytes,bytes.length);
        //接收客户端内容 封装为DatagramPacket
        datagramSocket.receive(packet);

        //真正的数据
        byte[] data = packet.getData();
        String content = new String(data,0,packet.getLength());

        //将要发送给客户端的数据转换成二进制 （简写了）
        byte[] sendContent = String.valueOf(content.length()).getBytes();
        //发送数据报
        DatagramPacket datagramPacket = new DatagramPacket(sendContent,sendContent.length,packet.getAddress(),packet.getPort());
        datagramSocket.send(datagramPacket);
    }
}
