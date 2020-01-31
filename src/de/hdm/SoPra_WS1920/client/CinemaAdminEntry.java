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
	Person p = new Person();
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
	
	public void loadAdminStart() {
		content = new Content();
		header = new Header(content);
//		navBar = new NavigationBar(this,header,content);
		navBar = new NavigationBar(header,content);
		
		RootPanel.get().add(navBar); //Add the Menu to the RootPanel
		RootPanel.get().add(header);
		RootPanel.get().add(content); //Add the (main-)content to the RootPanel
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
			loadAdminStart();
			
		
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
				loadAdminStart();
			} else {
				RegistrationFormDialogBox dlgBox = new RegistrationFormDialogBox(loginInfo.getEmailAddress());
				dlgBox.center();
			}
		}
		
	}
	
	/**
	 * Die Nested-Class <code>GetPinnwandCallback</code> implementiert einen AsyncCallback,
	 * welcher bei erfolgreicher durchf�hrung eine Pinnwand zur�ckgibt.
	 */
	
		// TODO Auto-generated method stub
//		Movie m = new Movie();
//		m.setName("Joker");
//		m.setGenre("Drama");
//		m.setDescription("A Movie about a clown");
		/*
		 * Navbar Widgets
		 */
//		Vector<Movie> movies = new Vector<Movie>();
//		for(Movie m:movies) {
//			MovieCard moviecard = new MovieCard(m);
//		}
//		
		
		
		
//		navbar.setStyleName("navbar");
//		mCard = new MovieCard(content,m);
//		content.add(mCard);
//		mCard2 = new MovieCard(content,m);
//		content.add(mCard2);
//		
//		menuIcon = new Image("/Images/menu.png");
//		menuIcon.setStyleName("menuIcon");
//		menuIcon.addClickHandler(new MenuClickHandler());
//		
//		cinemas = new Label("Cinema");	//Menu Item 1
//		cinemas.setStyleName("navbar-element");
//		cinemas.addClickHandler(new ShowCinemasClickHandler(header, content));
//		
//		movies = new Label("Movie"); //Menu Item 2
//		movies.setStyleName("navbar-element");
//		movies.addClickHandler(new ShowMoviesClickHandler(header, content));
//		
//		screenings = new Label("Screening"); //Menu Item 3
//		screenings.setStyleName("navbar-element");
//		screenings.addClickHandler(new ShowScreeningsClickHandler(header, content));
//		
//		navbar.add(menuIcon);
//		navbar.add(cinemas); //Add Item 1 to Menu
//		navbar.add(movies);	 //Add Item 2 to Menu
//		navbar.add(screenings); //Add Item 3 to Menu
//		
//		/*
//		 * Header Widgets
//		 */
//		Label label = new Label("Click to choose date/time :");
//		  // Define date format
//		        @SuppressWarnings("deprecation")
//				DateTimeFormat dateFormat = DateTimeFormat.getFullDateTimeFormat();
//		  // Create new DateBox
//		  DateBox dateBox = new DateBox();
//		  // Set date format to the dateBox 
//		  dateBox.setFormat(new DateBox.DefaultFormat(dateFormat));
//		  // Create new Vertical Panel
//		  VerticalPanel vp = new VerticalPanel();
//		  // Add widgets to Verical Panel
//		  vp.add(label);
//		        vp.add(dateBox);
//		         
//		Add Vertical Panel to Root Panel
//		RootPanel.get().add(vp);

		

	
//	public void showSurveyManagementEntry() {
//		RootPanel.get().clear();
//		surveyContent = new SurveyContent();
//		surveyHeader = new SurveyManagementHeader(surveyContent);
//		surveyNavBar = new SurveyNavigationBar(this,surveyHeader, surveyContent);
//		
//		RootPanel.get().add(navBar);
//		RootPanel.get().add(header);
//		RootPanel.get().add(content);
//		
//	}
//	
//	public void showCinemaAdminEntry() {
//		RootPanel.get().clear();
//		content = new Content();
//		header = new Header(content);
//		navBar = new NavigationBar(this,header,content);
//		RootPanel.get().add(navBar); //Add the Menu to the RootPanel
//		RootPanel.get().add(header);
//		RootPanel.get().add(content); //Add the (main-)content to the RootPanel
//	}
//	class MenuClickHandler implements ClickHandler{
//
//		@Override
//		public void onClick(ClickEvent event) {
//			if(menuOpen==0) {
//				navbar.setStyleName("navbar-show");
//				menuIcon.setStyleName("menuIcon-show");
//				cinemas.setStyleName("navbar-element-show");
//				movies.setStyleName("navbar-element-show");
//				screenings.setStyleName("navbar-element-show");
//				menuOpen=1;
//			}else {
//				navbar.setStyleName("navbar");
//				menuIcon.setStyleName("menuIcon");
//				cinemas.setStyleName("navbar-element");
//				movies.setStyleName("navbar-element");
//				screenings.setStyleName("navbar-element");
//				menuOpen=0;
//			}
//		}
//		
//	}
//	
//	class ShowCinemasClickHandler implements ClickHandler{
//		
//		public ShowCinemasClickHandler(Header header, Content content) {
//			
//		}
//
//		@Override
//		public void onClick(ClickEvent event) {
//			// TODO Auto-generated method stub
//			header.showCinemaHeader();
//			content.showCinemas();
//		}
//		
//	}
//	
//	class ShowMoviesClickHandler implements ClickHandler{
//		
//		public ShowMoviesClickHandler(Header header, Content content) {
//			
//		}
//
//		@Override
//		public void onClick(ClickEvent event) {
//			// TODO Auto-generated method stub
//			header.showMovieHeader();
//			content.showMovies();
//		}
//		
//	}
//	class ShowScreeningsClickHandler implements ClickHandler{
//		
//		public ShowScreeningsClickHandler(Header header, Content content) {
//			
//		}
//
//		@Override
//		public void onClick(ClickEvent event) {
//			// TODO Auto-generated method stub
//			header.showScreeningHeader();
//			content.showScreenings();
//		}
//		
//	}
}
