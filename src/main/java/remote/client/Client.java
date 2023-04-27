package remote.client;

import java.io.IOException;

public interface Client {

    void sendMsgToServer(String ip, int port, byte[] msg) throws IOException;

}
