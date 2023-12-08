package cn.oddworld.lucene.annalysis;

import java.io.IOException;

public abstract class TokenStream {

    abstract public  Token next() throws IOException;

    public  void close() throws IOException{};
}
