package de.hdm.SoPra_WS1920.shared;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * 
 */
public class LoginInfo implements IsSerializable{
	
    /**
     * 
     */
    private boolean loggedIn = false;
    private String loginURL;
    private String logoutURL;
    private String emailAddress;
    private String nickname;
    
    /**
     * 
     * @return boolean loggedIn
     */
    public boolean isLoggedIn() {
    	return loggedIn;
    }
    
    /**
     * 
     * @param boolean loggedIn
     */
    public void setLoggedIn(boolean loggedIn) {
	    this.loggedIn = loggedIn;
	}

    /**
     * 
     * @return String loginURL
     */
	public String getLoginUrl() {
	    return loginURL;
	}

	/**
	 * 
	 * @param String loginUrl
	 */
	public void setLoginUrl(String loginUrl) {
	    this.loginURL = loginUrl;
	}

	/**
	 * 
	 * @return String logoutURL
	 */
	public String getLogoutUrl() {
	    return logoutURL;
	}

	/**
	 * 
	 * @param String logoutUrl
	 */
	public void setLogoutUrl(String logoutUrl) {
	    this.logoutURL = logoutUrl;
	}

	/**
	 * 
	 * @return String emailAddress
	 */
	public String getEmailAddress() {
	    return emailAddress;
	}

	/**
	 * 
	 * @param String emailAddress
	 */
	public void setEmailAddress(String emailAddress) {
	    this.emailAddress = emailAddress;
	}
	
	public String getNickname() {
	    return nickname;
	}
	/**
	 * 
	 * @param nickname
	 */
	public void setNickname(String nickname) {
		    this.nickname = nickname;
	}

}