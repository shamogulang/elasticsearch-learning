package cn.oddworld.lucene.annalysis;

public class Token {

    String termText;
    int startOffset;
    int endOffset;
    String type = "word";

    public Token(String text, int start, int end){
        this.termText = text;
        this.startOffset = start;
        this.endOffset = end;
    }

    public Token(String text, int start, int end, String type){
        this.termText = text;
        this.startOffset = start;
        this.endOffset = end;
        this.type = type;
    }

    public String termText(){
        return termText;
    }

    public int startOffset(){
        return startOffset;
    }

    public int endOffset(){
        return endOffset;
    }

    public String type(){
        return type;
    }
}
