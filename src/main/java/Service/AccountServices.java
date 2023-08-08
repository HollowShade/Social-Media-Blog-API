package Service;

//This service file needs to use the accountDAO and account model.
import DAO.AccountDAO;
import Model.Account;

/**
 * The AccountServices class connects account related endpoints to the AccountDAO
 */
public class AccountServices {
    //AccountDao field for accessing the account DAO
    AccountDAO dao = new AccountDAO();
    
    /**
     * The CreateAccount method takes a new account from the controller, checks to see if it meets account requirements, and
     * tells the AccountDAO to add it to the account database. If requirements aren't met or the AccountDAO fails to add the account
     * to the account database, this method tells the controller that the account can't be created.
     * @param newAccount The account provided by the controller
     */
    public Account CreateAccount(Account newAccount){
        //Check the parameters to be sure they meet requirements
        if (newAccount.getUsername().equals(null) || newAccount.getUsername().equals("") || newAccount.getPassword().length() < 4){
            return null;
        }
        return dao.CreateAccount(newAccount);
    }

    /**
     * The Login method takes an account from the controller and tells the AccountDAO to see if there's an account with the
     * provided username and password in the account database. The accountDAO should return whether the username password combo exists
     * in the Account database or not.
     * @param loginAccount The account provided by the controller
     */
    public Account Login(Account loginAccount){
        return dao.GetAccount(loginAccount);
    }
}
