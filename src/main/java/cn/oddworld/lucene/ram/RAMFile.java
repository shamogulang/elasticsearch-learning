package cn.oddworld.lucene.ram;

import java.util.Vector;

public class RAMFile {

   public Vector buffers = new Vector();
   public long length;
   public long lastModified = System.currentTimeMillis();
}
