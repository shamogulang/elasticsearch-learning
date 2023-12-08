package cn.oddworld.lucene;

import java.io.IOException;

public abstract class OutputStream {

    protected   static int BUFFER_SIZE = 1024;
    /**
     * content buffer
     */
    private final byte[] buffer = new byte[BUFFER_SIZE];
    /**
     * position in file of buffer
     */
    private long bufferStart = 0;
    /**
     * position in buffer
     */
    private int bufferPosition=0;

    public final  void flush() throws IOException{
        /**
         * how to handle this buffer
         */
        flushBuffer(buffer, bufferPosition);
        bufferStart += bufferPosition;
        /**
         * reset position of this buffer
         */
        bufferPosition = 0;
    }

    abstract  protected  void flushBuffer(byte[] b, int lent) throws IOException;

    public final  void writeByte(byte b) throws IOException{
        if(bufferPosition >= BUFFER_SIZE){
            flush();
        }
        buffer[bufferPosition++] = b;
    }

    /**
     * write length byte of b in this buffer
     * @param b
     * @param length
     */
    public final  void writeBytes(byte[] b, int length) throws IOException{
      for(int i =0; i< length;i++){
          writeByte(b[i]);
      }
    }

    /**
     * write integer in this buffer
     * @param i
     */
    public final  void writeInt(int i) throws IOException{
        writeByte((byte)(i >> 24));
        writeByte((byte)(i >> 16));
        writeByte((byte)(i >> 8));
        writeByte((byte)i);
    }

    /**
     * write valid int into this buffer
     * ex: 7 = 00000000 000000000 000000000 00000111
     * you just need to write 1 byte 00000111 to this buffer
     * @param i
     */
    public final  void writeValInt(int i) throws IOException{

        /**
         * 0x7F = 0000 0000 0000 0000 0000 0000 0111 1111
         * ~0x7F = 1111 1111 1111 1111 1111 1111 1000 0000
         */
        while((i & ~0x7F) != 0){
            /**
             * store low 7th pos of int and set the first byte equal 1
             */
            writeByte((byte)((i & 0x7F) | 0x80));
            /**
             * it unsign right shift with 0 fill and change the value of i
             */
            i >>>= 7;
        }
        writeByte((byte)i);
    }

    public final  void writeLong(long i) throws IOException{
        writeInt((int)(i >> 32));
        writeInt((int)i);
    }

    public final void writeValLong(long i) throws IOException{
        while ((i & ~0x7F) != 0){
            writeByte((byte)((i & 0x7F) | 0x80));
            i >>>= 7;
        }
        writeByte((byte)i);
    }

    public final  void writeString(String s) throws IOException{
        int length = s.length();
        writeValInt(length);
        writeChars(s,0,length);
    }

    public final  void writeChars(String s, int start, int length) throws IOException{
       int end = start + length;
       for(int i = start; i < end;i++){
           int code = s.charAt(i);
           writeValInt(code);
       }
    }

    /**
     * get total length of the file buffer
     * @return
     */
    public final long getFilePosition(){

        return bufferStart + bufferPosition;
    }

    /**
     * seek pos byte in the file
     * @param pos
     */
    public void seek(long pos) throws IOException{
        flush();
        bufferStart = pos;
    }

    /**
     * close the stream
     */
    public void close() throws IOException{
        flush();
    }

    abstract public long length() throws IOException;
}
