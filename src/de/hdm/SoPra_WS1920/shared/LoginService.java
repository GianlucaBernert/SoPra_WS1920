package de.hdm.SoPra_WS1920.shared;


import java.util.*;

/**
 * 
 */
public interface LoginService extends RemoteService {

    /**
     * 
     */
    public void login(requestURL: String): LoginInfo();

}