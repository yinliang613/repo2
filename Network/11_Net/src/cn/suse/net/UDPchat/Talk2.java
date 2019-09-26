package cn.suse.net.UDPchat;


import java.net.SocketException;

public class Talk2 {
    public static void main(String[] args) throws SocketException {
        new Thread(new TalkReceive(9999,"talk1")).start();
        new Thread(new TalkSend(7777,"localhost",8888)).start();

    }
}
