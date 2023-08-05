package DAO;

//This class works with accounts, import the account model
import Model.Account;

//It also works with databases, import Connection
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * The AccountDAO class serves as the DAO for the account table
 * At the moment, it can make accounts and track every account it contains,
 * In the future, maybe it will also update and delete accounts in case the user wants to change their username or leave the app.
 */
public class AccountDAO {
    //TODO: Add User method
    public Account CreateAccount(Account newAccount){
        //Create a connection
        Connection link = Util.ConnectionUtil.getConnection();

        //From here on in, we need a try-catch block
        try {
            //Create a statement
            PreparedStatement sql = link.prepareStatement("INSERT INTO account VALUES (DEFAULT, ?, ?);");

        } 
        catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }    
        return null;
    }

    //TODO: Get user method
    public Account GetAccount(Account loginAccount){
        return null;
    }
}
