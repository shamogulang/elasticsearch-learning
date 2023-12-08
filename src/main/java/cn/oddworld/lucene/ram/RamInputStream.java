package cn.oddworld.lucene.ram;

import cn.oddworld.lucene.InputStream;

import java.io.IOException;

public class RamInputStream extends InputStream {

    RAMFile file;
    int pointer = 0;

    public RamInputStream(RAMFile file){
        this.file = file;
        length = file.length;
    }

    @Override
    protected void readInternal(byte[] b, int offset, int length) throws IOException {

        int bufferNumber = pointer/InputStream.BUFFER_SIZE;
        int bufferOffset = pointer%InputStream.BUFFER_SIZE;
        int bytesInBuffer = InputStream.BUFFER_SIZE - bufferOffset;
        int bytesToCopy = bytesInBuffer >= length ? length : bytesInBuffer;
        byte[] buffer = (byte[])file.buffers.elementAt(bufferNumber);
        System.arraycopy(buffer, bufferOffset, b, offset, bytesToCopy);

        if(bytesToCopy < length){
            offset += bytesToCopy;
            bytesToCopy = length -bytesToCopy;
            buffer = (byte[])file.buffers.elementAt(bufferNumber+1);
            System.arraycopy(buffer, 0, b, offset, bytesToCopy);
        }
        pointer += length;
    }

    @Override
    protected void close() throws IOException {

    }

    @Override
    protected void seekInternal(long pos) throws IOException {
          pointer = (int)pos;
    }
}
