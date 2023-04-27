package msg;

import java.io.Serializable;

public class Msg implements Serializable {

    private int msgType;

    public Msg(int msgType) {
        this.msgType = msgType;
    }

    public int getMsgType() {
        return msgType;
    }

    public void setMsgType(int msgType) {
        this.msgType = msgType;
    }
}
