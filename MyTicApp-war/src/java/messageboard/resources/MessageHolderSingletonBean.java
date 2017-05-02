/*
 *MyTicApp
 */
package messageboard.resources;

//import java.time.Instant;
import messageboard.entities.Message;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.Singleton;
//import java.time.*;

@Singleton
public class MessageHolderSingletonBean 
{
    private LinkedList<Message> list = new LinkedList<>();
    private int maxMessages = 100;
    int currentId = 60;
    //private LocalDateTime dt;
    private Date dd = java.util.Calendar.getInstance().getTime();
    private Date dt = new Date();
    
    public MessageHolderSingletonBean() 
    {
        // initial content
       // dt  = LocalDateTime(2017,04,01)
        addMessage("There is always a lite at the end of the tunnel.", new Date(0));
        addMessage("msg1", new Date(1000));
        addMessage("Practice makes perfect.", new Date(2000));
        addMessage("Curiosity ... the ...?", new Date(3000));//1 day = 60*60*24 = 86400
        addMessage("msg4", dt);
        addMessage("There is always a light at the end of the tunnel.", dd);//1 year = 86400 * 365 = 31536000
    }

    public List<Message> getMessages() 
    {
        List<Message> l = new LinkedList<>();

        int index = 0;

        while (index < list.size() && index < maxMessages) 
        {
            l.add(list.get(index));
            index++;
        }

        return l;
    }

    private synchronized int getNewId() 
    {
        return currentId++;
    }

    public synchronized Message addMessage(String msg) 
    {
        return addMessage(msg, new Date());
    }

    private synchronized Message addMessage(String msg, Date date) 
    {
        Message m = new Message(date, msg, getNewId());

        list.addFirst(m);

        return m;
    }

    public Message getMessage(int uniqueId) 
    {
        int index = 0;
        Message m;

        while (index < list.size()) 
        {
            if ((m = list.get(index)).getUniqueId() == uniqueId) 
            {
                return m;
            }
            index++;
        }
        return null;
    }

    public synchronized boolean deleteMessage(int uniqueId) 
    {
        int index = 0;
        while (index < list.size()) 
        {
            if (list.get(index).getUniqueId() == uniqueId) 
            {
                list.remove(index);
                return true;
            }
            index++;
        }
        return false;
    }
}
