package de.hdm.SoPra_WS1920.client.gui.Admin;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;

import de.hdm.SoPra_WS1920.shared.bo.Cinema;

public class CinemaCardEdit extends FlowPanel{
	
	CinemaCard parentCard;
	Cinema cinemaToShow;
	
	Label cardDescription;
	Image cancelIcon;
	Button invisibleButton;
	
	Label nameLabel;
	TextBox nameTextBox;
	Label cinemaChainLabel;
//	ListBox cinemaChainListBox;
	Label adressLabel;
	TextBox streetTextBox;
	TextBox streetNrTextBox;
	TextBox zipCodeTextBox;
	TextBox cityTextBox;
	
	FlowPanel deleteArea;
	Image deleteIcon;
	Label deleteLabel;
	
	Button saveButton;
	
	public CinemaCardEdit(CinemaCard cinemaCard, Cinema cinema) {
		this.parentCard=cinemaCard;
		this.cinemaToShow=cinema;
	}
	
	public void onLoad() {
		super.onLoad();
		
		cardDescription = new Label("Edit Cinema");
		cardDescription.setStyleName("CardDescription");
		cancelIcon = new Image("/Images/png/007-close.png");
		cancelIcon.setStyleName("CancelIcon");
		cancelIcon.addClickHandler(new CancelClickHandler());
		invisibleButton = new Button();
		invisibleButton.setStyleName("InvisibleButton");
		
		nameLabel = new Label("Name");
		nameLabel.setStyleName("TextBoxLabel");
		nameTextBox = new TextBox();
		nameTextBox.setStyleName("CardTextBox");
		nameTextBox.getElement().setPropertyString("placeholder", "name");
		cinemaChainLabel = new Label("Cinema Chain");
		cinemaChainLabel.setStyleName("TextBoxLabel");
//		cinemaChainListBox = new ListBox();
//		cinemaChainListBox.setStyleName("CardListBox");
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
		zipCodeTextBox.setText(cinemaToShow.getzipCode());
		cityTextBox.setText(cinemaToShow.getCity());
		
		deleteIcon = new Image("/Images/png/008-rubbish-bin.png");
		deleteIcon.setStyleName("DeleteIcon");
		deleteIcon.addClickHandler(new DeleteClickHandler());
		deleteLabel = new Label("Delete Cinema");
		deleteLabel.setStyleName("DeleteCardLabel");
		
		saveButton = new Button("Save");
		saveButton.setStyleName("SaveButton");
		saveButton.addClickHandler(new SaveClickHandler());
		
		this.add(cardDescription);
		this.add(cancelIcon);
		this.add(nameLabel);
		this.add(nameTextBox);
		this.add(cinemaChainLabel);
//		this.add(cinemaChainListBox);
		this.add(adressLabel);
		this.add(streetTextBox);
		this.add(streetNrTextBox);
		this.add(zipCodeTextBox);
		this.add(cityTextBox);
		this.add(deleteIcon);
		this.add(deleteLabel);
		this.add(saveButton);
		
	}
	
	class SaveClickHandler implements ClickHandler{
		
		@Override
		public void onClick(ClickEvent event) {
			// TODO Auto-generated method stub
			cinemaToShow.setName(nameTextBox.getText());
//			Logic required for mapping the cinema chain name to the Id/FK
			cinemaToShow.setCinemaChainFK(1);
			cinemaToShow.setStreet(streetTextBox.getText());
			cinemaToShow.setStreetNo(streetNrTextBox.getText());
			cinemaToShow.setzipCode(zipCodeTextBox.getText());
			cinemaToShow.setCity(cityTextBox.getText());
			parentCard.showCinemaCardView(cinemaToShow);
		}
		
	}
	
	class CancelClickHandler implements ClickHandler{

		@Override
		public void onClick(ClickEvent event) {
			// TODO Auto-generated method stub
			parentCard.showCinemaCardView(cinemaToShow);
		}
		
	}
	
	class DeleteClickHandler implements ClickHandler{

		@Override
		public void onClick(ClickEvent event) {
			// TODO Auto-generated method stub
			//proxy.deleteCinema()
			parentCard.remove();
		}
		
	}

}
