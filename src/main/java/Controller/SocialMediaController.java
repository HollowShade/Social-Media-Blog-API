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

        //TODO: Create message method and endpoint (POST localhost:8080/messages)

        //TODO: Get all messages method and endpoint (GET localhost:8080/messages)

        //TODO: Get message by ID method and endpoint (GET localhost:8080/messages/{message_id})

        //TODO: Delete message by ID method and endpoint (DELETE localhost:8080/messages/{message_id})

        //TODO: Update message by ID method and endpoint (PATCH localhost:8080/messages/{message_id})

        //TODO: Get messages by Account ID method and endpoint (GET localhost:8080/accounts/{account_id}/messages)

        return app;
    }

    /**
     * The CreateAccount method takes a username and password from JSON and tells AccountServices to turn it into a new account
     * for the account database.
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
            //context.status(400); TODO: Find a better error
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            //context.status(400); TODO: Find a better error
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
            Account loginAccount = AccountServer.CreateAccount(input);

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
            //context.status(401); TODO: Find a better error
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            //context.status(401); TODO: Find a better error
        }
    }

    /**
     * This is an example handler for an example endpoint. I've commented it out so users can't use it in the final version of the app.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     
    private void exampleHandler(Context context) {
        context.json("sample text");
    }*/


}