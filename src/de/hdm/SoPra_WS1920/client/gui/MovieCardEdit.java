package de.hdm.SoPra_WS1920.client.gui;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;

import de.hdm.SoPra_WS1920.shared.bo.Movie;

//Edit Mode of a MovieCard
public class MovieCardEdit extends FlowPanel{
	Label nameLabel;
	TextBox name;
	Label genreLabel;
	TextBox genre;
	Label descriptionLabel;
	TextBox description;
	Button save;
	Button cancel;
	Button delete;
	Movie movieToShow;
	MovieCard parentCard;
	Image saveIcon;
	Image cancelIcon;
	Image deleteIcon;
	public MovieCardEdit(MovieCard movieCard, Movie movieToShow) {
		// TODO Auto-generated constructor stub
		this.parentCard=movieCard;
		this.movieToShow=movieToShow;
	}

	public void onLoad() {
		super.onLoad();

		nameLabel = new Label("Title");
		name=new TextBox();
		genreLabel = new Label("Genre");
		genre=new TextBox();
		descriptionLabel = new Label("Description");
		description=new TextBox();
		save=new Button("SAVE");
		cancel=new Button("CANCEL");
		delete=new Button("Delete");
		delete.setStyleName("invisibleButton");
		name.setText(movieToShow.getName());
		genre.setText(movieToShow.getGenre());
		description.setText(movieToShow.getDescription());
		
		saveIcon = new Image("/Images/002-checked.svg");
		saveIcon.setStyleName("saveIcon");
		
		cancelIcon = new Image("/Images/001-unchecked.svg");
		cancelIcon.setStyleName("cancelIcon");
		
		deleteIcon = new Image("/Images/003-delete.svg");
		deleteIcon.setStyleName("deleteIcon");
		
		this.add(nameLabel);
		this.add(name);
		this.add(genreLabel);
		this.add(genre);
		this.add(descriptionLabel);
		this.add(description);
		this.add(save);
		this.add(cancel);
		this.add(delete);
		this.add(saveIcon);
		this.add(cancelIcon);
		this.add(deleteIcon);
	}
}
