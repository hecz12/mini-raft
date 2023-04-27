package remote.client;

import java.io.IOException;
import java.net.Socket;

public class BlockingClientServiceImpl implements Client {


    @Override
    public void sendMsgToServer(String ip, int port, byte[] msg) throws IOException {
        try (Socket socket = new Socket(ip, port)) {
            socket.getOutputStream().write(msg);
        }
    }
}
