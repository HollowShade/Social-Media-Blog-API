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

    /**
     * The GetMessageByID method takes a message ID provided by the controller and tells the MessageDAO to find a message in the
     * message database with the same ID.
     * @param id The ID from the URL's endpoint.
     */
    public Message GetMessageByID(int id){
        return dao.GetMessageByID(id);
    }

    /**
     * The GetMessagesByUser takes a user ID provided by the controller and tells the MessageDAO to retrieve all messages with
     * this user ID so it can return them to the controller, even if this user hasn't made any posts.
     * @param userID The UserID from the URL's endpoint.
     */
    public List<Message> GetMessagesByUser(int userID){
        return dao.GetMessagesByUser(userID);
    }

    /**
     * The DeleteMessage method takes the message ID provided by the controller and tells the messageDAO to retrieve and delete the
     * message in the message database with the same ID.
     * @param id The ID from the URL's endpoint.
     */
    public Message DeleteMessage(int id){
        return dao.DeleteMessage(id);
    }

    /**
     * The UpdateMessage method takes a message from the controller and tells the MessageDAO to update and retrieve the
     * message in the message database with the same ID with a new message provided in the input.
     * @param messageUpdate The message from the controller
     */
    public Message UpdateMessage(Message messageUpdate){
        //Check if the message meets message requirements.
        if (messageUpdate.getMessage_text().equals("") || messageUpdate.getMessage_text().equals(null) || messageUpdate.getMessage_text().length() > 254){
            return null;
        }

        //If the requirements are met, create the message and return its results.
        return dao.UpdateMessage(messageUpdate);
    }
}
