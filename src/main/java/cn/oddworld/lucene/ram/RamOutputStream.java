package cn.oddworld.lucene.ram;

import cn.oddworld.lucene.OutputStream;

import java.io.IOException;

public class RamOutputStream extends OutputStream {
    RAMFile file;
    int pointer = 0;

    public RamOutputStream(RAMFile file) {
        this.file = file;
    }

    @Override
    protected void flushBuffer(byte[] b, int lent) throws IOException {
        /**
         * exist buffer cnt of RAMFile buffer 1024 byte
         */
        int bufferNumber = pointer/OutputStream.BUFFER_SIZE;
        /**
         * exist buffer of the last one RAMFile buffer(from 0 to 1024)
         */
        int bufferOffset = pointer%OutputStream.BUFFER_SIZE;
        /**
         * rest buffer of the last RAMFile buffer
         */
        int bytesInBuffer = OutputStream.BUFFER_SIZE - bufferOffset;
        /**
         * rest buffer enough just input the lent buffet
         * not enough just input the rest buffer size buffer
         */
        int bytesToCopy = bytesInBuffer >= lent? lent : bytesInBuffer;
        if(bufferNumber == file.buffers.size()){
            file.buffers.addElement(new byte[OutputStream.BUFFER_SIZE]);
        }
        byte[] buffer = (byte[]) file.buffers.elementAt(bufferNumber);
        System.arraycopy(b,0, buffer, bufferOffset, bytesToCopy);
        if(bytesToCopy < lent){
            int srcOffset = bytesToCopy;
            bytesToCopy = lent - bytesToCopy;
            bufferNumber++;
            if(bufferNumber == file.buffers.size()){
                file.buffers.addElement(new byte[OutputStream.BUFFER_SIZE]);
            }
            buffer =( byte[]) file.buffers.elementAt(bufferNumber);
            System.arraycopy(b, srcOffset, buffer, 0, bytesToCopy );
        }
        pointer += lent;
        if(pointer > file.length){
            file.length = pointer;
        }
        file.lastModified = System.currentTimeMillis();
    }

    public final void close() throws IOException {
        super.close();
    }

    @Override
    public final void seek(long pos) throws IOException {
        super.seek(pos);
        pointer = (int)pos;
    }

    @Override
    public final long length() throws IOException {
        return file.length;
    }

}