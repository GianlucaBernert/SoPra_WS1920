package de.hdm.SoPra_WS1920.client.gui.Admin;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;

import de.hdm.SoPra_WS1920.client.CinemaAdminEntry;
import de.hdm.SoPra_WS1920.shared.bo.Person;

/*
 * Navigator, der Schaltfl�chen zur Ausf�hrung von Unterprogrammen enth�lt.
 * 
 * @SebastianHerrmann
 */
public class NavigationBar extends FlowPanel {
	
	Person person;
	String logOutUrl;
	
	Header header;
	Content content;
	Image menuIcon;
	Image logoIcon;
	Label logoText;
	Label cinemas;
	Label cinemaChains;
	Label movies;
	Label screenings;
	Label settings;
	Label switchToEditor;
	Label logOut;
	Button b;
	CinemaAdminEntry cinemaAdminEntry;
	
	/*
	 * Konstruktor der Klasse NavigationBar
	 * @param header, content
	 */
	public NavigationBar(Header header,Content content) {
		this.header=header;
		this.content=content;
//		this.cinemaAdminEntry = cinemaAdminEntry;
	}
	
	public void onLoad() {
		super.onLoad();
		this.setStyleName("navbar");
		
		b = new Button();
		b.setStyleName("InvisibleButton");
		
		logoIcon = new Image("/Images/png/clapperboard.png");
		logoIcon.setStylePrimaryName("LogoIcon");
		logoText = new Label("Popcorns");
		logoText.setStylePrimaryName("LogoText");
		
		cinemas = new Label("Cinema");	//Menu Item 1
		cinemas.setStyleName("navbar-element");
		cinemas.addClickHandler(new ShowCinemasClickHandler(header, content, this));
		
		cinemaChains = new Label("Cinema Chains");	//Menu Item 1
		cinemaChains.setStyleName("navbar-element");
		cinemaChains.addClickHandler(new ShowCinemaChainsClickHandler(header, content, this));
		
		movies = new Label("Movie"); //Menu Item 2
		movies.setStyleName("navbar-element");
		movies.addClickHandler(new ShowMoviesClickHandler(header, content, this));
		
		screenings = new Label("Screening"); //Menu Item 3
		screenings.setStyleName("navbar-element");
		screenings.addClickHandler(new ShowScreeningsClickHandler(header, content, this));
		
		switchToEditor = new Label("Switch to Editor");
		switchToEditor.setStyleName("navbar-element switchClient");
		switchToEditor.addClickHandler(new SwitchToEditorClickHandler());
		
		settings = new Label("Settings");
		settings.setStyleName("navbar-element bottom");
		settings.addClickHandler(new SettingsClickHandler(this));
		
		logOut = new Label("Logout");
		logOut.setStyleName("navbar-element logout");
		logOut.addClickHandler(new LogOutClickHandler());
		
		this.add(b);
		this.add(logoIcon);
		this.add(logoText);
		this.add(cinemas); //Add Item 1 to Menu
		this.add(cinemaChains);
		this.add(movies);	 //Add Item 2 to Menu
		this.add(screenings); //Add Item 3 to Menu
		this.add(switchToEditor);
		this.add(settings);
		this.add(logOut);
	}
	
	/*
	 * ClickHandler, der es erm�glicht zum Editor-Client zu steuern.
	 */
	class SwitchToEditorClickHandler implements ClickHandler{

		@Override
		public void onClick(ClickEvent event) {
			// TODO Auto-generated method stub
//			Window.Location.assign(GWT.getHostPageBaseURL() + "SurveyManagement.html");
//			cinemaAdminEntry.showSurveyManagementEntry();
			Window.Location.replace("/SurveyManagement.html");
		}
		
	}
	
	/*
	 * ClickHandler, der die Kinos anzeigt.
	 */
	class ShowCinemasClickHandler implements ClickHandler{
		NavigationBar navigationBar;
		public ShowCinemasClickHandler(Header header, Content content, NavigationBar navigationBar) {
			this.navigationBar=navigationBar;
		}

		@Override
		public void onClick(ClickEvent event) {
			// TODO Auto-generated method stub
			
			cinemas.setStyleName("navbar-element active");
			cinemaChains.setStyleName("navbar-element");
			movies.setStyleName("navbar-element");
			screenings.setStyleName("navbar-element");
			settings.setStyleName("navbar-element bottom");
			
			header.showCinemaHeader();
			content.showCinemas();
			navigationBar.setStyleName("navbar");

		}
		
	}
	
