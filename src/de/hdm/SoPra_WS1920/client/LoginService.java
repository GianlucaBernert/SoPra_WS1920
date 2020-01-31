package de.hdm.SoPra_WS1920.client;


import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import de.hdm.SoPra_WS1920.shared.LoginInfo;

/**
 * <code>LoginService</code> will be implemented on the server-side
 * @see LoginServicImpl
 * 
 * @RemoteServiceRelativePath associates the service with a default path relative to the module base URL
 * 
 * @author GianlucaBernert
 **/
@RemoteServiceRelativePath("login")
public interface LoginService extends RemoteService {
	  public LoginInfo login(String requestUri);

}