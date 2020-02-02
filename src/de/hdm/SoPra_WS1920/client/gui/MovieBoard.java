package de.hdm.SoPra_WS1920.client.gui;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;

import de.hdm.SoPra_WS1920.shared.bo.Movie;

/**
 * Klasse, die dafür zustaendig ist, dem User alle verfuegbaren Movies anzuzeigen
 * @author Sebastian Hermann
 */
public class MovieBoard extends FlowPanel {
	
	Movie movieToShow;
	SurveyContent content;
	MovieBoardView movieBoardView;
	
	Label name;
	Label genre;
	Label description;
	
	public MovieBoard(SurveyContent content, Movie movieToShow) {
		this.content = content;
		this.movieToShow = movieToShow;
	}
	
	public void onLoad() {
		super.onLoad();
		this.setStylePrimaryName("Card");
		
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
