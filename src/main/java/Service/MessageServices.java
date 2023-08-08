package Service;

//This service file needs to use the message model.
import Model.Message;

//The create message method calls for using both message and account DAOs
import DAO.*;

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

        //Make an AccountDAO and use it to see if the message's user ID exists in the account database
        AccountDAO userTest = new AccountDAO();
        
        if (userTest.GetAccountByID(text.getPosted_by()) == null){
            return null;
        }

        //If both requirements are met, create the message and return its results.
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
