//package de.hdm.SoPra_WS1920.client.gui.Admin;
//
//import com.google.gwt.event.dom.client.ClickEvent;
//import com.google.gwt.event.dom.client.ClickHandler;
//import com.google.gwt.user.client.Window;
//import com.google.gwt.user.client.ui.Button;
//import com.google.gwt.user.client.ui.DialogBox;
//import com.google.gwt.user.client.ui.FlowPanel;
//import com.google.gwt.user.client.ui.Image;
//import com.google.gwt.user.client.ui.Label;
//import com.google.gwt.user.client.ui.TextArea;
//import com.google.gwt.user.client.ui.TextBox;
//
//import de.hdm.SoPra_WS1920.client.gui.Admin.MovieCardEdit.CancelClickHandler;
//import de.hdm.SoPra_WS1920.client.gui.Admin.MovieCardEdit.SaveClickHandler;
//import de.hdm.SoPra_WS1920.shared.bo.Movie;
//
//public class CreateBoDialogbox extends DialogBox{
//
//	CinemaCardEdit cinemaCardEdit;
//	MovieCardEdit movieCardEdit;
//	ScreeningCardEdit screeningCardEdit;
//	
//	FlowPanel wrapperPanel;
//	
//	Label cardDescription;
//	Image cancelIcon;
//	Button invisibleButton;
//	FlowPanel editWrapper;
//	
//	Button saveButton;
//	
//	Header header;
//	Content content;
//	
//	public CreateBoDialogbox(Header header, Content content) {
//		this.content = content;
//		this.header = header;
//	}
//	
//	public void onLoad() {
//		super.onLoad();
//		this.setStyleName("CreateBoDialogBox");
//		
//		wrapperPanel = new FlowPanel();
//		cardDescription = new Label("Add Movie");
//		cardDescription.setStyleName("CardDescription");
//		cancelIcon = new Image("/Images/png/007-close.png");
//		cancelIcon.setStyleName("CancelIcon");
//		cancelIcon.addClickHandler(new CancelClickHandler(this));
//		invisibleButton = new Button();
//		invisibleButton.setStyleName("InvisibleButton");
//		
//		movieCardEdit = new MovieCardEdit();
//		
//		saveButton = new Button("Save");
//		saveButton.addClickHandler(new SaveClickHandler(this));
//		saveButton.setStyleName("SaveButton");
//
//		wrapperPanel.add(cardDescription);
//		wrapperPanel.add(cancelIcon);
//		wrapperPanel.add(invisibleButton);
//		wrapperPanel.add(movieCardEdit);
//		wrapperPanel.add(saveButton);
//		this.add(wrapperPanel);
//		
//	}
//	
//	class CancelClickHandler implements ClickHandler{
//		CreateBoDialogbox createBoDialogbox;
//		public CancelClickHandler(CreateBoDialogbox createBoDialogbox) {
//			// TODO Auto-generated constructor stub
//			this.createBoDialogbox = createBoDialogbox;
//		}
//
//		@Override
//		public void onClick(ClickEvent event) {
//			// TODO Auto-generated method stub
//			createBoDialogbox.hide();
//		}
//		
//	}
//	
//	class SaveClickHandler implements ClickHandler{
//		
//		CreateBoDialogbox createBoDialogbox;
//		public SaveClickHandler(CreateBoDialogbox createBoDialogbox) {
//			// TODO Auto-generated constructor stub
//			this.createBoDialogbox = createBoDialogbox;
//		}
//		@Override
//		public void onClick(ClickEvent event) {
//			if(header.headline.getText().equals("Movies")) {
//				Movie m = new Movie();
//				m.setName(movieCardEdit.nameTextBox.getText());
//				m.setGenre(movieCardEdit.genreTextBox.getText());
//				m.setDescription(movieCardEdit.descriptionTextArea.getText());
//				content.add(new MovieCard(content,m));
//				
//				createBoDialogbox.hide();
//			}
//			
//		}
//		
//	}
//	
//	public void showCinemaCardEdit() {
//	
//	}
//	
//	public void showMovieCardEdit() {
//		
//	}
//	
//	public void showScreeningCardEdit() {
//		
//	}
//
//}
