package cn.oddworld.lucene;

import cn.oddworld.lucene.annalysis.SimpleAnalyzer;
import cn.oddworld.lucene.annalysis.Token;
import cn.oddworld.lucene.annalysis.TokenStream;

import java.io.StringReader;
import java.util.Vector;

public class Test {

    public static void main(String[] args) throws Exception {
        SimpleAnalyzer simpleAnalyzer = new SimpleAnalyzer();
//        TokenStream tokenStream = simpleAnalyzer.tokenStream("123", new StringReader("Netty is an NIO client server framework which enables quick and easy development of network applications such as protocol servers and clients. It greatly simplifies and streamlines network programming such as TCP and UDP socket server.\n" +
//                "\n" +
//                "'Quick and easy' doesn't mean that a resulting application will suffer from a maintainability or a performance issue. Netty has been designed carefully with the experiences earned from the implementation of a lot of protocols such as FTP, SMTP, HTTP, and various binary and text-based legacy protocols. As a result, Netty has succeeded to find a way to achieve ease of development, performance, stability, and flexibility without a compromise"));
        TokenStream tokenStream = simpleAnalyzer.tokenStream("123", new StringReader("你留1在"));
        while (true){
            Token next = tokenStream.next();
            if(next == null){
                break;
            }
            String s = next.termText();
            System.out.println(s + "==>start"+ next.startOffset() +"=="+"end"+next.endOffset());
        }

    }
}
