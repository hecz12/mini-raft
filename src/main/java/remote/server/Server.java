package remote.server;

public interface Server {

    /**
     * receive msg from client, in this case usually propose
     * @return receive bytes
     */
    byte[] receiveMsg();

    /**
     * stop
     */
    void stop();

}
