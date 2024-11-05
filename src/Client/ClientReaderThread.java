package Client;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class ClientReaderThread implements Runnable {
    private Socket socket;

    public ClientReaderThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            InputStream inputStream = socket.getInputStream();
            DataInputStream dis = new DataInputStream(inputStream);
            while(true){
                try {
                    String str=dis.readUTF();
                    System.out.println(socket.getInetAddress()+" on port"+socket.getPort()+" msg:"+str);
                } catch (IOException e) {
                    System.out.println("你下线了");
                    dis.close();
                    socket.close();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
