package cn.oddworld.lucene.fs;

import cn.oddworld.lucene.InputStream;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class FsInputStream extends InputStream {

    private class  Descriptor extends RandomAccessFile{
        public long position;

        public Descriptor(File file, String mode) throws FileNotFoundException {
            super(file, mode);
        }
    }
    Descriptor file =null;
    boolean isClone;

    public FsInputStream(File file) throws IOException {
        this.file = new Descriptor(file, "r");
        length = file.length();
    }

    @Override
    protected void readInternal(byte[] b, int offset, int length) throws IOException {
        synchronized (file){
            long position = getFilePointer();
            if(position != file.getFilePointer()){
                file.seek(position);
                file.position = position;
            }
            int total = 0;
            do{
                int i = file.read(b, offset+total, length-total);
                if(i == -1){
                    throw new IOException("read past EOF");
                }
                file.position += i;
                total += i;
            }while (total < length);
        }
    }

    @Override
    protected void close() throws IOException {
       if(!isClone){
           file.close();
       }
    }

    @Override
    protected void seekInternal(long pos) throws IOException {

    }

    @Override
    public Object clone() {
        FsInputStream clone = (FsInputStream)super.clone();
        clone.isClone = true;
        return clone;
    }
}
