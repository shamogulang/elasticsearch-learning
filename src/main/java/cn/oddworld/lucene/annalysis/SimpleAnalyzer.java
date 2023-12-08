package cn.oddworld.lucene.annalysis;

import java.io.Reader;

public class SimpleAnalyzer extends Analyzer {


    @Override
    public final  TokenStream tokenStream(String fieldName, Reader reader){

        return new LowerCaseTokenizer(reader);
    }
}
