package Client;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {
        try(
                Socket socket=new Socket("127.0.0.1",8888);
                OutputStream outputStream = socket.getOutputStream();
                DataOutputStream dos = new DataOutputStream(outputStream);
                Scanner sc= new Scanner(System.in);
                ) {

            new Thread( new ClientReaderThread(socket)).start();//收线程
            while(true){
                System.out.println("D:\\StudyJava\\TCP\\src\\Server\\Server.java-->/");
                String str= sc.nextLine();
                if(str.equals("exit")){
                    System.out.println("欢迎下次使用");
                    break;
                }
                dos.writeUTF(str);
                dos.flush();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}

