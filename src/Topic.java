import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Topic
{
    //
    //  <имя топика<имя кто создал, голосование>>
    //
    private HashMap<String, HashMap<String, Voting>> topics;

    public Topic()
    {
        this.topics = new HashMap<String, HashMap<String, Voting>>();
    }

    public void createTopic(String Topic_name, String name_id ) {
        if (!topics.containsValue(Topic_name))
        {
            Voting free_vote = new Voting();
            topics.put(Topic_name, new HashMap<String, Voting>());
            topics.get(Topic_name).put(name_id, free_vote);
            System.out.println("Topic created: " + Topic_name);
        } else
        {
            System.out.println("Topic already exists: " + Topic_name);
        }
    }

    public void load_vote(String Topic_name, String name_id, Voting vote)
    {
        if (topics.containsKey(Topic_name))
        {
            //topics.put(name_id, new HashMap<String, Voting>());
            topics.get(Topic_name).put(name_id, vote);
            System.out.println("load_vote: " + vote.toString());
        } else
        {
            System.out.println("not load: " + vote.toString());
        }

    }



}

class Voting {
    private String name;
    private String description;
    private ArrayList<String> options;

    public Voting()
    {
        this.name = "free";
        this.description = "free";

    }
    public Voting(String name, String description, ArrayList<String> options) {
        this.name = name;
        this.description = description;
        this.options = options;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public ArrayList<String> getOptions() {
        return options;
    }
}