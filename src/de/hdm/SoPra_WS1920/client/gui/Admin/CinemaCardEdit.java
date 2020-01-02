package de.hdm.SoPra_WS1920.client.gui.Admin;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;

import de.hdm.SoPra_WS1920.client.ClientsideSettings;
import de.hdm.SoPra_WS1920.shared.CinemaAdministrationAsync;
import de.hdm.SoPra_WS1920.shared.bo.Cinema;

public class CinemaCardEdit extends DialogBox{
	
	FlowPanel formWrapper;
	CinemaCard parentCard;
	Cinema cinemaToShow;
	
	Label cardDescription;
	Image cancelIcon;
	Button invisibleButton;
	
	Label nameLabel;
	TextBox nameTextBox;
	Label cinemaChainLabel;
	ListBox cinemaChainListBox;
	Label adressLabel;
	TextBox streetTextBox;
	TextBox streetNrTextBox;
	TextBox zipCodeTextBox;
	TextBox cityTextBox;
	
	Label deleteLabel;
	Image deleteIcon;
	Button saveButton;
	
	Header header;
	Content content;
	
	CinemaAdministrationAsync cinemaAdministration;
	
	public CinemaCardEdit(CinemaCard cinemaCard, Cinema cinema) {
		this.parentCard=cinemaCard;
		this.cinemaToShow=cinema;
	}
	
	public CinemaCardEdit(Header header, Content content) {
		this.header = header;
		this.content = content;
		
		Cinema c = new Cinema();
		c.setName("");
		c.setCinemaChainFK(1);
		c.setStreet("");
		c.setStreetNo("");
		c.setZipCode("");
		c.setCity("");
		cinemaToShow = c;
	}
	
	
	public void onLoad() {
		super.onLoad();
		this.setStyleName("EditCard");
		cinemaAdministration = ClientsideSettings.getCinemaAdministration();
		
		formWrapper = new FlowPanel();
		
		cardDescription = new Label("Add Cinema");
		cardDescription.setStyleName("CardDescription");
		cancelIcon = new Image("/Images/png/007-close.png");
		cancelIcon.setStyleName("CancelIcon");
		cancelIcon.addClickHandler(new CancelClickHandler(this));
		invisibleButton = new Button();
		invisibleButton.setStyleName("InvisibleButton");
		
		nameLabel = new Label("Title");
		nameLabel.setStyleName("TextBoxLabel");
		nameTextBox = new TextBox();
		nameTextBox.setStyleName("CardTextBox");
		nameTextBox.getElement().setPropertyString("placeholder", "name");
		cinemaChainLabel = new Label("Cinema Chain");
		cinemaChainLabel.setStyleName("TextBoxLabel");
		cinemaChainListBox = new ListBox();
		cinemaChainListBox.addItem("Cinemax");
		cinemaChainListBox.addItem("Cinemax2");
		cinemaChainListBox.setStyleName("CardListBox");
		adressLabel = new Label("Adress");
		adressLabel.setStyleName("TextBoxLabel");
		streetTextBox = new TextBox();
		streetTextBox.setStyleName("CardTextBox");
		streetTextBox.getElement().setPropertyString("placeholder", "Street");
		streetNrTextBox = new TextBox();
		streetNrTextBox.setStyleName("CardTextBox");
		streetNrTextBox.getElement().setPropertyString("placeholder", "Street No");
		zipCodeTextBox = new TextBox();
		zipCodeTextBox.setStyleName("CardTextBox");
		zipCodeTextBox.getElement().setPropertyString("placeholder", "Zip");
		cityTextBox = new TextBox();
		cityTextBox.setStyleName("CardTextBox");
		cityTextBox.getElement().setPropertyString("placeholder", "City");
		
		nameTextBox.setText(cinemaToShow.getName());
		streetTextBox.setText(cinemaToShow.getStreet());
		streetNrTextBox.setText(cinemaToShow.getStreetNo());
		zipCodeTextBox.setText(cinemaToShow.getZipCode());
		cityTextBox.setText(cinemaToShow.getCity());
		
		formWrapper.add(cardDescription);
		formWrapper.add(cancelIcon);
		formWrapper.add(nameLabel);
		formWrapper.add(nameTextBox);
		formWrapper.add(cinemaChainLabel);
		formWrapper.add(cinemaChainListBox);
		formWrapper.add(adressLabel);
		formWrapper.add(streetTextBox);
		formWrapper.add(streetNrTextBox);
		formWrapper.add(zipCodeTextBox);
		formWrapper.add(cityTextBox);
		
		if(parentCard!=null) {
			cardDescription.setText("Edit Cinema");
			deleteIcon = new Image("/Images/png/008-rubbish-bin.png");
			deleteIcon.setStyleName("DeleteIcon");
			deleteIcon.addClickHandler(new DeleteClickHandler(this));
			deleteLabel = new Label("Delete");
			deleteLabel.setStyleName("DeleteLabel");
			deleteLabel.addClickHandler(new DeleteClickHandler(this));
			formWrapper.add(deleteIcon);
			formWrapper.add(deleteLabel);
		}
		
		
		saveButton = new Button("Save");
		saveButton.setStyleName("SaveButton");
		saveButton.addClickHandler(new SaveClickHandler(this));
		
		cinemaToShow.setName(nameTextBox.getText());
//		Logic required for mapping the cinema chain name to the Id/FK
		cinemaToShow.setCinemaChainFK(1);
		cinemaToShow.setStreet(streetTextBox.getText());
		cinemaToShow.setStreetNo(streetNrTextBox.getText());
		cinemaToShow.setZipCode(zipCodeTextBox.getText());
		cinemaToShow.setCity(cityTextBox.getText());
		
//		formWrapper.add(cardDescription);
//		formWrapper.add(cancelIcon);
//		formWrapper.add(nameLabel);
//		formWrapper.add(nameTextBox);
//		formWrapper.add(cinemaChainLabel);
//		formWrapper.add(cinemaChainListBox);
//		formWrapper.add(adressLabel);
//		formWrapper.add(streetTextBox);
//		formWrapper.add(streetNrTextBox);
//		formWrapper.add(zipCodeTextBox);
//		formWrapper.add(cityTextBox);
//		formWrapper.add(deleteIcon);
//		formWrapper.add(deleteLabel);
		formWrapper.add(saveButton);
		this.add(formWrapper);
		
	}
	
