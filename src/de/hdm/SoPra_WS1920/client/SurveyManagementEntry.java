package de.hdm.SoPra_WS1920.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.SoPra_WS1920.client.gui.SurveyNavigationBar;
import de.hdm.SoPra_WS1920.client.gui.Admin.AuthenticationForm;

import de.hdm.SoPra_WS1920.shared.LoginInfo;
import de.hdm.SoPra_WS1920.shared.SurveyManagementAsync;
import de.hdm.SoPra_WS1920.shared.bo.Person;

import de.hdm.SoPra_WS1920.client.gui.SurveyContent;
import de.hdm.SoPra_WS1920.client.gui.SurveyManagementHeader;

public class SurveyManagementEntry implements EntryPoint {
	
	SurveyManagementAsync surveyManagement = ClientsideSettings.getSurveyManagement();
	Person p = new Person();
	private LoginInfo loginInfo = null;
	
	private Label lastName = new Label("Nachname");
	private Label firstName = new Label("Vorname");
	
	//private TextBox nickInput = new TextBox();
	private TextBox lastInput = new TextBox();
	private TextBox firstInput = new TextBox();
	
	SurveyNavigationBar surveyNavBar;
	SurveyManagementHeader surveyHeader;
	SurveyContent surveyContent;
	
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
	          loadEditor(result);
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
	public void loadEditor(LoginInfo loginInfo){
		//Window.alert("Eingeloggt mit der mail " + loginInfo.getEmailAddress());
		surveyManagement.getPersonByEmail(loginInfo.getEmailAddress(), new GetUserCallback());
		//Window.alert(loginInfo.getEmailAddress());
		//pinnwandVerwaltung.getUserById(1, new GetUserCallback());
	}
	
	public void loadEditorStart(Person person) {
		
		surveyContent = new SurveyContent();
		surveyContent.setPerson(person);
		surveyHeader = new SurveyManagementHeader(surveyContent);
		surveyHeader.setPerson(person);
//		navBar = new SurveyNavigationBar(this,header, content);
		surveyNavBar = new SurveyNavigationBar(surveyHeader, surveyContent);
		surveyNavBar.setPerson(person);
		
		RootPanel.get().add(surveyNavBar);
		RootPanel.get().add(surveyHeader);
		RootPanel.get().add(surveyContent);
	}
	
	class RegistrationFormDialogBox extends DialogBox {
		
		/**
		 * Instantiierung der notwendigen GUI Objekte
		 */
		private Label abfrage = new Label("Du bist noch nicht registriert!"
				+ " Bitte lege einen User an.");
		private Button yesBtn = new Button("Registrieren");
		private Button noBtn = new Button("Abbrechen");
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
			this.setStylePrimaryName("CardDescription");
			// Adding Styles to Interaction Fields
			//vPanel.setStyleName("CardDescription");
			//nickInput.addStyleName("control input content_margin");
			firstInput.setStylePrimaryName("CardTextBox");
			lastInput.setStylePrimaryName("CardTextBox");
			
			yesBtn.setStylePrimaryName("SaveButton");
			noBtn.setStylePrimaryName("SaveButton");
			
			abfrage.setStylePrimaryName("TextBoxLabel");
			firstName.setStylePrimaryName("TextBoxLabel");
			lastName.setStylePrimaryName("TextBoxLabel");
			
			googleMail = mail;
			yesBtn.addClickHandler(new CreateUserClickHandler(this));
			noBtn.addClickHandler(new DontCreateUserClickHandler(this));
			vPanel.add(abfrage);
			//vPanel.add(nickInput);
			vPanel.add(firstName);
			vPanel.add(firstInput);
			vPanel.add(lastName);
			vPanel.add(lastInput);
			btnPanel.add(yesBtn);
			btnPanel.add(noBtn);
			vPanel.add(btnPanel);
			this.add(vPanel);
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
			surveyManagement.createPerson(firstInput.getText(), lastInput.getText(), loginInfo.getEmailAddress(), new CreateUserCallback(parentRFDB));
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
			//
			Window.alert("Ihr Nutzer wurde angelegt");
			Cookies.setCookie("gmail", result.getEMail());
			Cookies.setCookie("userId", result.getId() + "");
			GWT.log("UserId created: " + result.getId());
			Cookies.setCookie("firstName", result.getFirstname());
			Cookies.setCookie("lastName", result.getLastname());
			// hide(); - funktioniert nicht
			this.parentRFDB.hide();
			loadEditorStart(result);
			
		
		}
		
	}
	
	/**
	 * Die Nested Class <code>CreatePinnwandCallback</code> implementiert den AsyncCallback, 
	 * welcher bei einem erfolgreichen durchf�hren eine Pinnwand zur�ckgibt.
	 */
	
	
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
				loadEditorStart(result);
				
			} else {
				RegistrationFormDialogBox dlgBox = new RegistrationFormDialogBox(loginInfo.getEmailAddress());
				dlgBox.center();
			}
		}
		
	}
	
		
		/*navbar.setStylePrimaryName("navbar");
		
		menuIcon = new Image("/Images/menu.png");
		menuIcon.setStyleName("menuIcon");
		//menuIcon.addClickHandler(new MenuClickHandler());
		
		movies = new Label("Movies"); //Menu Item 1
		movies.setStyleName("navbar-element");
		movies.addClickHandler(new ShowMoviesClickHandler(header, content));
		
		groups = new Label("Groups"); //Menu Item 2
		groups.setStyleName("navbar-element");
		groups.addClickHandler(new ShowGroupsClickHandler(header, content));
		
	
		surveys = new Label("Surveys"); //Menu Item 2
		surveys.setStyleName("navbar-element");
		surveys.addClickHandler(new ShowSurveysClickHandler(header, content));
		
		navbar.add(menuIcon);
		navbar.add(movies);
		navbar.add(groups);
		navbar.add(surveys);
		
		RootPanel.get().add(navbar);
		RootPanel.get().add(header);
		RootPanel.get().add(content);
		
	}
	
	class MenuClickHandler implements ClickHandler{

		@Override
		public void onClick(ClickEvent event) {
			if(menuOpen==0) {
				navbar.setStyleName("navbar-show");
				menuIcon.setStyleName("menuIcon-show");
				movies.setStyleName("navbar-element-show");
				groups.setStyleName("navbar-element-show");
				surveys.setStyleName("navbar-element-show");
				menuOpen=1;
			}else {
				navbar.setStyleName("navbar");
				menuIcon.setStyleName("menuIcon");
				movies.setStyleName("navbar-element");
				groups.setStyleName("navbar-element");
				surveys.setStyleName("navbar-element");
				menuOpen=0;
			}
		}
		
	}
	
	class ShowGroupsClickHandler implements ClickHandler{
		
		public ShowGroupsClickHandler(SurveyManagementHeader header, SurveyContent content) {
			
		}
		

		@Override
		public void onClick(ClickEvent event) {
			header.showGroupHeader();
		
		}
		
	}
	
	class ShowSurveysClickHandler implements ClickHandler{
		
		public ShowSurveysClickHandler(SurveyManagementHeader header, SurveyContent content) {
			
		}

		@Override
		public void onClick(ClickEvent event) {
			header.showSurveyHeader();
			
		}
		
	}
		
	class ShowMoviesClickHandler implements ClickHandler{
		
		public ShowMoviesClickHandler(SurveyManagementHeader header, SurveyContent content) {
			
		}

		@Override
		public void onClick(ClickEvent event) {
			header.showMoviesHeader();
			
		}*/
	
		
}		
		

		
		
	
	

	


