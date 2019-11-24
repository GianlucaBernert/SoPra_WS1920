package de.hdm.SoPra_WS1920.client.gui;

import com.google.gwt.user.client.ui.FlowPanel;

import de.hdm.SoPra_WS1920.shared.bo.Movie;

public class MovieCard extends FlowPanel{
		
	Movie movieToShow;
	FlowPanel main;
		
	public void onLoad() {
		super.onLoad();
		this.setStylePrimaryName("moviecard");
		this.showMovieCardView(movieToShow);
	}
		
	public MovieCard(Movie movieToShow) {
		this.movieToShow=movieToShow;

	}
		
	public MovieCard(FlowPanel main, Movie movieToShow) {
		this.main=main;
		this.movieToShow=movieToShow;
		}

	public void showMovieCardEdit(Movie movieToShow) {
		this.movieToShow=movieToShow;

	}
		
	public void showMovieCardView(Movie movieToShow) {
		this.movieToShow=movieToShow;

	}
	public void remove() {
		main.remove(this);
	}
		
		
		
		
}

