package de.hdm.SoPra_WS1920.client.gui.Admin;

import com.google.gwt.user.client.ui.FlowPanel;

import de.hdm.SoPra_WS1920.shared.bo.Movie;

public class MovieCard extends FlowPanel{
		
	Movie movieToShow;
	Content content;
		
	public void onLoad() {
		super.onLoad();
		this.setStylePrimaryName("moviecard");
		this.showMovieCardView(movieToShow);
	}
		
	public MovieCard(Content content, Movie movieToShow) {
		this.content=content;
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
		content.remove(this);
	}
		
		
}

