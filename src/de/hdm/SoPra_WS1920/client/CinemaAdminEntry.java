package de.hdm.SoPra_WS1920.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;

import de.hdm.SoPra_WS1920.client.gui.SurveyContent;
import de.hdm.SoPra_WS1920.client.gui.SurveyManagementHeader;
import de.hdm.SoPra_WS1920.client.gui.SurveyNavigationBar;
import de.hdm.SoPra_WS1920.client.gui.Admin.AuthenticationForm;
import de.hdm.SoPra_WS1920.client.gui.Admin.Content;
import de.hdm.SoPra_WS1920.client.gui.Admin.Header;
import de.hdm.SoPra_WS1920.client.gui.Admin.NavigationBar;
import de.hdm.SoPra_WS1920.shared.CinemaAdministrationAsync;
import de.hdm.SoPra_WS1920.shared.LoginInfo;
import de.hdm.SoPra_WS1920.shared.bo.Person;



/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class CinemaAdminEntry implements EntryPoint{
	CinemaAdministrationAsync cinemaAdministration = ClientsideSettings.getCinemaAdministration();
	private LoginInfo loginInfo = null;
	
	private Label lastName = new Label("Nachname");
	private Label firstName = new Label("Vorname");
	
	//private TextBox nickInput = new TextBox();
	private TextBox lastInput = new TextBox();
	private TextBox firstInput = new TextBox();
	
	NavigationBar navBar;
	Header header;
	Content content;
	
	@Override
	public void onModuleLoad() {
		 //Check login status using login service.
	    LoginServiceAsync loginService = GWT.create(LoginService.class);
	    loginService.login(GWT.getHostPageBaseURL(), new AsyncCallback<LoginInfo>() {
	      public void onFailure(Throwable error) {
	    	  RootPanel.get().add(new HTML(error.toString()));
	      }

	      public void onSuccess(LoginInfo result) {
	        loginInfo = result;
	        if(loginInfo.isLoggedIn()) {
	          loadAdmin(result);
	        } else {
	          loadLogin();
	        }
	      }
	    });
		//loadAdminStart();
	}
	//Login Panel anzeigen
	public void loadLogin(){		
	    RootPanel.get().add(new AuthenticationForm(loginInfo.getLoginUrl()));
//		signInLink.setHref(loginInfo.getLoginUrl());
//	    loginPanel.add(loginLabel);
//	    loginPanel.add(signInLink);
//	    RootPanel.get("stockList").add(loginPanel);
	}
	
	
	//wird erst nach Erfolgreichem Login geladen
	public void loadAdmin(LoginInfo loginInfo){
		//Window.alert("Eingeloggt mit der mail " + loginInfo.getEmailAddress());
		cinemaAdministration.getPersonByEMail(loginInfo.getEmailAddress(), new GetUserCallback());
		//Window.alert(loginInfo.getEmailAddress());
		//pinnwandVerwaltung.getUserById(1, new GetUserCallback());
	}
	
	public void loadAdminStart(Person person) {
		content = new Content();
		content.setPerson(person);
		header = new Header(content);
		header.setPerson(person);
//		navBar = new NavigationBar(this,header,content);
		navBar = new NavigationBar(header,content);
		navBar.setPerson(person);
		navBar.setLogOutURL(loginInfo.getLogoutUrl());
		
		RootPanel.get().add(navBar); //Add the Menu to the RootPanel
		RootPanel.get().add(header);
		RootPanel.get().add(content); //Add the (main-)content to the RootPanel
	}
	
	class RegistrationFormDialogBox extends DialogBox {
		
		/**
		 * Instantiierung der notwendigen GUI Objekte
		 */
		private Label abfrage = new Label("Sign Up");
		private Button signUpButton = new Button("Sign Up");
//		private Button noBtn = new Button("Abbrechen");
		private FlowPanel formWrapper = new FlowPanel();
		private VerticalPanel vPanel = new VerticalPanel();
		private HorizontalPanel btnPanel = new HorizontalPanel();
	
		/**
		 * Ein String der die E-Mail Adresse speichert
		 */
		private String googleMail = "";
		
		/**
		 * Aufruf des Konstruktors
		 * @param mail
		 */
		public RegistrationFormDialogBox(String mail) {
			this.setStylePrimaryName("RegistrationForm");
			
			firstInput.setStylePrimaryName("CardTextBox");
			lastInput.setStylePrimaryName("CardTextBox");
			
			signUpButton.setStylePrimaryName("SignUpButton");
//			noBtn.setStylePrimaryName("SaveButton");
			
			abfrage.setStylePrimaryName("CardDescription");
			firstName.setStylePrimaryName("TextBoxLabel");
			lastName.setStylePrimaryName("TextBoxLabel");
			
			googleMail = mail;
			signUpButton.addClickHandler(new CreateUserClickHandler(this));
//			noBtn.addClickHandler(new DontCreateUserClickHandler(this));
			formWrapper.add(abfrage);
			//vPanel.add(nickInput);
			formWrapper.add(firstName);
			formWrapper.add(firstInput);
			formWrapper.add(lastName);
			formWrapper.add(lastInput);
			btnPanel.add(signUpButton);
//			btnPanel.add(noBtn);
			formWrapper.add(btnPanel);
			this.add(formWrapper);
			//this.setWidth("300px");
		}
	}
	
	/**
	 * Die Nested-Class <code>CreateUserClickHandler</code> implementiert das ClickHandler-Interface
	 * welches es erm�glicht auf den Wunsch des Users zu reagieren, dass er einen User anlegen m�chte.
	 */
	private class CreateUserClickHandler implements ClickHandler {
		RegistrationFormDialogBox parentRFDB;
		
		public CreateUserClickHandler(RegistrationFormDialogBox rfdb) {
			this.parentRFDB = rfdb;
		}
		
		@Override
		public void onClick(ClickEvent event) {
			cinemaAdministration.createPerson(firstInput.getText(), lastInput.getText(), loginInfo.getEmailAddress(), new CreateUserCallback(parentRFDB));
		}
		
	}
	
	/**
	 * Die Nested-Class <code>CreateUserCallback</code> implementiert den AsyncCallback, 
	 * welcher bei erfolgreicher Vollf�hrung einen User zur�ckgibt.
	 */
	private class CreateUserCallback implements AsyncCallback<Person> {
		RegistrationFormDialogBox parentRFDB;
		
		public CreateUserCallback(RegistrationFormDialogBox rfdb) {
			this.parentRFDB = rfdb;
			
		}
		

		@Override
		public void onFailure(Throwable caught) {
		}

		/**
		 * Cookie Setzung f�r das gesamte System
		 */
		@Override
		public void onSuccess(Person result) {
			Window.alert("Ihr Nutzer wurde angelegt");
			Cookies.setCookie("gmail", result.getEMail());
			Cookies.setCookie("userId", result.getId() + "");
			GWT.log("UserId created: " + result.getId());
			Cookies.setCookie("firstName", result.getFirstname());
			Cookies.setCookie("lastName", result.getLastname());
			// hide(); - funktioniert nicht
			this.parentRFDB.hide();
			loadAdminStart(result);
			
		}
		
	}
	
	
	/**
	 * Die Nested-Class <code>DontCreateUserClickHandler</code> implementiert das ClickHandler-Interface
	 * welches es erm�glicht das anlegen eines Users abbricht.
	 */
	private class DontCreateUserClickHandler implements ClickHandler {
		RegistrationFormDialogBox parentRFDB;
		
		public DontCreateUserClickHandler(RegistrationFormDialogBox rfdb) {
			this.parentRFDB = rfdb;
		}

		@Override
		public void onClick(ClickEvent event) {
			this.parentRFDB.hide();
		}
		
	}
		
	/**
	 * Die Nested-Class implementiert einen AsyncCallback, welcher bei einem erfolgreichen
	 * durchf�hren einen User zur�ckgibt.
	 */
	public class GetUserCallback implements AsyncCallback<Person> {

		@Override
		public void onFailure(Throwable caught) {
			
		}

		@Override
		public void onSuccess(Person result) {
			
			if(result != null) {
				//Window.alert("U1 is corrected: " + u1.toString());
				Cookies.setCookie("gmail", result.getEMail());
				Cookies.setCookie("userId", result.getId() + "");
				Cookies.setCookie("firstName", result.getFirstname());
				Cookies.setCookie("lastName", result.getLastname());
				loadAdminStart(result);
			} else {
				RegistrationFormDialogBox dlgBox = new RegistrationFormDialogBox(loginInfo.getEmailAddress());
				dlgBox.center();
			}
		}
		
	}

}
