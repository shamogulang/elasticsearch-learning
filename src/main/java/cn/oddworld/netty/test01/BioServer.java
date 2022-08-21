package cn.oddworld.netty.test01;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class BioServer {


    /**
     * 无法连接多个线程进行客户端的连接
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(3333);

        while (true){
            System.out.println("服务端已经启动,等待客户端连接。。。");
            Socket accept = serverSocket.accept();
            System.out.println("连接到客户端,线程="+ Thread.currentThread().getName());

            OutputStream outputStream = accept.getOutputStream();
            outputStream.write("server connect success, send data...".getBytes());
            outputStream.flush();

            // 通过socket获取输入流
            InputStream inputStream = accept.getInputStream();
            byte[] bytes = new byte[1024];
            while (true){
                System.out.println("开始读取");
                int read = inputStream.read(bytes);
                System.out.println("读取完毕");

                if(read != -1){
                    System.out.println("jeffchan: "+new java.lang.String(bytes,0, read));
                }else {
                    // 退出客户端的时候，会返回-1
                    break;
                }
            }
        }
    }
}
