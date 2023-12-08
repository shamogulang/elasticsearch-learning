package cn.oddworld.lucene.demo;

import cn.oddworld.lucene.fs.FsInputStream;
import cn.oddworld.lucene.fs.FsOutputStream;

import java.io.File;

public class Client {

    public static void main(String[] args) throws Exception {

        FsOutputStream os = new FsOutputStream(new File("./test"));
        os.writeString("hello world");
        os.close();

        FsInputStream fsInputStream = new FsInputStream(new File("./test"));
        //randomFIleInputStream.seek(2);
        String s = fsInputStream.readString();
        System.out.println(s);
    }
}
