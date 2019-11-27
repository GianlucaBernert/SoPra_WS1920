package de.hdm.SoPra_WS1920.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;

import de.hdm.SoPra_WS1920.shared.bo.Movie;

//ViewMode of a MovieCard
public class MovieCardView extends FlowPanel{
	Label name;
	Label genre;
	Label description;
	Button edit;
	Movie movieToShow;
	MovieCard parentCard;
	Image editIcon;
	FlowPanel editIconWrapper;
	
	public MovieCardView(MovieCard movieCard, Movie movieToShow) {
		this.parentCard=movieCard;
		this.movieToShow=movieToShow;
	}

	public void onLoad() {
		super.onLoad();
		
		name=new Label(movieToShow.getName());
		genre=new Label(movieToShow.getGenre());
		description=new Label(movieToShow.getDescription());
		edit=new Button("");
		editIcon = new Image("/Images/004-edit.svg");
		editIcon.addClickHandler(new EditClickHandler(this));
		
		this.add(name);
		this.add(genre);
		this.add(description);
		this.add(edit); //Without this, the clickable icon doesn't work
		this.add(editIcon);
	
	}
	
	class EditClickHandler implements ClickHandler{
		MovieCardView movieCardView;
		public EditClickHandler(MovieCardView movieCardView) {
			// TODO Auto-generated constructor stub
			this.movieCardView=movieCardView;
		}

		@Override
		public void onClick(ClickEvent event) {
			// TODO Auto-generated method stub
			parentCard.showMovieCardEdit(movieToShow);
		}
		
	}
}
