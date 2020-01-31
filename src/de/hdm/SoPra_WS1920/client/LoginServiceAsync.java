package de.hdm.SoPra_WS1920.client;


import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.SoPra_WS1920.shared.LoginInfo;


/**
 * The <code>LoginServiceAsync</code Interface will be implemented
 * on the client client-side
 * @see ReportGenerator
 * 
 * @Author GianlucaBernert
 **/
public interface LoginServiceAsync {
	  public void login(String requestUri, AsyncCallback<LoginInfo> async);

}