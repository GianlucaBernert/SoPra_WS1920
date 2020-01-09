package de.hdm.SoPra_WS1920.client.gui.Admin;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;

public class NavigationBar extends FlowPanel {
	
	Header header;
	Content content;
	Image menuIcon;
//	Image createIcon;
//	Label headerLabel;
	Label cinemas;
	Label cinemaChains;
	Label movies;
	Label screenings;
	Label settings;
	Label switchToEditor;
	Label logOut;
	Button b;
//	private int menuOpen = 0;
	
	public NavigationBar(Header header,Content content) {
		this.header=header;
		this.content=content;
	}
	
	public void onLoad() {
		super.onLoad();
		this.setStyleName("navbar");
		b = new Button();
		b.setStyleName("InvisibleButton");
		
//		headerLabel = new Label();
//		headerLabel.setStyleName("headerLabel");
		
//		menuIcon = new Image("/Images/png/002-menu-button.png");
//		menuIcon.setStyleName("menuIcon");
//		menuIcon.addClickHandler(new MenuClickHandler(this));
		
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
		
		settings = new Label("Settings");
		settings.setStyleName("navbar-element bottom");
		settings.addClickHandler(new SettingsClickHandler());
		
		logOut = new Label("Logout");
		logOut.setStyleName("navbar-element logout");
		logOut.addClickHandler(new LogOutClickHandler());
		
//		createIcon = new Image("/Images/png/001-add-button.png");
//		createIcon.setStyleName("createIcon");
//		createIcon.addClickHandler(new CreateBoClickHandler(header, content));

		this.add(b);
//		this.add(menuIcon);
//		this.add(headerLabel);
//		this.add(createIcon);
		this.add(cinemas); //Add Item 1 to Menu
		this.add(cinemaChains);
		this.add(movies);	 //Add Item 2 to Menu
		this.add(screenings); //Add Item 3 to Menu
		this.add(settings);
		this.add(logOut);
	}
//	class MenuClickHandler implements ClickHandler{
//
//		NavigationBar navigationBar;
//		public MenuClickHandler(NavigationBar navigationBar) {
//			this.navigationBar = navigationBar;
//		}
//
//		@Override
//		public void onClick(ClickEvent event) {
//			if(menuOpen==0) {
////				navigationBar.remove(createIcon);
//				navigationBar.setStyleName("navbar-show");
//				menuIcon.setStyleName("menuIcon-show");
//				cinemas.setStyleName("navbar-element-show");
//				movies.setStyleName("navbar-element-show");
//				screenings.setStyleName("navbar-element-show");
//				menuOpen=1;
//			}else {
////				navigationBar.add(createIcon);
//				navigationBar.setStyleName("navbar");
//				menuIcon.setStyleName("menuIcon");
//				cinemas.setStyleName("navbar-element");
//				movies.setStyleName("navbar-element");
//				screenings.setStyleName("navbar-element");
//				menuOpen=0;
//				
//			}
//		}
//		
//	}
//	
//	class DecisionBox extends DialogBox{
//		Content c;
//		FlowPanel contentWrapper;
//		Label cinemaDecision;
//		Label cinemaChainDecision;
//		Image closeIcon;
//		
//		public DecisionBox(Content content) {
//			this.c=content;
//		}
//		
//		public void onLoad() {
//			super.onLoad();
//			this.setStyleName("createDecisionBox");
//			
//			contentWrapper = new FlowPanel();
//			
//			closeIcon = new Image("/Images/png/007-close.png");
//			closeIcon.setStyleName("cancelIcon");
//			closeIcon.addClickHandler(new CloseClickHandler(this));
//			
//			cinemaDecision = new Label("New Cinema");
//			cinemaDecision.setStyleName("decision-element");
//			cinemaDecision.addClickHandler(new CinemaDecisionClickHandler(this));
//			
//			cinemaChainDecision = new Label("New Cinema Chain");
//			cinemaChainDecision.setStyleName("decision-element");
//			cinemaChainDecision.addClickHandler(new CinemaChainDecisionClickHandler(this));
//			
//			contentWrapper.add(closeIcon);
//			contentWrapper.add(cinemaDecision);
//			contentWrapper.add(cinemaChainDecision);
//			this.add(contentWrapper);
//		}
//		class CloseClickHandler implements ClickHandler{
//			DecisionBox decisionBox;
//			public CloseClickHandler(DecisionBox decisionBox) {
//				// TODO Auto-generated constructor stub
//				this.decisionBox=decisionBox;
//			}
//
//			@Override
//			public void onClick(ClickEvent event) {
//				// TODO Auto-generated method stub
//				decisionBox.hide();
//				
//			}
//			
//		}
//		class CinemaDecisionClickHandler implements ClickHandler{
//			DecisionBox createDecisionBox;
//			public CinemaDecisionClickHandler(DecisionBox createDecisionBox) {
//				// TODO Auto-generated constructor stub
//				this.createDecisionBox=createDecisionBox;
//			}
//
//			@Override
//			public void onClick(ClickEvent event) {
//				createDecisionBox.hide();
//				//new CreateBoDialogBox(new Cinema(),c);
//			}
//			
//		}
//		
//		class CinemaChainDecisionClickHandler implements ClickHandler{
//			DecisionBox createDecisionBox;
//			public CinemaChainDecisionClickHandler(DecisionBox createDecisionBox) {
//				// TODO Auto-generated constructor stub
//				this.createDecisionBox=createDecisionBox;
//			}
//
//			@Override
//			public void onClick(ClickEvent event) {
//				createDecisionBox.hide();
//				//new CreateBoDialogBox(new Cinema(),c);
//			}
//			
////		}
//		
//	}
	
