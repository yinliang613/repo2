package cn.suse.net.BS;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {


    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(8888);
        while (true) {
            Socket socket = server.accept();
            new Thread(() -> {
                try {
                    InputStream is = socket.getInputStream();

                    BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
                    String line = br.readLine();
                    System.out.println(line);
                    String[] arr = line.split(" ");
                    String htmlpath = arr[1].substring(1);
                    System.out.println(htmlpath);
                    FileInputStream fis = new FileInputStream(htmlpath);
                    OutputStream os = socket.getOutputStream();
                    // 写入HTTP协议响应头,固定写法
                    os.write("HTTP/1.1 200 OK\r\n".getBytes());
                    os.write("Content-Type:text/html\r\n".getBytes());
                    // 必须要写入空行,否则浏览器不解析
                    os.write("\r\n".getBytes());
                    int len = 0;
                    byte[] bytes = new byte[1024];
                    while((len=fis.read(bytes))!=-1){
                        os.write(bytes,0,len);

                    }
                    fis.close();
                    socket.close();

                }catch (IOException e){
                    e.printStackTrace();
                }


            }).start();


        }


    }


}



