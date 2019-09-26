package cn.suse.net.UDPchat;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;

public class TalkSend implements Runnable{
    private String toIP;
    private int toPort;
    private DatagramSocket client;
    private BufferedReader reader;

    public TalkSend(int port,String toIP, int toPort) throws SocketException {
        this.toIP = toIP;
        this.toPort = toPort;
        client = new DatagramSocket(port);
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    @Override
    public void run() {
        while(true){
            String data;

            try {
                data = reader.readLine();
                byte[] datas = data.getBytes();
                DatagramPacket packet =new DatagramPacket(datas,0,datas.length,
                        new InetSocketAddress(this.toIP,this.toPort));
                //4、发送包裹send​(DatagramPacket p) *
                client.send(packet);
                if(data.equals("bye")){
                    break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            // 5、释放资源
        }
            client.close();

    }
}
