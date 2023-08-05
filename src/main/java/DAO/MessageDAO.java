package DAO;

//This class works with messages, import the message model
import Model.Message;

//This class also works with lists, import list.
import java.util.List;

//It also works with databases, import Connection
import java.sql.Connection;

/**
 * The MessageDAO class serves as the DAO for the message table
 * It will create, read, update, and delete messages per the user's request
 */
public class MessageDAO {
    //TODO: Create message method
    public Message CreateMessage(Message newMessage){
        return null;
    }

    //TODO: Get all messages method
    public List<Message> GetAllMessages(){
        return null;
    }

    //TODO: Get message by ID method
    public Message GetMessageByID(int messageID){
        return null;
    }
    
    //TODO: Get messages by Account ID method
    public List<Message> GetMessagesByUser(int userID){
        return null;
    }

    //TODO: Update message by ID method
    public Message UpdatMessage(Message newMessage){
        return null;
    }

    //TODO: Delete message by ID method
    public Message DeleteMessage(int messageID){
        return null;
    }
}
