package de.hdm.SoPra_WS1920.client.gui.Admin;

import com.google.gwt.event.dom.client.ClickEvent;

import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;

import de.hdm.SoPra_WS1920.shared.bo.Movie;

/*
 * Klasse zum Anzeigen eines einzelnen Movie-Objekts. 
 * 
 * @author SebastianHerrmann
 */
//ViewMode of a MovieCard
public class MovieCardView extends FlowPanel{
	Label name;
	Label genre;
	Label description;
	Button edit;
	Movie movieToShow;
	MovieCard parentCard;
	Image editIcon;

	/*
	 * Konstruktor von MovieCardView
	 * @param movieCard, movieToShow
	 */
	public MovieCardView(MovieCard movieCard, Movie movieToShow) {
		this.parentCard=movieCard;
		this.movieToShow=movieToShow;
	}

	public void onLoad() {
		super.onLoad();
		
		name=new Label(movieToShow.getName());
		name.setStyleName("CardViewTitle");
		genre=new Label(movieToShow.getGenre());
		genre.setStyleName("CardViewSubTitle");
		description=new Label(movieToShow.getDescription());
		description.setStyleName("CardViewParagraph");
		edit=new Button("");
		edit.setStyleName("InvisibleButton");
		editIcon = new Image("/Images/png/006-pen.png");
		editIcon.setStyleName("EditIcon");
		editIcon.addClickHandler(new EditClickHandler());
		
		this.add(name);
		this.add(genre);
		this.add(description);
		this.add(edit); //Without this, the clickable icon doesn't work
		this.add(editIcon);
	
	}
	
	/*
	 * ClickHandler zum Editieren eines Movies. 
	 */
	class EditClickHandler implements ClickHandler{

		@Override
		public void onClick(ClickEvent event) {
			// TODO Auto-generated method stub
			parentCard.showMovieCardEdit(movieToShow);
		}
	}
}
