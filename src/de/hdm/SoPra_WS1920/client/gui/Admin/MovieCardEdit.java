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
	
	MovieCard parentCard;
	Movie movieToShow;
	
	Label cardDescription;
	Image cancelIcon;
	Button invisibleButton;
	
	Label nameLabel;
	TextBox nameTextBox;
	Label genreLabel;
	TextBox genreTextBox;
	Label descriptionLabel;
	TextArea descriptionTextArea;
	
	Button cancel;
	Label deleteLabel;
	Image deleteIcon;
	
	
	Button saveButton;
	
	
	public MovieCardEdit(MovieCard movieCard, Movie movieToShow) {
		// TODO Auto-generated constructor stub
		this.parentCard=movieCard;
		this.movieToShow=movieToShow;
	}

	public MovieCardEdit() {
		Movie m = new Movie();
		m.setName("");
		m.setGenre("");
		m.setDescription("");
		movieToShow = m;
	}

	public void onLoad() {
		super.onLoad();
		
		cardDescription = new Label("Edit Movie");
		cardDescription.setStyleName("CardDescription");
		cancelIcon = new Image("/Images/png/007-close.png");
		cancelIcon.setStyleName("CancelIcon");
		cancelIcon.addClickHandler(new CancelClickHandler());
		invisibleButton = new Button();
		invisibleButton.setStyleName("InvisibleButton");
		
		nameLabel = new Label("Title");
		nameLabel.setStyleName("TextBoxLabel");
		nameTextBox=new TextBox();
		nameTextBox.setStyleName("CardTextBox");
		nameTextBox.getElement().setPropertyString("placeholder", "Title");
		genreLabel = new Label("Genre");
		genreLabel.setStyleName("TextBoxLabel");
		genreTextBox=new TextBox();
		genreTextBox.setStyleName("CardTextBox");
		genreTextBox.getElement().setPropertyString("placeholder", "Genre");
		descriptionLabel = new Label("Description");
		descriptionLabel.setStyleName("TextBoxLabel");
		descriptionTextArea=new TextArea();
		descriptionTextArea.setStyleName("CardTextArea");
		
		deleteIcon = new Image("/Images/png/008-rubbish-bin.png");
		deleteIcon.setStyleName("DeleteIcon");
		deleteIcon.addClickHandler(new DeleteClickHandler());
		
		deleteLabel = new Label("Delete");
		deleteLabel.setStyleName("DeleteLabel");
		deleteLabel.addClickHandler(new DeleteClickHandler());
		
		saveButton = new Button("Save");
		saveButton.addClickHandler(new SaveClickHandler());
		saveButton.setStyleName("SaveButton");
		
		nameTextBox.setText(movieToShow.getName());
		genreTextBox.setText(movieToShow.getGenre());
		descriptionTextArea.setText(movieToShow.getDescription());
		
		this.add(cardDescription);
		this.add(cancelIcon);
		this.add(nameLabel);
		this.add(nameTextBox);
		this.add(genreLabel);
		this.add(genreTextBox);
		this.add(descriptionLabel);
		this.add(descriptionTextArea);
		this.add(deleteIcon);
		this.add(deleteLabel);
		this.add(saveButton);
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
			movieToShow.setName(nameTextBox.getText());
			movieToShow.setGenre(genreTextBox.getText());
			movieToShow.setDescription(descriptionTextArea.getText());
			//parentCard.update(movieToShow);
			//thisDialogBox.hide();
			parentCard.showMovieCardView(movieToShow);
		}
		
	}
	
	class CancelClickHandler implements ClickHandler{
		
		@Override
		public void onClick(ClickEvent event) {
			// TODO Auto-generated method stub
			parentCard.showMovieCardView(movieToShow);
			//thisDialogBox.hide();
		}
		
	}
	
	class DeleteClickHandler implements ClickHandler{
		
		@Override
		public void onClick(ClickEvent event) {
			// TODO Auto-generated method stub
			parentCard.remove();
			//thisDialogBox.hide();
		}
		
	}

}