	/*
	 * ClickHandler, der die Kinoketten anzeigt
	 */
	class ShowCinemaChainsClickHandler implements ClickHandler{
		NavigationBar navigationBar;
		public ShowCinemaChainsClickHandler(Header header, Content content, NavigationBar navigationBar) {
			this.navigationBar=navigationBar;
		}

		@Override
		public void onClick(ClickEvent event) {
			// TODO Auto-generated method stub
			
			cinemas.setStyleName("navbar-element");
			cinemaChains.setStyleName("navbar-element active");
			movies.setStyleName("navbar-element");
			screenings.setStyleName("navbar-element");
			settings.setStyleName("navbar-element bottom");
			
//			headerLabel.setText("Cinemas");
			header.showCinemaChainHeader();
			content.showCinemaChains();
//			surveyNavigationBar.add(createIcon);
			navigationBar.setStyleName("navbar");
//			menuIcon.setStyleName("menuIcon");
//			cinemas.setStyleName("navbar-element");
//			movies.setStyleName("navbar-element");
//			screenings.setStyleName("navbar-element");
//			menuOpen=0;
		}
		
	}
	
	/*
	 * ClickHandler, der die Movies anzeigt.
	 */
	class ShowMoviesClickHandler implements ClickHandler{
		NavigationBar navigationBar;
		public ShowMoviesClickHandler(Header header, Content content, NavigationBar navigationBar) {
			this.navigationBar=navigationBar;
		}

		@Override
		public void onClick(ClickEvent event) {
			// TODO Auto-generated method stub
			
			cinemas.setStyleName("navbar-element");
			cinemaChains.setStyleName("navbar-element");
			movies.setStyleName("navbar-element  active");
			screenings.setStyleName("navbar-element");
			settings.setStyleName("navbar-element bottom");
			
//			headerLabel.setText("Movies");
			header.showMovieHeader();
			content.showMovies();
//			surveyNavigationBar.add(createIcon);
			navigationBar.setStyleName("navbar");
//			menuIcon.setStyleName("menuIcon");
//			cinemas.setStyleName("navbar-element");
//			movies.setStyleName("navbar-element");
//			screenings.setStyleName("navbar-element");
//			menuOpen=0;
		}
		
	}
	
	/*
	 * ClickHandler, der die Screenings anzeigt.
	 */
	class ShowScreeningsClickHandler implements ClickHandler{
		NavigationBar navigationBar;
		public ShowScreeningsClickHandler(Header header, Content content, NavigationBar navigationBar) {
			this.navigationBar=navigationBar;
		}

		@Override
		public void onClick(ClickEvent event) {
			// TODO Auto-generated method stub
			
			cinemas.setStyleName("navbar-element");
			cinemaChains.setStyleName("navbar-element");
			movies.setStyleName("navbar-element");
			screenings.setStyleName("navbar-element active");
			settings.setStyleName("navbar-element bottom");
			
			header.showScreeningHeader();
			content.showScreenings();
			
			navigationBar.setStyleName("navbar");

		}
		
	}
	
	/*
	 * ClickHandler, der die Einstellungen aufruft.
	 */
	class SettingsClickHandler implements ClickHandler{
		NavigationBar navigationBar;
		public SettingsClickHandler(NavigationBar navigationBar) {
			// TODO Auto-generated constructor stub
			this.navigationBar = navigationBar;
		}

		@Override
		public void onClick(ClickEvent event) {
			// TODO Auto-generated method stub
			cinemas.setStyleName("navbar-element");
			cinemaChains.setStyleName("navbar-element");
			movies.setStyleName("navbar-element");
			screenings.setStyleName("navbar-element");
			settings.setStyleName("navbar-element bottom");
			UserSettingsForm userSettingsForm = new UserSettingsForm(navigationBar,person);
			userSettingsForm.center();
			userSettingsForm.show();
		}
		
	}
	
	
	/*
	 * ClickHandler, der den LougOut organisiert.
	 */
	class LogOutClickHandler implements ClickHandler{

		@Override
		public void onClick(ClickEvent event) {
			Window.Location.assign(logOutUrl);
			Window.alert("Successfully logged out!");
			
		}
		
	}
	
	/*
	 * Methode, mit der eine Person gesetzt wird.
	 * @param person
	 */
	public void setPerson(Person person) {
		this.person = person;
	}
	
	/*
	 * Methode, mit der eine Person zur�ck gegeben wird.
	 * @return person
	 */
	public Person getPerson() {
		return person;
	}

	/*
	 * Methode, um die LogOutURL zu setzen.
	 */
	public void setLogOutURL(String logoutUrl) {

		// TODO Auto-generated method stub
		this.logOutUrl = logOutUrl;
		
	}
	
	public String getLogOutURL() {
		return logOutUrl;
	}
	
}
