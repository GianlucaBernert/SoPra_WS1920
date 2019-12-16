package de.hdm.SoPra_WS1920.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;

public class NavigationBar extends FlowPanel {
	
	Header header;
	Content content;
	Image menuIcon;
	Image createIcon;
	Label headerLabel;
	Label cinemas;
	Label movies;
	Label screenings;
	private int menuOpen = 0;
	
	public NavigationBar(Header header,Content content) {
		this.header=header;
		this.content=content;
	}
	
	public void onLoad() {
		super.onLoad();
		this.setStyleName("navbar");
		
		headerLabel = new Label();
		headerLabel.setStyleName("headerLabel");
		
		menuIcon = new Image("/Images/png/002-menu-button.png");
		menuIcon.setStyleName("menuIcon");
		menuIcon.addClickHandler(new MenuClickHandler(this));
		
		cinemas = new Label("Cinema");	//Menu Item 1
		cinemas.setStyleName("navbar-element");
		cinemas.addClickHandler(new ShowCinemasClickHandler(header, content));
		
		movies = new Label("Movie"); //Menu Item 2
		movies.setStyleName("navbar-element");
		movies.addClickHandler(new ShowMoviesClickHandler(header, content));
		
		screenings = new Label("Screening"); //Menu Item 3
		screenings.setStyleName("navbar-element");
		screenings.addClickHandler(new ShowScreeningsClickHandler(header, content));
		
		createIcon = new Image("/Images/png/001-add-button.png");
		createIcon.setStyleName("createIcon");
//		createIcon.addClickHandler(new CreateBoClickHandler(header, content));
		
		
		this.add(menuIcon);
		this.add(headerLabel);
		this.add(createIcon);
		this.add(cinemas); //Add Item 1 to Menu
		this.add(movies);	 //Add Item 2 to Menu
		this.add(screenings); //Add Item 3 to Menu
	}
	class MenuClickHandler implements ClickHandler{

		NavigationBar navigationBar;
		public MenuClickHandler(NavigationBar navigationBar) {
			this.navigationBar = navigationBar;
		}

		@Override
		public void onClick(ClickEvent event) {
			if(menuOpen==0) {
				navigationBar.remove(createIcon);
				navigationBar.setStyleName("navbar-show");
				menuIcon.setStyleName("menuIcon-show");
				cinemas.setStyleName("navbar-element-show");
				movies.setStyleName("navbar-element-show");
				screenings.setStyleName("navbar-element-show");
				menuOpen=1;
			}else {
				navigationBar.add(createIcon);
				navigationBar.setStyleName("navbar");
				menuIcon.setStyleName("menuIcon");
				cinemas.setStyleName("navbar-element");
				movies.setStyleName("navbar-element");
				screenings.setStyleName("navbar-element");
				menuOpen=0;
				
			}
		}
		
	}
	
	class ShowCinemasClickHandler implements ClickHandler{
		
		public ShowCinemasClickHandler(Header header, Content content) {
			
		}

		@Override
		public void onClick(ClickEvent event) {
			// TODO Auto-generated method stub
			headerLabel.setText("Cinemas");
			header.showCinemaHeader();
			content.showCinemas();
		}
		
	}
	
	class ShowMoviesClickHandler implements ClickHandler{
		
		public ShowMoviesClickHandler(Header header, Content content) {
			
		}

		@Override
		public void onClick(ClickEvent event) {
			// TODO Auto-generated method stub
			headerLabel.setText("Movies");
			header.showMovieHeader();
			content.showMovies();
		}
		
	}
	class ShowScreeningsClickHandler implements ClickHandler{
		
		public ShowScreeningsClickHandler(Header header, Content content) {
			
		}

		@Override
		public void onClick(ClickEvent event) {
			// TODO Auto-generated method stub
			headerLabel.setText("Screenings");
			header.showScreeningHeader();
			content.showScreenings();
		}
		
	}

}
