package de.hdm.SoPra_WS1920.client.gui;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;

public class Content extends FlowPanel{
	public void onLoad() {
		super.onLoad();
		this.setStyleName("content");
	}
	public void showCinemas() {
		this.clear();
		this.add(new Label("Cinemas"));
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
