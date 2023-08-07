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
    public Account CreateAccount(Account newAccount){
        //Check the parameters to be sure they meet requirements
        if (newAccount.getUsername().equals(null) || newAccount.getUsername().equals("") || newAccount.getPassword().length() < 4){
            return null;
        }
        return dao.CreateAccount(newAccount);
    }

    //TODO: Get user method
    public Account Login(Account loginAccount){
        return null;
    }
}
