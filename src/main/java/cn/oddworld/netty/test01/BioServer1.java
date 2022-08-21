package cn.oddworld.netty.test01;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class BioServer1 {


    /**
     * 开启一个线程对数据的读取进行处理。
     * 这里的逻辑相当于了单独用了main线程来处理请求的连接
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(3333);

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(3,3,0, TimeUnit.DAYS, new LinkedBlockingDeque<>());
        while (true){
            System.out.println("服务端已经启动,等待客户端连接。。。");
            Socket accept = serverSocket.accept();
            System.out.println("连接到客户端,线程="+ Thread.currentThread().getName());

            OutputStream outputStream = accept.getOutputStream();
            outputStream.write("server connect success, send data...".getBytes());
            outputStream.flush();

            // 通过socket获取输入流
            InputStream inputStream = accept.getInputStream();
            threadPoolExecutor.submit(() -> {
                byte[] bytes = new byte[1024];
                while (true){
                    System.out.println("开始读取");
                    int read = 0;
                    try {
                        read = inputStream.read(bytes);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.out.println("读取完毕");

                    if(read != -1){
                        System.out.println("thread: "+Thread.currentThread().getName()+" "+new String(bytes,0, read));
                    }else {
                        System.out.println("done...");
                        break;
                    }
                }
            });
        }
    }
}
