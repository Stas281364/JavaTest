import java.io.*;
import java.net.*;


public class Server
{

    public static void main(String[] args) throws IOException
    {
        ServerSocket serverSocket = new ServerSocket(1234);
        System.out.println("Server started");

        while (true)
        {
            //Socket clientSocket = serverSocket.accept();
            //Chat chat = new Chat(serverSocket);
            //System.out.println("New client connected");


            //Thread thread = new Thread(new ClientHandler(clientSocket));
            Thread thread = new Thread(new ClientHandler(new Chat(serverSocket)));
            thread.start();
        }


    }
}

