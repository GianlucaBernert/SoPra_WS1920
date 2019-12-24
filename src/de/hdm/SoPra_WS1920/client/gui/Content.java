package de.hdm.SoPra_WS1920.client.gui;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.datepicker.client.DatePicker;

import de.hdm.SoPra_WS1920.client.gui.Admin.CinemaCard;
import de.hdm.SoPra_WS1920.shared.bo.Cinema;
import de.hdm.SoPra_WS1920.shared.bo.Movie;
import de.hdm.SoPra_WS1920.shared.bo.Screening;

public class Content extends FlowPanel{
	
	Cinema c;
	Screening s;
	Movie m;
	DatePicker dP;
	
	public void onLoad() {
		super.onLoad();
		this.setStyleName("content");
//		dP = new DatePicker();
//		this.add(dP);
		
		//Example Objects for testing the GUI. Those objects will be deleted as soon as the backend is attached to the frontend
		c = new Cinema();
		c.setName("Cinemax");
		c.setCity("Stuttgart");
		//c.setPostCode("70372");
		c.setPersonFK(1);
		c.setStreet("Deckerstra�e");
		c.setStreetNo("49");
		c.setId(1);
		
		m = new Movie();
		m.setName("Joker");
		m.setGenre("Drama");
		m.setDescription("Forever alone in a crowd, failed comedian Arthur Fleck seeks connection "
				+ "as he walks the streets of Gotham City. Arthur wears two masks -- the one he "
				+ "paints for his day job as a clown, and the guise he projects in a futile attempt "
				+ "to feel like he's part of the world around him.");
		m.setId(1);
//		
//		s = new Screening();
//		try {
//			Date date;
//			date = new SimpleDateFormat("dd/MM/yyyy").parse("31/12/2019");
//			s.setDate(date);
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//			s.setCinemaFK(1);
//			s.setMovieFK(1);
//		
		
		
	}
	public void showCinemas() {
		this.clear();
//		this.add(new Label("Cinemas"));
		CinemaCard cinemaCard = new CinemaCard(this, c);
		this.add(cinemaCard);
		//Get all cinemas where user is permitted to
		//for every cinema...
		//create a new CinemaCard
	}
	
	public void showMovies() {
		this.clear();
		this.add(new Label("Movies"));
		//Get all movies
		//for every movie...
		//create a MovieCard
	}

	public void showScreenings() {
		this.clear();
		this.add(new Label("Screenings"));
		//Get all screenings of the all cinemas where user is permitted to
		//for every screening...
		//create a new ScreeningCard
	}
}
