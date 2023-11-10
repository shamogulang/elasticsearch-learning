package cn.oddworld.http;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Client {

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket();
        socket.connect(new InetSocketAddress("localhost", 8080));

        InputStream in = socket.getInputStream();
        socket.setSoTimeout(10000);
        System.out.println("reading...");
        in.read();
        System.out.println("read end");
    }
}
