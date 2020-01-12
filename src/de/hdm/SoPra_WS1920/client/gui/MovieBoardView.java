package de.hdm.SoPra_WS1920.client.gui;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;

import de.hdm.SoPra_WS1920.shared.bo.Movie;

public class MovieBoardView extends FlowPanel {
	
	Label name;
	Label genre;
	Label description;
	
	Movie movieToShow;
	MovieBoard parentCard;
	
	
	public MovieBoardView(MovieBoard movieBoard, Movie movieToShow) {
		this.parentCard = movieBoard;
		this.movieToShow = movieToShow;
	}
	
	public void onLoad() {
		super.onLoad();
		
		name = new Label(movieToShow.getName());
		name.setStylePrimaryName("CardViewTitle");
		
		genre = new Label(movieToShow.getGenre());
		genre.setStylePrimaryName("CardViewSubTitle");
		
		description=new Label(movieToShow.getDescription());
		description.setStyleName("CardViewParagraph");
		
		this.add(name);
		this.add(genre);
		this.add(description);
	}

}
