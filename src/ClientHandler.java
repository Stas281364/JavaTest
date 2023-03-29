import java.net.Socket;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ClientHandler implements Runnable {

    //private final Socket clientSocket;
    private final Chat chat;
    InputStream inputStream;
    OutputStream outputStream;
    Topic topic1 = new Topic();


    String[] check_command = {  "create topic -n=",
                                "view",
                                "create vote -t=",
                                "view -t=-v=",
                                "vote -t=",
                                "vote -t=-v=",
                                "delete -t=-v="};

    /*public ClientHandler(Socket clientSocket)
    {
        this.clientSocket = clientSocket;

    }
    */
    public ClientHandler(Chat chat)
    {
        this.chat = chat;
    }


    public void run()
    {
        try {

            String word = chat.read_chat(); // ждём пока клиент что-нибудь нам напишет
            System.out.println(word);
            chat.send_chat("Привет, сообщение принято");

            word = chat.read_chat(); // ждём пока клиент что-нибудь нам напишет
            System.out.println(word);

            String check_enter = "login -n=";
            String enter = word;;
            String username = "";
            if (enter.contains(check_enter))
            {

                username =  enter.replace(check_enter, "");
                chat.send_chat("Вы вошли как: " +  username);

            }


            Scanner scanner = new Scanner(System.in);
            String command = "";
            String name_topic = "";
            String Vote_name = "";
            do {
                chat.send_chat("Вводите команды");

                word = chat.read_chat(); // ждём пока клиент что-нибудь нам напишет
                System.out.println(word);


                command = word;

                if (command.contains(check_command[0]))
                {

                    name_topic = command.replace(check_command[0], "");
                    //chat.send_chat(name_topic = command.replace(check_command[0], ""));


                    topic1.createTopic(name_topic, username );


                }
                if (command.equals(check_command[1]))
                {

                }
                if (command.contains(check_command[2]))
                {

                    Vote_name = command.replace(check_command[2], "");
                    chat.send_chat("Введите тему голосования:");

                    word = chat.read_chat(); // ждём пока клиент что-нибудь нам напишет
                    System.out.println(word);
                    String Vote_tema = word;

                    int numOfOptions = 0;
                    chat.send_chat("Введите количество вариантов ответа:");
                    word = chat.read_chat(); // ждём пока клиент что-нибудь нам напишет
                    System.out.println(word);
                    numOfOptions = Integer.parseInt(word);

                    ArrayList<String> options = new ArrayList<>();
                    for (int i = 0; i < numOfOptions; i++)
                    {
                        chat.send_chat("Введите вариант ответа №" + (i+1) + ":");
                        word = chat.read_chat(); // ждём пока клиент что-нибудь нам напишет
                        System.out.println(word);
                        options.add(word);

                    }

                    Voting voting = new Voting(Vote_name, Vote_tema, options);
                    topic1.load_vote(name_topic, Vote_tema, voting);
                    System.out.println("Голосование \"" + voting.getName() + "\" успешно создано на тему \"" + voting.getDescription() + "\".");
                    chat.send_chat("Голосование \"" + voting.getName() + "\" успешно создано на тему \"" + voting.getDescription() + "\"." + "нажмите enter, чтобы продолжить");
                    System.out.println("Варианты ответа:");
                    for (int i = 0; i < options.size(); i++) {
                        System.out.println((i+1) + ") " + options.get(i));
                    }
                }


            } while(!command.equals("exit"));








            /*
             inputStream = clientSocket.getInputStream();
             outputStream = clientSocket.getOutputStream();



            // Read data from client
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream));

            String word = reader.readLine(); // ждём пока клиент что-нибудь нам напишет
            System.out.println(word);
            // не долго думая отвечает клиенту
            writer.write("Сервер принял: " + word + "\n" + "вы законнектились");
            writer.flush(); // выталкиваем все из буфера
            */

            //clientSocket.close();
            //System.out.println("Клиент отключился: " + clientSocket.getInetAddress());
        }
        catch (NullPointerException e)
        {
            throw new RuntimeException(e);
        }

    }
}
