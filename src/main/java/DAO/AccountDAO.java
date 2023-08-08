package DAO;

//This class works with accounts, import the account model
import Model.Account;

//It also works with databases, import sql
import java.sql.*;

/**
 * The AccountDAO class serves as the DAO for the account table
 * At the moment, it can make accounts and track every account it contains,
 * In the future, maybe it will also update and delete accounts in case the user wants to change their username or leave the app.
 */
public class AccountDAO {
    /**
     * The CreateAccount method takes an account from accountServices and attempts to add it to the account database.
     * If it succeeds, it returns the new account with ID
     * If it fails, it tells AccountServices that the account wasn't added.
     * @param newAccount The account provided by AccountServices
     */
    public Account CreateAccount(Account newAccount){
        //Create a connection
        Connection link = Util.ConnectionUtil.getConnection();

        //From here on in, we need a try-catch block
        try {
            //Create a statement
            PreparedStatement sql = link.prepareStatement("INSERT INTO account VALUES (DEFAULT, ?, ?);", Statement.RETURN_GENERATED_KEYS);

            //Set sql's parameters
            sql.setString(1, newAccount.getUsername());
            sql.setString(2, newAccount.getPassword());

            //Execute the sql statement
            sql.executeUpdate();

            //Retrieve the generated values
            ResultSet newEntry = sql.getGeneratedKeys();

            //Check if there's an entry, if so, add it's ID to the account object parameter and return it
            if (newEntry.next()){
                newAccount.setAccount_id(newEntry.getInt(1));
                return newAccount;
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
     * The GetAccount method takes an account from AccountServices and checks if it exists in the account database.
     * If it succeeds, it returns the matching account
     * If it fails, it tells AccountServices that the account doesn't exist.
     * @param loginAccount The account provided by AccountServices
     */
    public Account GetAccount(Account loginAccount){
        //Create a connection
        Connection link = Util.ConnectionUtil.getConnection();

        //From here on in, we need a try-catch block
        try {
            //Create a statement
            PreparedStatement sql = link.prepareStatement("SELECT * FROM account WHERE username = ? AND password = ?;");

            //Set sql's parameters
            sql.setString(1, loginAccount.getUsername());
            sql.setString(2, loginAccount.getPassword());

            //Execute the sql statement and retrieve the resultset
            ResultSet matchingAccount = sql.executeQuery();

            //Check if there's an entry, if so, add it's ID to the account object parameter and return it
            if (matchingAccount.next()){
                loginAccount.setAccount_id(matchingAccount.getInt(1));
                return loginAccount;
            }
        } 
        catch (SQLException e) {
            //If something goes wrong, give the developer details
            e.printStackTrace();
        }

        //If we're outside of the try catch block after the connection, assume the operation failed.
        return null;
    }
}