	class SaveClickHandler implements ClickHandler{
		CinemaCardEdit cinemaCardEdit;
		
		public SaveClickHandler(CinemaCardEdit cinemaCardEdit) {
			this.cinemaCardEdit = cinemaCardEdit;
		}

		@Override
		public void onClick(ClickEvent event) {
			// TODO Auto-generated method stub
			cinemaAdministration.createCinema(
					nameTextBox.getText(), 
					cityTextBox.getText(), 
					streetTextBox.getText(), 
					streetNrTextBox.getText(), 
					zipCodeTextBox.getText(), 
					3, 
					1, 
					new CreateCinemaCallback());
			cinemaToShow.setName(nameTextBox.getText());
			cinemaToShow.setCinemaChainFK(1);
			cinemaToShow.setStreet(streetTextBox.getText());
			cinemaToShow.setStreetNo(streetNrTextBox.getText());
			cinemaToShow.setZipCode(zipCodeTextBox.getText());
			cinemaToShow.setCity(cityTextBox.getText());
			
			if(parentCard==null) {
				parentCard = new CinemaCard(content,cinemaToShow);
				parentCard.showCinemaCardView(cinemaToShow);
				content.add(parentCard);
				cinemaCardEdit.hide();
			}else {
				parentCard.showCinemaCardView(cinemaToShow);
				cinemaCardEdit.hide();
			}
		}
		class CreateCinemaCallback implements AsyncCallback<Cinema>{

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				Window.alert("Problem with the Callback!");
			}

			@Override
			public void onSuccess(Cinema result) {
				// TODO Auto-generated method stub
				cinemaToShow=result;
//				cinemaToShow.setName(nameTextBox.getText());
//				cinemaToShow.setCinemaChainFK(1);
//				cinemaToShow.setStreet(streetTextBox.getText());
//				cinemaToShow.setStreetNo(streetNrTextBox.getText());
//				cinemaToShow.setZipCode(zipCodeTextBox.getText());
//				cinemaToShow.setCity(cityTextBox.getText());
				
				if(parentCard==null) {
					parentCard = new CinemaCard(content,cinemaToShow);
					parentCard.showCinemaCardView(cinemaToShow);
					content.add(parentCard);
					cinemaCardEdit.hide();
				}else {
					parentCard.showCinemaCardView(cinemaToShow);
					cinemaCardEdit.hide();
				}
			}
			
		}
		
	}
	
	class CancelClickHandler implements ClickHandler{
		CinemaCardEdit cinemaCardEdit;
		public CancelClickHandler(CinemaCardEdit cinemaCardEdit) {
			// TODO Auto-generated constructor stub
			this.cinemaCardEdit = cinemaCardEdit;
		}

		@Override
		public void onClick(ClickEvent event) {
			// TODO Auto-generated method stub
			if(parentCard==null) {
				cinemaCardEdit.hide();
			}else {
				parentCard.showCinemaCardView(cinemaToShow);
				cinemaCardEdit.hide();
			}
		}
		
	}
	
	class DeleteClickHandler implements ClickHandler{
		CinemaCardEdit cinemaCardEdit;
		public DeleteClickHandler(CinemaCardEdit cinemaCardEdit) {
			// TODO Auto-generated constructor stub
			this.cinemaCardEdit = cinemaCardEdit;
		}

		@Override
		public void onClick(ClickEvent event) {
			// TODO Auto-generated method stub
			//proxy.deleteCinema()
			cinemaCardEdit.hide();
			parentCard.remove();
		}
		
	}

}
