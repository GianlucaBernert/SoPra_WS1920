package de.hdm.SoPra_WS1920.client.gui.Admin;

import com.google.gwt.user.client.ui.FlowPanel;

import de.hdm.SoPra_WS1920.shared.bo.Movie;

public class MovieCard extends FlowPanel{
		
	Movie movieToShow;
	FlowPanel main;
	MovieCardView movieCardView;
	MovieCardEdit movieCardEdit;
		
	public void onLoad() {
		super.onLoad();
		this.setStyleName("Card");
		this.showMovieCardView(movieToShow);
		movieCardView = new MovieCardView(this, movieToShow);
	}
		
	public MovieCard(FlowPanel main, Movie movieToShow) {
		this.main=main;
		this.movieToShow=movieToShow;
		}

	public void showMovieCardEdit(Movie movieToShow) {
		this.movieToShow=movieToShow;
		this.clear();
		this.add(new MovieCardEdit(this,movieToShow));
	}
		
	public void showMovieCardView(Movie movieToShow) {
		this.movieToShow=movieToShow;
		this.clear();
		this.add(new MovieCardView(this,movieToShow));
	}
	public void remove() {
		main.remove(this);
	}
		
		
		
		
}

