package de.hdm.SoPra_WS1920.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;

import de.hdm.SoPra_WS1920.client.gui.Header;
import de.hdm.SoPra_WS1920.client.gui.MovieCard;
import de.hdm.SoPra_WS1920.shared.bo.Movie;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class CinemaAdminEntry implements EntryPoint{
	
	FlowPanel navbar;
	Header header;
	FlowPanel main;
	Image menuIcon;
	Label cinemas;
	Label movies;
	Label screenings;
	private int menuOpen = 0;
	MovieCard mCard;
	MovieCard mCard2;
	
	//Example Movie
	
	
	
	
	
	@Override
	public void onModuleLoad() {
		// TODO Auto-generated method stub
		Movie m = new Movie();
		m.setName("Joker");
		m.setGenre("Drama");
		m.setDescription("A Movie about a clown");
		/*
		 * Navbar Widgets
		 */
		navbar = new FlowPanel();
		main = new FlowPanel();
		
		
		navbar.setStyleName("navbar");
		main.setStyleName("content");
		mCard = new MovieCard(main,m);
		main.add(mCard);
		mCard2 = new MovieCard(main,m);
		main.add(mCard2);
		
		menuIcon = new Image("/Images/menu.png");
		menuIcon.setStyleName("menuIcon");
		menuIcon.addClickHandler(new MenuClickHandler());
		
		cinemas = new Label("Cinema");	//Menu Item 1
		cinemas.setStyleName("navbar-element");
		movies = new Label("Movie"); //Menu Item 2
		movies.setStyleName("navbar-element");
		screenings = new Label("Screening"); //Menu Item 3
		screenings.setStyleName("navbar-element");
		
		navbar.add(menuIcon);
		navbar.add(cinemas); //Add Item 1 to Menu
		navbar.add(movies);	 //Add Item 2 to Menu
		navbar.add(screenings); //Add Item 3 to Menu
		
		/*
		 * Header Widgets
		 */
		Header header = new Header();
		
		RootPanel.get().add(navbar); //Add the Menu to the RootPanel
		RootPanel.get().add(header);
		RootPanel.get().add(main); //Add the (main-)content to the RootPanel
	}
	class MenuClickHandler implements ClickHandler{

		@Override
		public void onClick(ClickEvent event) {
			if(menuOpen==0) {
				navbar.setStyleName("navbar-show");
				menuIcon.setStyleName("menuIcon-show");
				cinemas.setStyleName("navbar-element-show");
				movies.setStyleName("navbar-element-show");
				screenings.setStyleName("navbar-element-show");
				menuOpen=1;
			}else {
				navbar.setStyleName("navbar");
				menuIcon.setStyleName("menuIcon");
				cinemas.setStyleName("navbar-element");
				movies.setStyleName("navbar-element");
				screenings.setStyleName("navbar-element");
				menuOpen=0;
			}
		}
		
	}
}
