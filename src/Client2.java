import java.io.IOException;
import java.net.Socket;
import java.io.*;
import java.util.Scanner;


public class Client2 {

    public static void ClientConnect() throws IOException
    {
        //Socket socket = new Socket("localhost", 1234);
        Chat chat = new Chat("localhost", 1234);

        chat.send_chat("Привет, я клиент  - это я отправил с помощью чата");

        String word = chat.read_chat(); // ждём пока сервер что-нибудь нам напишет
        System.out.println( "Ответ с сервера: " + word);

        Scanner in = new Scanner(System.in);
        String username = in.nextLine();

        chat.send_chat(username);

        word = chat.read_chat(); // ждём пока сервер что-нибудь нам напишет
        System.out.println( "Ответ с сервера: " + word);

    }
    public static void main(String[] args) throws IOException {
        ClientConnect();
    }
}
