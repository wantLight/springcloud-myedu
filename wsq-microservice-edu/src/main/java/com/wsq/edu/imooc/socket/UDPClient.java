package com.wsq.edu.imooc.socket;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/**
 * @author xyzzg
 * @version 1.0
 * @date 2019-10-28 19:54
 */
public class UDPClient {
    public static void main(String[] args) throws Exception {
        DatagramSocket datagramSocket = new DatagramSocket();
        byte[] buf = "hello xyzzg".getBytes();
        InetAddress address = InetAddress.getByName("127.0.0.1");
        DatagramPacket packet = new DatagramPacket(buf,buf.length,address,65001);
        datagramSocket.send(packet);

        byte[] data = new byte[100];
        DatagramPacket receivedPacket = new DatagramPacket(data,data.length);
        datagramSocket.receive(receivedPacket);

        String content = new String(receivedPacket.getData(),0,receivedPacket.getLength());
        System.out.println(content);
    }
}
