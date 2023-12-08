package cn.oddworld.lucene.ram;

import cn.oddworld.lucene.Directory;
import cn.oddworld.lucene.InputStream;
import cn.oddworld.lucene.OutputStream;
import org.apache.lucene.store.RAMInputStream;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Hashtable;

public class RamDirectory extends Directory {

    Hashtable files = new Hashtable();

    @Override
    public String[] list() throws IOException, SecurityException {
        String[] result = new String[files.size()];
        int i =0;
        Enumeration keys = files.keys();
        while (keys.hasMoreElements()){
            result[i++] = (String) keys.nextElement();
        }
        return result;
    }

    @Override
    public boolean fileExists(String name) throws IOException, SecurityException {
        RAMFile file = (RAMFile)files.get(name);
        return file != null;
    }

    @Override
    public long fileModified(String name) throws IOException, SecurityException {
        RAMFile file = (RAMFile) files.get(name);
        return file.lastModified;
    }

    @Override
    public void deleteFile(String name) throws IOException, SecurityException {

        files.remove(name);
    }

    @Override
    public void renameFile(String from, String to) throws IOException, SecurityException {
        RAMFile file = (RAMFile) files.get(from);
        files.remove(file);
        files.put(to, file);
    }

    @Override
    public long fileLength(String name) throws IOException, SecurityException {
        RAMFile file = (RAMFile) files.get(name);
        return file.length;
    }

    @Override
    public OutputStream createFile(String name) throws IOException, SecurityException {
        RAMFile ramFile = new RAMFile();
        files.put(name, ramFile);
        return new RamOutputStream(ramFile);
    }

    @Override
    public InputStream openFile(String name) throws IOException, SecurityException {
        RAMFile file = (RAMFile) files.get(name);
        return new RamInputStream(file);
    }

    @Override
    public void close() throws IOException, SecurityException {

    }
}
