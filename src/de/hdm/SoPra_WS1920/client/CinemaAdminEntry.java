package de.hdm.SoPra_WS1920.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;

import de.hdm.SoPra_WS1920.client.gui.Content;
import de.hdm.SoPra_WS1920.client.gui.Header;
import de.hdm.SoPra_WS1920.client.gui.MovieCard;
import de.hdm.SoPra_WS1920.shared.bo.Movie;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class CinemaAdminEntry implements EntryPoint{
	
	FlowPanel navbar;
	Header header;
	Content content;
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
//		Vector<Movie> movies = new Vector<Movie>();
//		for(Movie m:movies) {
//			MovieCard moviecard = new MovieCard(m);
//		}
//		
		navbar = new FlowPanel();
		content = new Content();
		
		header = new Header();
		navbar.setStyleName("navbar");
		mCard = new MovieCard(content,m);
		content.add(mCard);
		mCard2 = new MovieCard(content,m);
		content.add(mCard2);
		
		menuIcon = new Image("/Images/menu.png");
		menuIcon.setStyleName("menuIcon");
		menuIcon.addClickHandler(new MenuClickHandler());
		
		cinemas = new Label("Cinema");	//Menu Item 1
		cinemas.setStyleName("navbar-element");
		cinemas.addClickHandler(new ShowCinemasClickHandler(header, content));
		
		movies = new Label("Movie"); //Menu Item 2
		movies.setStyleName("navbar-element");
		movies.addClickHandler(new ShowMoviesClickHandler(header, content));
		
		screenings = new Label("Screening"); //Menu Item 3
		screenings.setStyleName("navbar-element");
		screenings.addClickHandler(new ShowScreeningsClickHandler(header, content));
		
		navbar.add(menuIcon);
		navbar.add(cinemas); //Add Item 1 to Menu
		navbar.add(movies);	 //Add Item 2 to Menu
		navbar.add(screenings); //Add Item 3 to Menu
		
		/*
		 * Header Widgets
		 */

		
		RootPanel.get().add(navbar); //Add the Menu to the RootPanel
		RootPanel.get().add(header);
		RootPanel.get().add(content); //Add the (main-)content to the RootPanel
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
	
	class ShowCinemasClickHandler implements ClickHandler{
		
		public ShowCinemasClickHandler(Header header, Content content) {
			
		}

		@Override
		public void onClick(ClickEvent event) {
			// TODO Auto-generated method stub
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
			header.showScreeningHeader();
			content.showScreenings();
		}
		
	}
}
