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

    /**
     * The CreateMessage method takes a message from the controller and tells the message DAO to turn it into a new message
     * for the message database if the message meets some requirements. These requirements are to not be empty and to not be over
     * 254 characters in length. If the message wasn't created, tell the controller as such. Otherwise, send it the new message.
     * @param text The message from the controller
     */
    public Message CreateMessage(Message text){
        //Check if the message meets message requirements.
        if (text.getMessage_text().equals("") || text.getMessage_text().equals(null) || text.getMessage_text().length() > 254){
            return null;
        }

        //If the requirements are met, create the message and return its results.
        return dao.CreateMessage(text);
    }

    /**
     * The GetAllMessagesMethod returns all the messages in the message database, even if it's empty
     */
    public List<Message> GetAllMessages(){
        return dao.GetAllMessages();
    }

    //TODO: Get message by ID method
    public Message GetMessageByID(int id){
        return dao.GetMessageByID(id);
    }

    //TODO: Get messages by Account ID method
    public List<Message> GetMessagesByUser(int userID){
        return dao.GetMessagesByUser(userID);
    }

    //TODO: Delete message by ID method
    public Message DeleteMessage(int id){
        return dao.DeleteMessage(id);
    }

    //TODO: Update message by ID method
    public Message UpdateMessage(Message messageUpdate){
        //Check if the message meets message requirements.
        if (messageUpdate.getMessage_text().equals("") || messageUpdate.getMessage_text().equals(null) || messageUpdate.getMessage_text().length() > 254){
            return null;
        }

        //If the requirements are met, create the message and return its results.
        return dao.UpdateMessage(messageUpdate);
    }
}