	class ShowCinemasClickHandler implements ClickHandler{
		NavigationBar navigationBar;
		public ShowCinemasClickHandler(Header header, Content content, NavigationBar navigationBar) {
			this.navigationBar=navigationBar;
		}

		@Override
		public void onClick(ClickEvent event) {
			// TODO Auto-generated method stub
//			headerLabel.setText("Cinemas");
			header.showCinemaHeader();
			content.showCinemas();
//			navigationBar.add(createIcon);
			navigationBar.setStyleName("navbar");
//			menuIcon.setStyleName("menuIcon");
			cinemas.setStyleName("navbar-element");
			movies.setStyleName("navbar-element");
			screenings.setStyleName("navbar-element");
//			menuOpen=0;
		}
		
	}
	
	class ShowCinemaChainsClickHandler implements ClickHandler{
		NavigationBar navigationBar;
		public ShowCinemaChainsClickHandler(Header header, Content content, NavigationBar navigationBar) {
			this.navigationBar=navigationBar;
		}

		@Override
		public void onClick(ClickEvent event) {
			// TODO Auto-generated method stub
//			headerLabel.setText("Cinemas");
			header.showCinemaChainHeader();
			content.showCinemaChains();
//			navigationBar.add(createIcon);
			navigationBar.setStyleName("navbar");
//			menuIcon.setStyleName("menuIcon");
			cinemas.setStyleName("navbar-element");
			movies.setStyleName("navbar-element");
			screenings.setStyleName("navbar-element");
//			menuOpen=0;
		}
		
	}
	
	class ShowMoviesClickHandler implements ClickHandler{
		NavigationBar navigationBar;
		public ShowMoviesClickHandler(Header header, Content content, NavigationBar navigationBar) {
			this.navigationBar=navigationBar;
		}

		@Override
		public void onClick(ClickEvent event) {
			// TODO Auto-generated method stub
//			headerLabel.setText("Movies");
			header.showMovieHeader();
			content.showMovies();
//			navigationBar.add(createIcon);
			navigationBar.setStyleName("navbar");
//			menuIcon.setStyleName("menuIcon");
			cinemas.setStyleName("navbar-element");
			movies.setStyleName("navbar-element");
			screenings.setStyleName("navbar-element");
//			menuOpen=0;
		}
		
	}
	class ShowScreeningsClickHandler implements ClickHandler{
		NavigationBar navigationBar;
		public ShowScreeningsClickHandler(Header header, Content content, NavigationBar navigationBar) {
			this.navigationBar=navigationBar;
		}

		@Override
		public void onClick(ClickEvent event) {
			// TODO Auto-generated method stub
//			headerLabel.setText("Screenings");
			header.showScreeningHeader();
			content.showScreenings();
//			navigationBar.add(createIcon);
			navigationBar.setStyleName("navbar");
//			menuIcon.setStyleName("menuIcon");
			cinemas.setStyleName("navbar-element");
			movies.setStyleName("navbar-element");
			screenings.setStyleName("navbar-element");
//			menuOpen=0;
		}
		
	}
	class SettingsClickHandler implements ClickHandler{

		public SettingsClickHandler() {
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onClick(ClickEvent event) {
			// TODO Auto-generated method stub
			UserSettingsForm userSettingsForm = new UserSettingsForm(header, content);
			userSettingsForm.center();
			userSettingsForm.show();
		}
		
	}
	
	class LogOutClickHandler implements ClickHandler{

		@Override
		public void onClick(ClickEvent event) {
			Window.alert("Successfully logged out!");
			
		}
		
	}
//	class CreateBoClickHandler implements ClickHandler{
//		Header h;
//		Content c;
//		DecisionBox dB;
//		public CreateBoClickHandler(Header header, Content content) {
//			// TODO Auto-generated constructor stub
//			this.h=header;
//			this.c=content;
//		}
//
//		@Override
//		public void onClick(ClickEvent event) {
//			dB = new DecisionBox(c);
//			
//			if(headerLabel.getText().equals("Cinemas")) {
////				dB = new DecisionBox(c);
//				dB.show();
////				dB.center();
//
//				
//			}else if(headerLabel.getText().equals("Movies")) {
//				//new CreateBoDialogBox(new Movie(),c);
//			}else if(headerLabel.getText().equals("Screenings")){
//				//new CreateBoDialogBox(new Screening(),c);
//			}
//			
//			
//		}
//		
//	}

}
