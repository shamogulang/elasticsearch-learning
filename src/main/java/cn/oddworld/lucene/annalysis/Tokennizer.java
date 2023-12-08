package cn.oddworld.lucene.annalysis;

import java.io.IOException;
import java.io.Reader;

public abstract class Tokennizer extends TokenStream {

    protected Reader input;

    @Override
    public void close() throws IOException{
        input.close();
    }
}
