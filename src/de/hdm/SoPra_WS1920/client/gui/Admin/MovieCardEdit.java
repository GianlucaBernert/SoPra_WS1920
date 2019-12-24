package de.hdm.SoPra_WS1920.client.gui.Admin;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;

import de.hdm.SoPra_WS1920.shared.bo.Movie;

//Edit Mode of a MovieCard
public class MovieCardEdit extends FlowPanel{
	Label nameLabel;
	TextBox name;
	Label genreLabel;
	TextBox genre;
	Label descriptionLabel;
	TextArea description;
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
		nameLabel.setStyleName("inputLabel");
		name=new TextBox();
		name.setStyleName("inputTextBox");
		genreLabel = new Label("Genre");
		genreLabel.setStyleName("inputLabel");
		genre=new TextBox();
		genre.setStyleName("inputTextBox");
		descriptionLabel = new Label("Description");
		descriptionLabel.setStyleName("inputLabel");
		description=new TextArea();
		description.setStyleName("inputTextArea");
		save=new Button("SAVE");
		save.setStyleName("invisibleButton");
		cancel=new Button("CANCEL");
		cancel.setStyleName("invisibleButton");
		delete=new Button("Delete");
		delete.setStyleName("invisibleButton");
		name.setText(movieToShow.getName());
		genre.setText(movieToShow.getGenre());
		description.setText(movieToShow.getDescription());
		
		saveIcon = new Image("/Images/002-checked.svg");
		saveIcon.setStyleName("saveIcon");
		saveIcon.addClickHandler(new SaveClickHandler());
		
		cancelIcon = new Image("/Images/001-unchecked.svg");
		cancelIcon.setStyleName("cancelIcon");
		cancelIcon.addClickHandler(new CancelClickHandler());
		
		deleteIcon = new Image("/Images/003-delete.svg");
		deleteIcon.setStyleName("deleteIcon");
		deleteIcon.addClickHandler(new DeleteClickHandler());
		
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
	class SaveClickHandler implements ClickHandler{
//		MovieCardEdit movieCardEdit;
//		public SaveClickHandler(MovieCardEdit movieCardEdit) {
//			// TODO Auto-generated constructor stub
//			this.movieCardEdit=movieCardEdit;
//		}
		
		@Override
		public void onClick(ClickEvent event) {
			// TODO Auto-generated method stub
			movieToShow.setName(name.getText());
			movieToShow.setGenre(genre.getText());
			movieToShow.setDescription(description.getText());
			parentCard.showMovieCardView(movieToShow);
		}
		
	}
	
	class CancelClickHandler implements ClickHandler{
		
		@Override
		public void onClick(ClickEvent event) {
			// TODO Auto-generated method stub
			parentCard.showMovieCardView(movieToShow);
		}
		
	}
	
	class DeleteClickHandler implements ClickHandler{
		
		@Override
		public void onClick(ClickEvent event) {
			// TODO Auto-generated method stub
			parentCard.remove();
		}
		
	}
}
