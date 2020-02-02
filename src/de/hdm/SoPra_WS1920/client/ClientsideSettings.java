package de.hdm.SoPra_WS1920.client;

import com.google.gwt.core.client.GWT;

import de.hdm.SoPra_WS1920.shared.CinemaAdministration;
import de.hdm.SoPra_WS1920.shared.CinemaAdministrationAsync;
import de.hdm.SoPra_WS1920.shared.CommonSettings;
import de.hdm.SoPra_WS1920.shared.SurveyManagement;
import de.hdm.SoPra_WS1920.shared.SurveyManagementAsync;

/**
 * Klasse mit Eigenschaften und Diensten, die für alle Client-seitigen Klassen
 * relevant sind.
 * 
 * @author Sebastian Hermann
 */
public class ClientsideSettings extends CommonSettings {

  /**
   * Remote Service Proxy zur Verbindungsaufnahme mit dem Server-seitgen Dienst
   * <code>Pinnwandverwaltung</code>.
   */

  private static CinemaAdministrationAsync cinemaAdministration = null;
  private static SurveyManagementAsync surveyManagement = null;
  private static LoginServiceAsync loginAdministration = null;


  /**
	 * Remote Service Proxy zur Verbindungsaufnahme mit den serverseitigen Dienst
	 * LoginService
	 */

  public static CinemaAdministrationAsync getCinemaAdministration() {

    if (cinemaAdministration == null) {
    	cinemaAdministration = GWT.create(CinemaAdministration.class);
    }

    return cinemaAdministration;
  }
  
  public static SurveyManagementAsync getSurveyManagement() {
	  
	  if(surveyManagement == null) {
		  surveyManagement = GWT.create(SurveyManagement.class);
	  }
	  
	  return surveyManagement;  
  }
  
  public static LoginServiceAsync getLoginService() {

	    if (loginAdministration == null) {
	    	loginAdministration = GWT.create(LoginService.class);
	    }

	    return loginAdministration;
	  }
  
	} 


