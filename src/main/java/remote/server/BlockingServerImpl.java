package remote.server;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;

public class BlockingServerImpl implements Server {

    private ServerSocket serverSocket;

    private int port;

    private BlockingQueue<byte[]> queue = new LinkedBlockingQueue<>();

    private ExecutorService executor = new ThreadPoolExecutor(10, 20, 30, TimeUnit.SECONDS, new ArrayBlockingQueue<>(20));

    private boolean stop = false;

    public BlockingServerImpl(int port) throws IOException {
        this.port = port;
        serverSocket = new ServerSocket(this.port,128);

        new Thread(() -> {
            while (true) {
                if(stop) {
                    return;
                }
                try {
                    Socket client = serverSocket.accept();
                    executor.execute(new FetchMsgThread(client, queue));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }

    @Override
    public byte[] receiveMsg() {
        try {
            return queue.take();
        } catch (InterruptedException e) {
            System.out.println("get msg from queue error");
        }
        return null;
    }

    @Override
    public void stop() {
        try {
            stop = true;
            Thread.sleep(1000);
            serverSocket.close();
        } catch (IOException | InterruptedException e) {
            System.out.println("server socket close error");
        }
    }

    class FetchMsgThread implements Runnable {

        private final Socket socket;

        private final BlockingQueue<byte[]> queue;

        public FetchMsgThread(Socket socket, BlockingQueue<byte[]> queue) {
            super();
            this.socket = socket;
            this.queue = queue;
        }

        @Override
        public void run() {
            try {
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                InputStream inputStream = this.socket.getInputStream();
                byte[] buf = new byte[4096];
                int n;
                while ((n = inputStream.read(buf)) >= 0) {
                    stream.write(buf, 0, n);
                }
                this.queue.put(stream.toByteArray());
                inputStream.close();
                this.socket.close();
            } catch (IOException | InterruptedException e) {
                System.out.println("fetch msg thread has some err");
                throw new RuntimeException(e);
            }
        }
    }

}
