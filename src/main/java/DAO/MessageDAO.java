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
        //Create a connection
        Connection link = Util.ConnectionUtil.getConnection();

        //From here on in, we need a try-catch block
        try {
            //Create a statement
            PreparedStatement sql = link.prepareStatement("INSERT INTO message VALUES (DEFAULT, ?, ?, ?);", Statement.RETURN_GENERATED_KEYS);

            //Set sql's parameters
            sql.setInt(1, newMessage.getPosted_by());
            sql.setString(2, newMessage.getMessage_text());
            sql.setLong(3, newMessage.getTime_posted_epoch());

            //Execute the sql statement
            sql.executeUpdate();

            //Retrieve the generated values
            ResultSet newEntry = sql.getGeneratedKeys();

            //Check if there's an entry, if so, add it's ID to the account object parameter and return it
            if (newEntry.next()){
                newMessage.setMessage_id(newEntry.getInt(1));
                return newMessage;
            }
        } 
        catch (SQLException e) {
            //If something goes wrong, give the developer details
            e.printStackTrace();
        }

        //If we're outside of the try catch block after the connection, assume the operation failed.
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
        } 
        catch (SQLException e) {
            //If something goes wrong, give the developer details
            e.printStackTrace();
        }

        //Whatever happens, return the message list, even if it's empty (the controller's alright with an empty list)
        return messageList;
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
