package cn.oddworld.lucene;

import java.io.IOException;

public abstract class Directory {


    /**
     * returns an array of strings, one ofr each file in the directory
     * @return
     * @throws IOException
     * @throws SecurityException
     */
    abstract public  String[] list() throws IOException, SecurityException;

    /**
     * returen true if a file with the given name exists
     * @param name
     * @return
     * @throws IOException
     * @throws SecurityException
     */
    abstract public boolean fileExists(String name)
        throws IOException, SecurityException;

    /**
     * return the time the named file was last modified
     * @param name
     * @return
     * @throws IOException
     * @throws SecurityException
     */
    abstract public long fileModified(String name)
        throws IOException, SecurityException;

    /**
     * removes an existing file in the directory
     * @param name
     * @throws IOException
     * @throws SecurityException
     */
    abstract public void deleteFile(String name)
        throws IOException, SecurityException;

    /**
     * renames an existing file in the directory
     * if a file already exist with the new name
     * then it is replaced, this replacement should
     * be atomic
     * @throws IOException
     * @throws SecurityException
     */
    abstract public void renameFile(String from, String to)
        throws IOException, SecurityException;

    /**
     * returns the length of a file in the directory
     * @param name
     * @return
     * @throws IOException
     * @throws SecurityException
     */
    abstract public long fileLength(String name)
        throws IOException, SecurityException;

    /**
     * creates a new, empty file in the directory
     * with the given name, returns a stream writing this
     * file
     * @param name
     * @return
     * @throws IOException
     * @throws SecurityException
     */
    abstract public OutputStream createFile(String name)
        throws IOException, SecurityException;

    /**
     * returns a stream reading an existing file
     * @param name
     * @return
     * @throws IOException
     * @throws SecurityException
     */
    abstract public InputStream openFile(String name)
        throws IOException, SecurityException;

    /**
     * closes the store
     * @throws IOException
     * @throws SecurityException
     */
    abstract public void close()
        throws IOException, SecurityException;
}
