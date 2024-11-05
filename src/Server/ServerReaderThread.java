package Server;

import java.io.*;
import java.net.Socket;

import static Server.Server.onlineSocket;

public class ServerReaderThread implements Runnable{
    private Socket socket;

    public ServerReaderThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            InputStream inputStream = socket.getInputStream();
            DataInputStream dis = new DataInputStream(inputStream);
            while(true){
                try {
                    String str = dis.readUTF();
                    System.out.println("msg:"+str+" from"+socket.getInetAddress()+" on port"+
                            socket.getPort());
                    sendMsgToAll(str);
                } catch (IOException e) {
                    System.out.println(socket.getInetAddress()+" on port"+ socket.getPort()+"下线了");
                    onlineSocket.remove(socket);//移除这个socket
                    dis.close();
                    socket.close();
                   break;

                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private void sendMsgToAll(String str) throws IOException {
        for (Socket everySocket : onlineSocket) {
            OutputStream outputStream = everySocket.getOutputStream();
            DataOutputStream dos = new DataOutputStream(outputStream);
            dos.writeUTF(str);
            dos.flush();
        }
    }
}
