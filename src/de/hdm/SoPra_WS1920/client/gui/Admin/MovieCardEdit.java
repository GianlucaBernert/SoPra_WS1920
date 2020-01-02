package de.hdm.SoPra_WS1920.client.gui.Admin;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import de.hdm.SoPra_WS1920.shared.bo.Movie;

//Edit Mode of a MovieCard
public class MovieCardEdit extends DialogBox{
	
	FlowPanel formWrapper;
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
	
	Header header;
	Content content;
	
	public MovieCardEdit(MovieCard movieCard, Movie movieToShow) {
		// TODO Auto-generated constructor stub
		this.parentCard=movieCard;
		this.movieToShow=movieToShow;
	}

	public MovieCardEdit(Header header, Content content) {
		this.header = header;
		this.content = content;
		
		Movie m = new Movie();
		m.setName("");
		m.setGenre("");
		m.setDescription("");
		movieToShow = m;
	}

	public void onLoad() {
		super.onLoad();
		this.setStyleName("EditCard");
		formWrapper = new FlowPanel();
		
		cardDescription = new Label("Add Movie");
		cardDescription.setStyleName("CardDescription");
		cancelIcon = new Image("/Images/png/007-close.png");
		cancelIcon.setStyleName("CancelIcon");
		cancelIcon.addClickHandler(new CancelClickHandler(this));
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
		descriptionTextArea.getElement().setAttribute("maxlength", "350");
		
		formWrapper.add(cardDescription);
		formWrapper.add(cancelIcon);
		formWrapper.add(nameLabel);
		formWrapper.add(nameTextBox);
		formWrapper.add(genreLabel);
		formWrapper.add(genreTextBox);
		formWrapper.add(descriptionLabel);
		formWrapper.add(descriptionTextArea);
		
		if(parentCard!=null) {
			cardDescription.setText("Edit Movie");
			deleteIcon = new Image("/Images/png/008-rubbish-bin.png");
			deleteIcon.setStyleName("DeleteIcon");
			deleteIcon.addClickHandler(new DeleteClickHandler(this));
			
			deleteLabel = new Label("Delete");
			deleteLabel.setStyleName("DeleteLabel");
			deleteLabel.addClickHandler(new DeleteClickHandler(this));
			formWrapper.add(deleteIcon);
			formWrapper.add(deleteLabel);
		}
//		deleteIcon = new Image("/Images/png/008-rubbish-bin.png");
//		deleteIcon.setStyleName("DeleteIcon");
//		deleteIcon.addClickHandler(new DeleteClickHandler(this));
//		
//		deleteLabel = new Label("Delete");
//		deleteLabel.setStyleName("DeleteLabel");
//		deleteLabel.addClickHandler(new DeleteClickHandler(this));
		
		saveButton = new Button("Save");
		saveButton.addClickHandler(new SaveClickHandler(this));
		saveButton.setStyleName("SaveButton");
		
		nameTextBox.setText(movieToShow.getName());
		genreTextBox.setText(movieToShow.getGenre());
		descriptionTextArea.setText(movieToShow.getDescription());
		
//		formWrapper.add(cardDescription);
//		formWrapper.add(cancelIcon);
//		formWrapper.add(nameLabel);
//		formWrapper.add(nameTextBox);
//		formWrapper.add(genreLabel);
//		formWrapper.add(genreTextBox);
//		formWrapper.add(descriptionLabel);
//		formWrapper.add(descriptionTextArea);
//		formWrapper.add(deleteIcon);
//		formWrapper.add(deleteLabel);
		formWrapper.add(saveButton);
		this.add(formWrapper);
	}
	class SaveClickHandler implements ClickHandler{
		MovieCardEdit movieCardEdit;
		public SaveClickHandler(MovieCardEdit movieCardEdit) {
			// TODO Auto-generated constructor stub
			this.movieCardEdit = movieCardEdit;
		}

		@Override
		public void onClick(ClickEvent event) {
			// TODO Auto-generated method stub
			movieToShow.setName(nameTextBox.getText());
			movieToShow.setGenre(genreTextBox.getText());
			movieToShow.setDescription(descriptionTextArea.getText());
			
			if(parentCard==null) {
				parentCard = new MovieCard(content,movieToShow);
				parentCard.showMovieCardView(movieToShow);
				content.add(parentCard);
				movieCardEdit.hide();
			}else {
				parentCard.showMovieCardView(movieToShow);
				movieCardEdit.hide();
			}
			
		}
		
	}
	
	class CancelClickHandler implements ClickHandler{
		MovieCardEdit movieCardEdit;
		public CancelClickHandler(MovieCardEdit movieCardEdit) {
			// TODO Auto-generated constructor stub
			this.movieCardEdit = movieCardEdit;
		}

		@Override
		public void onClick(ClickEvent event) {
			// TODO Auto-generated method stub
			
			if(parentCard==null) {
				movieCardEdit.hide();
			}else {
				parentCard.showMovieCardView(movieToShow);
				movieCardEdit.hide();
			}
			
		}
		
	}
	
	class DeleteClickHandler implements ClickHandler{
		MovieCardEdit movieCardEdit;
		public DeleteClickHandler(MovieCardEdit movieCardEdit) {
			// TODO Auto-generated constructor stub
			this.movieCardEdit = movieCardEdit;
		}

		@Override
		public void onClick(ClickEvent event) {
			// TODO Auto-generated method stub
			movieCardEdit.hide();
			parentCard.remove();
		}
		
	}

}
