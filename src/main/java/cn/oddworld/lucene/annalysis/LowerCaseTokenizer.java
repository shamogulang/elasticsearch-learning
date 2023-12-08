package cn.oddworld.lucene.annalysis;

import java.io.IOException;
import java.io.Reader;

public class LowerCaseTokenizer extends Tokennizer {

    public LowerCaseTokenizer(Reader in){
        input = in;
    }

    private int offset =0;
    private int bufferIndex = 0;
    private int dataLen = 0;
    private final  static int MAX_WORD_LEN = 255;
    private final static  int IO_BUFFER_SIZE = 1024;
    private  final char[] buffer = new char[MAX_WORD_LEN];
    private final char[] ioBuffer = new char[IO_BUFFER_SIZE];

    @Override
    public Token next() throws IOException {
        int length = 0;
        int start = offset;
        while (true){
           final char c;
           offset++;
           if(bufferIndex >= dataLen){
               dataLen = input.read(ioBuffer);
               bufferIndex = 0;
           }
           if(dataLen == -1){
               if(length >0){
                   break;
               }else {
                   return null;
               }
           }else {
               c = (char) ioBuffer[bufferIndex++];
           }

           if(Character.isLetter(c)){

               if(length == 0){
                   start = offset -1;
               }
               buffer[length++] = Character.toLowerCase(c);

               if(length == MAX_WORD_LEN){
                   break;
               }
           }else if(length > 0){
               break;
           }

        }
        return new Token(new String(buffer, 0, length), start, start+length);
    }
}
