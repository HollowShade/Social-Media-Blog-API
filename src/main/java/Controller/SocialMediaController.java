package Controller;

import io.javalin.Javalin;
import io.javalin.http.Context;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller. The endpoints you will need can be
 * found in readme.md as well as the test cases. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
public class SocialMediaController {
    /**
     * In order for the test cases to work, you will need to write the endpoints in the startAPI() method, as the test
     * suite must receive a Javalin object from this method.
     * @return a Javalin app object which defines the behavior of the Javalin controller.
     */
    public Javalin startAPI() {
        Javalin app = Javalin.create();
        //Commenting out example endpoint: app.get("example-endpoint", this::exampleHandler);

        //TODO: Add User method and endpoint (POST localhost:8080/register)

        //TODO: Get user method and endpoint (POST localhost:8080/login)

        //TODO: Create message method and endpoint (POST localhost:8080/messages)

        //TODO: Get all messages method and endpoint (GET localhost:8080/messages)

        //TODO: Get message by ID method and endpoint (GET localhost:8080/messages/{message_id})

        //TODO: Delete message by ID method and endpoint (DELETE localhost:8080/messages/{message_id})

        //TODO: Update message by ID method and endpoint (PATCH localhost:8080/messages/{message_id})

        //TODO: Get messages by Account ID method and endpoint (GET localhost:8080/accounts/{account_id}/messages)

        return app;
    }

    /**
     * This is an example handler for an example endpoint. I've commented it out so users can't use it in the final version of the app.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     
    private void exampleHandler(Context context) {
        context.json("sample text");
    }*/


}