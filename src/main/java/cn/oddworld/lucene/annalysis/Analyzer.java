package cn.oddworld.lucene.annalysis;

import java.io.Reader;

public abstract class Analyzer {

    public TokenStream tokenStream(String fieldName, Reader reader){

        return tokenStream(reader);
    }

    public  TokenStream tokenStream(Reader reader){
        return tokenStream(null, reader);
    }
}
