import java.io.IOException;
import java.net.Socket;
import java.io.*;
import java.util.Scanner;

public class Client1 {

    public static String name_id = "";

    public static void ClientConnect() throws IOException
    {
        //Socket socket = new Socket("localhost", 1234);
        Chat chat = new Chat("localhost", 1234);

        chat.send_chat("Привет, я клиент  - это я отправил с помощью чата");

        String word = chat.read_chat(); // ждём пока сервер что-нибудь нам напишет
        System.out.println( "Ответ с сервера: " + word);

        Scanner in = new Scanner(System.in);
        String username = in.nextLine();
        name_id = username;

        chat.send_chat(username);
        word = chat.read_chat(); // ждём пока сервер что-нибудь нам напишет
        System.out.println( "Ответ с сервера: " + word);

        do {
            word = chat.read_chat(); // ждём пока сервер что-нибудь нам напишет
            System.out.println( "Ответ с сервера: " + word);
            String command = in.nextLine();

            chat.send_chat(command);

        }while (true);



        /*
        OutputStream outputStream = socket.getOutputStream();
        InputStream inputStream = socket.getInputStream();

        // Send request to server
        PrintWriter writer = new PrintWriter(outputStream);
        writer.println("Hello, server! im client1");
        writer.flush();

        // Read response from server
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String response = reader.readLine();
        System.out.println("Ответ от сервера: " + response);

        //socket.close();
        */


    }
    public static void main(String[] args) throws IOException {
        ClientConnect();
    }
}
