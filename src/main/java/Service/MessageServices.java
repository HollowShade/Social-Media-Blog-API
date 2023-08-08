package Service;

//This service file needs to use the message model and DAO.
import Model.Message;
import DAO.MessageDAO;

//This class also works with lists, import list.
import java.util.List;

/**
 * The MessageServices class connects message related endpoints to the MessageDAO
 */
public class MessageServices {
    //MessageDao field for accessing the messageDao
    MessageDAO dao = new MessageDAO();

    //TODO: Create message method
    public Message CreateMessage(Message text){
        //Check if the message meets message requirements.
        if (text.getMessage_text().equals("") || text.getMessage_text().equals(null) || text.getMessage_text().length() > 254){
            return null;
        }

        //If the requirements are met, create the message and return its results.
        return dao.CreateMessage(text);
    }

    //TODO: Get all messages method
    public List<Message> GetAllMessages(){
        return dao.GetAllMessages();
    }

    //TODO: Get message by ID method
    public Message GetMessageByID(int id){
        return null;
    }

    //TODO: Get messages by Account ID method
    public List<Message> GetMessagesByUser(int userID){
        return null;
    }

    //TODO: Delete message by ID method
    public Message DeleteMessage(int id){
        return null;
    }

    //TODO: Update message by ID method
    public Message UpdateMessage(int id){
        return null;
    }
}
