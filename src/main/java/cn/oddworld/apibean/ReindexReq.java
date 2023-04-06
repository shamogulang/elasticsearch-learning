package cn.oddworld.apibean;

public class ReindexReq {

    private String sourceIndex;
    private String targetIndex;

    public String getSourceIndex() {
        return sourceIndex;
    }

    public void setSourceIndex(String sourceIndex) {
        this.sourceIndex = sourceIndex;
    }

    public String getTargetIndex() {
        return targetIndex;
    }

    public void setTargetIndex(String targetIndex) {
        this.targetIndex = targetIndex;
    }
}
