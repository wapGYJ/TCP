package Server;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {
    public static List<Socket> onlineSocket=new ArrayList<>();
    public static void main(String[] args) throws Exception {
        try( ServerSocket serverSocket = new ServerSocket(8888);){
            System.out.println("服务启动");
            while (true){
                Socket socket = serverSocket.accept();
                onlineSocket.add(socket);//添加到在线
                System.out.println(socket.getInetAddress()+""+socket.getPort()+"上线");
                new Thread(new ServerReaderThread(socket)).start();//为每一个socket创建线程
            }

        } catch (Exception e) {
            throw new Exception(e);
        }

    }

}
