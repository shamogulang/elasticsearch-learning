package cn.oddworld.lucene.fs;

import cn.oddworld.lucene.OutputStream;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class FsOutputStream extends OutputStream {

    RandomAccessFile file;

    public FsOutputStream(File path) throws IOException {
        if(path.isFile()){
          throw new IOException(path.getName()+" already exists");
        }
        this.file = new RandomAccessFile(path, "rw");
    }

    @Override
    protected void flushBuffer(byte[] b, int lent) throws IOException {
        this.file.write(b, 0, lent);
    }

    @Override
    public final void close() throws IOException{
        super.close();
        file.close();
    }

    @Override
    public final void seek(long pos) throws IOException{
        super.seek(pos);
        file.seek(pos);
    }

    @Override
    public final long length() throws IOException{
        return file.length();
    }

}
