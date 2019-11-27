package de.hdm.SoPra_WS1920.server;


import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.hdm.SoPra_WS1920.client.LoginService;
import de.hdm.SoPra_WS1920.shared.LoginInfo;

/**
 * 
 */
public class LoginServiceImpl extends RemoteServiceServlet implements LoginService{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    /**
     * @param String requestURL 
     * @return LoginInfo loginInfo
     */
    public LoginInfo login(String requestURL) {
        UserService personService = UserServiceFactory.getUserService();
        User p = personService.getCurrentUser();
        LoginInfo loginInfo = new LoginInfo();
        
        if (p != null) {
        	loginInfo.setLoggedIn(true);
        	loginInfo.setEmailAddress(p.getEmail());
        	loginInfo.setLogoutUrl(personService.createLogoutURL(requestURL));
        }else {
        	loginInfo.setLoggedIn(false);
        	loginInfo.setLoginUrl(personService.createLoginURL(requestURL));
        }
        return loginInfo;
    }

}