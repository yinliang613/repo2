package cn.suse.net.UDPchat;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class TalkReceive implements Runnable {
    private int port;
    private DatagramSocket server;
    private String from;

    public TalkReceive(int port, String from) {
        this.port = port;
        try {
            server = new DatagramSocket(port);
        } catch (SocketException e) {
            e.printStackTrace();
        }
        this.from = from;
    }

    @Override
    public void run() {
        while (true) {

            byte[] bytes = new byte[1024 * 60];
            DatagramPacket packet = new DatagramPacket(bytes, 0, bytes.length);
            try {
                server.receive(packet); //阻塞式
                byte[] datas = packet.getData();
                int len = packet.getLength();
                String s = new String(datas, 0, len);
                System.out.println(from +":"+ s);
                if(s.equals("bye")){
                    break;
                }


            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        server.close();


    }
}
