package Service;

//This service file needs to use the messageDAO and message model.
import DAO.MessageDAO;
import Model.Message;

//This class also works with lists, import list.
import java.util.List;

/**
 * The MessageServices class connects message related endpoints to the MessageDAO
 */
public class MessageServices {
    //MessageDao field for accessing the messageDao
    MessageDAO dao = new MessageDAO();

    //TODO: Create message method
    public Message CreateMessage(String text){
        return null;
    }

    //TODO: Get all messages method
    public List<Message> GetAllMessages(){
        return null;
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
