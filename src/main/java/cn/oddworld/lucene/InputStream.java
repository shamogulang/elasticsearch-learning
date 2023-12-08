package cn.oddworld.lucene;

import java.io.IOException;

public abstract class InputStream  {

    protected static int BUFFER_SIZE = OutputStream.BUFFER_SIZE;
    private byte[] buffer;
    private char[] chars;

    /**
     * position in file of buffer
     */
    private long bufferStart =0;
    /**
     * end of valid bytes
     */
    private int bufferLength=0;
    /**
     * next byte to read
     */
    private int bufferPosition=0;
    /**
     * set by subclasses
     */
    protected  long length;

    public final  byte readByte() throws IOException {
        if(bufferPosition >= bufferLength){
           refill();
        }
        return buffer[bufferPosition++];
    }

    public final  void readBytes(byte[] b, int offset,int lent) throws  IOException{
        if(lent < BUFFER_SIZE){
            for(int i =0; i < lent;i++){
               b[i+offset] = readByte();
            }
        }else {
            long start = getFilePointer();
            seekInternal(start);
            readInternal(b,offset, lent);
            bufferStart = start + lent;
            bufferPosition = 0;
            bufferLength = 0;
        }
    }
    public final int readInt() throws IOException{
        return (readByte() & 0xFF) << 24 | (readByte() & 0xFF) << 16
                | (readByte() & 0xFF) << 8 | (readByte() & 0xFF);
    }

    public final int readVint() throws IOException{
        byte b = readByte();
        int i = b & 0x7F;
        for(int shift = 7; (b & 0x80) != 0;shift +=7){
            b= readByte();
            i |= (b  & 0x7F) << shift;
        }
        return i;
    }

    public final  long readLong() throws IOException{
        return ((long)readInt()) << 32 | (readInt() & 0xFFFFFFFFL);
    }
    public final long readVlong() throws IOException{
        byte b = readByte();
        long i = b & 0x7F;
        for(int shift =7; (b & 0x80) != 0;shift +=7){
            b = readByte();
            i |= (b & 0x7F) << shift;
        }
        return i;
    }

    public final String readString() throws IOException{
        int length = readVint();
        if(chars == null || length > chars.length){
            chars = new char[length];
        }
        readChars(chars, 0, length);
        return new String(chars, 0,length);
    }

    public final void readChars(char[] buffer, int start, int length) throws IOException{
        for(int i = start; i < length;i++){
            buffer[i] = (char) readVint();
        }
    }


    protected final  void refill() throws  IOException{
        long start = bufferStart + bufferPosition;
        long end = start + BUFFER_SIZE;
        if(end > length){
            end = length;
        }
        bufferLength = (int)(end - start);
        if(bufferLength == 0){
            throw new IOException("read past EOF");
        }
        if(buffer == null){
            buffer = new byte[BUFFER_SIZE];
        }
        readInternal(buffer, 0, bufferLength);
        bufferStart = start;
        bufferPosition = 0;
    }

    abstract protected void readInternal(byte[] b, int offset, int length)
            throws IOException;

    abstract protected void close() throws IOException;

    public final long getFilePointer(){
        return bufferStart+bufferPosition;

    }
    public final void seek(long pos)throws  IOException{
        if(pos>=bufferStart && pos < (bufferStart+bufferLength)){
            bufferPosition = (int)(pos-bufferStart);
        }else{
            bufferStart = pos;
            bufferPosition = 0;
            bufferLength = 0;
            seekInternal(pos);
        }
    }

    abstract protected void seekInternal(long pos) throws IOException;

    @Override
    public Object clone() {
        InputStream clone = null;
        try {
            clone = (InputStream)super.clone();
        } catch (CloneNotSupportedException e) {}

        if (buffer != null) {
            clone.buffer = new byte[BUFFER_SIZE];
            System.arraycopy(buffer, 0, clone.buffer, 0, bufferLength);
        }

        clone.chars = null;

        return clone;
    }

}
