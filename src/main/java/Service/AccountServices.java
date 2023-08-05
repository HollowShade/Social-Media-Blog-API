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
    
    //TODO: Add User method
    public Account CreateAccount(String username, String password){
        //Check the parameters to be sure they meet requirements
        if (username.equals(null) || username.equals("") || password.length() < 4){
            return null;
        }
        return dao.CreateAccount(new Account(username, password));
    }

    //TODO: Get user method
    public Account Login(String username, String password){
        return null;
    }
}
