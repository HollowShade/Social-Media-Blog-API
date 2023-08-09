package Controller;

import io.javalin.Javalin;
import io.javalin.http.Context;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
//This import allows us to translate JSON into Java and vice versa
import com.fasterxml.jackson.databind.ObjectMapper;

//There are methods that return message and account contents, so this class needs to use both models
import Model.*;

//Accessing those methods requires their service methods, import the service classes
import Service.*;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller. The endpoints you will need can be
 * found in readme.md as well as the test cases. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
public class SocialMediaController {
    //Objects that allow us to access service methods
    AccountServices AccountServer = new AccountServices();
    MessageServices MessageServer = new MessageServices();

    //Can an objectmapper be a field?
    ObjectMapper translater = new ObjectMapper();
    
    /**
     * In order for the test cases to work, you will need to write the endpoints in the startAPI() method, as the test
     * suite must receive a Javalin object from this method.
     * @return a Javalin app object which defines the behavior of the Javalin controller.
     */
    public Javalin startAPI() {
        Javalin app = Javalin.create();
        //Commenting out example endpoint: app.get("example-endpoint", this::exampleHandler);

        //Account registration endpoint
        app.post("register", this::CreateAccount);

        //Login endpoint
        app.post("login", this::Login);

        //Create Message endpoint
        app.post("messages", this::CreateMessage);

        //Get all messages endpoint
        app.get("messages", this::GetAllMessages);

        //Get message by message id endpoint
        app.get("messages/{message_id}", this::GetMessageByID);
        
        //Get messages by account id endpoint
        app.get("accounts/{account_id}/messages", this::GetMessagesByUser);

        //Delete message endpoint
        app.delete("messages/{message_id}", this::DeleteMessage);

        //Update message endpoint
        app.patch("messages/{message_id}", this::UpdateMessage);

        return app;
    }

    /**
     * The CreateAccount method takes a username and password from JSON and tells AccountServices to turn it into a new account
     * for the account database. The user should know if the account wasn't added to the database
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    private void CreateAccount(Context context){
        try {
            //Get input from JSON and send it to AccountServices
            Account input = translater.readValue(context.body(), Account.class);
            Account newAccount = AccountServer.CreateAccount(input);

            //The method succeeds if newAccount isn't null, in which case we return the account to json. Otherwise, tell JSON that the method failed.
            if(newAccount != null){
                context.json(translater.writeValueAsString(newAccount));
            }
            else {
                context.status(400);
            }
        } //If an exception occurs, we want to know about it, we also want to tell JSON about the failure
        catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    /**
     * The Login method takes a username and password from JSON and tells AccountServices to see if there's an account with the
     * provided username and password in the account database before letting the user log in. If AccountServices can't deliver, 
     * the user can't log in.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    private void Login(Context context){
        try {
            //Get input from JSON and send it to AccountServices
            Account input = translater.readValue(context.body(), Account.class);
            Account loginAccount = AccountServer.Login(input);

            //The method succeeds if newAccount isn't null, in which case we return the account to json. Otherwise, tell JSON that the method failed.
            if(loginAccount != null){
                context.json(translater.writeValueAsString(loginAccount));
            }
            else {
                context.status(401);
            }
        } //If an exception occurs, we want to know about it, we also want to tell JSON about the failure
        catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    /**
     * The CreateMessage method takes a message from JSON and tells MessageServices to turn it into a new message
     * for the message database. The user should know if the message wasn't added to the database.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    private void CreateMessage(Context context){
        try {
            //Get input from JSON and send it to MessageServices
            Message input = translater.readValue(context.body(), Message.class);
            Message newMessage = MessageServer.CreateMessage(input);

            //The method succeeds if newAccount isn't null, in which case we return the account to json. Otherwise, tell JSON that the method failed.
            if(newMessage != null){
                context.json(translater.writeValueAsString(newMessage));
            }
            else {
                context.status(400);
            }
        } //If an exception occurs, we want to know about it, we also want to tell JSON about the failure
        catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    /**
     * The GetAllMessagesMethod returns all the messages in the message database, even if it's empty
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    private void GetAllMessages(Context context){
        context.json(MessageServer.GetAllMessages());
    }

    /**
     * The GetMessageByID method takes the message ID in the url's endpoint and tells message services to find a message in the
     * message database with the same ID.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    private void GetMessageByID(Context context){
        int messageID = Integer.parseInt(context.pathParam("message_id"));

        //Check if there's a message associated with messageID. If there is a value, send it to JSON
        Message targetMessage = MessageServer.GetMessageByID(messageID);
        if(targetMessage != null){
            context.json(targetMessage);
        }
    }

    /**
     * The GetMessagesByUser method takes the user ID in the url's endpoint and tells message services to retrieve all messages 
     * with this user ID so it can return them to the user, even if this user hasn't made any posts.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    private void GetMessagesByUser(Context context){
        int accountID = Integer.parseInt(context.pathParam("account_id"));
        context.json(MessageServer.GetMessagesByUser(accountID));
    }

    /**
     * The DeleteMessage method takes the message ID in the url's endpoint and tells message services to retrieve and delete the
     * message in the message database with the same ID.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    private void DeleteMessage(Context context){
        int messageID = Integer.parseInt(context.pathParam("message_id"));
        
        //Check if there's a message associated with messageID. If there is a value, send it to JSON
        Message deletedMessage = MessageServer.DeleteMessage(messageID);
        if(deletedMessage != null){
            context.json(deletedMessage);
        }
    }

    /**
     * The UpdateMessage method takes the message ID in the url's endpoint and tells message services to update and retrieve the
     * message in the message database with the same ID with a new message provided in the context's body.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    private void UpdateMessage(Context context){
        try {
            //Get input from JSON and send it to MessageServices
            Message input = translater.readValue(context.body(), Message.class);
            input.setMessage_id(Integer.parseInt(context.pathParam("message_id")));
            Message updatedMessage = MessageServer.UpdateMessage(input);

            //The method succeeds if newAccount isn't null, in which case we return the account to json. Otherwise, tell JSON that the method failed.
            if(updatedMessage != null){
                context.json(translater.writeValueAsString(updatedMessage));
            }
            else {
                context.status(400);
            }
        } //If an exception occurs, we want to know about it, we also want to tell JSON about the failure
        catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    /**
     * This is an example handler for an example endpoint. I've commented it out so users can't use it in the final version of the app.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     
    private void exampleHandler(Context context) {
        context.json("sample text");
    }*/
}