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
    /**
     * The CreateMessage method takes a message from message services and turns it into a new message for the message database. 
     * If the message wasn't created, tell message services as such. Otherwise, send it the new message.
     * @param text The message from message services
     */
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

    /**
     * The GetAllMessagesMethod returns all the messages in the message database, even if it's empty
     */
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
        //Create a connection
        Connection link = Util.ConnectionUtil.getConnection();

        //From here on in, we need a try-catch block
        try {
            //Create a statement
            PreparedStatement sql = link.prepareStatement("SELECT * FROM message WHERE message_id = ?;");

            //Set sql's parameters
            sql.setInt(1, messageID);

            //Execute the sql statement and retrieve the query
            ResultSet targetMessage = sql.executeQuery();

            //Check if there's an entry. If there is one, return it
            if (targetMessage.next()){
                return new Message(targetMessage.getInt(1), targetMessage.getInt(2), targetMessage.getString(3), targetMessage.getLong(4));
            }
        } 
        catch (SQLException e) {
            //If something goes wrong, give the developer details
            e.printStackTrace();
        }

        //If we're outside of the try catch block after the connection, assume the operation failed.
        return null;
    }
    
    //TODO: Get messages by Account ID method
    public List<Message> GetMessagesByUser(int userID){
        //Create a connection and a list that will contain the messages in the message database
        Connection link = Util.ConnectionUtil.getConnection();
        List<Message> messageList = new LinkedList<>();

        //From here on in, we need a try-catch block
        try {
            //Create a statement
            PreparedStatement sql = link.prepareStatement("SELECT * FROM message WHERE posted_by = ?;");

            //Set sql's parameters
            sql.setInt(1, userID);

            //Execute the sql statement and retrieve the resultset
            ResultSet messages = sql.executeQuery();

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

    //TODO: Update message by ID method
    public Message UpdateMessage(Message newMessage){
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

    //TODO: Delete message by ID method
    public Message DeleteMessage(int messageID){
        //Create a connection
        Connection link = Util.ConnectionUtil.getConnection();

        //To return the deleted message, we should retrieve it before deleting it
        Message deletedMessage = this.GetMessageByID(messageID);

        //The retrieved message might be empty. If it is, there's nothing to delete.
        if (deletedMessage != null){
            //From here on in, we need a try-catch block
            try {
                //Create a statement
                PreparedStatement sql = link.prepareStatement("DELETE * FROM message WHERE message_id = ?;");

                //Set sql's parameters
                sql.setInt(1, messageID);

                //Execute the sql statement and retrieve the rows affected
                int success  = sql.executeUpdate();

                //If no rows were affected, nothing was deleted, so null the target message
                if (success == 0){
                    deletedMessage = null;
                }
            } 
            catch (SQLException e) {
                //If something goes wrong, give the developer details and null the target message
                e.printStackTrace();
                deletedMessage = null;
            }
        }

        //Once the code is finished, return the deletedMessage, even if it doesn't contain a message
        return deletedMessage;
    }
}
