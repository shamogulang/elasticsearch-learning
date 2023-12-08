package cn.oddworld.lucene.fs;

import cn.oddworld.lucene.Directory;
import cn.oddworld.lucene.InputStream;
import cn.oddworld.lucene.OutputStream;

import java.io.File;
import java.io.IOException;
import java.util.Hashtable;

public class FsDirectory extends Directory {

    private static final Hashtable DIRECTORS = new Hashtable();

    public static FsDirectory getDirectory(String path, boolean create) throws IOException{
        return getDirectory(new File(path), create);
    }

    public static FsDirectory getDirectory(File file, boolean create) throws IOException{
        file = new File(file.getCanonicalPath());
        FsDirectory dir;
        synchronized (DIRECTORS){
            dir = (FsDirectory)DIRECTORS.get(file);
            if(dir == null){
                dir = new FsDirectory(file, create);
                DIRECTORS.put(file, dir);
            }
        }
        synchronized (dir) {
            dir.refCount++;
        }
        return dir;
    }

    private File directory = null;
    private int refCount;

    private FsDirectory(File path, boolean create) throws IOException{
            directory = path;
            if(!directory.exists() && create){
                directory.mkdir();
            }
            if(!directory.isDirectory()){
                throw new IOException(path+" not a dir");
            }
            if(create){
                // clear old files
                String[] files = directory.list();
                for(int i =0; i < files.length;i++){
                    File file = new File(directory, files[i]);
                    if(!file.delete()){
                        throw new IOException("couldn't delete " + files[i]);
                    }
                }
            }
    }

    /**
     * returns an array of strings, one for each file in the directory
     * @return
     * @throws IOException
     * @throws SecurityException
     */
    @Override
    public String[] list() throws IOException, SecurityException {
        return directory.list();
    }

    @Override
    public boolean fileExists(String name) throws IOException, SecurityException {
        File file = new File(directory, name);
        return file.exists();
    }

    @Override
    public long fileModified(String name) throws IOException, SecurityException {
        File file = new File(directory, name);
        return file.lastModified();
    }

    public static final  long fileModified(File directory, String name) throws IOException, SecurityException {
        File file = new File(directory, name);
        return file.lastModified();
    }

    @Override
    public void deleteFile(String name) throws IOException, SecurityException {
        File file = new File(directory, name);
        if(!file.delete()){
           throw  new IOException("couldnot delete "+ name);
        }
    }

    @Override
    public void renameFile(String from, String to ) throws IOException, SecurityException {
        File old = new File(directory, from);
        File nu = new File(directory, to);
        if(nu.exists()){
            if(!nu.delete()){
                throw new IOException("could not delete "+ to);
            }
        }
        if(!old.renameTo(nu)){
           throw  new IOException(" could not rename "+ from +" to "+ to);
        }
    }

    @Override
    public long fileLength(String name) throws IOException, SecurityException {
        File file = new File(directory, name);
        return file.length();
    }

    @Override
    public OutputStream createFile(String name) throws IOException, SecurityException {
        return new FsOutputStream(new File(directory, name));
    }

    @Override
    public InputStream openFile(String name) throws IOException, SecurityException {
        return new FsInputStream(new File(directory, name));
    }

    @Override
    public void close() throws IOException, SecurityException {
        if(--refCount <= 0){
            synchronized (DIRECTORS){
                DIRECTORS.remove(directory);
            }
        }
    }
}
