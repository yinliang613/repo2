package cn.suse.net.UDPchat;



import java.net.SocketException;

public class Talk1 {
    public static void main(String[] args) throws SocketException {
        new Thread(new TalkReceive(8888,"talk2")).start();
        new Thread(new TalkSend(6666,"localhost",9999)).start();

    }
}
