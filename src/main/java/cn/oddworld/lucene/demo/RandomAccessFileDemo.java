package cn.oddworld.lucene.demo;

import java.io.RandomAccessFile;

public class RandomAccessFileDemo {
   public static void main(String[] args) throws Exception{
   
         // create a new RandomAccessFile with filename test
         RandomAccessFile raf = new RandomAccessFile("./test.txt", "rw");
         // write something in the file
         raf.write("Hello world".getBytes());

         System.out.println(raf.length());

         // set the file pointer at 0 position
         raf.seek(2);

       System.out.println(raf.length());


//         // print the string
         System.out.println("" + raf.readLine());
//
//         // set the file pointer at 5 position
//         raf.seek(5);
//
//         // write something in the file
//         raf.writeUTF("This is an example");
//
//         // set the file pointer at 0 position
//         raf.seek(0);
//
//         // print the string
//         System.out.println("" + raf.readUTF());
         
   }
}