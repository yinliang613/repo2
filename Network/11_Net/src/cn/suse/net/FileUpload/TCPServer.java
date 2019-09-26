package cn.suse.net.FileUpload;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class TCPServer {
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(8888);
        while (true) {
            Socket socket = server.accept();
            new Thread(() -> {
                try {
                    InputStream is = socket.getInputStream();
                    OutputStream os = socket.getOutputStream();
                    File file = new File("D:/Upload");
                    if (!file.exists()) {
                        file.mkdir();
                    }
                    String filename = "suse" + System.currentTimeMillis() + new Random().nextInt(9999) + ".png";
                    FileOutputStream fos = new FileOutputStream(file + "\\" + filename);
                    byte[] bytes = new byte[1024];
                    int len;
                    while ((len = is.read(bytes)) != -1) {
                        fos.write(bytes, 0, len);
                    }

                    os.write("上传成功".getBytes());
                    fos.close();
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }).start();

        }
    }
}
