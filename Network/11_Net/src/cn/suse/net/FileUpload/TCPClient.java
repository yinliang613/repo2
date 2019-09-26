package cn.suse.net.FileUpload;

import java.io.*;
import java.net.Socket;

public class TCPClient {
    public static void main(String[] args) throws IOException {
        Socket client = new Socket("127.0.0.1",8888);
        OutputStream os = client.getOutputStream();
        InputStream is = client.getInputStream();
        FileInputStream fis = new FileInputStream("C:\\1.png");
        BufferedInputStream bis = new BufferedInputStream(fis);
        int len = 0;
        byte[] bytes = new byte[1024];
        while((len = bis.read(bytes))!=-1){
            os.write(bytes,0,len);
        }
        client.shutdownOutput();
        System.out.println("11111111111");

        int len2 =0;
        byte[] bytes1 = new byte[1024];
        String s =null;
        while((len2 = is.read(bytes1))!=-1){

            s =new String(bytes1,0,len2);
        }

        System.out.println(s);
        fis.close();
        client.close();
    }


}
