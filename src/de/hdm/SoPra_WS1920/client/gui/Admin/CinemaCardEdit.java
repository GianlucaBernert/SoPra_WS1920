package de.hdm.SoPra_WS1920.client.gui.Admin;

import java.util.Vector;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Cookies;
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
import de.hdm.SoPra_WS1920.shared.bo.CinemaChain;

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
	Vector<CinemaChain> chinemaChainsOfUser;
	
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
		cinemaAdministration.getCinemaChainByPersonFK(Integer.parseInt(Cookies.getCookie("userId")), new CinemaChainCallback());
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
		
		formWrapper.add(saveButton);
		this.add(formWrapper);
		
	}
	
	class CinemaChainCallback implements AsyncCallback<Vector<CinemaChain>>{

		@Override
		public void onFailure(Throwable caught) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onSuccess(Vector<CinemaChain> result) {
			// TODO Auto-generated method stub
			chinemaChainsOfUser = result;
			for(CinemaChain cinemaChain:result) {
				cinemaChainListBox.addItem(cinemaChain.getName());
			}
		}

		
	}
	
	class SaveClickHandler implements ClickHandler{
		CinemaCardEdit cinemaCardEdit;
		
		public SaveClickHandler(CinemaCardEdit cinemaCardEdit) {
			this.cinemaCardEdit = cinemaCardEdit;
		}

		@Override
		public void onClick(ClickEvent event) {
			// TODO Auto-generated method stub
			if(nameTextBox.getText().length()==0 
				|| cityTextBox.getText().length()==0  
				|| streetTextBox.getText().length()==0 
				|| streetNrTextBox.getText().length()==0 
				|| zipCodeTextBox.getText().length()==0
				) {
				Window.alert("Please fill in all fields.");
			}else {
				
				if(parentCard==null) {
					cinemaAdministration.createCinema(nameTextBox.getText(), 
							cityTextBox.getText(), streetTextBox.getText(), 
							streetNrTextBox.getText(), zipCodeTextBox.getText(), 
							cinemaCardEdit.getSelectedCinemaChain(cinemaChainListBox.getSelectedValue()), 
							Integer.parseInt(Cookies.getCookie("userId")), 
							new CreateCinemaCallback());
	
				}else {
	
					cinemaToShow.setName(nameTextBox.getText());
					cinemaToShow.setCity(cityTextBox.getText()); 
					cinemaToShow.setStreet(streetTextBox.getText()); 
					cinemaToShow.setStreetNo(streetNrTextBox.getText()); 
					cinemaToShow.setZipCode(zipCodeTextBox.getText()); 
					cinemaToShow.setCinemaChainFK(cinemaCardEdit.getSelectedCinemaChain(cinemaChainListBox.getSelectedValue()));
					cinemaToShow.setPersonFK(Integer.parseInt(Cookies.getCookie("userId")));
					cinemaAdministration.updateCinema(cinemaToShow, new UpdateCinemaCinemaCallback());
				}
			}
		}
		
		class UpdateCinemaCinemaCallback implements AsyncCallback<Cinema>{

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				Window.alert("Problem with the connection");
			}

			@Override
			public void onSuccess(Cinema result) {
				// TODO Auto-generated method stub
				parentCard.showCinemaCardView(result);
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
//				Window.alert("after dataBase result: "+result.getStreetNo());
//				Window.alert("after dataBase cinemaToShow: "+cinemaToShow.getStreetNo());
				parentCard = new CinemaCard(content, result);
				parentCard.showCinemaCardView(result);
				content.add(parentCard);
				cinemaCardEdit.hide();
				
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
			cinemaAdministration.deleteCinema(cinemaToShow, new DeleteCinemaCallback(cinemaCardEdit));
			
		}
		
	}
	
	class DeleteCinemaCallback implements AsyncCallback<Void>{
		CinemaCardEdit cinemaCardEdit;
		
		public DeleteCinemaCallback(CinemaCardEdit cinemaCardEdit) {
			// TODO Auto-generated constructor stub
			this.cinemaCardEdit = cinemaCardEdit;
		}

		@Override
		public void onFailure(Throwable caught) {
			// TODO Auto-generated method stub
			Window.alert("Problem with the Callback");
		}

		@Override
		public void onSuccess(Void result) {
			// TODO Auto-generated method stub
			cinemaCardEdit.hide();
			parentCard.remove();
		}
		
	}

	public int getSelectedCinemaChain(String selectedValue) {
		// TODO Auto-generated method stub
		CinemaChain cinemaChain = null;
		for(CinemaChain cH: chinemaChainsOfUser) {
			if(!selectedValue.equals(cH.getName())) {
				continue;
			}
			cinemaChain = cH;
			
		}
		return cinemaChain.getId();
		
	}

}
