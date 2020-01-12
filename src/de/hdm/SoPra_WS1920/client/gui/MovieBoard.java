package de.hdm.SoPra_WS1920.client.gui;

import com.google.gwt.user.client.ui.FlowPanel;

import de.hdm.SoPra_WS1920.shared.bo.Movie;

public class MovieBoard extends FlowPanel {
	
	Movie movieToShow;
	SurveyContent content;
	MovieBoardView movieBoardView;
	
	public void onLoad() {
		super.onLoad();
		this.setStylePrimaryName("Card");
		this.showMovieBoardView(movieToShow);
	}
	
	public MovieBoard(SurveyContent content, Movie movieToShow) {
		this.content = content;
		this.movieToShow = movieToShow;
	}
	
	public MovieBoard() {
		
	}
	
	public void showMovieBoardView(Movie movieToShow) {
		this.movieToShow = movieToShow;
		this.clear();
		this.add(new MovieBoardView(this, movieToShow));
	}

}
