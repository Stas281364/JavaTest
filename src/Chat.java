import java.io.*;
import java.net.*;

public class Chat {

    private Socket socket;
    private BufferedReader reader;
    private BufferedWriter writer;

    public Chat(ServerSocket socket)    //конструктор для сервера
    {
        try {
            this.socket = socket.accept();
            System.out.println("New client connected");
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.reader = CreateReader();
        this.writer = CreateWriter();
    }

    public Chat(String ip, int port)    //конструктор для клиента
    {
        try {
            this.socket = new Socket(ip, port);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.reader = CreateReader();
        this.writer = CreateWriter();
    }

    public void send_chat(String msg)   //Отправть
    {
        try {
            writer.write(msg + "\n");
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public String read_chat() //Принять
    {
        try {
            return reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void close()
    {
        try {
            socket.close();
            writer.close();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public BufferedReader CreateReader()
    {
        try {
            return new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public BufferedWriter CreateWriter()
    {
        try {
            return new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
