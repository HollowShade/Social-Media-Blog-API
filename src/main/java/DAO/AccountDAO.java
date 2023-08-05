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
    //TODO: Add User method
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
                link.close();
                return newAccount;
            }
        } 
        catch (SQLException e) {
            //If something goes wrong, give the developer details
            e.printStackTrace();
        }
        return null;
    }

    //TODO: Get user method
    public Account GetAccount(Account loginAccount){
        return null;
    }
}
