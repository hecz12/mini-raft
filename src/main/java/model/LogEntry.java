package model;

public class LogEntry {

    private long index;

    private long term;

    private int Option;

    private byte[] data;

    public long getIndex() {
        return index;
    }

    public void setIndex(long index) {
        this.index = index;
    }

    public long getTerm() {
        return term;
    }

    public void setTerm(long term) {
        this.term = term;
    }

    public int getOption() {
        return Option;
    }

    public void setOption(int option) {
        Option = option;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}
