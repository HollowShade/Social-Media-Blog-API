package DAO;

//This class works with messages, import the message model
import Model.Message;

//This class also works with lists, import list and linkedlist.
import java.util.List;
import java.util.LinkedList;

//It also works with databases, import sql
import java.sql.*;

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
        //Create a connection and a list that will contain the messages in the message database
        Connection link = Util.ConnectionUtil.getConnection();
        List<Message> messageList = new LinkedList<>();

        //From here on in, we need a try-catch block
        try {
            //Create a statement
            Statement sql = link.createStatement();

            //Execute the sql statement and retrieve the resultset
            ResultSet messages = sql.executeQuery("SELECT * FROM message;");

            //Take all the entries from the result set and add them to the message list
            while (messages.next()){
                messageList.add(new Message(messages.getInt(1), messages.getInt(2), messages.getString(3), messages.getLong(4)));
            }

            //When finished with the loop, close the link and return the message list.
            link.close();
            return messageList;
        } 
        catch (SQLException e) {
            //If something goes wrong, give the developer details
            e.printStackTrace();
        }

        //If we're outside of the try catch block after the connection, assume the operation failed.
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
