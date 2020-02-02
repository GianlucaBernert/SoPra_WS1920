package de.hdm.SoPra_WS1920.client.gui.Admin;

import com.google.gwt.user.client.ui.FlowPanel;

import de.hdm.SoPra_WS1920.shared.bo.Movie;

/**
 * Klasse zur Darstellung von Movies. Sie ist die Basis-Klasse der Movie-Karten wie
 * MovieCardView und MovieCardEdit.
 * 
 * @author SebastianHerrmann 
 *
 */
public class MovieCard extends FlowPanel{
		
	Movie movieToShow;
	Content content;
	MovieCardView movieCardView;
	MovieCardEdit movieCardEdit;
		
	public void onLoad() {
		super.onLoad();
		this.setStyleName("Card");
		this.showMovieCardView(movieToShow);
//		movieCardView = new MovieCardView(this, movieToShow);
	}
	/*
	 * Konstruktor der MovieCard
	 * @param content, movieToShow
	 */
	public MovieCard(Content content, Movie movieToShow) {
		this.content = content;
		this.movieToShow=movieToShow;
		}

	/*
	 * Default-Konstruktor der Movie-Card
	 */
	public MovieCard() {
		// TODO Auto-generated constructor stub
	}

	/*
	 * Methode, um die MovieCardEdit aufzurufen.
	 * @param movieToShow 
	 */
	public void showMovieCardEdit(Movie movieToShow) {
		this.movieToShow=movieToShow;
//		this.clear();
		MovieCardEdit movieCardEdit = new MovieCardEdit(this,movieToShow);
		movieCardEdit.center();
		movieCardEdit.show();
//		this.add(new MovieCardEdit(this,movieToShow));
	}
	
	/*
	 * Methode, um die MovieCardView anzeigen zu lassen. 
	 * @param movieToShow
	 */
	public void showMovieCardView(Movie movieToShow) {
		this.movieToShow=movieToShow;
		this.clear();
		this.add(new MovieCardView(this,movieToShow));
	}
	/*
	 * Methode, um den Inhalt eines Films zu entfernen.
	 */
	public void remove() {
		content.remove(this);
	}
		
		
		
		
}

